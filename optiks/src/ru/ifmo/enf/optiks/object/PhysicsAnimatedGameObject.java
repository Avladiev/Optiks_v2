package ru.ifmo.enf.optiks.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public class PhysicsAnimatedGameObject implements GameObject {

    TextureRegion texture;
    ObjectType type;
    Body body;
    private PhysicsAnimatedGameObject next;

    public PhysicsAnimatedGameObject(final TextureRegion texture, final ObjectType type, final Body body) {
        this.texture = texture;
        this.type = type;
        this.body = body;
    }

    public void setNext(final PhysicsAnimatedGameObject next) {
        this.next = next;
    }

    @Override
    public void draw(final SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, body.getPosition().x, body.getPosition().y, body.getWorldCenter().x, body.getWorldCenter().y,
                texture.getRegionWidth(), texture.getRegionHeight(), 1, 1, body.getAngle());
        next.draw(spriteBatch);
    }

    @Override
    public boolean isClicked(final float x, final float y) {
        switch (type) {
            case LASER:
            case MIRROR:

                return true;
            default:
                return false;
        }
    }
}
