package dev.lupluv.moveschem.commands;

import de.dytanic.cloudnet.common.io.FileUtils;
import dev.lupluv.moveschem.utils.FileManager;
import dev.lupluv.moveschem.utils.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class CopytoCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(commandSender instanceof Player){
            Player p = (Player) commandSender;
            if(!p.hasPermission("ms.copyto")){
                p.sendMessage(Strings.noPerms);
            }else{
                if(strings.length == 2){
                    String schematic = strings[0].replace(".schematic", "");
                    String server = strings[1];
                    File schematicFile = FileManager.getInstance().getSchematic(schematic);
                    if(schematicFile.exists()){
                        if(FileManager.getInstance().isServerExist(server)){
                            File copyToFolder = FileManager.getInstance().getCopyToFile(server, schematic);
                            Path from = schematicFile.toPath();
                            Path to = copyToFolder.toPath();
                            try {
                                FileUtils.copy(from, to);
                                p.sendMessage(Strings.prefix + "Die Schematic wurde erfolgreich kopiert!");
                            } catch (IOException e) {
                                p.sendMessage(Strings.prefix + "Beim kopieren der Schematic ist ein Fehler aufgetreten. Siehe Konsole für mehr Infos.");
                                e.printStackTrace();
                            }
                        }else{
                            p.sendMessage(Strings.prefix + "Der Server existiert nicht!");
                        }
                    }else{
                        p.sendMessage(Strings.prefix + "Diese Schematic existiert nicht!");
                    }
                }else{
                    p.sendMessage(Strings.prefix + "Benutzung: /copyto <schematic> <server>");
                }
            }
        }

        return false;
    }
}
