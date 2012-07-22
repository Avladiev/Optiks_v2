package ru.ifmo.enf.optiks.platform;

import com.badlogic.gdx.math.Vector2;
import junit.framework.TestCase;
import ru.ifmo.enf.optiks.object.ObjectType;
import ru.ifmo.enf.optiks.object.container.LevelСontainer;
import ru.ifmo.enf.optiks.object.container.ObjectContainer;
import ru.ifmo.enf.optiks.object.container.SimpleObjectСontainer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public class DesktopProviderTest extends TestCase {
    private static final File FILE;

    static {
        try {
            FILE = File.createTempFile("levels", "json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void test1() throws FileNotFoundException {
        DesktopProvider provider = new DesktopProvider(FILE);
        assertEquals(provider.levels.size(), 0);

    }

    public void test2() throws FileNotFoundException {
        DesktopProvider provider = new DesktopProvider(FILE);
        assertEquals(provider.getLastSeason(), -1);

    }


    public void test3() throws FileNotFoundException {
        DesktopProvider provider = new DesktopProvider(FILE);
        try {
            assertEquals(provider.getLastLevel((byte) 0), 0);
            assertTrue(false);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }


    public void test4() throws FileNotFoundException {
        DesktopProvider provider = new DesktopProvider(FILE);
        try {
            assertEquals(provider.getLevel((byte) 0, (byte) 0), null);
            assertTrue(false);
        } catch (IndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    public void test5() throws FileNotFoundException {
        DesktopProvider provider = new DesktopProvider(FILE);
        final LevelСontainer levelСontainer = createLevelContainer();
        provider.saveLevel(levelСontainer,(byte)0,(byte)0);
        assertEquals(provider.getLastLevel((byte) 0), 0);
        assertEquals(provider.getLastSeason(), 0);
        assertEquals(provider.getLevel((byte)0,(byte)0),levelСontainer);
    }

    public void test6() throws FileNotFoundException {
        DesktopProvider provider = new DesktopProvider(FILE);
        final LevelСontainer levelСontainer = createLevelContainer();
        provider.saveLevel(levelСontainer,(byte)0,(byte)0);
        provider.save();
        provider = new DesktopProvider(FILE);
        assertEquals(provider.getLevel((byte)0,(byte)0),levelСontainer);

        assertEquals(provider.getLastLevel((byte) 0), 0);
        assertEquals(provider.getLastSeason(), 0);

    }










    private LevelСontainer createLevelContainer() {
        final List<ObjectContainer> objectContainers = new ArrayList<ObjectContainer>();
        final List<SimpleObjectСontainer> simpleObjectСontainers = new ArrayList<SimpleObjectСontainer>();
        simpleObjectСontainers.add(new SimpleObjectСontainer(new Vector2(12, 12), 12, ObjectType.AIM));
        objectContainers.add(new ObjectContainer(new SimpleObjectСontainer(new Vector2(1, 2), 12, ObjectType.AIM), simpleObjectСontainers,
                new SimpleObjectСontainer(new Vector2(12, 12), 43, ObjectType.BARRIER_CIRCLE)));

        return new LevelСontainer(simpleObjectСontainers, objectContainers);
    }

}