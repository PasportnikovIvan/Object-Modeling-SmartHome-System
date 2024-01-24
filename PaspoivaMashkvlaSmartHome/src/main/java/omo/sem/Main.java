package omo.sem;

import omo.sem.system.SmartHouse;

import java.io.IOException;
import java.util.logging.LogManager;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            LogManager.getLogManager().readConfiguration(
                    Main.class.getResourceAsStream("/logger/logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e);
        }

        SmartHouse house = SmartHouse.getInstance();

        house.init("config/config1.json");
        house.start(10000,9000000000000000000L);
    }
}
