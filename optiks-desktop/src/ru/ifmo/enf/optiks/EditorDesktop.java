package ru.ifmo.enf.optiks;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.ifmo.enf.optiks.platform.DesktopProvider;
import ru.ifmo.enf.optiks.platform.Provider;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 20.07.12
 */
public class EditorDesktop {

    public static void main(final String[] args) throws FileNotFoundException {
        final LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Optiks-Editor";
        cfg.useGL20 = true;
        cfg.width = 800;
        cfg.height = 480;

        new LwjglApplication(new OptiksEditor(), cfg);
    }
}
