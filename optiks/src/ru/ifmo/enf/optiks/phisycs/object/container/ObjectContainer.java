package ru.ifmo.enf.optiks.phisycs.object.container;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 * this object contains from simpleObjects
 * attacher + lego + .. + lego + one of game objects
 */
public class ObjectContainer {
    // attacher to display border
    private SimpleObjectСontainer attacher;
    // LEGO detail
    private List<SimpleObjectСontainer> lego;
    // laser || aim || mirror || null
    private SimpleObjectСontainer mainGameObject;

    public ObjectContainer( @NotNull final SimpleObjectСontainer attacher, @NotNull final List<SimpleObjectСontainer> lego,  @NotNull final SimpleObjectСontainer mainGameObject) {
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

    public Collection<SimpleObjectСontainer> getAll() {
        final Collection<SimpleObjectСontainer> objects = new ArrayList<SimpleObjectСontainer>();
        objects.add(attacher);
        objects.addAll(lego);
        objects.add(mainGameObject);
        return objects;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final ObjectContainer that = (ObjectContainer) o;

        if (attacher != null ? !attacher.equals(that.attacher) : that.attacher != null) return false;
        if (lego != null ? !lego.equals(that.lego) : that.lego != null) return false;
        if (mainGameObject != null ? !mainGameObject.equals(that.mainGameObject) : that.mainGameObject != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = attacher != null ? attacher.hashCode() : 0;
        result = 31 * result + (lego != null ? lego.hashCode() : 0);
        result = 31 * result + (mainGameObject != null ? mainGameObject.hashCode() : 0);
        return result;
    }


}
