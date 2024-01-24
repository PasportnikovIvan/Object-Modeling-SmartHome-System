package omo.sem.system.event;

/**
 * Abstract class representing event handler.
 */
public abstract class AbstractEventHandler implements InterfaceEventHandler {

    protected InterfaceEventHandler nextHandler;

    /**
     * Sets the next event handler.
     * @param nextHandler next handler
     */
    @Override
    public void setNext(InterfaceEventHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
