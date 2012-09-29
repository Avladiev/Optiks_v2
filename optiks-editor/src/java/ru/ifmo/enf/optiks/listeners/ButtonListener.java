package ru.ifmo.enf.optiks.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import ru.ifmo.enf.optiks.button.Button;
import ru.ifmo.enf.optiks.screen.EditorScreen;
import ru.ifmo.enf.optiks.util.OverlapTester;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 27.09.12
 */
public class ButtonListener extends InputAdapter {

    private final EditorScreen editorScreen;
    private final List<Button> buttons;

    @SuppressWarnings("FieldCanBeLocal")
    private Vector3 touchPoint;

    public ButtonListener(final EditorScreen editorScreen) {
        this.editorScreen = editorScreen;
        this.buttons = new ArrayList<Button>();
    }

    @Override
    public boolean touchDown(final int x, final int y, final int pointer, final int button) {
        touchPoint = new Vector3(x, y, 0);
        editorScreen.getCamera().unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
        for (final Button btn : buttons) {
            if (OverlapTester.pointInRectangle(btn.getBoundingRectangle(), touchPoint.x, touchPoint.y)) {
                btn.touchDown();
                return true;
            }
        }
        return false;
    }

    public void addButton(final Button button) {
        buttons.add(button);
    }
}
