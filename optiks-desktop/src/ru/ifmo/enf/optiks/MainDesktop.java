package ru.ifmo.enf.optiks;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class MainDesktop {

    public static void main(final String[] args) {
        final LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Optiks";
        cfg.useGL20 = true;
        cfg.width = 480;
        cfg.height = 320;

        new LwjglApplication(new OptiksGame(), cfg);
    }
}
