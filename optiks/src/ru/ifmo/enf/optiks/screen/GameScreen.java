package ru.ifmo.enf.optiks.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.sun.istack.internal.NotNull;
import ru.ifmo.enf.optiks.OptiksGame;
import ru.ifmo.enf.optiks.listener.RotationDragListenerPlay;
import ru.ifmo.enf.optiks.phisycs.GameObjectFactory;
import ru.ifmo.enf.optiks.phisycs.PhysicWorldUpdater;
import ru.ifmo.enf.optiks.phisycs.contact.CollisionListener;
import ru.ifmo.enf.optiks.phisycs.object.GameObject;
import ru.ifmo.enf.optiks.phisycs.object.container.LevelContainer;

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

//    private final RotationDragListenerSpider rotationDragListener;

    private GameObject[] buttons;
//    private PhysicsAnimatedGameObject[] attacherObjects;

    public GameScreen(final OptiksGame optiksGame) {
        this.optiksGame = optiksGame;
        this.world = optiksGame.getWorld();
        this.factory = optiksGame.getFactory();
        this.camera = optiksGame.getCamera();
        this.batch = new SpriteBatch();

        world.setContactListener(new CollisionListener());

//        Gdx.input.setInputProcessor(new GestureDetector(new RotationDragListenerSpider(world)));
        Gdx.input.setInputProcessor(new RotationDragListenerPlay(world));
//        laserListener = new LaserListener();
//        mirrorListener = new MirrorListener(this.world);
        render = new Box2DDebugRenderer(true, true, false, true);
        shapeRenderer = new ShapeRenderer(10);
    }

    /**
     * method that allows to set current level
     * todo create Joints
     *
     * @param level
     */

    public void setLevel(@NotNull final LevelContainer level) {
        factory.setLevel(level, OptiksGame.width / 10 + 2, OptiksGame.height / 10 + 2);
    }

    @Override
    public void render(final float v) {

        PhysicWorldUpdater.update(v, world);
//        world.step(1/60, 8, 2);
        world.clearForces();
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        camera.update();

        batch.setProjectionMatrix(camera.combined);

        shapeRenderer.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.line(100, 10, 0, 0);
        shapeRenderer.end();

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
}
