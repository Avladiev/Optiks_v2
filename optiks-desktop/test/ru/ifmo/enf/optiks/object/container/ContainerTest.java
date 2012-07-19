package ru.ifmo.enf.optiks.object.container;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import junit.framework.TestCase;
import ru.ifmo.enf.optiks.object.ObjectType;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class ContainerTest extends TestCase {

    public void test1() {
        final GameObjectData[] objectDatas = new GameObjectData[1];
        objectDatas[0] = new JsonGameObjectData(new Vector2(1, 2), 12f, ObjectType.AIM);
        final LevelData levelData = new JsonLevelData(objectDatas);
        final Json  json = new Json();
        final String s = json.toJson(levelData);
        System.out.println(s);

        LevelData levelData1 = json.fromJson(JsonLevelData.class,s);
        assertEquals(levelData,levelData1);



    }
}
