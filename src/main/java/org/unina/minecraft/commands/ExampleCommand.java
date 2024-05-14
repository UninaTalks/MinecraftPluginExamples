package org.unina.minecraft.commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ExampleCommand implements CommandExecutor {
    /**
     * Metodo eseguito quando il plugin legge il comando inviato dal mittente
     * @param sender Mittente del comando (console o giocatore)
     * @param command Istanza del comando inviato
     * @param str Testo inviato in chat senza lo / (slash)
     * @param args Argomenti del comando
     * @return Ritorna sempre "true" per evitare il messaggio di errore standard di Bukkit
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String str, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§eCiao! Non hai specificato alcun argomento.");
            if (sender instanceof Player player)
                // Riproduci un suono
                // https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/entity/Player.html#playSound(org.bukkit.entity.Entity,org.bukkit.Sound,float,float)
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1F, 1F);
        } else {
            switch (args[0].toUpperCase()) {
                case "ARGOMENTO1":
                    // Controlla che il mittente abbia il permesso adatto per eseguire il comando
                    if (!sender.hasPermission("example.command.arg1")) {
                        sender.sendMessage("§cErrore non possiedi i permessi adatti!");
                        return true;
                    }
                    sender.sendMessage("§aComando 1 inviato correttamente!");
                    if (sender instanceof Player player)
                        // Invia un messaggio sullo schermo dell'utente
                        // https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/entity/Player.html#sendTitle(java.lang.String,java.lang.String,int,int,int)
                        player.sendTitle("TITOLO", "§a§lSottotitolo", 20, 60, 20);
                    break;
                case "ARGOMENTO2":
                    sender.sendMessage("§aComando 2 inviato correttamente!");
                    break;
                default:
                    sender.sendMessage("§cSotto-comando sconosciuto!");
            }
        }
        return true;
    }
}
