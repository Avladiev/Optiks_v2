package ru.ifmo.enf.optiks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import ru.ifmo.enf.optiks.graphics.Assets;
import ru.ifmo.enf.optiks.platform.Provider;
import ru.ifmo.enf.optiks.screen.GameScreen;
import ru.ifmo.enf.optiks.screen.MenuScreen;

public class OptiksGame extends Game {

    private final Provider provider;

    public static float width;
    public static float height;

    private static GameScreen gameScreen;
    private static Screen menuScreen;

    private World world;

    private OrthographicCamera camera;
    private boolean isLoaded;
    private boolean flag;

    public OptiksGame(final Provider provider) {
        this.provider = provider;
        width = provider.getResolution().x;
        height = provider.getResolution().y;
    }

    @Override
    public void create() {

        Assets.inst().load(Assets.GAME_OBJECTS_PACK, TextureAtlas.class);
        world = new World(new Vector2(0, 0), true);
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

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Provider getProvider() {
        return provider;
    }

    @Override
    public void render() {
        Gdx.graphics.getGL20().glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //todo
        //super.render();
        if (isLoaded) {
            if (!flag) {
                gameScreen.setLevel(this.getProvider().getLevel((byte) 0, (byte) 0));
                flag = !flag;
            }
            getScreen().render(Gdx.graphics.getDeltaTime());
        } else {
            if (Assets.inst().getProgress() < 1) {
                Assets.inst().update();
            } else {
                isLoaded = true;
            }
        }
    }
}
