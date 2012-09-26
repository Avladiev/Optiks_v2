package ru.ifmo.enf.optiks.physics;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class PhysicsStackCommand {

    private static final List<PhysicsCommand> list = new ArrayList<PhysicsCommand>();

    public static void doCommand() {
        for (final PhysicsCommand command : list) {
            command.doCommand();
        }
        list.clear();
    }

    public static void addCommand(final PhysicsCommand command) {
        list.add(command);
    }
}
