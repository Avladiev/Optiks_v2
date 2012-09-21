package ru.ifmo.enf.optiks.panel;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import ru.ifmo.enf.optiks.graphics.Assets;
import ru.ifmo.enf.optiks.phisycs.object.ObjectType;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 29.08.12
 */
public class ObjPanelItem {

    private final ObjectType type;
    private final int countAvailable;

    private float x = 0;
    private float y = 0;
    private float width = 64;
    private float height = 64;

    public ObjPanelItem(final ObjectType type, final int countAvailable) {
        this.type = type;
        this.countAvailable = countAvailable;
    }

    public ObjectType getType() {
        return type;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(final float x) {
        this.x = x;
    }

    public void setY(final float y) {
        this.y = y;
    }

    public void setPosition(final float x, final float y) {
        this.x = x;
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setWidth(final float width) {
        this.width = width;
    }

    public void setHeight(final float height) {
        this.height = height;
    }

    public Rectangle getBoundingRectangle() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(final SpriteBatch batch) {
        // TODO draw items count available
        batch.draw(Assets.getTextureRegion(type), x, y, width, height);
    }

    public void draw(final SpriteBatch batch, final float alpha) {
        // TODO draw items count available
        final Sprite sprite = new Sprite(Assets.getTextureRegion(type));
        sprite.setBounds(x, y, width, height);
        final Color color = sprite.getColor();
        sprite.setColor(color.r, color.g, color.b, alpha);
        sprite.draw(batch);
    }
}
