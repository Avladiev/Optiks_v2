package ru.ifmo.enf.optiks.graphics;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import ru.ifmo.enf.optiks.physics.util.Calculate;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class TrajectoryRenderer {
    public static void draw(final ArrayList<Vector2> list, final ShapeRenderer shapeRenderer) {
        if (list != null) {
            final Iterator<Vector2> iterator = list.iterator();

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            Vector2 vec1;
            if (iterator.hasNext()) {
                vec1 = new Vector2(iterator.next());
                vec1 = Calculate.physicsToCameraVector(vec1.x, vec1.y);
                while (iterator.hasNext()) {
                    Vector2 vec2 = new Vector2(iterator.next());
                    vec2 = Calculate.physicsToCameraVector(vec2.x, vec2.y);

                    shapeRenderer.line(vec1.x, vec1.y, vec2.x, vec2.y);
                    vec1 = vec2;
                }
            }
            shapeRenderer.end();
        }
    }
}
