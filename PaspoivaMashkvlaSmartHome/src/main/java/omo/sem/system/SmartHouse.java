package omo.sem.system;

import omo.sem.objects.PhysicalHouse;
import omo.sem.system.config.ConfigBuilder;
import omo.sem.system.event.EventDispatcher;
import omo.sem.system.report.ReportSystem;
import omo.sem.system.simulation.Simulation;
import omo.sem.system.task.TaskSystem;

/**
 * Class representing the main system.
 */
public class SmartHouse {
    // References
    private static SmartHouse instance;
    private EventDispatcher eventDispatcher;
    private Simulation simulation;
    private ReportSystem reportSystem;
    private TaskSystem taskSystem;
    private PhysicalHouse physicalHouse;

    // Status
    private boolean wasInitialized = false;

    private SmartHouse() {
        this.simulation = new Simulation();
        this.eventDispatcher = new EventDispatcher();
        this.taskSystem = new TaskSystem();
    }

    /**
     * Returns system's instance.
     *
     * @return instance
     */
    public static SmartHouse getInstance() {
        if (instance == null) {
            instance = new SmartHouse();
        }
        return instance;
    }

    /**
     * Initializes the house and all needed systems.
     *
     * @param physicalHouse the house
     */
    public void init(PhysicalHouse physicalHouse) {
        if (wasInitialized) return;

        this.reportSystem = new ReportSystem();
        this.physicalHouse = physicalHouse;

        wasInitialized = true;
    }

    public void init(String path) {
        if (wasInitialized) return;

        this.reportSystem = new ReportSystem();
        this.physicalHouse = ConfigBuilder.build(path);

        wasInitialized = true;
    }

    /**
     * Starts the simulation.
     *
     * @param speed          simulation's speed
     * @param timeToSimulate time to simulate
     * @throws Exception system wasn't initialized
     */
    public void start(int speed, long timeToSimulate) throws Exception {
        if (!wasInitialized) {
            throw new Exception("The system must be initialized");
        }

        simulation.start(speed, timeToSimulate);
    }

    /**
     * Stops the simulation.
     */
    public void stop() {
        simulation.stop();
    }

    /**
     * Returns report system.
     *
     * @return report system
     */
    public ReportSystem getReportSystem() {
        return reportSystem;
    }

    /**
     * Returns event dispatcher.
     *
     * @return event dispatcher
     */
    public EventDispatcher getEventDispatcher() {
        return eventDispatcher;
    }

    /**
     * Returns simulation.
     *
     * @return simulation simulation
     */
    public Simulation getSimulation() {
        return simulation;
    }

    /**
     * Returns task system.
     *
     * @return task system
     */
    public TaskSystem getTaskSystem() {
        return taskSystem;
    }

    /**
     * Returns house.
     *
     * @return house
     */
    public PhysicalHouse getHouse() {
        return physicalHouse;
    }
}
