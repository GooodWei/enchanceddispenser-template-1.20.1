package com.godwei.config;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ConfigReader {
    private static final String location = "..\\config\\EnhancedDispenser\\config.json";

    public static Config ReadConfig() {
        try {
            File file = new File(location);
            FileReader fileReader = new FileReader(file);
            JsonReader reader = new JsonReader(fileReader);
            return new Gson().fromJson(reader, Config.class);
        } catch (FileNotFoundException e) {
            return new Config(true, true, true, true, true);
        }
    }
    public static boolean canBucketFill(){
        return ReadConfig().CanBucketFill();
    }

    public static boolean canBucketExtract(){
        return ReadConfig().CanBucketExtract();
    }

    public static boolean canPaintEntities(){
        return ReadConfig().isCanPaintEntities();
    }

    public static boolean canMineBlocks(){
        return ReadConfig().isCanDispenserMineBlock();
    }

    public static boolean canBottleExtract(){
        return ReadConfig().CanBottleExtract();
    }
}
