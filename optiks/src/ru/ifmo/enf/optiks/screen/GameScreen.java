package ru.ifmo.enf.optiks.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.sun.istack.internal.NotNull;
import ru.ifmo.enf.optiks.OptiksGame;
import ru.ifmo.enf.optiks.graphics.TrajectoryRenderer;
import ru.ifmo.enf.optiks.listener.ButtonTapListener;
import ru.ifmo.enf.optiks.listener.RotationDragListenerPlay;
import ru.ifmo.enf.optiks.physics.PhysicWorldUpdater;
import ru.ifmo.enf.optiks.physics.WorldFactory;
import ru.ifmo.enf.optiks.physics.contact.CollisionListener;
import ru.ifmo.enf.optiks.physics.object.GameObject;
import ru.ifmo.enf.optiks.physics.object.container.LevelContainer;

import java.util.ArrayList;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public class GameScreen implements Screen {

    private final OptiksGame optiksGame;

    private final World world;
    private final WorldFactory worldFactory;
    private final OrthographicCamera camera;

    private final Box2DDebugRenderer render;
    private final SpriteBatch batch;
    private final ShapeRenderer shapeRenderer;

    private final RotationDragListenerPlay rotationDragListenerPlay;
    private final ButtonTapListener buttonTapListener;


//    private final RotationDragListenerSpider rotationDragListener;

    private GameObject[] buttons;
//    private PhysicsAnimatedGameObject[] attacherObjects;

    public GameScreen(final OptiksGame optiksGame) {
        this.optiksGame = optiksGame;
        this.world = optiksGame.getWorld();
        this.worldFactory = new WorldFactory(world);
        this.camera = optiksGame.getCamera();
        this.batch = new SpriteBatch();
        this.rotationDragListenerPlay = new RotationDragListenerPlay(world);
        this.buttonTapListener = new ButtonTapListener(this);

        world.setContactListener(new CollisionListener());

        final InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(new GestureDetector(buttonTapListener));
        inputMultiplexer.addProcessor(rotationDragListenerPlay);

        Gdx.input.setInputProcessor(inputMultiplexer);

        render = new Box2DDebugRenderer(true, true, false, true);
        shapeRenderer = new ShapeRenderer(10);
        PhysicWorldUpdater.setWorld(world);
    }

    /**
     * method that allows to set current level
     * @param level
     */

    public void setLevel(@NotNull final LevelContainer level) {
        worldFactory.createWalls(OptiksGame.width / 10 + 2, OptiksGame.height / 10 + 2);
        worldFactory.loadLevel(level);
        buttonTapListener.setLaser(worldFactory.getLaser());
    }

    @Override
    public void render(final float v) {

        PhysicWorldUpdater.update(v);
        world.clearForces();

        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        camera.update();

        batch.setProjectionMatrix(camera.combined);

        shapeRenderer.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Rectangle);
        shapeRenderer.rect(-230, 90, 40, 40);
        shapeRenderer.end();

        if (worldFactory.getLaser().getBullet() != null) {
            final ArrayList<Vector2> list = worldFactory.getLaser().getBullet().getCollisionPoints();
            if (list != null) {
                TrajectoryRenderer.draw(list, shapeRenderer);
            }
        }

        batch.begin();
        batch.end();

        render.render(world, camera.projection.scale(WorldFactory.PHYSICS_SCALE, WorldFactory.PHYSICS_SCALE, WorldFactory.PHYSICS_SCALE));
    }

    @Override
    public void resize(final int i, final int i1) {
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
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
