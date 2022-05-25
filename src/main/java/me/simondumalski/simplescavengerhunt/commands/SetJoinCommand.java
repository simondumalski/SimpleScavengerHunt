package me.simondumalski.simplescavengerhunt.commands;

import me.simondumalski.simplescavengerhunt.Main;
import me.simondumalski.simplescavengerhunt.utils.Message;
import me.simondumalski.simplescavengerhunt.utils.SubCommand;
import org.bukkit.entity.Player;

public class SetJoinCommand extends SubCommand {

    private Main plugin;

    public SetJoinCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getCommand() {
        return "setjoin";
    }

    @Override
    public String getDescription() {
        return "Set the spawn point for joining a scavenger hunt.";
    }

    @Override
    public String getUsage() {
        return "/scavengerhunt setjoin";
    }

    @Override
    public String getPermission() {
        return "scavengerhunt.admin";
    }

    @Override
    public boolean perform(Player p, String[] args) {

        plugin.getLocationManager().setJoinLocation(p.getLocation());
        plugin.getMessageManager().sendConfigMessage(p, Message.JOIN_LOCATION_SET);

        return true;
    }

}
