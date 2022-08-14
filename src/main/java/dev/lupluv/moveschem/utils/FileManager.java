package dev.lupluv.moveschem.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {

    private static FileManager instance;
    public static FileManager getInstance() {
        if(instance == null){
            instance = new FileManager();
        }
        return instance;
    }

    File configFile;

    public void loadFiles() throws IOException {
        File folder = new File("plugins//MoveSchem");
        configFile = new File("plugins//MoveSchem//config.yml");
        if(!folder.exists()) folder.mkdir();
        if(!configFile.exists()) {
            configFile.createNewFile();
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(configFile);
            cfg.set("CloudNetDirectory", "/home/eztoria/cloud/");
            cfg.save(configFile);
        }
    }

    public File getConfigFile() {
        return configFile;
    }

    public FileConfiguration getConfig(){
        return YamlConfiguration.loadConfiguration(configFile);
    }

    public String getDir(){
        return getConfig().getString("CloudNetDirectory");
    }

    public File getSchematic(String name){
        return new File("plugins//FastAsyncWorldEdit//schematics//" + name + ".schematic");
    }

    // File file = new File(getDir() + "local/services/" + name + "/plugins/FastAsyncWorldEdit/schematics");

    public boolean isServerExist(String name){
        File file = new File(getDir() + "local/services/" + name);
        return file.exists();
    }

    public File getCopyToFile(String name, String schemName){
        File file = new File(getDir() + "local/services/" + name + "/plugins/FastAsyncWorldEdit/schematics/" + schemName + ".schematic");
        return file;
    }

}
