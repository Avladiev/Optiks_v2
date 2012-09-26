package ru.ifmo.enf.optiks.physics.util;

import com.badlogic.gdx.math.Rectangle;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 27.08.12
 */
public class OverlapTester {

    public static boolean pointInRectangle(final Rectangle r, final float x, final float y) {
        return r.x <= x && r.x + r.width >= x && r.y <= y && r.y + r.height >= y;
    }
}
