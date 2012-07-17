package ru.ifmo.enf.optiks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import ru.ifmo.enf.optiks.screen.GameScreen;
import ru.ifmo.enf.optiks.screen.MenuScreen;

public class OptiksGame extends Game {

    private static final GameScreen GAME_SCREEN = new GameScreen();
    private static final Screen MENU_SCREEN = new MenuScreen();

    @Override
    public void create() {
        setScreen(GAME_SCREEN);
        //todo
    }

    public GameScreen getGameScreen() {
        return GAME_SCREEN;
    }

    private Screen getMenuScreen() {
        return MENU_SCREEN;
    }
}
