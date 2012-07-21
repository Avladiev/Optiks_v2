package ru.ifmo.enf.optiks.object.container;

import java.util.List;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public class LevelСontainer {
    List<ObjectСontainer> objectСontainers;


    public LevelСontainer() {
    }

    public LevelСontainer(List<ObjectСontainer> objectСontainers) {
        this.objectСontainers = objectСontainers;
    }


    public List<ObjectСontainer> getObjectСontainers() {
        return objectСontainers;
    }
}
