package ru.ifmo.enf.optiks.object.container;

import java.util.List;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 * this object contains from simpleObjects
 * attacher + lego + .. + lego + one of game objects
 */
public class ObjectContainer {
    // крепление
    private SimpleObjectСontainer attacher;
    // лего
    private List<SimpleObjectСontainer> lego;
    // основное объект: лазер, зеркало, мишень или ничего
    private SimpleObjectСontainer mainGameObject;

    public ObjectContainer(final SimpleObjectСontainer attacher, final List<SimpleObjectСontainer> lego, final SimpleObjectСontainer mainGameObject) {
        this.attacher = attacher;
        this.lego = lego;
        this.mainGameObject = mainGameObject;
    }

    public void setAttacher(final SimpleObjectСontainer attacher) {
        this.attacher = attacher;
    }

    public void setLego(final List<SimpleObjectСontainer> lego) {
        this.lego = lego;
    }

    public void setMainGameObject(final SimpleObjectСontainer mainGameObject) {
        this.mainGameObject = mainGameObject;
    }

    public SimpleObjectСontainer getAttacher() {
        return attacher;
    }

    public List<SimpleObjectСontainer> getLego() {
        return lego;
    }

    public SimpleObjectСontainer getMainGameObject() {
        return mainGameObject;
    }
}
