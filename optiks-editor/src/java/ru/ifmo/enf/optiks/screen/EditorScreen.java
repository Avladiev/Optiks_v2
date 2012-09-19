package ru.ifmo.enf.optiks.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import ru.ifmo.enf.optiks.OptiksEditor;
import ru.ifmo.enf.optiks.graphics.Assets;
import ru.ifmo.enf.optiks.panel.ObjectsPanel;
import ru.ifmo.enf.optiks.phisycs.GameObjectFactory;
import ru.ifmo.enf.optiks.phisycs.object.ObjectType;
import ru.ifmo.enf.optiks.listeners.ObjPanelGestureListener;
import ru.ifmo.enf.optiks.util.OverlapTester;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 20.07.12
 */
public class EditorScreen implements Screen {

    private final World world;
    private final GameObjectFactory factory;
    private final ObjectsPanel objectsPanel;
    private final Camera camera;
    private final SpriteBatch batch;
    private final Sprite gameObjectsBtn;
    private final Vector3 touchPoint;

    public EditorScreen(final OptiksEditor optiksEditor) {

        /* Physics world & graphics */
        this.world = optiksEditor.getWorld();
        this.factory = optiksEditor.getFactory();
        this.camera = optiksEditor.getCamera();
        this.batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        touchPoint = new Vector3();

        /* Buttons */
        gameObjectsBtn = new Sprite(Assets.inst().get(Assets.EDITOR_GAME_OBJECTS_BTN, Texture.class));
        gameObjectsBtn.setBounds(Gdx.graphics.getWidth() - 70, 6, 64, 64);

        /* Game objects panel*/
        objectsPanel = new ObjectsPanel();
        objectsPanel.addItem(ObjectType.LASER, 1);
        objectsPanel.addItem(ObjectType.AIM, 1);
        objectsPanel.addItem(ObjectType.MIRROR, 10);
        //objectsPanel.addItem(ObjectType.LEGO, -1);
        //objectsPanel.addItem(ObjectType.DYNAMIC_LEGO, 0);

        // Gesture Listener for objectsPanel
        final GestureDetector.GestureListener gestureListener = new ObjPanelGestureListener(objectsPanel, camera);
        Gdx.input.setInputProcessor(new GestureDetector(gestureListener));
    }

    @Override
    public void render(final float delta) {
        camera.update();
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(Assets.inst().get(Assets.EDITOR_BACKGROUND_TEXTURE, Texture.class), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameObjectsBtn.draw(batch);
        objectsPanel.render(batch, delta);
        batch.end();
        update();
    }

    @Override
    public void resize(final int width, final int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    private void update() {
        if (Gdx.input.justTouched()) {
            camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (OverlapTester.pointInRectangle(gameObjectsBtn.getBoundingRectangle(), touchPoint.x, touchPoint.y)) {
                if (objectsPanel.isVisible()) {
                    objectsPanel.hide();
                } else {
                    objectsPanel.show();
                }
            }
        }
    }
}
