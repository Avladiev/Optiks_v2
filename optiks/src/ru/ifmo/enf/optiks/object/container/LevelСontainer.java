package ru.ifmo.enf.optiks.object.container;

import java.util.List;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public class LevelСontainer {
    List<SimpleObjectСontainer> simpleObjectСontainers;


    public LevelСontainer() {
    }

    public LevelСontainer(List<SimpleObjectСontainer> simpleObjectСontainers) {
        this.simpleObjectСontainers = simpleObjectСontainers;
    }


    public List<SimpleObjectСontainer> getSimpleObjectСontainers() {
        return simpleObjectСontainers;
    }
}
