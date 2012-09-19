package ru.ifmo.enf.optiks;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.surfaceview.FixedResolutionStrategy;
import ru.ifmo.enf.optiks.platform.AndroidProvider;
import ru.ifmo.enf.optiks.platform.Provider;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useAccelerometer = false;
        cfg.useCompass = false;
        cfg.useWakelock = false;
        cfg.useGL20 = true;
        cfg.numSamples = 2;
        cfg.resolutionStrategy = new FixedResolutionStrategy(800, 480);




        final Display display = getWindowManager().getDefaultDisplay();
        Log.d("xxxxxxxxxxxxxxx", "h = " + display.getHeight() + " w =" + display.getWidth());

        final DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Log.d("Resolution", "resolution: " + metrics.widthPixels + " x " + metrics.heightPixels);

        final Provider provider = new AndroidProvider(this);
        initialize(new OptiksGame(provider), cfg);
    }
}