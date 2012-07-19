package ru.ifmo.enf.optiks.phisycs;

import aurelienribon.bodyeditor.BodyEditorLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import ru.ifmo.enf.optiks.object.ObjectType;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class BodyFactory {

    /**
     * physics world
     */
    public  final World world;

    /**
     * string path to Fixture .json File
     */

    private static final String JSON_PATH = "optiks.json";

    /**
     * Fixture loader
     */
    private  final BodyEditorLoader loader ;

/*    *//**
     * string name of Body Fixture
     *//*
    public static final String LASER = "";
    public static final String AIM = "";
    public static final String BULLET = "";
    public static final String MIRROR_RECTANGLE = "";
    public static final String MIRROR_CIRCLE = "";
    public static final String BARRIER_RECTANGLE = "";
    public static final String BARRIER_CIRCLE = "";
    public static final String CIRCLE = "circle";
    public static final String RECTANGLE = "rectangle";*/

    /**
     * Fixture bits for each objects
     * need to set collision groups
     */
    private static final short LASER_BIT = 1;
    private static final short AIM_MIRROR_BARRIER_BIT = 2;
    private static final short BULLET_BIT = 4;


    public BodyFactory(final World world) {
        this.world = world;
        this.loader = new BodyEditorLoader(Gdx.files.internal(JSON_PATH));
    }

    public  Body createBody(final float x, final float y, final float width, final float angle, final String fixtureName, final boolean isStatic) {
        BodyDef.BodyType bodyType = BodyDef.BodyType.DynamicBody;
        if (isStatic) {
            bodyType = BodyDef.BodyType.StaticBody;
        }
        Body body = world.createBody(createBodyDef(x, y, angle, bodyType));
      //  createFixture(fixtureName, body, width);
        return body;
    }

    private  BodyDef createBodyDef(final float x, final float y, final float angle, final BodyDef.BodyType bodyType) {
        BodyDef bd = new BodyDef();
        bd.position.set(x, y);
        bd.angle = angle;
        bd.type = bodyType;
        return bd;
    }

    private  void createFixture(final ObjectType type, final Body body, final float width) {
        FixtureDef fd = new FixtureDef();
        fd.density = 10f;
        fd.restitution = 0f;
        fd.friction = 0f;

        switch (type) {

            case LASER:
                break;
            case AIM:
                break;
            case BULLET:
                break;
            case MIRROR_RECTANGLE:
                break;
            case MIRROR_CIRCLE:
                break;
            case BARRIER_RECTANGLE:
                break;
            case BARRIER_CIRCLE:
                break;
            case CIRCLE:
                break;
            case RECTANGLE:
                break;
        }

      /*  if (type.equalsIgnoreCase(BULLET)) {
            fd.density = 10000f;
            fd.restitution = 1f;

            fd.filter.categoryBits = BULLET_BIT;
            fd.filter.groupIndex = BULLET_BIT + AIM_MIRROR_BARRIER_BIT;
        } else if (type.equalsIgnoreCase(LASER)) {
            fd.filter.categoryBits = LASER_BIT;
            fd.filter.groupIndex = LASER_BIT + AIM_MIRROR_BARRIER_BIT;
        } else if (type.equalsIgnoreCase(MIRROR_RECTANGLE) || type.equalsIgnoreCase(MIRROR_CIRCLE) ||
                type.equalsIgnoreCase(BARRIER_RECTANGLE) || type.equalsIgnoreCase(BARRIER_CIRCLE) || type.equalsIgnoreCase(AIM)) {

            fd.filter.categoryBits = AIM_MIRROR_BARRIER_BIT;
            fd.filter.groupIndex = AIM_MIRROR_BARRIER_BIT + LASER_BIT + BULLET_BIT;
        } else if (type.equalsIgnoreCase(RECTANGLE) || type.equalsIgnoreCase(CIRCLE)) {
            fd.filter.categoryBits = LASER_BIT;
            fd.filter.groupIndex = LASER_BIT;
        }
        fd.filter.maskBits = 0;

        loader.attachFixture(body, type, fd, width);*/
    }
}
