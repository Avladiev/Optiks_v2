package ru.ifmo.enf.optiks.phisycs;

import aurelienribon.bodyeditor.BodyEditorLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class BodyFactory {

    /**
     * physics world
     */
    public static final World WORLD = new World(new Vector2(0, 0), true);

    /**
     * string path to Fixture .json File
     */

    private static final String JSON_PATH = "optiks.json";

    /**
     * Fixture loader
     */
    private static final BodyEditorLoader LOADER = new BodyEditorLoader(Gdx.files.internal(JSON_PATH));

    /**
     * string name of Body Fixture
     */
    public static final String LASER = "";
    public static final String AIM = "";
    public static final String BULLET = "";
    public static final String MIRROR_RECTANGLE = "";
    public static final String MIRROR_CIRCLE = "";
    public static final String BARRIER_RECTANGLE = "";
    public static final String BARRIER_CIRCLE = "";
    public static final String CIRCLE = "circle";
    public static final String RECTANGLE = "rectangle";

    /**
     * Fixture bits for each objects
     */
    private static final short LASER_BIT = 1;
    private static final short AIM_MIRROR_BARRIER_BIT = 2;
    private static final short BULLET_BIT = 4;

    public static Body createBody(final float x, final float y, final float width, final float angle, final String fixtureName) {
        Body body = WORLD.createBody(createBodyDef(x, y, angle, BodyDef.BodyType.StaticBody));
        createFixture(fixtureName, body, width);
        return body;
    }

    private static BodyDef createBodyDef(final float x, final float y, final float angle, final BodyDef.BodyType bodyType) {
        BodyDef bd = new BodyDef();
        bd.position.set(x, y);
        bd.angle = angle;
        bd.type = bodyType;
        return bd;
    }

    private static void createFixture(final String name, final Body body, final float width) {
        FixtureDef fd = new FixtureDef();
        fd.density = 0f;
        fd.restitution = 0f;
        fd.friction = 0f;

        if (name.equalsIgnoreCase(BULLET)) {
            fd.density = 10000f;
            fd.restitution = 1f;

            fd.filter.categoryBits = BULLET_BIT;
            fd.filter.groupIndex = BULLET_BIT + AIM_MIRROR_BARRIER_BIT;
        } else if (name.equalsIgnoreCase(LASER)) {
            fd.filter.categoryBits = LASER_BIT;
            fd.filter.groupIndex = LASER_BIT + AIM_MIRROR_BARRIER_BIT;
        } else if (name.equalsIgnoreCase(MIRROR_RECTANGLE) || name.equalsIgnoreCase(MIRROR_CIRCLE) ||
                name.equalsIgnoreCase(BARRIER_RECTANGLE) || name.equalsIgnoreCase(BARRIER_CIRCLE) || name.equalsIgnoreCase(AIM)) {

            fd.filter.categoryBits = AIM_MIRROR_BARRIER_BIT;
            fd.filter.groupIndex = AIM_MIRROR_BARRIER_BIT + LASER_BIT + BULLET_BIT;
        }
        fd.filter.maskBits = 0;

        LOADER.attachFixture(body, name, fd, width);
    }
}
