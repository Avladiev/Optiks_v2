package ru.ifmo.enf.optiks.phisycs;

import aurelienribon.bodyeditor.BodyEditorLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.sun.istack.internal.NotNull;
import ru.ifmo.enf.optiks.phisycs.joint.RevoluteJointBehavior;
import ru.ifmo.enf.optiks.phisycs.object.*;
import ru.ifmo.enf.optiks.phisycs.object.container.LevelContainer;
import ru.ifmo.enf.optiks.phisycs.object.container.ObjectContainer;
import ru.ifmo.enf.optiks.phisycs.object.container.SimpleObjectСontainer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public final class GameObjectFactory {

    public static float physicsScale = 3f;

    //  physics world
    private final World world;

    //  string path to Fixture .json File
    private static final String JSON_PATH = "optiks_fixture.json";

    //  Fixture loader
    private final BodyEditorLoader loader;

    private Bullet bullet;

    /**
     * Fixture bits for each objects
     * need to set collision groups
     */
//    private static final short LASER_BIT = 1;
//    private static final short BULLET_BIT = 2;
//    private static final short GAME_OBJECT_BIT = 4;
//    private static final short MENU_OBJECT_BIT = 8;
    public GameObjectFactory(final World world) {
        this.world = world;
        this.loader = new BodyEditorLoader(Gdx.files.internal(JSON_PATH));
    }

    public void setLevel(@NotNull final LevelContainer level, final float width, final float height) {
        final List<GameObject> list = new ArrayList<GameObject>();
        list.add(createWall(width, height));
        for (final ObjectContainer objectContainer : level.getObjectContainers()) {
            list.add(createMultiplexGameObject(objectContainer));
        }
        for (final SimpleObjectСontainer simpleObjectСontainer : level.getSimpleObjectСontainers()) {
            list.add(createGameObject(simpleObjectСontainer));
        }
        for (final GameObject object : list) {
            object.setActive(true);
        }
    }

    private GameObject createMultiplexGameObject(final ObjectContainer objectContainer) {
        final Iterator<SimpleObjectСontainer> iterator = objectContainer.getAll().iterator();
        final GameObject attacher = createGameObject(iterator.next());
        GameObject current = attacher;
        GameObject next;
        while (iterator.hasNext()) {
            next = createGameObject(iterator.next());
            current.setNext(next);
            next.setPrevious(current);
            // todo collide connection

            boolean collide = true;
            if (next instanceof Laser) {
                this.bullet = createBullet(next);
                collide = false;
            }
            next.setJoint(world.createJoint(RevoluteJointBehavior.createRevoluteJoint(next, current, collide)));
//            RevoluteJoint joint = next.getJoint();
//            joint.setLimits(joint.getJointAngle(), joint.getJointAngle());

            current = next;
        }
        attacher.getBody().setType(BodyDef.BodyType.StaticBody);
        return attacher;
    }

    public GameObject createGameObject(final SimpleObjectСontainer objectContainer) {
        GameObject object = null;
        switch (objectContainer.getObjectType()) {
            case STATIC_CIRCLE_ATTACHER:
            case ATTACHER:
                object = new Attacher();
                break;
            case LEGO:
                object = new Lego();
                break;
            case RECTANGLE_BARRIER:
                break;
            case LASER:
                object = new Laser();
                break;
            case AIM:
                object = new Aim();
                break;
            case MIRROR:
                object = new Mirror();
                break;
            case DYNAMIC_LEGO:
                break;
            case CIRCLE:
                break;
            case RECTANGLE:
                break;
            case ROPE:
                object = new Rope();
                break;
            case ROCKET:
                object = new Rocket();
                break;
            default:
                object = new Laser();
                break;
        }
        final Body body = world.createBody(createBodyDef(objectContainer.getPos(), objectContainer.getAngle(), BodyDef.BodyType.DynamicBody));
        object.setBody(body);
        body.setGravityScale(0);
        createFixture(body, objectContainer.getObjectType(), object.getSizeScale());
        object.setFixtureProperties();
        object.getBody().setActive(false);
        return object;
    }

    private Bullet createBullet(final GameObject laser) {
        Body body = world.createBody(createBodyDef(new Vector2(0, 0), 0, BodyDef.BodyType.DynamicBody));
        CircleShape shape = new CircleShape();
        shape.setRadius(1);
        shape.setPosition(new Vector2(0, 0));
        body.createFixture(shape, 0);
        return new Bullet(laser, body);
    }

    private BodyDef createBodyDef(final Vector2 vec, final float angle, final BodyDef.BodyType bodyType) {
        final BodyDef bd = new BodyDef();
        bd.type = bodyType;
        bd.position.set(vec.x, vec.y);
        bd.angle = angle * MathUtils.degreesToRadians;
        bd.type = bodyType;
        return bd;
    }

    private void createFixture(final Body body, final ObjectType objectType, final float sizeScale) {
        //todo collision filter
        loader.attachFixture(body, objectType.toString(), new FixtureDef(), sizeScale);
        for (Fixture fixture : body.getFixtureList()) {
            if (fixture.getShape() instanceof CircleShape) {

            } else {
                fixture.getShape().setRadius(0.1f);
            }
        }
    }

    public GameObject createWall(final float width, final float height) {
        final Body body = world.createBody(createBodyDef(new Vector2(0, 0), 0, BodyDef.BodyType.StaticBody));
        final GameObject wall = new Wall();
        wall.setBody(body);
        body.setUserData(wall);

        final PolygonShape topShape = new PolygonShape();
        topShape.setAsBox(width, 1, new Vector2(0, height), 0);
        topShape.setRadius(2);

        final PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(width, 1, new Vector2(0, -height), 0);
        groundShape.setRadius(2);

        final PolygonShape leftShape = new PolygonShape();
        leftShape.setAsBox(1, height, new Vector2(-width, 0), 0);
        leftShape.setRadius(2);

        final PolygonShape rightShape = new PolygonShape();
        rightShape.setAsBox(1, height, new Vector2(width, 0), 0);
        rightShape.setRadius(2);

        body.createFixture(topShape, 0);
        body.createFixture(leftShape, 0);
        body.createFixture(groundShape, 0);
        body.createFixture(rightShape, 0);

        return wall;
    }

    public Bullet getBullet() {
        return bullet;
    }
}