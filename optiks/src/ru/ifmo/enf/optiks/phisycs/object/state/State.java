package ru.ifmo.enf.optiks.phisycs.object.state;

import ru.ifmo.enf.optiks.phisycs.object.GameObject;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public abstract class State {
    State previous;
    GameObject gameObject;

    protected State(final GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public void setPreState() {
        setPreProperties();
        if (previous != null) {
            previous.setPreProperties();
        }
    }

    protected abstract void setPreProperties();

    public void setPostState() {
        setPostProperties();
        if (previous != null) {
            previous.setPostProperties();
        }
    }

    protected abstract void setPostProperties();

    public void setPrevious(final State previous) {
        this.previous = previous;
    }
}
