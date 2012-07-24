package ru.ifmo.enf.optiks;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import ru.ifmo.enf.optiks.platform.AndroidProvider;
import ru.ifmo.enf.optiks.platform.Provider;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 20.07.12
 */
public class EditorActivity extends AndroidApplication {
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;

        final Provider provider = new AndroidProvider(this);

        initialize(new OptiksGame(provider), cfg);
    }
}
