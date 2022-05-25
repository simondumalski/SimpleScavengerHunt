package me.simondumalski.simplescavengerhunt.listeners;

import me.simondumalski.simplescavengerhunt.Main;
import me.simondumalski.simplescavengerhunt.utils.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class CombatListener implements Listener {

    private Main plugin;

    public CombatListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCombat(EntityDamageByEntityEvent e) {

        try {

            //Check if the event occurred in the scavenger hunt world
            if (e.getDamager().getWorld() != plugin.getLocationManager().getJoinLocation().getWorld()) {
                return;
            }

            //Check if damager is a player
            if (e.getDamager() instanceof Player) {

                Player p = (Player) e.getDamager();
                e.setCancelled(true);
                plugin.getMessageManager().sendConfigMessage(p, Message.PVP_DISABLED);

            }

        } catch (NullPointerException ex) {

        }

    }

}
