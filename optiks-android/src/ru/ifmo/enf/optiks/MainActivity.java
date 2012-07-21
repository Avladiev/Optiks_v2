package ru.ifmo.enf.optiks;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import ru.ifmo.enf.optiks.platform.AndroidProvider;
import ru.ifmo.enf.optiks.platform.Provider;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        final Provider provider  = new AndroidProvider();
        initialize(new OptiksGame(provider), cfg);
    }
}