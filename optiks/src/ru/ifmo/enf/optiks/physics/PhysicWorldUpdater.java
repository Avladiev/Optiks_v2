package ru.ifmo.enf.optiks.physics;

import com.badlogic.gdx.physics.box2d.World;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class PhysicWorldUpdater {
    private static final float BOX_STEP = 1 / 80f;
    private static final int BOX_VELOCITY_ITERATIONS = 8;
    private static final int BOX_POSITION_ITERATIONS = 4;
    private static float accumulator = 0;

    private static boolean updateTrajectory = false;

    private static PhysicWorldUpdater myInstance = new PhysicWorldUpdater();
    private static World world;

    public static PhysicWorldUpdater getInstance() {
        return myInstance;
    }

    private PhysicWorldUpdater() {
    }

    public static void setWorld(final World world) {
        PhysicWorldUpdater.world = world;
    }

    public static void update(final float dt) {
        accumulator += dt;
        while (accumulator > BOX_STEP) {
            world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
            accumulator -= BOX_STEP;
        }
    }

    public static void updateTrajectory() {
        updateTrajectory = true;
        while (updateTrajectory) {
            world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
        }
    }

    public static void stopUpdateTrajectory() {
        updateTrajectory = false;
    }
}
