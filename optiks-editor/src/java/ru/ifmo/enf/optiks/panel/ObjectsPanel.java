package ru.ifmo.enf.optiks.panel;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.ifmo.enf.optiks.accessors.SpriteAccessor;
import ru.ifmo.enf.optiks.graphics.Assets;
import ru.ifmo.enf.optiks.object.ObjectType;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 14.08.12
 */
public class ObjectsPanel {

    private final TweenManager tweenManager;

    private final Map<ObjectType, Integer> availableTypes;

    private Sprite background;

    private boolean isVisible = false;

    public ObjectsPanel() {
        availableTypes = new HashMap<ObjectType, Integer>();
        tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        background = new Sprite(Assets.inst().get(Assets.EDITOR_GAME_OBJECTS_BG, Texture.class));
        background.setBounds(25, -64, Gdx.graphics.getWidth() - 100, 64);
    }

    /**
     * @param type           ObjectType
     * @param countAvailable max available items this type.
     *                       -1 means infinity
     */
    public void addItem(final ObjectType type, final int countAvailable) {
        availableTypes.put(type, countAvailable);
    }

    public void show(final SpriteBatch batch) {
        isVisible = true;
        Timeline.createSequence()
                .push(Tween.to(background, SpriteAccessor.POS_XY, 1f).target(background.getX(), 10))
                .start(tweenManager);
    }

    public void render(final SpriteBatch batch, final float delta) {
        tweenManager.update(delta);
        background.draw(batch);
    }

    public void hide(final SpriteBatch batch) {
        isVisible = false;
        Timeline.createSequence()
                .push(Tween.to(background, SpriteAccessor.POS_XY, 1f).target(background.getX(), -(background.getHeight() + 10)))
                .start(tweenManager);
    }

    public boolean isVisible() {
        return isVisible;
    }
}
