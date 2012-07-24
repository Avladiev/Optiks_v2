package ru.ifmo.enf.optiks.platform;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.google.gson.Gson;
import ru.ifmo.enf.optiks.object.container.LevelContainer;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public class AndroidProvider implements Provider {
    private static final String TAG = "AndroidProviderTAG";
    
    private final DBHelper dbHelper;
    private final Gson gson = new Gson();

    public AndroidProvider(final Context context) {
        this.dbHelper = new DBHelper(context);
    }

    @Override
    public LevelContainer getLevel(final byte season, final byte level) {
        final Cursor cursor = dbHelper.getWritableDatabase().query(DBHelper.TABLE_NAME, null,
                DBHelper._ID + " = " + DBHelper.bytesToShort(season, level), null, null, null, null);
        cursor.moveToFirst();
        final int index = cursor.getColumnIndex(DBHelper.VALUE);
        final String json = cursor.getString(index);
        Log.d(TAG,json);
        
        return gson.fromJson(json,LevelContainer.class);
    }

    @Override
    public void saveLevel(final LevelContainer levelContainer, final byte season, final byte level) {
       throw new UnsupportedOperationException();
    }

    @Override
    public byte getLastSeason() {
        return (byte) (dbHelper.getReadableDatabase().query(DBHelper.TABLE_NAME, null, null, null, null, null, null).getCount() - 1);
    }

    @Override
    public byte getLastLevel(final byte season) {
        return (byte) (dbHelper.getReadableDatabase().query(DBHelper.TABLE_NAME, null, DBHelper.SEASON + "=" + season, null, null, null, null).getCount() - 1);
    }

    @Override
    public void save() {
        dbHelper.close();

    }
}
