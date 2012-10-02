package ru.ifmo.enf.optiks.listener;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class BodyQueryAABBCallback implements QueryCallback {

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
