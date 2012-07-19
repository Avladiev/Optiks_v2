package ru.ifmo.enf.optiks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import ru.ifmo.enf.optiks.phisycs.BodyFactory;
import ru.ifmo.enf.optiks.screen.GameScreen;
import ru.ifmo.enf.optiks.screen.MenuScreen;

public class OptiksGame extends Game {

    private static Screen gameScreen;
    private static Screen menuScreen;

    private World world;
    private BodyFactory factory;

    private OrthographicCamera camera;



    @Override
    public void create() {
        world = new World(new Vector2(0, -90), true);
        factory = new BodyFactory(world);
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameScreen = new GameScreen(this);
        menuScreen = new MenuScreen(this);
        setScreen(gameScreen);
        //todo
    }

    public Screen getGameScreen() {
        return gameScreen;
    }

    private Screen getMenuScreen() {
        return menuScreen;
    }

    public World getWorld() {
        return world;
    }

    public BodyFactory getFactory() {
        return factory;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
