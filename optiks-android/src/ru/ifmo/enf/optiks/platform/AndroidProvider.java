package ru.ifmo.enf.optiks.platform;

import ru.ifmo.enf.optiks.object.container.LevelСontainer;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public class AndroidProvider implements Provider {
    @Override
    public LevelСontainer getLevel(final byte season, final byte level) {
        //todo
        return null;
    }

    @Override
    public void saveLevel(final LevelСontainer levelСontainer, final byte season, final byte level) {
        //todo

    }

    @Override
    public byte getLastSeason() {
        //todo
        return 0;
    }

    @Override
    public byte getLastLevel(final byte season) {
        //todo
        return 0;
    }

    @Override
    public void save() {
        //todo

    }
}
