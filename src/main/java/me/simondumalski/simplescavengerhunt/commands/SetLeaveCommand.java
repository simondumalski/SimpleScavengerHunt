package me.simondumalski.simplescavengerhunt.commands;

import me.simondumalski.simplescavengerhunt.Main;
import me.simondumalski.simplescavengerhunt.utils.Message;
import me.simondumalski.simplescavengerhunt.utils.SubCommand;
import org.bukkit.entity.Player;

public class SetLeaveCommand extends SubCommand {

    private Main plugin;

    public SetLeaveCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getCommand() {
        return "setleave";
    }

    @Override
    public String getDescription() {
        return "Set the spawn point for leaving a scavenger hunt.";
    }

    @Override
    public String getUsage() {
        return "/scavengerhunt setleave";
    }

    @Override
    public String getPermission() {
        return "scavengerhunt.admin";
    }

    @Override
    public boolean perform(Player p, String[] args) {

        plugin.getLocationManager().setLeaveLocation(p.getLocation());
        plugin.getMessageManager().sendConfigMessage(p, Message.LEAVE_LOCATION_SET);

        return true;
    }

}
