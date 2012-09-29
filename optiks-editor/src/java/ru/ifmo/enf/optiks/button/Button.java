package ru.ifmo.enf.optiks.button;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 27.09.12
 */
public abstract class Button {

    protected final Sprite sprite;

    /*private int x;
    private int y;
    private int width;
    private int height;*/

    public Button(final Sprite sprite) {
        this.sprite = sprite;
    }

    public void setBounds(final int x, final int y, final int width, final int height) {
        /*this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;*/
        sprite.setBounds(x, y, width, height);
    }

    public Rectangle getBoundingRectangle() {
        return sprite.getBoundingRectangle();
    }

    public void draw(final SpriteBatch batch) {
        sprite.draw(batch);
    }

    public abstract void touchDown();
}
