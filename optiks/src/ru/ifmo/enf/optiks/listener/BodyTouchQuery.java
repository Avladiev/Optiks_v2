package ru.ifmo.enf.optiks.listener;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import ru.ifmo.enf.optiks.phisycs.object.GameObject;
import ru.ifmo.enf.optiks.phisycs.utils.Calculate;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class BodyTouchQuery {
    private final World world;
    private BodyQueryAABBCallback bodyQueryCallback;
    private final List<GameObject> query;

    public BodyTouchQuery(final World world) {
        this.world = world;
        bodyQueryCallback = new BodyQueryAABBCallback(this);
        query = new ArrayList<GameObject>();
    }

    public GameObject getQueryBody(final float x, final float y) {
        world.QueryAABB(bodyQueryCallback, x, y, x, y);
        GameObject queryObject = null;
        double distance = Integer.MAX_VALUE;
        for (final GameObject object : query) {
            if (!object.isMovable()) {
                continue;
            }
            final double tempDistance = Calculate.calculateDistance(object.getBody().getWorldCenter(), x, y);
            if (tempDistance <= distance && tempDistance < 12) {
                distance = tempDistance;
                queryObject = object;
            }
        }

        System.out.println("distance" + distance);
        query.clear();
        return queryObject;
    }

    public void addQueryBody(final Body body) {
        if (body.getUserData() instanceof GameObject) {
            final GameObject gameObject = (GameObject) body.getUserData();
            if (gameObject.isCanTouch() && !query.contains(gameObject)) {
                query.add(gameObject);
            }
        }
    }

    public boolean isRotate(final Vector2 pointA, final Vector2 pointB) {
        return Calculate.calculateDistance(pointA, pointB) > 5;
    }
}