package dev.lupluv.moveschem;

import dev.lupluv.moveschem.commands.CopytoCmd;
import dev.lupluv.moveschem.utils.FileManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class MoveSchem extends JavaPlugin {

    @Override
    public void onEnable() {

        try {
            FileManager.getInstance().loadFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getCommand("copyto").setExecutor(new CopytoCmd());

    }

    @Override
    public void onDisable() {

    }

}
