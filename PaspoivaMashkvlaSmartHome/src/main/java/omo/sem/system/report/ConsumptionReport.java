package omo.sem.system.report;

import omo.sem.system.SmartHouse;
import omo.sem.objects.device.Device;
import omo.sem.objects.device.DeviceFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for generating consumption report.
 */
public class ConsumptionReport {
    private FileWriter consumptionReport = null;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Instantiates a new Consumption report.
     */
    public ConsumptionReport() {
        try {
            this.consumptionReport = new FileWriter("report/ConsumptionReport.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates report.
     *
     * @throws IOException writing to file is unsuccessful
     */
    public void generateReport() throws IOException {
        List<Device> allDevices = new ArrayList<>(DeviceFactory.getInstance().getDevices());

        double totalElectricity = 0;
        int day = SmartHouse.getInstance().getSimulation().getDay();

        consumptionReport.write("__________________________________ Report for the " + day + " day __________________________________\n");

        consumptionReport.write("__________________________________ Electricity consumption __________________________________\n");
        for (Device device : allDevices) {
            double elUsed = device.calculateElectricityConsumption();
            totalElectricity += elUsed;
            consumptionReport.write(device.getName() + " has used " + df.format(elUsed) + " KwH " + "electricity for today.\n");
        }
        consumptionReport.write("Total electricity used by day: " + df.format(totalElectricity) + " KwH.\n");

        consumptionReport.flush();
    }
}
