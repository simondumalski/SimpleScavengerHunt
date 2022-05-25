package me.simondumalski.simplescavengerhunt.commands;

import me.simondumalski.simplescavengerhunt.Main;
import me.simondumalski.simplescavengerhunt.utils.Message;
import me.simondumalski.simplescavengerhunt.utils.SubCommand;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class LeaveCommand extends SubCommand {

    private Main plugin;

    public LeaveCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getCommand() {
        return "leave";
    }

    @Override
    public String getDescription() {
        return "Leave the scavenger hunt";
    }

    @Override
    public String getUsage() {
        return "/scavengerhunt leave";
    }

    @Override
    public String getPermission() {
        return "scavengerhunt.join";
    }

    @Override
    public boolean perform(Player p, String[] args) {

        Location joinLocation = plugin.getLocationManager().getJoinLocation();
        Location leaveLocation = plugin.getLocationManager().getLeaveLocation();

        //Check if the leave location is valid
        if (leaveLocation == null) {
            plugin.getMessageManager().sendConfigMessage(p, Message.LEAVE_LOCATION_NOT_SET);
            return true;
        }

        //Check if the player is in the scavenger hunt world
        if (p.getWorld() != joinLocation.getWorld()) {
            plugin.getMessageManager().sendConfigMessage(p, Message.NOT_IN_SCAVENGER_HUNT);
            return true;
        }

        //Teleport the player to the leave location
        if (!p.teleport(leaveLocation)) {
            plugin.getMessageManager().sendConfigMessage(p, Message.TELEPORT_FAILED);
            return true;
        }

        //Clear the player's inventory and then restore it
        p.getInventory().clear();
        plugin.getInventoryManager().loadInventory(p);

        plugin.getMessageManager().sendConfigMessage(p, Message.LEAVE_SCAVENGER_HUNT);

        return true;
    }

}
