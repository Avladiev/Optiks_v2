package ru.ifmo.enf.optiks.accessors;

import aurelienribon.tweenengine.TweenAccessor;
import ru.ifmo.enf.optiks.panel.ObjPanelItem;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 29.08.12
 */
public class ObjPanelItemAccessor implements TweenAccessor<ObjPanelItem> {

    public static final int POS_XY = 1;

    @Override
    public int getValues(final ObjPanelItem target, final int tweenType, final float[] returnValues) {
        switch (tweenType) {
            case POS_XY:
                returnValues[0] = target.getX();
                returnValues[1] = target.getY();
                return 2;
            default:
                assert false;
                return -1;

        }
    }

    @Override
    public void setValues(final ObjPanelItem target, final int tweenType, final float[] newValues) {
        switch (tweenType) {
            case POS_XY:
                target.setPosition(newValues[0], newValues[1]);
                break;

            default:
                assert false;
        }
    }
}
