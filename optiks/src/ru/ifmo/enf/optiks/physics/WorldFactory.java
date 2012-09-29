package ru.ifmo.enf.optiks.physics;

import aurelienribon.bodyeditor.BodyEditorLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import ru.ifmo.enf.optiks.physics.joint.JointDefFactory;
import ru.ifmo.enf.optiks.physics.object.*;
import ru.ifmo.enf.optiks.physics.object.container.LevelContainer;
import ru.ifmo.enf.optiks.physics.object.container.SimpleObjectСontainer;

import java.util.List;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class WorldFactory {

    /* physics world */
    private final World world;

    /* string path to Fixture .json File */
    private static final String JSON_PATH = "optiks_fixture.json";

    /* Fixture loader */
    private final BodyEditorLoader loader;
    private Laser laser;

    public WorldFactory(final World world) {
        this.world = world;
        this.loader = new BodyEditorLoader(Gdx.files.internal(JSON_PATH));
    }

    public void loadLevel(final LevelContainer levelContainer) {
        final List<GameObject> gameObjects = LevelFactory.createLevel(levelContainer);
        for (final GameObject gameObject : gameObjects) {
            attachGameObject(gameObject);
            GameObject previous = gameObject;
            while (previous.hasNext()) {
                final GameObject next = previous.getNext();
                attachGameObject(next);

                previous.setNext(next);
                next.setPrevious(previous);

                final JointDef jointDef = JointDefFactory.createJointDef(next, previous);
                next.setJoint(world.createJoint(jointDef));

                previous = next;
            }
        }
    }

    public GameObject createGameObject(final SimpleObjectСontainer simpleObjectСontainer) {
        final GameObject gameObject = GameObjectFactory.createGameObject(simpleObjectСontainer);
        attachGameObject(gameObject);
        return gameObject;
    }

    private void attachGameObject(final GameObject gameObject) {
        final BodyDef bodyDef = createBodyDef(gameObject.getOriginPosition(), gameObject.getOriginAngle(), BodyDef.BodyType.DynamicBody);
        final Body body = world.createBody(bodyDef);

        gameObject.setBody(body);

        body.setGravityScale(0);
        createFixture(body, gameObject.getObjectType(), gameObject.getSizeScale());

        gameObject.setFixtureProperties();
        switch (gameObject.getObjectType()) {
            case STATIC_CIRCLE_ATTACHER:
            case ATTACHER:
                body.setType(BodyDef.BodyType.StaticBody);
                break;
            case LASER:
                laser = ((Laser) gameObject);
                laser.setBullet(createBullet(laser));
                body.setType(BodyDef.BodyType.DynamicBody);
                break;
            default:
                body.setType(BodyDef.BodyType.DynamicBody);
                break;
        }
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
        loader.attachFixture(body, objectType.toString(), new FixtureDef(), sizeScale);
        for (final Fixture fixture : body.getFixtureList()) {
            if (fixture.getShape() instanceof CircleShape) {
            } else {
                fixture.getShape().setRadius(0.1f);
            }
        }
    }

    private Bullet createBullet(final GameObject laser) {
        final Body body = world.createBody(createBodyDef(new Vector2(0, 0), 0, BodyDef.BodyType.DynamicBody));
        final CircleShape shape = new CircleShape();
        shape.setRadius(1);
        shape.setPosition(new Vector2(0, 0));
        body.createFixture(shape, 0);
        return new Bullet(laser, body);
    }

    public GameObject createWalls(final float width, final float height) {
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

    /* constant of physics scale */
    public static float PHYSICS_SCALE = 3f;

    public Laser getLaser() {
        return laser;
    }
}
