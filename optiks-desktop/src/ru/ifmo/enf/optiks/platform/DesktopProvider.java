package ru.ifmo.enf.optiks.platform;

import com.google.gson.Gson;
import ru.ifmo.enf.optiks.object.container.LevelСontainer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public class DesktopProvider implements Provider {

    private final Gson gson = new Gson();
    private List<List<LevelСontainer>> levels;


    // private LevelСontainer[][] levels;


    public DesktopProvider(final File file) throws FileNotFoundException {

        /*   сontainers = json.fromJson(LevelСontainer[][].class,fileHandle) ;
        System.out.println(сontainers.length);*/

        /* levels = new LevelСontainer[1][1];
        List<ObjectСontainer> objectContainers = new ArrayList<ObjectСontainer>();
        objectContainers.add(new ObjectСontainer(new Vector2(1, 2), 3, ObjectType.AIM));
        levels[0][0] = new LevelСontainer((objectContainers));
        json.toJson(levels,fileHandle);*/
        // levels = json.fromJson(LevelСontainer[][].class,fileHandle);

        try {
            levels = gson.fromJson(new InputStreamReader(new FileInputStream(file), "utf8"), List.class);
        } catch (UnsupportedEncodingException e) {

        }


    }

    @Override
    public LevelСontainer getLevel(final byte season, final byte level) {
        return levels.get(season).get(level);
    }

    @Override
    public void saveLevel(final LevelСontainer levelСontainer, final byte season, final byte level) {
        if (season < levels.size()) {
            List<LevelСontainer> levelСontainerList = levels.get(season);
            levelСontainerList.add(level, levelСontainer);
        }  else{
            List<LevelСontainer> levelСontainerList = new ArrayList<LevelСontainer>();
            levelСontainerList.add(level,levelСontainer);
            levels.add(season,levelСontainerList );
        }

    }

    @Override
    public byte getLastSeason() {
        //todo
        return 0;
    }

    @Override
    public byte getLastLevel(final byte season) {
        //todo
        return 0;
    }
}
