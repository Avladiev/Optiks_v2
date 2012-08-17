package ru.ifmo.enf.optiks;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.ifmo.enf.optiks.platform.DesktopProvider;
import ru.ifmo.enf.optiks.platform.Provider;

import java.io.File;
import java.io.FileNotFoundException;

public class MainDesktop {

    public static void main(final String[] args) throws FileNotFoundException {
        final LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Optiks";
        cfg.useGL20 = true;
        cfg.width = 800;
        cfg.height = 480;
        final File file = new File("levels.json");

        final Provider provider = new DesktopProvider(file);

        new LwjglApplication(new OptiksGame(provider), cfg);
    }
}
