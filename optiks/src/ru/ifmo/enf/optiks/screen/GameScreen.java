package ru.ifmo.enf.optiks.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import ru.ifmo.enf.optiks.OptiksGame;
import ru.ifmo.enf.optiks.object.GameObject;
import ru.ifmo.enf.optiks.phisycs.BodyFactory;
import ru.ifmo.enf.optiks.phisycs.PhysicWorldUpdater;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public class GameScreen implements Screen {

    private final OptiksGame optiksGame;
    private final BodyFactory factory;
    private final World world;
    private final OrthographicCamera camera;

    private final Box2DDebugRenderer render;

    static final float BOX_STEP = 1 / 80f;
    static final int BOX_VELOCITY_ITERATIONS = 8;
    static final int BOX_POSITION_ITERATIONS = 4;
    float accumulator = 0;



    private GameObject[] mirrors;
    private GameObject[] buttons;
    private GameObject laser;
    private GameObject[] otherObjects;


    public GameScreen(final OptiksGame optiksGame) {
        this.optiksGame = optiksGame;
        this.world = optiksGame.getWorld();
        this.factory = optiksGame.getFactory();
        this.camera = optiksGame.getCamera();

      /*  Body body = factory.createBody(50f, 100f, 100, 0, BodyFactory.CIRCLE, false);
        factory.createBody(-150f, 100f, 100, 0, BodyFactory.RECTANGLE, false);
        factory.createBody(-100f, -200f, 200, 0, BodyFactory.RECTANGLE, true);*/
        render = new Box2DDebugRenderer(true, true, true, true);
    }

    @Override
    public void render(final float v) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        PhysicWorldUpdater.update(v, world);
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
