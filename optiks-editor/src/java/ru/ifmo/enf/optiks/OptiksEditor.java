package ru.ifmo.enf.optiks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import ru.ifmo.enf.optiks.screen.EditorScreen;
import ru.ifmo.enf.optiks.screen.HighscoresScreen;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 20.07.12
 */
public class OptiksEditor extends Game {

    private final OrthographicCamera camera;

    public OptiksEditor(final OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public void create() {
        this.setScreen(new EditorScreen(this));
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
