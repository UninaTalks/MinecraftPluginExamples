package org.unina.minecraft;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.unina.minecraft.commands.ExampleCommand;
import org.unina.minecraft.listeners.ExampleListener;
import org.unina.minecraft.placeholders.ExamplePlaceholdersExpansion;

public final class MinecraftExamplePlugin extends JavaPlugin {
    // Istanza della classe "main" del plugin usata quando registriamo comandi, listeners...
    private static MinecraftExamplePlugin instance;

    public static MinecraftExamplePlugin getInstance() {
        return instance;
    }

    /**
     * Questo metodo viene eseguito quando il plugin viene caricato (prima di essere abilitato)
     */
    @Override
    public void onLoad() {
        // Copia le configurazioni default del file all'interno della cartella "resources"
        getConfig().options().copyDefaults(true);
        // Salva il config default
        saveDefaultConfig();
    }

    /**
     * Questo metodo viene eseguito quando il plugin viene abilitato
     */
    @Override
    public void onEnable() {
        instance = this;

        // Registra il comando "/esempio ..." - RICORDA DI INSERIRLO NEL PLUGIN.YML!
        getCommand("esempio").setExecutor(new ExampleCommand());
        // Registra event listeners
        getServer().getPluginManager().registerEvents(new ExampleListener(), this);
        // Registra i placeholders di PlaceholderAPI (https://www.spigotmc.org/resources/placeholderapi.6245/)
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null)
            new ExamplePlaceholdersExpansion().register();

        Bukkit.getConsoleSender().sendMessage("§aPlugin avviato con successo.", "§aVersione: " + getDescription().getVersion());
    }

    /**
     * Questo metodo viene eseguito quando il plugin viene disabilitato
     */
    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§aPlugin disattivato con successo.");
    }
}
