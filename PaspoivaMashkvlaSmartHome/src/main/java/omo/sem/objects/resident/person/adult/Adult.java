package omo.sem.objects.resident.person.adult;

import omo.sem.objects.item.Item;
import omo.sem.objects.resident.person.adult.state.*;
import omo.sem.objects.resident.person.child.Child;
import omo.sem.objects.resident.pet.Pet;
import omo.sem.system.SmartHouse;
import omo.sem.system.event.Event;
import omo.sem.system.event.AbstractEventHandler;
import omo.sem.system.event.events.*;
import omo.sem.system.task.Task;
import omo.sem.objects.UsableObject;
import omo.sem.objects.device.Device;
import omo.sem.objects.device.Documentation;
import omo.sem.objects.resident.person.Person;
import omo.sem.objects.resident.person.PersonGender;
import omo.sem.objects.Room;

import java.beans.EventHandler;
import java.util.logging.Logger;

/**
 * Class representing the adult.
 */
public class Adult extends Person {
    // Logger
    private static final Logger log = Logger.getLogger(Adult.class.getName());

    public Adult(Room room, String name, PersonGender gender) {
        super(room, name);
        this.state = new AdultWaitingState(this);
        this.deviceBreakingChance = 0.1;
        this.gender = gender;

        initEventHandlers();
    }

    /**
     * Feeds the child.
     * @param child the child
     */
    public void careChild(Child child) {
        if (!room.equals(child.getRoom())) {
            moveTo(child.getRoom());
        }

        log.info(String.format("Adult \"%s\" care the child \"%s\" [%s]",
                name,
                child.getName(),
                SmartHouse.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Feed the pet.
     * @param pet the pet
     */
    public void feedPet(Pet pet) {
        if (!room.equals(pet.getRoom())) {
            moveTo(pet.getRoom());
        }

        log.info(String.format("Adult \"%s\" fed the pet \"%s\" [%s]",
                name,
                pet.getName(),
                SmartHouse.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Chilling in the room
     * @param room the room
     */
    public void chillInRoom(Room room) {
        log.info(String.format("Adult \"%s\" is chillin' out u feel me in \"%s\" [%s]",
                name,
                room.getName(),
                SmartHouse.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Fixes the device.
     * @param device the device
     */
    public void fixDevice(Device device) {
        Documentation doc = device.getDocumentation();
        usingObject = device;
        changeState(new AdultDeviceFixingState(this));
        doc.fixDevice(device);
    }

    /**
     * Completes fixing the device.
     * @param device the device
     */
    public void completeFixingDevice(Device device) {
        device.setTime(0);
        usingObject = null;
        changeState(new AdultWaitingState(this));

        log.info(String.format("%s \"%s\" fixed the object \"%s\" [%s]",
                this.getClass().getSimpleName(),
                this.getName(),
                device.getName(),
                SmartHouse.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Use the object (device, item).
     * @param object the object
     */
    public void useObject(UsableObject object) {
        this.usingObject = object;

        if (object instanceof Device) {
            SmartHouse.getInstance().getReportSystem().getActivityAndUsageReport().deviceCount(this, (Device) object);
        } else {
            SmartHouse.getInstance().getReportSystem().getActivityAndUsageReport().itemCount(this, (Item) object);
        }

        changeState(new AdultDeviceUsingState(this));
    }

    /**
     * UnUse the object.
     * @param object the object
     */
    public void unUseObject(UsableObject object) {
        this.usingObject = null;
        changeState(new AdultWaitingState(this));
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.state.update(time);
    }

    /**
     * Initiates event handlers.
     */
    private void initEventHandlers() {
        SmartHouse.getInstance().getEventDispatcher().addEventHandler(BrokenDeviceEvent.class, "global", new AbstractEventHandler() {
            @Override
            public void handle(Event e) {
                if (Adult.this.state instanceof AdultWaitingState) {
                    if (!Adult.this.getRoom().equals(e.getLocation())) {
                        Adult.this.moveTo((Room) e.getLocation());
                    }
                    ((Device) e.getSource()).fix(Adult.this);
                }

                if (nextHandler != null) {
                    nextHandler.handle(e);
                } else {
                    SmartHouse.getInstance().getTaskSystem().addTask(new Task(e));
                }
            }
        });

        SmartHouse.getInstance().getEventDispatcher().addEventHandler(ChildEvent.class, "global", new AbstractEventHandler() {
            @Override
            public void handle(Event e) {
                if (Math.random() <= 0.40 && !(Adult.this.state instanceof AdultWorkingState)) {
                    log.info(String.format("Adult \"%s\" doesn't want to care \"%s's\" baby [%s]",
                            name,
                            e.getSource().getName(),
                            SmartHouse.getInstance().getSimulation().getFormattedTime()));

                    if (nextHandler != null) {
                        nextHandler.handle(e);
                    } else {
                        SmartHouse.getInstance().getTaskSystem().addTask(new Task(e));
                    }

                    return;
                }


                if (!(Adult.this.state instanceof AdultWaitingState)
                        && !(Adult.this.state instanceof AdultWorkingState)
                        && !(Adult.this.state instanceof AdultDeviceFixingState)
                        && !(Adult.this.state instanceof AdultDeviceUsingState)) {
                    Adult.this.changeState(new AdultWaitingState(Adult.this));
                }

                if (!(Adult.this.state instanceof AdultWorkingState)
                        && !(Adult.this.state instanceof AdultDeviceFixingState)
                        && !(Adult.this.state instanceof AdultDeviceUsingState)) {
                    ((Child) e.getSource()).care(Adult.this);
                }
            }
        });

        SmartHouse.getInstance().getEventDispatcher().addEventHandler(HungryPetEvent.class, "global", new AbstractEventHandler() {
            @Override
            public void handle(Event e) {
                if (Math.random() <= 0.40 && !(Adult.this.state instanceof AdultWorkingState)) {
                    log.info(String.format("Adult \"%s\" doesn't want to feed the pet \"%s\" [%s]",
                            name,
                            e.getSource().getName(),
                            SmartHouse.getInstance().getSimulation().getFormattedTime()));

                    if (nextHandler != null) {
                        nextHandler.handle(e);
                    } else {
                        SmartHouse.getInstance().getTaskSystem().addTask(new Task(e));
                    }

                    return;
                }


                if (!(Adult.this.state instanceof AdultWaitingState)
                        && !(Adult.this.state instanceof AdultWorkingState)
                        && !(Adult.this.state instanceof AdultDeviceFixingState)
                        && !(Adult.this.state instanceof AdultDeviceUsingState)) {
                    Adult.this.changeState(new AdultWaitingState(Adult.this));
                }

                if (!(Adult.this.state instanceof AdultWorkingState)
                        && !(Adult.this.state instanceof AdultDeviceFixingState)
                        && !(Adult.this.state instanceof AdultDeviceUsingState)) {
                    ((Pet) e.getSource()).feed(Adult.this);
                }
            }
        });

        SmartHouse.getInstance().getEventDispatcher().addEventHandler(MusicEvent.class, "global", new AbstractEventHandler() {
            @Override
            public void handle(Event e) {
                if (Adult.this.state instanceof AdultSleepingState) {
                    if (Math.random() <= 0.30) {
                        log.info(String.format("Adult \"%s\" is already chilling while sleeping in the room \"%s\" [%s]",
                                name,
                                e.getLocation().getName(),
                                SmartHouse.getInstance().getSimulation().getFormattedTime()));

                        if (nextHandler != null) {
                            nextHandler.handle(e);
                        } else {
                            SmartHouse.getInstance().getTaskSystem().addTask(new Task(e));
                        }

                        return;
                    }
                }

                if (Adult.this.state instanceof AdultWaitingState) {
                    if (!Adult.this.getRoom().equals(e.getLocation())) {
                        Adult.this.moveTo((Room) e.getLocation());
                    }
                    ((Device) e.getSource()).on();
                }

                if (nextHandler != null) {
                    nextHandler.handle(e);
                } else {
                    SmartHouse.getInstance().getTaskSystem().addTask(new Task(e));
                }
            }
        });
    }
}