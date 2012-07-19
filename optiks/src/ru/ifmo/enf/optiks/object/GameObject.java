package ru.ifmo.enf.optiks.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public interface GameObject {
    void draw(final SpriteBatch spriteBatch);
    boolean isClicked(final float x,final float y);
}
