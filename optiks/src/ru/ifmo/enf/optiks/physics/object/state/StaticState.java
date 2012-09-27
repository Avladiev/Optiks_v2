package ru.ifmo.enf.optiks.physics.object.state;

import com.badlogic.gdx.physics.box2d.BodyDef;
import ru.ifmo.enf.optiks.physics.object.GameObject;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class StaticState extends State {
    BodyDef.BodyType type;

    public StaticState(final GameObject gameObject) {
        super(gameObject);
        type = gameObject.getBody().getType();
    }

    @Override
    protected void setPreProperties() {
        gameObject.getBody().setType(BodyDef.BodyType.StaticBody);
    }

    @Override
    protected void setPostProperties() {
        gameObject.getBody().setType(type);
    }
}
