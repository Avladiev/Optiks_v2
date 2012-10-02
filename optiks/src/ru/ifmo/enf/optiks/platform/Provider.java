package ru.ifmo.enf.optiks.platform;

import com.badlogic.gdx.math.Vector2;
import ru.ifmo.enf.optiks.physics.object.container.LevelContainer;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public interface Provider {

    LevelContainer getLevel(final byte season, final byte level);

    void saveLevel(final LevelContainer levelContainer, final byte season, final byte level);

    byte getLastSeason();

    /**
     * @param season
     * @return number of last level in the current season
     */
    byte getLastLevel(final byte season);

    void save();

    public Vector2 getResolution();
}
