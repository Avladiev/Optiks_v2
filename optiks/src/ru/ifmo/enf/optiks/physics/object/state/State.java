package ru.ifmo.enf.optiks.physics.object.state;

import ru.ifmo.enf.optiks.physics.object.GameObject;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public abstract class State {
    protected State previous;
    protected State next;
    protected final GameObject gameObject;

    protected State(final GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public void setPreState() {
        setPreProperties();
        if (previous != null) {
            previous.setPreState();
        }
        if (next != null) {
            next.setPreState();
        }
    }

    public void setPostState() {
        setPostProperties();
        gameObject.stopBody();
        if (previous != null) {
            previous.setPostState();
        }
        if (next != null) {
            next.setPostState();
        }
    }

    protected abstract void setPreProperties();

    protected abstract void setPostProperties();
}
