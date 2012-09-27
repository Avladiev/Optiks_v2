package ru.ifmo.enf.optiks.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class CommandList {

    private static final List<Command> list = new ArrayList<Command>();

    public static void doCommand() {
        for (final Command command : list) {
            command.doCommand();
        }
        list.clear();
    }

    public static void addCommand(final Command command) {
        list.add(command);
    }
}
