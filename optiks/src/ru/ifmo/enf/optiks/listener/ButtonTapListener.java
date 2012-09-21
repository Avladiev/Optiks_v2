package ru.ifmo.enf.optiks.listener;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import ru.ifmo.enf.optiks.phisycs.object.Bullet;
import ru.ifmo.enf.optiks.phisycs.util.OverlapTester;
import ru.ifmo.enf.optiks.screen.GameScreen;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class ButtonTapListener extends GestureDetector.GestureAdapter {
    private Bullet bullet;
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
        System.out.println(x + "   " + y);
        camera.unproject(touchPoint.set(x, y, 0));
        System.out.println((touchPoint.x + camera.viewportWidth / 2) + "   " + (-touchPoint.y + camera.viewportHeight / 2));
        if (OverlapTester.pointInRectangle(new Rectangle(170, 100, 40, 40), touchPoint.x + camera.viewportWidth / 2, (-touchPoint.y + camera.viewportHeight / 2))) {
            System.out.println("ok");
            bullet.shoot();
            return true;
        } else {
            System.out.println("no");
        }
        return false;
    }

    public void setBullet(final Bullet bullet) {
        this.bullet = bullet;
    }
}
