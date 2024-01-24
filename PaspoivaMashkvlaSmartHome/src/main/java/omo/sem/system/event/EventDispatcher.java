package omo.sem.system.event;
import omo.sem.system.SmartHouse;

import java.io.IOException;
import java.util.*;

/**
 * Class for working with events and event handlers.
 */
public class EventDispatcher {

    private final Map<String, LinkedList<InterfaceEventHandler>> eventHandlers = new HashMap<>();

    /**
     * Adds new event handler.
     * @param event event to be handled
     * @param context event's context
     * @param handler handler to add
     */
    public void addEventHandler(Class<? extends Event> event, String context, InterfaceEventHandler handler) {
        String key = event + context;

        if (eventHandlers.containsKey(key)) {
            handler.setNext(eventHandlers.get(key).getLast());
            eventHandlers.get(key).add(handler);
        } else {
            LinkedList<InterfaceEventHandler> handlers = new LinkedList<>(Collections.singletonList(handler));
            eventHandlers.put(key, handlers);
        }
    }

    /**
     * Removes existing event handler.
     * @param event handled event
     * @param context event's context
     * @param handler handler to remove
     */
    public void removeEventHandler(Class<? extends Event> event, String context, InterfaceEventHandler handler) {
        String key = event + context;

        if (eventHandlers.containsKey(key)) {
            eventHandlers.get(key).remove(handler);

            if (eventHandlers.get(key).isEmpty()) {
                eventHandlers.remove(key);
            }
        }
    }

    /**
     * Dispatches event.
     * @param e event to dispatch
     * @param context event's context (e.g. location)
     */
    public void dispatchEvent(Event e, String context) {
        String key = e.getClass() + context;

        try {
            SmartHouse.getInstance().getReportSystem().getEventReport().generateReport(e);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        if (eventHandlers.containsKey(key)) {
            Objects.requireNonNull(eventHandlers.get(key).peekLast()).handle(e);
        }
    }
}
