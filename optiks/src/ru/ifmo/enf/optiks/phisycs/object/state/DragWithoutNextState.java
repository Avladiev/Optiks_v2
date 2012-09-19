package ru.ifmo.enf.optiks.phisycs.object.state;

import ru.ifmo.enf.optiks.phisycs.object.GameObject;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class DragWithoutNextState extends State{

    public DragWithoutNextState(final GameObject gameObject) {
        super(gameObject);
        if (gameObject.hasPrevious()) {
            previous = new DragWithoutNextState(gameObject.getPrevious());
        }
    }

    @Override
    protected void setPreProperties() {
    }

    @Override
    protected void setPostProperties() {
    }
}
