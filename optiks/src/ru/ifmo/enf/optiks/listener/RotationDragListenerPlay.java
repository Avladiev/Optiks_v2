package ru.ifmo.enf.optiks.listener;

import com.badlogic.gdx.physics.box2d.World;
import ru.ifmo.enf.optiks.phisycs.object.state.StateFactoryPlay;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class RotationDragListenerPlay extends RotationDragListener {
    public RotationDragListenerPlay(final World world) {
        super(world, new StateFactoryPlay());
    }
}
