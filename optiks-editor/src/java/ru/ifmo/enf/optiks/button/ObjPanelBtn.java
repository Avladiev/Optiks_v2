package ru.ifmo.enf.optiks.button;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.ifmo.enf.optiks.accessors.SpriteAccessor;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 27.09.12
 */
public abstract class ObjPanelBtn extends Button {

    private final Sprite recycle;

    public ObjPanelBtn(final Sprite sprite, final Sprite recycle) {
        super(sprite);
        this.recycle = recycle;
    }

    public void setState(final State state, final Timeline timeline) {
        switch (state) {
            case BUTTON:
                timeline.push(Tween.to(recycle, SpriteAccessor.OPACITY, TWEEN_TIME).target(0));
                timeline.push(Tween.to(sprite, SpriteAccessor.OPACITY, TWEEN_TIME).target(1));
                break;
            case RECYCLE:
                timeline.push(Tween.to(sprite, SpriteAccessor.OPACITY, TWEEN_TIME).target(0));
                timeline.push(Tween.to(recycle, SpriteAccessor.OPACITY, TWEEN_TIME).target(1));
                break;
        }
    }

    @Override
    public void setBounds(final int x, final int y, final int width, final int height) {
        recycle.setBounds(x, y, width, height);
        super.setBounds(x, y, width, height);
    }

    @Override
    public void draw(final SpriteBatch batch) {
        sprite.draw(batch);
        recycle.draw(batch);
    }

    public static enum State {
        BUTTON,
        RECYCLE
    }

    /* Constants */
    private static final float TWEEN_TIME = 1f;
}
