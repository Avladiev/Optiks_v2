import aurelienribon.tweenstudiotest.App;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Main {
    public static void main(String[] args) {
        new LwjglApplication(new App(), "My Super Awesome Game !", 320, 480, false);
    }
}
