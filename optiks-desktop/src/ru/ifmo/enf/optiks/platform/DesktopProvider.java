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

    final Gson gson = new Gson();
    final List<List<LevelСontainer>> levels;
    final File file;

    // private LevelСontainer[][] levels;


    public DesktopProvider(final File file) throws FileNotFoundException {
        this.file = file;
        /*   сontainers = json.fromJson(LevelСontainer[][].class,fileHandle) ;
        System.out.println(сontainers.length);*/

        /* levels = new LevelСontainer[1][1];
        List<ObjectСontainer> objectContainers = new ArrayList<ObjectСontainer>();
        objectContainers.add(new ObjectСontainer(new Vector2(1, 2), 3, ObjectType.AIM));
        levels[0][0] = new LevelСontainer((objectContainers));
        json.toJson(levels,fileHandle);*/
        // levels = json.fromJson(LevelСontainer[][].class,fileHandle);
        LevelСontainer[][] levelСontainers = null;


        try {
            levelСontainers = gson.fromJson(new InputStreamReader(new FileInputStream(file), "utf8"), LevelСontainer[][].class);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        levels = new ArrayList<List<LevelСontainer>>();

        if (levelСontainers != null) {
            for (int i = 0; i < levelСontainers.length; i++) {
                for (int j = 0; j < levelСontainers[i].length; j++) {
                    if (i >= levels.size()) {
                        levels.add(new ArrayList<LevelСontainer>());
                    }
                    levels.get(i).add(levelСontainers[i][j]);
                }
            }
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
        } else {
            List<LevelСontainer> levelСontainerList = new ArrayList<LevelСontainer>();
            levelСontainerList.add(level, levelСontainer);
            levels.add(season, levelСontainerList);
        }

    }

    @Override
    public byte getLastSeason() {

        return (byte) (levels.size() - 1);
    }

    @Override
    public byte getLastLevel(final byte season) {
        return (byte) (levels.get(season).size() - 1);
    }

    @Override
    public void save() {
        LevelСontainer[][] levelСontainers = new LevelСontainer[levels.size()][];
        for (int i = 0; i < levels.size(); i++) {
            for (int j = 0; j < levels.get(i).size(); j++) {
                if (levelСontainers[i] == null) {
                    levelСontainers[i] = new LevelСontainer[levels.get(i).size()];
                }
                levelСontainers[i][j] = levels.get(i).get(j);
            }
        }


        try {
            final Writer writer = new FileWriter(file);
            System.out.println(gson.toJson(levelСontainers));
            writer.write(gson.toJson(levels));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
