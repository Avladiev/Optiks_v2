package ru.ifmo.enf.optiks.phisycs;

import aurelienribon.bodyeditor.BodyEditorLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.sun.istack.internal.NotNull;
import ru.ifmo.enf.optiks.object.Aim;
import ru.ifmo.enf.optiks.object.GameObject;
import ru.ifmo.enf.optiks.object.Laser;
import ru.ifmo.enf.optiks.object.ObjectType;
import ru.ifmo.enf.optiks.object.container.LevelContainer;
import ru.ifmo.enf.optiks.object.container.ObjectContainer;
import ru.ifmo.enf.optiks.object.container.SimpleObjectСontainer;
import ru.ifmo.enf.optiks.phisycs.joints.RevoluteJointBehavior;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public final class GameObjectFactory {

    //  physics world
    private final World world;

    //  string path to Fixture .json File
    private static final String JSON_PATH = "optiks_fixture.json";

    //  Fixture loader
    private final BodyEditorLoader loader;

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

    public void setLevel(@NotNull final LevelContainer level) {
        final List<GameObject> list = new ArrayList<GameObject>();
        for (final ObjectContainer objectContainer : level.getObjectContainers()) {
            list.add(createMultiplexGameObject(objectContainer));
        }
        for (final SimpleObjectСontainer simpleObjectСontainer : level.getSimpleObjectСontainers()) {
            list.add(createGameObject(simpleObjectСontainer));
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
            world.createJoint(RevoluteJointBehavior.createRevoluteJoint(next, current, true));
            current = next;
        }
        attacher.getBody().setType(BodyDef.BodyType.StaticBody);
        return attacher;
    }

    private GameObject createGameObject(final SimpleObjectСontainer objectContainer) {
        GameObject object = null;
        final BodyDef.BodyType bodyType = BodyDef.BodyType.DynamicBody;
        switch (objectContainer.getObjectType()) {
            case STATIC_CIRCLE_ATTACHER:
            case STATIC_RECTANGLE_ATTACHER:
            case STATIC_LEGO:
            case RECTANGLE_BARRIER:
                break;
            default:
                object = new Laser();
                break;
            case LASER:
                object = new Laser();
                break;
            case AIM:
                object = new Aim();
                break;
            case BULLET:
                break;
            case MIRROR:
                break;
            case DYNAMIC_LEGO:
                break;
            case PLAY:
                break;
            case OPTIONS:
                break;
            case ABOUT:
                break;
            case SEASON:
                break;
            case LEVEL:
                break;
            case CIRCLE:
                break;
            case RECTANGLE:
                break;
        }
        final Body body = world.createBody(createBodyDef(objectContainer.getPos(), objectContainer.getAngle(), BodyDef.BodyType.DynamicBody));
        object.setBody(body);
//        body.setGravityScale(0);
        createFixture(body, objectContainer.getObjectType());
        return object;
    }

    private BodyDef createBodyDef(final Vector2 vec, final float angle, final BodyDef.BodyType bodyType) {
        final BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;
        bd.position.set(vec.x, vec.y);
        bd.angle = angle;
        bd.type = bodyType;
        return bd;
    }

    private void createFixture(final Body body, final ObjectType objectType) {
        //todo collision filter
        loader.attachFixture(body, objectType.toString(), new FixtureDef(), 10);
    }
}
