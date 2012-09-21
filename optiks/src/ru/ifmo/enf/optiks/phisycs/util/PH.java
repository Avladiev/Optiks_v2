package ru.ifmo.enf.optiks.phisycs.util;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class PH {
    private static PH ourInstance = new PH();

    public static PH getInstance() {
        return ourInstance;
    }

    private PH() {
    }
}
