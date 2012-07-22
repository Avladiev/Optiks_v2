package ru.ifmo.enf.optiks.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public class PhysicsAnimatedGameObject implements GameObject {

    Body body;
    ObjectType type;
    PhysicsAnimatedGameObject next;

    @Override
    public void draw(final SpriteBatch spriteBatch) {
        //todo
        /*spriteBatch.draw(null, body.getPosition().x, body.getPosition().y, body.getWorldCenter().x, body.getWorldCenter().y,
        0, 0, 1, 1, body.getAngle());*/
    }

    @Override
    public boolean isClicked(final float x, final float y) {
        switch (type) {
            case LASER:
            case MIRROR:
            case DYNAMIC_CIRCLE_ATTACHER:
            case DYNAMIC_RECTANGLE_ATTACHER:
                return true;
            default:
                return false;
        }
    }
}
