package ru.ifmo.enf.optiks.phisycs.object.state;

import ru.ifmo.enf.optiks.phisycs.object.GameObject;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class DragState extends State {

    public DragState(final GameObject gameObject) {
        super(gameObject);
    }

    @Override
    protected void setPreProperties() {
        //todo
        gameObject.getBody().setFixedRotation(true);
    }

    @Override
    protected void setPostProperties() {
        //todo
        gameObject.getBody().setFixedRotation(false);
    }
}
