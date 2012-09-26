package ru.ifmo.enf.optiks.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.sun.istack.internal.NotNull;
import ru.ifmo.enf.optiks.OptiksGame;
import ru.ifmo.enf.optiks.listener.ButtonTapListener;
import ru.ifmo.enf.optiks.listener.RotationDragListenerPlay;
import ru.ifmo.enf.optiks.physics.GameObjectFactory;
import ru.ifmo.enf.optiks.physics.contact.CollisionListener;
import ru.ifmo.enf.optiks.physics.object.GameObject;
import ru.ifmo.enf.optiks.physics.object.container.LevelContainer;
import ru.ifmo.enf.optiks.physics.util.Calculate;
import ru.ifmo.enf.optiks.physics.util.PhysicWorldUpdater;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public class GameScreen implements Screen {

    private final OptiksGame optiksGame;

    private final World world;
    private final GameObjectFactory factory;
    private final OrthographicCamera camera;

    private final Box2DDebugRenderer render;
    private final SpriteBatch batch;
    private final ShapeRenderer shapeRenderer;

    private final RotationDragListenerPlay rotationDragListenerPlay;
    private final ButtonTapListener buttonTapListener;


//    private final RotationDragListenerSpider rotationDragListener;

    private GameObject[] buttons;
//    private PhysicsAnimatedGameObject[] attacherObjects;

    public GameScreen(final OptiksGame optiksGame) {
        this.optiksGame = optiksGame;
        this.world = optiksGame.getWorld();
        this.factory = optiksGame.getFactory();
        this.camera = optiksGame.getCamera();
        this.batch = new SpriteBatch();
        this.rotationDragListenerPlay = new RotationDragListenerPlay(world);
        this.buttonTapListener = new ButtonTapListener(this);

        world.setContactListener(new CollisionListener());

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(new GestureDetector(buttonTapListener));
        inputMultiplexer.addProcessor(rotationDragListenerPlay);

        Gdx.input.setInputProcessor(inputMultiplexer);

        render = new Box2DDebugRenderer(true, true, false, true);
        shapeRenderer = new ShapeRenderer(10);
        PhysicWorldUpdater.setWorld(world);
    }

    /**
     * method that allows to set current level
     * todo create Joints
     *
     * @param level
     */

    public void setLevel(@NotNull final LevelContainer level) {
        factory.setLevel(level, OptiksGame.width / 10 + 2, OptiksGame.height / 10 + 2);
        buttonTapListener.setBullet(factory.getBullet());
    }

    @Override
    public void render(final float v) {

        PhysicWorldUpdater.update(v);
//        world.step(1/60, 8, 2);
        world.clearForces();
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        camera.update();

        batch.setProjectionMatrix(camera.combined);

        shapeRenderer.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Rectangle);
        shapeRenderer.rect(-230, 90, 40, 40);
        shapeRenderer.end();

        if (factory.getBullet() != null) {
            ArrayList<Vector2> list = factory.getBullet().getCollisionPoints();
            if (list != null) {
                Iterator<Vector2> iterator = list.iterator();

                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                Vector2 vec1;
                if (iterator.hasNext()) {
                    vec1 = new Vector2(iterator.next());
                    if (vec1 != null) {
                        vec1 = Calculate.physicsToCameraVector(vec1.x, vec1.y);
                        while (iterator.hasNext()) {
                            Vector2 vec2 = new Vector2(iterator.next());
                            vec2 = Calculate.physicsToCameraVector(vec2.x, vec2.y);

                            shapeRenderer.line(vec1.x, vec1.y, vec2.x, vec2.y);
                            vec1 = vec2;
                        }
                    }
                }
                shapeRenderer.end();
            }
        }
        batch.begin();
/*
        for (final GameObject object : buttons) {
            object.draw(batch);
        }
*/
        /*for (final PhysicsAnimatedGameObject object : attacherObjects) {
            object.draw(batch);
        }*/
        batch.end();

        render.render(world, camera.projection.scale(GameObjectFactory.physicsScale, GameObjectFactory.physicsScale, GameObjectFactory.physicsScale));
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

    public OrthographicCamera getCamera() {
        return camera;
    }
}
