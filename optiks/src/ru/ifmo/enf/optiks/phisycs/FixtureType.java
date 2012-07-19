package ru.ifmo.enf.optiks.phisycs;

import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class FixtureType {
    public static final FixtureDef FIXTURE_DEF;
    static {
        FIXTURE_DEF = new FixtureDef();
        FIXTURE_DEF.density = 0;
        FIXTURE_DEF.friction = 0;
        FIXTURE_DEF.restitution = 0;
    }

}
