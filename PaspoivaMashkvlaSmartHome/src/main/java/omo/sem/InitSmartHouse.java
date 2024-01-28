package omo.sem;

import omo.sem.system.SmartHouse;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.Level;


public class InitSmartHouse {

    private static final Logger logger = Logger.getLogger(InitSmartHouse.class.getName());

    static int SIMULATION_SPEED = 10000;
    static long SIMULATION_TIME = 9000000000000000000L;

    public static void run() throws Exception {
        try {
            LogManager.getLogManager().readConfiguration(
                    SmartHouseMain.class.getResourceAsStream("/logger/logging.properties"));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Could not setup logger configuration", e);
            throw e;
        }

        SmartHouse house = SmartHouse.getInstance();

        try {
            // First config
            // house.init("config/config1.json");

            // Second config
            house.init("config/config2.json");

            house.start(SIMULATION_SPEED, SIMULATION_TIME);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error during SmartHouse initialization", e);
            throw e;
        }
    }
}
