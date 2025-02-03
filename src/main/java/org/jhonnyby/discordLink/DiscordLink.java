package org.jhonnyby.discordLink;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class DiscordLink extends JavaPlugin {

    private FileConfiguration config;
    private static final String DEFAULT_PREFIX = "§9[Minedrop] §r";

    @Override
    public void onEnable() {
        // Load or create the config file
        saveDefaultConfig();
        config = getConfig();

        String prefix = config.getString("prefix", DEFAULT_PREFIX);
        getLogger().info(prefix + config.getString("messages.enable", "DiscordLink Plugin aktiviert!"));
    }

    @Override
    public void onDisable() {
        String prefix = config.getString("prefix", DEFAULT_PREFIX);
        getLogger().info(prefix + config.getString("messages.disable", "DiscordLink Plugin deaktiviert!"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ((command.getName().equalsIgnoreCase("discord") || command.getName().equalsIgnoreCase("dc"))) {
            String prefix = config.getString("prefix", DEFAULT_PREFIX);

            if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("discord.admin.reload")) {
                    reloadConfig();
                    config = getConfig();
                    sender.sendMessage(prefix + "Config wurde neu geladen!");
                    getLogger().info(prefix + "Config wurde neu geladen!");
                    return true;
                } else {
                    sender.sendMessage(prefix + "Du hast keine Berechtigung, diesen Befehl auszuführen!");
                    return true;
                }
            } else if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage(prefix + config.getString("messages.discord", "§aTritt unserem Discord bei: §ehttps://discord.gg/Wepk7v6d"));
                return true;
            }
        }
        return false;
    }
}
