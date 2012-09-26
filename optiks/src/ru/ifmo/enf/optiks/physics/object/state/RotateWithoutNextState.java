package ru.ifmo.enf.optiks.physics.object.state;

import ru.ifmo.enf.optiks.physics.object.GameObject;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 26.09.12
 */
public class RotateWithoutNextState extends State {

    public RotateWithoutNextState(final GameObject gameObject) {
        super(gameObject);
    }

    @Override
    protected void setPreProperties() {

    }

    @Override
    protected void setPostProperties() {

    }
}
