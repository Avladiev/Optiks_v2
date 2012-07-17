package ru.ifmo.enf.optiks.level;

import com.badlogic.gdx.physics.box2d.World;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */

public interface Level {

    /**
     * is called after setWorld() !!!
     */
    void build();

    void render(float delta);

    void resize(int width, int height);

    void show();

    void hide();

    void pause();

    void resume();

    void destroy();

    void setWorld(final World world);

    void create();
}
