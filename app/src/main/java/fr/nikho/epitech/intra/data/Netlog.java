package fr.nikho.epitech.intra.data;

import java.util.ArrayList;
import java.util.List;

public class Netlog {

    private double date;
    private double current;
    private double average;

    public Netlog(Double[] array) {
        date = array[0];
        current = array[1];
        average = array[5];
    }

    public double getDateInTS() {
        return date;
    }

    public double getCurrentInTS() {
        return current;
    }

    public double getAverageInTS() {
        return average;
    }

    public static List<Netlog> parse(Double[][] array) {
        List<Netlog> netlogs = new ArrayList<>();
        for (Double[] d1 : array)
            netlogs.add(new Netlog(d1));
        return netlogs;
    }
}
