package me.simondumalski.simplescavengerhunt.listeners;

import me.simondumalski.simplescavengerhunt.Main;
import me.simondumalski.simplescavengerhunt.utils.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {

    private Main plugin;

    public CommandListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommandSend(PlayerCommandPreprocessEvent e) {

        try {

            Player p = e.getPlayer();
            String command = e.getMessage();

            //Check if the command was run in the scavenger hunt world
            if (p.getWorld() != plugin.getLocationManager().getJoinLocation().getWorld()) {
                return;
            }

            //Check if the player has the host permission
            if (p.hasPermission("scavengerhunt.host")) {
                return;
            }

            //Check if the command being run is the leave command
            if (command.equalsIgnoreCase("/scavengerhunt leave") || command.equalsIgnoreCase("/shunt leave")) {
                return;
            }

            //Block the command otherwise
            e.setCancelled(true);
            plugin.getMessageManager().sendConfigMessage(p, Message.COMMAND_BLOCKED);

        } catch (NullPointerException ex) {

        }

    }

}
