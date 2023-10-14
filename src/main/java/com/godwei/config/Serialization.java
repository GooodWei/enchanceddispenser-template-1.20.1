package com.godwei.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Serialization {
    private static final String location = "..\\config\\EnhancedDispenser\\config.json";

    public static void CreateAndWriteConfiguration() {
        File file = new File(location);
        if (!file.getParentFile().getParentFile().exists()){
            file.getParentFile().getParentFile().mkdirs();
        }
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Config cfg = new Config(true, true, true, true);
            String config = gson.toJson(cfg);
            try {
                FileWriter writer = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write(config);
                bufferedWriter.flush();
                bufferedWriter.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
