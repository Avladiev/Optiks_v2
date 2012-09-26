package ru.ifmo.enf.optiks.phisycs.object;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import ru.ifmo.enf.optiks.phisycs.util.PhysicWorldUpdater;

import java.util.ArrayList;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class Bullet extends GameObject {

    private GameObject laser;
    private final ArrayList<Vector2> collisionPoints;

    public Bullet(final GameObject laser, final Body body) {
        super(new Vector2(0, 0), new Vector2(0, 0), new Vector2(0, 0), 0, 0, 0);
        density = 1;
        friction = 0;
        restitution = 1;

        this.isMovable = false;
        this.laser = laser;

        body.setBullet(true);
        body.setActive(false);

        setBody(body);
        getBody().setTransform(calculateShootPosition(0.6f), 0);

        collisionPoints = new ArrayList<Vector2>();
        setFixtureProperties();
    }

    @Override
    public void bulletHitReaction(final Bullet bullet, final Fixture fixtureA) {
    }

    public void stop() {

        stopBody();
//        getBody().setActive(false);
        PhysicWorldUpdater.stopCalculate();


        addPoint(getWorldCenter());
    }

    public void shoot() {
        collisionPoints.clear();
        addPoint(laser.getWorldCenter());

        getBody().setTransform(calculateShootPosition(0.6f), 0);
        getBody().setActive(true);

        getBody().setLinearVelocity(calculateShootVector());
        PhysicWorldUpdater.calculateTrajectory();
    }

    public void continueShoot() {
        addPoint(getWorldCenter());
    }

    public void addPoint(final Vector2 vector2) {
        collisionPoints.add(new Vector2(vector2));
    }

    public Vector2 calculateShootPosition(final float velocity) {
        final float rotateAngle = MathUtils.radiansToDegrees * laser.getBody().getAngle() - 90;
        Vector2 point1 = new Vector2(laser.getWorldCenter());
        Vector2 point2 = new Vector2(laser.getAnchorB());

        return point1.add(point2.rotate(rotateAngle).mul(velocity));
    }

    public Vector2 calculateShootVector() {
        final float rotateAngle = MathUtils.radiansToDegrees * laser.getBody().getAngle() - 90;
        Vector2 point2 = new Vector2(laser.getAnchorB());

        return point2.rotate(rotateAngle);
    }

    public ArrayList<Vector2> getCollisionPoints() {
        return collisionPoints;
    }
}
