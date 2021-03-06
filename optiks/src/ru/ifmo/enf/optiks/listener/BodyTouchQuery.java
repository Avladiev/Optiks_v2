package ru.ifmo.enf.optiks.listener;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import ru.ifmo.enf.optiks.physics.object.GameObject;
import ru.ifmo.enf.optiks.util.Calculate;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class BodyTouchQuery {

    private final World world;
    private BodyQueryAABBCallback bodyQueryCallback;
    private final List<GameObject> query;

    private class BodyQueryAABBCallback implements QueryCallback {

        private final BodyTouchQuery bodyTouchQuery;

        public BodyQueryAABBCallback(final BodyTouchQuery bodyTouchQuery) {
            this.bodyTouchQuery = bodyTouchQuery;
        }

        @Override
        public boolean reportFixture(final Fixture fixture) {
            bodyTouchQuery.addQueryBody(fixture.getBody());
            return true;
        }
    }

    public BodyTouchQuery(final World world) {
        this.world = world;
        bodyQueryCallback = new BodyQueryAABBCallback(this);
        query = new ArrayList<GameObject>();
    }

    public GameObject getQueryBody(final float x, final float y, final boolean ignoreMovable) {
        world.QueryAABB(bodyQueryCallback, x - 0.5f, y - 0.5f, x + 0.5f, y + 0.5f);
        GameObject queryObject = null;
        double distance = Integer.MAX_VALUE;
        for (final GameObject object : query) {
            if (!ignoreMovable && !object.isMovable()) {
                continue;
            }
            final double tempDistance = Calculate.calculateDistance(object.getBody().getWorldPoint(object.getRotationCenter()), x, y);
            if (tempDistance <= distance && tempDistance < 15) {
                distance = tempDistance;
                queryObject = object;
            }
        }

        query.clear();
        return queryObject;
    }

    public void addQueryBody(final Body body) {
        if (body.getUserData() instanceof GameObject) {
            final GameObject gameObject = (GameObject) body.getUserData();
            if (!query.contains(gameObject)) {
                query.add(gameObject);
            }
        }
    }

    public boolean isRotate(final Vector2 pointA, final Vector2 pointB) {
        return Calculate.calculateDistance(pointA, pointB) > 5;
    }
}
