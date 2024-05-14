package org.unina.minecraft.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.unina.minecraft.MinecraftExamplePlugin;

// PlaceholderAPI è un plugin che permette di utilizzare placeholders registrati dai plugin in altri plugin.
// Ad esempio, possiamo usare %example_name_colored%, registrato in questo plugin, nel plugin della scoreboard per
// mostrare il nome colorato del giocatore.
public class ExamplePlaceholdersExpansion extends PlaceholderExpansion {
    public String identifier = "example", author = "Lorenzo", version = MinecraftExamplePlugin.getInstance().getDescription().getVersion();

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getVersion() {
        return version;
    }

    public String onPlaceholderRequest(Player player, @NotNull String identifier) {
        if (player == null) return "";

        if (identifier.equalsIgnoreCase("level"))
            return String.valueOf(player.getLevel());

        if (identifier.equalsIgnoreCase("name"))
            return player.getName();

        if (identifier.equalsIgnoreCase("name_colored"))
            return "§d§l" + player.getName();

        return null;
    }
}
