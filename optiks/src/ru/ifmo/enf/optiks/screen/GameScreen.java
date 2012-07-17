package ru.ifmo.enf.optiks.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import ru.ifmo.enf.optiks.phisycs.BodyFactory;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public class GameScreen implements Screen {
    Box2DDebugRenderer render;

    public GameScreen() {
        BodyFactory.createBody(0, 0, 10, 0, BodyFactory.CIRCLE);
        BodyFactory.createBody(0.05f, 0.05f, 10, 0, BodyFactory.RECTANGLE);
        render = new Box2DDebugRenderer(true, true, true, true);
    }

    @Override
    public void render(final float v) {
        render.render(BodyFactory.WORLD, null);
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
