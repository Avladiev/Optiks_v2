package ru.ifmo.enf.optiks.physics.object.state;

import ru.ifmo.enf.optiks.physics.object.GameObject;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public abstract class State {
    State previous;
    State next;
    GameObject gameObject;

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

    protected abstract void setPreProperties();

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

    protected abstract void setPostProperties();

    public void setPrevious(final State previous) {
        this.previous = previous;
    }
}
