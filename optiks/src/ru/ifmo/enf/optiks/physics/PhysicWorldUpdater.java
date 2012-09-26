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

    public static void update(final float dt, final World world) {
        accumulator += dt;
        while (accumulator > BOX_STEP) {
            world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
            accumulator -= BOX_STEP;
        }
    }
}
