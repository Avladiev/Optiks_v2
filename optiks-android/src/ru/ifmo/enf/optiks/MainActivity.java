package ru.ifmo.enf.optiks;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
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


        Display display = getWindowManager().getDefaultDisplay();
        Log.d("xxxxxxxxxxxxxxx", "h = " + display.getHeight() + " w =" + display.getWidth());


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Log.d("Resolution", "resolution: "+metrics.widthPixels+" x "+ metrics.heightPixels);

        final Provider provider = new AndroidProvider(this);
        initialize(new OptiksGame(provider), cfg);
    }
}