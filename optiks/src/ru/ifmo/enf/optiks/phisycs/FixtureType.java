package ru.ifmo.enf.optiks.phisycs;

import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class FixtureType {
    public static final FixtureDef GAME_OBJECT_FIXTURE_DEF;
    static {
        GAME_OBJECT_FIXTURE_DEF = new FixtureDef();
        GAME_OBJECT_FIXTURE_DEF.density = 10;
        GAME_OBJECT_FIXTURE_DEF.restitution = 0;
        GAME_OBJECT_FIXTURE_DEF.friction = 0;
    }

    public static final FixtureDef MENU_OBJECT_FIXTURE_DEF;
    static {
        MENU_OBJECT_FIXTURE_DEF = new FixtureDef();
        MENU_OBJECT_FIXTURE_DEF.density = 10;
        MENU_OBJECT_FIXTURE_DEF.restitution = 0;
        MENU_OBJECT_FIXTURE_DEF.friction = 0;
    }

    public static final FixtureDef BULLET_FIXTURE_DEF;
    static {
        BULLET_FIXTURE_DEF = new FixtureDef();
        BULLET_FIXTURE_DEF.density = 10;
        BULLET_FIXTURE_DEF.restitution = 1;
        BULLET_FIXTURE_DEF.friction = 0;
    }

}
