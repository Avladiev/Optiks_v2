package ru.ifmo.enf.optiks.listener;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import ru.ifmo.enf.optiks.physics.object.Laser;
import ru.ifmo.enf.optiks.screen.GameScreen;
import ru.ifmo.enf.optiks.util.OverlapTester;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class ButtonTapListener extends GestureDetector.GestureAdapter {
    private Laser laser;
    private GameScreen gameScreen;
    private final Camera camera;
    private Vector3 touchPoint;

    public ButtonTapListener(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.camera = gameScreen.getCamera();
        touchPoint = new Vector3();
    }

    @Override
    public boolean touchDown(final int x, final int y, final int pointer) {
        camera.unproject(touchPoint.set(x, y, 0));
        if (OverlapTester.pointInRectangle(new Rectangle(170, 100, 40, 40), touchPoint.x + camera.viewportWidth / 2, (-touchPoint.y + camera.viewportHeight / 2))) {
            laser.shoot();
            return true;
        } else {
        }
        return false;
    }

    public void setLaser(final Laser laser) {
        this.laser = laser;
    }
}
