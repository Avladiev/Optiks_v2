package ru.ifmo.enf.optiks.physics.object.container;

import com.sun.istack.internal.NotNull;

import java.util.List;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public class LevelContainer {
    private List<SimpleObjectСontainer> simpleObjectСontainers;
    private List<ObjectContainer> objectContainers;

    public LevelContainer() {
    }

    public LevelContainer(@NotNull final List<SimpleObjectСontainer> simpleObjectСontainers, @NotNull final List<ObjectContainer> objectContainers) {
        this.simpleObjectСontainers = simpleObjectСontainers;
        this.objectContainers = objectContainers;
    }

    public List<SimpleObjectСontainer> getSimpleObjectСontainers() {
        return simpleObjectСontainers;
    }

    public List<ObjectContainer> getObjectContainers() {
        return objectContainers;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final LevelContainer that = (LevelContainer) o;

        if (objectContainers != null ? !objectContainers.equals(that.objectContainers) : that.objectContainers != null)
            return false;
        if (simpleObjectСontainers != null ? !simpleObjectСontainers.equals(that.simpleObjectСontainers) : that.simpleObjectСontainers != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = simpleObjectСontainers != null ? simpleObjectСontainers.hashCode() : 0;
        result = 31 * result + (objectContainers != null ? objectContainers.hashCode() : 0);
        return result;
    }


    public void setSimpleObjectСontainers(final List<SimpleObjectСontainer> simpleObjectСontainers) {
        this.simpleObjectСontainers = simpleObjectСontainers;
    }

    public void setObjectContainers(final List<ObjectContainer> objectContainers) {
        this.objectContainers = objectContainers;
    }


}
