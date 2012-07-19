package ru.ifmo.enf.optiks.object.container;

import java.util.Arrays;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class JsonLevelData implements LevelData {
    private final GameObjectData[] objectData;


    public JsonLevelData(final GameObjectData[] objectData) {
        this.objectData = objectData;
    }

    @Override
    public GameObjectData[] getObjects() {
        return objectData;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final JsonLevelData that = (JsonLevelData) o;

        if (!Arrays.equals(objectData, that.objectData)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return objectData != null ? Arrays.hashCode(objectData) : 0;
    }
}
