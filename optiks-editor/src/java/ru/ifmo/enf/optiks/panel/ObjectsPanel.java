package ru.ifmo.enf.optiks.panel;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import ru.ifmo.enf.optiks.accessors.ObjPanelItemAccessor;
import ru.ifmo.enf.optiks.accessors.SpriteAccessor;
import ru.ifmo.enf.optiks.graphics.Assets;
import ru.ifmo.enf.optiks.phisycs.object.ObjectType;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 14.08.12
 */
public class ObjectsPanel {

    private final TweenManager tweenManager;
    private List<ObjPanelItem> items;
    private Sprite background;
    private boolean isVisible = false;

    /* Item's size & position values */
    private final int itemBorderSpace;
    private final int itemSizeX;
    private final int itemSizeY;
    private final int panelSizeX;
    private final int panelSizeY;
    private final int panelX;
    private final int panelY;

    /* Item's X position (to slide ability) */
    private int posX;

    /* Constants */
    private static final float APPEARING_TIME = 1f;

    public ObjectsPanel() {
        items = new ArrayList<ObjPanelItem>();
        tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        Tween.registerAccessor(ObjPanelItem.class, new ObjPanelItemAccessor());

        // TODO constants or dynamic values
        /* Item's size & position values */
        itemBorderSpace = 15;
        itemSizeX = 64;
        itemSizeY = itemSizeX;
        panelX = 25;
        panelY = 5;
        panelSizeX = Gdx.graphics.getWidth() - panelX - 75 /* Obj Button sizeX */;
        panelSizeY = itemSizeY;
        posX = panelX;

        background = new Sprite(Assets.inst().get(Assets.EDITOR_GAME_OBJECTS_BG, Texture.class));
        background.setBounds(panelX, -panelSizeY /* Hide it*/, panelSizeX, panelSizeY);
    }

    /**
     * @param type           ObjectType
     * @param countAvailable max available items this type.
     *                       -1 means infinity
     */
    public void addItem(final ObjectType type, final int countAvailable) {
        final ObjPanelItem item = new ObjPanelItem(type, countAvailable);
        item.setX(itemBorderSpace + posX + (itemBorderSpace + itemSizeX) * items.size());
        item.setY(background.getY());
        items.add(item);
    }

    public List<ObjPanelItem> getItems() {
        return items;
    }

    public void show() {
        isVisible = true;
        Timeline timeline = Timeline.createSequence();
        timeline = timeline.beginParallel();
        timeline = timeline.push(Tween.to(background, SpriteAccessor.POS_XY, APPEARING_TIME).target(background.getX(), panelY));
        for (final ObjPanelItem item : items) {
            timeline = timeline.push(Tween.to(item, ObjPanelItemAccessor.POS_XY, APPEARING_TIME).target(item.getX(), panelY));
        }
        timeline.start(tweenManager);
    }

    public void hide() {
        isVisible = false;
        Timeline timeline = Timeline.createSequence();
        timeline = timeline.beginParallel();
        timeline = timeline.push(Tween.to(background, SpriteAccessor.POS_XY, APPEARING_TIME).target(background.getX(), -(panelSizeY + panelY)));
        for (final ObjPanelItem item : items) {
            timeline = timeline.push(Tween.to(item, ObjPanelItemAccessor.POS_XY, APPEARING_TIME).target(item.getX(), -(itemSizeY + panelY)));
        }
        timeline.start(tweenManager);
    }

    public void render(final SpriteBatch batch, final float delta) {
        tweenManager.update(delta);
        this.draw(batch);
        update();
    }

    public boolean isVisible() {
        return isVisible;
    }

    public Rectangle getBoundingRectangle() {
        return new Rectangle(panelX, panelY, panelSizeX, panelSizeY);
    }

    private void draw(final SpriteBatch batch) {
        background.draw(batch);
        for (final ObjPanelItem item : items) {
            if (item.getX() >= panelX && item.getX() + itemSizeX <= panelX + panelSizeX) {
                item.draw(batch);
            } else {
                final float delta = (item.getX() < panelX) ? panelX - item.getX() : item.getX() + itemSizeX - (panelX + panelSizeX);
                if (delta > 15) {
                    item.draw(batch, 0);
                } else {
                    final float alpha = (15 - delta) / 15;
                    item.draw(batch, alpha);
                }
            }
        }
    }

    private void update() {
        // TODO Check click & drag on obj panel item
        if (Gdx.input.justTouched()) {
            //
        }
    }
}
