package omo.sem.system.report;

import omo.sem.system.SmartHouse;
import omo.sem.system.event.Event;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Class for generating event report.
 */
public class EventReport {
    private FileWriter eventReport = null;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Instantiates a new Event report.
     */
    public EventReport() {
        try {
            this.eventReport = new FileWriter("report/EventReport.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generate report.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void generateReport(Event event) throws IOException {
        eventReport.write(String.format("Event type \"%s\" from \"%s\" in \"%s\" [%s]\n",
                event.getClass().getSimpleName(),
                event.getSource().getName(),
                event.getLocation().getName(),
                SmartHouse.getInstance().getSimulation().getFormattedTime()));
        eventReport.flush();
    }
}
