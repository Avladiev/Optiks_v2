package ru.ifmo.enf.optiks.platform;

import ru.ifmo.enf.optiks.object.container.LevelСontainer;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public interface Provider {
    LevelСontainer getLevel(final byte season, final byte level);

    void saveLevel(final LevelСontainer levelСontainer, final byte season, final byte level);

    byte getLastSeason();

    byte getLastLevel(final byte season);

}
