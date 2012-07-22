package ru.ifmo.enf.optiks;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 20.07.12
 */
public class EditorDesktop {
    public static void main(String[] args) {
        final LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Optiks-Editor";
        cfg.useGL20 = true;
        cfg.width = 480;
        cfg.height = 320;

        new LwjglApplication(new OptiksGame(), cfg);
    }
}
