package ru.ifmo.enf.optiks.listeners;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import ru.ifmo.enf.optiks.listener.BodyTouchQuery;
import ru.ifmo.enf.optiks.phisycs.object.GameObject;
import ru.ifmo.enf.optiks.phisycs.util.Calculate;
import ru.ifmo.enf.optiks.screen.EditorScreen;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 20.09.12
 */
public class GameObjectListener extends InputAdapter {

    private final EditorScreen editorScreen;
    private final World world;
    private final BodyTouchQuery bodyTouchQuery;
    private GameObject activeObject;

    public GameObjectListener(final EditorScreen editorScreen) {
        this.editorScreen = editorScreen;
        world = editorScreen.getWorld();
        bodyTouchQuery = new BodyTouchQuery(world);
    }

    @Override
    public boolean touchDown(final int x, final int y, final int pointer, final int button) {
        switch (pointer) {
            case 0:
                final Vector2 vector = Calculate.toPhysicsVector(x, y);
                activeObject = bodyTouchQuery.getQueryBody(vector.x, vector.y);

                if (activeObject == null) {
                    return false;
                }


        }
        return false;
    }

    @Override
    public boolean touchUp(final int x, final int y, final int pointer, final int button) {
        editorScreen.getWorld().destroyJoint(editorScreen.getMouseJoint());
        return false;
    }
}
