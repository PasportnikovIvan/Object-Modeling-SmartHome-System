package omo.sem.system.event;

/**
 * Interface representing event handler.
 */
public interface InterfaceEventHandler {
    /**
     * Handles event.
     * @param e event to handle
     */
    void handle(Event e);

    /**
     * Sets next event handler.
     * @param handler the next event handler
     */
    void setNext(InterfaceEventHandler handler);
}