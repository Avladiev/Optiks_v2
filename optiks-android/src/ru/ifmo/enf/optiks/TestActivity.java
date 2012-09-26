package ru.ifmo.enf.optiks;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import ru.ifmo.enf.optiks.physics.object.container.LevelContainer;
import ru.ifmo.enf.optiks.physics.object.container.ObjectContainer;
import ru.ifmo.enf.optiks.physics.object.container.SimpleObjectСontainer;
import ru.ifmo.enf.optiks.platform.AndroidProvider;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public class TestActivity extends Activity {

    private static final String TAG = "TestActivityTAG";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidProvider androidProvider = new AndroidProvider(this);
        Log.d(TAG, " getlastseason =" + androidProvider.getLastSeason());
        Log.d(TAG, " getlastLevel =" + androidProvider.getLastLevel(androidProvider.getLastSeason()));
        LevelContainer levelContainer = androidProvider.getLevel((byte) 0, (byte) 0);
        for(final ObjectContainer container: levelContainer.getObjectContainers()){
            Log.d(TAG,"ObjectContainer "  + container + container.getMainGameObject());
            container.getMainGameObject() ;
        }
        for(final SimpleObjectСontainer container: levelContainer.getSimpleObjectСontainers()){
            Log.d(TAG,"ObjectContainer "  + container);
        }

    }
}
