package ru.ifmo.enf.optiks.platform;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public class DBHelper extends SQLiteOpenHelper implements BaseColumns {

    private static final String TAG = "DBHelperTAG";
    private static final int DB_VER = 1;

    public static final String DB_NAME = "levels";
    public static final String TABLE_NAME = "levels";
    public static final String VALUE = "value";
    public static final String SEASON = "seasor";
    public static final String LEVEL = "level";

    private final String dataFileName = "levels.json";
    private final Context mContext;

    public DBHelper(final Context context) {
        super(context, DB_NAME, null, DB_VER);
        Log.d(TAG, "constructor called");
        mContext = context;
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        Log.d(TAG, "onCreate() called");
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " SHORT PRIMARY KEY , "
                + SEASON + " BYTE , " +
                LEVEL + " BYTE , " +
                VALUE + " TEXT);");
        fillData(db);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    private String getData() {
        final InputStream stream;

        try {
            stream = mContext.getAssets().open(dataFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        final Scanner sc = new Scanner(stream);
        sc.useDelimiter("\n");
        final StringBuilder sb = new StringBuilder();
        while (sc.hasNext()) {
            sb.append(sc.next());
        }

        return sb.toString();
    }

    private void fillData(final SQLiteDatabase db) {
        Log.d(TAG, "fillData() called");
        final String data = getData();
        final ContentValues cv = new ContentValues();

        try {
            final JSONArray seasonArray = new JSONArray(data);
            for (byte i = 0, len = (byte) seasonArray.length(); i < len; ++i) {
                final JSONArray levelArray = seasonArray.getJSONArray(i);
                for (byte j = 0, len2 = (byte) levelArray.length(); j < len2; j++) {
                    final JSONObject object = levelArray.getJSONObject(j);
                    cv.put(_ID, bytesToShort(i, j));
                    cv.put(SEASON, i);
                    cv.put(LEVEL, j);
                    cv.put(VALUE, object.toString());
                    db.insert(TABLE_NAME, null, cv);
                    cv.clear();
                }
            }
        } catch (JSONException e) {

            throw new RuntimeException(e);
        }
        Log.d(TAG, "fillData() end");
    }

    static short bytesToShort(final byte i, final byte j) {
        int res = i;
        res <<= 8;
        res += j;
        return (short) res;
    }

    static byte getFirsByte(final short i) {
        return (byte) ((i & 65280) >> 8);
    }

    static byte getLastByte(final short i) {
        return (byte) ((i & 255));
    }
}


