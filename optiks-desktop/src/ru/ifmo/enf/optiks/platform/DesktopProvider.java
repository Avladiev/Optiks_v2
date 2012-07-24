package ru.ifmo.enf.optiks.platform;

import com.google.gson.Gson;
import ru.ifmo.enf.optiks.object.container.LevelContainer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public class DesktopProvider implements Provider {

    final Gson gson = new Gson();
    final List<List<LevelContainer>> levels;
    final File file;

    // private LevelContainer[][] levels;


    public DesktopProvider(final File file) throws FileNotFoundException {
        this.file = file;
        /*   сontainers = json.fromJson(LevelContainer[][].class,fileHandle) ;
        System.out.println(сontainers.length);*/

        /* levels = new LevelContainer[1][1];
        List<ObjectСontainer> objectContainers = new ArrayList<ObjectСontainer>();
        objectContainers.add(new ObjectСontainer(new Vector2(1, 2), 3, ObjectType.AIM));
        levels[0][0] = new LevelContainer((objectContainers));
        json.toJson(levels,fileHandle);*/
        // levels = json.fromJson(LevelContainer[][].class,fileHandle);
        LevelContainer[][] levelContainers = null;


        try {
            levelContainers = gson.fromJson(new InputStreamReader(new FileInputStream(file), "utf8"), LevelContainer[][].class);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        levels = new ArrayList<List<LevelContainer>>();

        if (levelContainers != null) {
            for (int i = 0; i < levelContainers.length; i++) {
                for (int j = 0; j < levelContainers[i].length; j++) {
                    if (i >= levels.size()) {
                        levels.add(new ArrayList<LevelContainer>());
                    }
                    levels.get(i).add(levelContainers[i][j]);
                }
            }
        }

    }

    @Override
    public LevelContainer getLevel(final byte season, final byte level) {
        return levels.get(season).get(level);
    }

    @Override
    public void saveLevel(final LevelContainer levelContainer, final byte season, final byte level) {
        if (season < levels.size()) {
            List<LevelContainer> levelContainerList = levels.get(season);
            levelContainerList.add(level, levelContainer);
        } else {
            List<LevelContainer> levelContainerList = new ArrayList<LevelContainer>();
            levelContainerList.add(level, levelContainer);
            levels.add(season, levelContainerList);
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
        LevelContainer[][] levelContainers = new LevelContainer[levels.size()][];
        for (int i = 0; i < levels.size(); i++) {
            for (int j = 0; j < levels.get(i).size(); j++) {
                if (levelContainers[i] == null) {
                    levelContainers[i] = new LevelContainer[levels.get(i).size()];
                }
                levelContainers[i][j] = levels.get(i).get(j);
            }
        }


        try {
            final Writer writer = new FileWriter(file);
            System.out.println(gson.toJson(levelContainers));
            writer.write(gson.toJson(levels));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
