package ru.ifmo.enf.optiks.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import ru.ifmo.enf.optiks.OptiksEditor;
import ru.ifmo.enf.optiks.button.ObjPanelBtn;
import ru.ifmo.enf.optiks.graphics.Assets;
import ru.ifmo.enf.optiks.listeners.ButtonListener;
import ru.ifmo.enf.optiks.listeners.GameObjectListener;
import ru.ifmo.enf.optiks.listeners.ObjPanelScrollListener;
import ru.ifmo.enf.optiks.listeners.collision.EditCollisionListener;
import ru.ifmo.enf.optiks.panel.ObjectsPanel;
import ru.ifmo.enf.optiks.physics.WorldFactory;
import ru.ifmo.enf.optiks.physics.object.GameObject;
import ru.ifmo.enf.optiks.physics.object.ObjectType;
import ru.ifmo.enf.optiks.util.CommandList;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 20.07.12
 */
public class EditorScreen implements Screen {

    private final World world;
    private final WorldFactory factory;
    private final ObjectsPanel objectsPanel;
    private final ObjPanelBtn objPanelBtn;
    private final Camera camera;
    private final SpriteBatch batch;
    private final Box2DDebugRenderer box2DDebugRenderer;
    private final GameObject wall;

    private MouseJoint mouseJoint = null;

    public EditorScreen(final OptiksEditor optiksEditor) {

        /* Box2D debug */
        box2DDebugRenderer = new Box2DDebugRenderer(true, true, false, true);

        /* Physics world & graphics */
        this.world = optiksEditor.getWorld();
        this.factory = optiksEditor.getFactory();
        this.camera = optiksEditor.getCamera();
        this.batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        /* Screen border */
        wall = factory.createWalls(135, 80);

        /* Game objects panel*/
        objectsPanel = new ObjectsPanel(this);
        objectsPanel.addItem(ObjectType.LASER, 1);
        //objectsPanel.addItem(ObjectType.AIM, 1);
        objectsPanel.addItem(ObjectType.MIRROR, 10);
        objectsPanel.addItem(ObjectType.LEGO, 1);
        objectsPanel.addItem(ObjectType.ATTACHER, 10);

        /* Buttons */
        final Sprite button = new Sprite(Assets.inst().get(Assets.EDITOR_OBJ_PANEL_BTN, Texture.class));
        final Sprite recycle = new Sprite(Assets.inst().get(Assets.EDITOR_RECYCLE, Texture.class));

        objPanelBtn = new ObjPanelBtn(button, recycle) {
            @Override
            public void touchDown() {
                if (objectsPanel.isVisible()) {
                    objectsPanel.hide();
                } else {
                    objectsPanel.show();
                }
            }
        };
        objPanelBtn.setBounds(Gdx.graphics.getWidth() - 70, 6, 64, 64);

        /* Button listener */
        final ButtonListener buttonListener = new ButtonListener(camera);
        buttonListener.addButton(objPanelBtn);

        /* Gesture Listeners */
        final GestureDetector.GestureListener gestureListener = new ObjPanelScrollListener(this);

        /* Input Processor for game objects */
        final GameObjectListener gameObjectListener = new GameObjectListener(this);

        /* Multi input processor, contains all processors */
        final InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(new GestureDetector(gestureListener));
        inputMultiplexer.addProcessor(gameObjectListener);
        inputMultiplexer.addProcessor(new GestureDetector(gameObjectListener.getGestureListener()));
        inputMultiplexer.addProcessor(buttonListener);
        Gdx.input.setInputProcessor(inputMultiplexer);

        /* Collision listener */
        world.setContactListener(new EditCollisionListener());

        /* Show obj panel */
        objectsPanel.show();
    }

    @Override
    public void render(final float delta) {
        camera.update();
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        world.step(1 / 60f, 3, 8);
        world.clearForces();
        batch.begin();
        batch.draw(Assets.inst().get(Assets.EDITOR_BACKGROUND_TEXTURE, Texture.class), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        objectsPanel.render(batch, delta);
        objPanelBtn.draw(batch);
        batch.end();
        box2DDebugRenderer.render(world, camera.projection.scale(WorldFactory.PHYSICS_SCALE, WorldFactory.PHYSICS_SCALE, WorldFactory.PHYSICS_SCALE));
        CommandList.doCommand();
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
        world.dispose();
        batch.dispose();
    }

    public World getWorld() {
        return world;
    }

    public WorldFactory getFactory() {
        return factory;
    }

    public MouseJoint getMouseJoint() {
        return mouseJoint;
    }

    public void setMouseJoint(final MouseJoint mouseJoint) {
        this.mouseJoint = mouseJoint;
    }

    public ObjectsPanel getObjPanel() {
        return objectsPanel;
    }

    public ObjPanelBtn getObjPanelBtn() {
        return objPanelBtn;
    }

    public Camera getCamera() {
        return camera;
    }

    public GameObject getWall() {
        return wall;
    }
}
