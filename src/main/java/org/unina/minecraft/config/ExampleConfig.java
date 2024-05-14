package org.unina.minecraft.config;

import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.unina.minecraft.MinecraftExamplePlugin;

// Tutorial su come integrare i file di configurazione nel proprio plugin: https://www.spigotmc.org/wiki/creating-a-config-file/
public class ExampleConfig {
    public void sendConfigMessageWithSound(Player player) {
        FileConfiguration config = MinecraftExamplePlugin.getInstance().getConfig();

        String content = config.getString("message.content");
        if (content == null)
            throw new RuntimeException("Non è stato specificato alcun messaggio nel config.yml");
        if (config.getBoolean("message.colored"))
            player.sendMessage("§a§l" + content);
        else
            player.sendMessage(content);


        String soundType = config.getString("sound-type");
        Sound sound;
        try {
            sound = Sound.valueOf(soundType);
            player.playSound(player.getLocation(), sound, 1F, 1F);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Tipo di suono sconosciuto: " + soundType);
        }
    }
}
