package ru.ifmo.enf.optiks.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import ru.ifmo.enf.optiks.physics.GameObjectFactory;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class Converter {

    public static Vector2 toPhysicsVector(final float x, final float y) {
        final float pointX = (x - Gdx.graphics.getWidth() / 2) / GameObjectFactory.physicsScale;
        final float pointY = -(y - Gdx.graphics.getHeight() / 2) / GameObjectFactory.physicsScale;
        return new Vector2(pointX, pointY);
    }

    public static Vector2 toGraphicsVector(final float x, final float y) {
        final float pointX = x * GameObjectFactory.physicsScale + Gdx.graphics.getWidth() / 2;
        final float pointY = -y * GameObjectFactory.physicsScale + Gdx.graphics.getHeight() / 2;
        return new Vector2(pointX, pointY);
    }

    public static Vector2 toGraphicsVector(final Vector2 vector2) {
        return toPhysicsVector(vector2.x, vector2.y);
    }

    public static Vector2 physicsToCameraVector(final float x, final float y) {
        return new Vector2(x * GameObjectFactory.physicsScale, y * GameObjectFactory.physicsScale);
    }
}
