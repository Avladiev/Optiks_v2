package ru.ifmo.enf.optiks.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.sun.istack.internal.NotNull;
import ru.ifmo.enf.optiks.OptiksGame;
import ru.ifmo.enf.optiks.listener.LaserListener;
import ru.ifmo.enf.optiks.listener.MirrorListener;
import ru.ifmo.enf.optiks.object.GameObject;
import ru.ifmo.enf.optiks.object.container.LevelContainer;
import ru.ifmo.enf.optiks.phisycs.GameObjectFactory;
import ru.ifmo.enf.optiks.phisycs.PhysicWorldUpdater;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public class GameScreen implements Screen {

    private final OptiksGame optiksGame;
    private final GameObjectFactory factory;
    private final World world;
    private final OrthographicCamera camera;

    private final Box2DDebugRenderer render;
    private final SpriteBatch batch;

    static final float BOX_STEP = 1 / 80f;
    static final int BOX_VELOCITY_ITERATIONS = 8;
    static final int BOX_POSITION_ITERATIONS = 4;
    float accumulator = 0;

    private final LaserListener laserListener;
    private final MirrorListener mirrorListener;

    private GameObject[] buttons;
//    private PhysicsAnimatedGameObject[] attacherObjects;

    public GameScreen(final OptiksGame optiksGame) {
        this.optiksGame = optiksGame;
        this.world = optiksGame.getWorld();
        this.factory = optiksGame.getFactory();
        this.camera = optiksGame.getCamera();
        this.batch = new SpriteBatch();

        laserListener = new LaserListener();
        mirrorListener = new MirrorListener(this.world);
        render = new Box2DDebugRenderer(true, true, true, true);
    }

    /**
     * method that allows to set current level
     * todo create Joints
     *
     * @param level
     */

    public void setLevel(@NotNull final LevelContainer level) {
        factory.setLevel(level);
    }

    @Override
    public void render(final float v) {

        PhysicWorldUpdater.update(v, world);

        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        camera.update();

        batch.setProjectionMatrix(camera.combined);

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

        render.render(world, camera.projection.scale(3, 3, 3));
        world.getBodies().next().applyLinearImpulse(10, 10, 10, 10);
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
