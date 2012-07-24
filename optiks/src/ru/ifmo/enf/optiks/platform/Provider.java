package ru.ifmo.enf.optiks.platform;

import com.sun.istack.internal.NotNull;
import ru.ifmo.enf.optiks.object.container.LevelContainer;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public interface Provider {
    LevelContainer getLevel(final byte season, final byte level);

    void saveLevel( @NotNull final LevelContainer levelContainer, final byte season, final byte level);

    byte getLastSeason();

    /**
     *
     * @param season
     * @return   number last level in the current season
     */
    byte getLastLevel(final byte season);


    void save();


}
