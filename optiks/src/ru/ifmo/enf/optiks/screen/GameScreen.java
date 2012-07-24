package ru.ifmo.enf.optiks.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.sun.istack.internal.NotNull;
import ru.ifmo.enf.optiks.OptiksGame;
import ru.ifmo.enf.optiks.object.GameObject;
import ru.ifmo.enf.optiks.object.PhysicsAnimatedGameObject;
import ru.ifmo.enf.optiks.object.container.LevelContainer;
import ru.ifmo.enf.optiks.object.container.ObjectContainer;
import ru.ifmo.enf.optiks.object.container.SimpleObjectСontainer;
import ru.ifmo.enf.optiks.phisycs.BodyFactory;
import ru.ifmo.enf.optiks.phisycs.PhysicWorldUpdater;

import java.util.Iterator;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public class GameScreen implements Screen {

    private final OptiksGame optiksGame;
    private final BodyFactory factory;
    private final World world;
    private final OrthographicCamera camera;

    private final Box2DDebugRenderer render;
    private final SpriteBatch batch;

    static final float BOX_STEP = 1 / 80f;
    static final int BOX_VELOCITY_ITERATIONS = 8;
    static final int BOX_POSITION_ITERATIONS = 4;
    float accumulator = 0;


    private GameObject[] buttons;
    private PhysicsAnimatedGameObject[] attacherObjects;

    public GameScreen(final OptiksGame optiksGame) {
        this.optiksGame = optiksGame;
        this.world = optiksGame.getWorld();
        this.factory = optiksGame.getFactory();
        this.camera = optiksGame.getCamera();
        this.batch = new SpriteBatch();

        /*  Body body = factory.createBody(50f, 100f, 100, 0, BodyFactory.CIRCLE, false);
   factory.createBody(-150f, 100f, 100, 0, BodyFactory.RECTANGLE, false);
   factory.createBody(-100f, -200f, 200, 0, BodyFactory.RECTANGLE, true);*/
        render = new Box2DDebugRenderer(true, true, true, true);
    }

    public void setLevel(@NotNull final LevelContainer level) {
        attacherObjects = new PhysicsAnimatedGameObject[level.getObjectContainers().size()];
        final Iterator attacherIterator = level.getObjectContainers().iterator();
        for (PhysicsAnimatedGameObject currentObject : attacherObjects) {
            final ObjectContainer container = (ObjectContainer) attacherIterator.next();
            final Iterator<SimpleObjectСontainer> objectIterator = container.getAll().iterator();

            final SimpleObjectСontainer current = objectIterator.next();

            TextureRegion textureRegion = null;
            currentObject = new PhysicsAnimatedGameObject(textureRegion, current.getObjectType(),
                    factory.createBody(current.getPos(), textureRegion.getRegionWidth(), current.getAngle(), current.getObjectType()));
            while (objectIterator.hasNext()) {
                final SimpleObjectСontainer next = objectIterator.next();
                textureRegion = null;
                final PhysicsAnimatedGameObject nextObject = new PhysicsAnimatedGameObject(textureRegion, next.getObjectType(),
                        factory.createBody(next.getPos(), textureRegion.getRegionWidth(), next.getAngle(), next.getObjectType()));

                currentObject.setNext(nextObject);
                currentObject = nextObject;
            }

        }
    }

    @Override
    public void render(final float v) {
        PhysicWorldUpdater.update(v, world);

        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        camera.update();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        for (final GameObject object : buttons) {
            object.draw(batch);
        }
        for (final GameObject object : attacherObjects) {
            object.draw(batch);
        }
        batch.end();

        render.render(world, camera.projection);
        //todo
    }

    @Override
    public void resize(final int i, final int i1) {
        //todo
    }

    @Override
    public void show() {
        //todo
    }

    @Override
    public void hide() {
        //todo
    }

    @Override
    public void pause() {
        //todo
    }

    @Override
    public void resume() {
        //todo
    }

    @Override
    public void dispose() {
        //todo
    }
}
