package ru.ifmo.enf.optiks.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import ru.ifmo.enf.optiks.physics.WorldFactory;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class Converter {

    public static Vector2 toPhysicsVector(final float x, final float y) {
        final float pointX = (x - Gdx.graphics.getWidth() / 2) / WorldFactory.PHYSICS_SCALE;
        final float pointY = -(y - Gdx.graphics.getHeight() / 2) / WorldFactory.PHYSICS_SCALE;
        return new Vector2(pointX, pointY);
    }

    public static Vector2 toGraphicsVector(final float x, final float y) {
        final float pointX = x * WorldFactory.PHYSICS_SCALE + Gdx.graphics.getWidth() / 2;
        final float pointY = -y * WorldFactory.PHYSICS_SCALE + Gdx.graphics.getHeight() / 2;
        return new Vector2(pointX, pointY);
    }

    public static Vector2 toGraphicsVector(final Vector2 vector2) {
        return toPhysicsVector(vector2.x, vector2.y);
    }

    public static Vector2 physicsToCameraVector(final float x, final float y) {
        return new Vector2(x * WorldFactory.PHYSICS_SCALE, y * WorldFactory.PHYSICS_SCALE);
    }
}
