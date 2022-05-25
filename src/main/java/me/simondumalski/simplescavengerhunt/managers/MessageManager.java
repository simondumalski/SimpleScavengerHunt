package me.simondumalski.simplescavengerhunt.managers;

import me.simondumalski.simplescavengerhunt.Main;
import me.simondumalski.simplescavengerhunt.utils.Message;
import me.simondumalski.simplescavengerhunt.utils.ConsoleMessage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageManager {

    private Main plugin;

    public MessageManager(Main plugin) {
        this.plugin = plugin;
    }

    public void sendHelpMessage(Player p) {

        String header = ConsoleMessage.HELP_HEADER.getMessage();

        if (header.contains("%version%")) {
            header = header.replace("%version%", plugin.getDescription().getVersion());
        }

        p.sendMessage(ChatColor.translateAlternateColorCodes('&', header));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/scavengerhunt join &8- &7Join the scavenger hunt."));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/scavengerhunt leave &8- &7Leave the scavenger hunt."));
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', ConsoleMessage.HELP_FOOTER.getMessage()));

    }

    public void sendConfigMessage(Player p, Message configValue) {

        String message = plugin.getConfig().getString(configValue.getConfigValue());

        if (message == null) {
            p.sendMessage(configValue.getConfigValue());
            return;
        }

        String prefix = plugin.getConfig().getString(Message.PREFIX.getConfigValue());

        if (prefix != null && message.contains("%prefix%")) {
            message = message.replace("%prefix%", prefix);
        }

        p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));

    }

    /**
     * Sends the specified message to console
     * @param consoleMessage Message to send
     */
    public void sendConsoleMessage(ConsoleMessage consoleMessage) {

        String message = consoleMessage.getMessage();
        System.out.println(ChatColor.translateAlternateColorCodes('&', message));

    }

}
