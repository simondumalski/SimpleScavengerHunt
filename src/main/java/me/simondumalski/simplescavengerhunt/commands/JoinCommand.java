package me.simondumalski.simplescavengerhunt.commands;

import me.simondumalski.simplescavengerhunt.Main;
import me.simondumalski.simplescavengerhunt.utils.Message;
import me.simondumalski.simplescavengerhunt.utils.SubCommand;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;
import java.util.List;

public class JoinCommand extends SubCommand {

    private Main plugin;

    public JoinCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getCommand() {
        return "join";
    }

    @Override
    public String getDescription() {
        return "Join a scavenger hunt.";
    }

    @Override
    public String getUsage() {
        return "/scavengerhunt join";
    }

    @Override
    public String getPermission() {
        return "scavengerhunt.join";
    }

    @Override
    public boolean perform(Player p, String[] args) {

        Location joinLocation = plugin.getLocationManager().getJoinLocation();

        //Check if the join location is valid
        if (joinLocation == null) {
            plugin.getMessageManager().sendConfigMessage(p, Message.JOIN_LOCATION_NOT_SET);
            return true;
        }

        //Check if the player is in the scavenger hunt world
        if (p.getWorld() == joinLocation.getWorld()) {
            plugin.getMessageManager().sendConfigMessage(p, Message.ALREADY_IN_SCAVENGER_HUNT);
            return true;
        }

        //Teleport the player to the join location
        if (!p.teleport(joinLocation)) {
            plugin.getMessageManager().sendConfigMessage(p, Message.TELEPORT_FAILED);
            return true;
        }

        //Save the player's inventory and then clear it
        plugin.getInventoryManager().saveInventory(p);
        p.getInventory().clear();
        p.setLevel(0);
        p.setExp(0);

        //Clear the player's active potion effects
        Collection<PotionEffect> potionEffects = p.getActivePotionEffects();

        if (!potionEffects.isEmpty()) {
            for (PotionEffect potionEffect : potionEffects) {
                p.removePotionEffect(potionEffect.getType());
            }
        }

        plugin.getMessageManager().sendConfigMessage(p, Message.JOIN_SCAVENGER_HUNT);

        return true;
    }

}
