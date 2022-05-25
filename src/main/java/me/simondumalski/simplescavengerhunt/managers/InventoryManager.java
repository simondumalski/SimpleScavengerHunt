package me.simondumalski.simplescavengerhunt.managers;

import me.simondumalski.simplescavengerhunt.Main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.UUID;

public class InventoryManager {

    private Main plugin;
    private HashMap<UUID, ItemStack[]> savedInventories;
    private HashMap<UUID, Float> savedExperience;
    private HashMap<UUID, Integer> savedLevels;

    public InventoryManager(Main plugin) {
        this.plugin = plugin;
    }

    public HashMap<UUID, ItemStack[]> getSavedInventories() {
        return savedInventories;
    }

    public HashMap<UUID, Integer> getSavedLevels() {
        return savedLevels;
    }

    public HashMap<UUID, Float> getSavedExperience() {
        return savedExperience;
    }

    public void saveInventory(Player p) {

        //Get the player's UUID and inventory
        UUID uuid = p.getUniqueId();
        PlayerInventory inventory = p.getInventory();

        //Add the inventory to the hashmap
        savedInventories.put(uuid, inventory.getContents());
        savedLevels.put(uuid, p.getLevel());
        savedExperience.put(uuid, p.getExp());

        //Save all the inventories to the data.yml
        plugin.getDataConfig().saveInventories();

    }

    public void loadInventory(Player p) {

        //Get the items in the players inventory from the saved inventories list
        ItemStack[] savedInventory = savedInventories.get(p.getUniqueId());
        int level = savedLevels.get(p.getUniqueId());
        float experience = savedExperience.get(p.getUniqueId());

        //Set the player's inventory to the saved inventory
        p.getInventory().setContents(savedInventory);
        p.setLevel(level);
        p.setExp(experience);

        //Delete the saved inventory from the list
        savedInventories.remove(p.getUniqueId());
        savedLevels.remove(p.getUniqueId());
        savedExperience.remove(p.getUniqueId());

    }

    public void loadInventoriesFromFile() {

        HashMap<UUID, ItemStack[]> temp = plugin.getDataConfig().loadInventories();
        HashMap<UUID, Integer> temp2 = plugin.getDataConfig().loadLevels();
        HashMap<UUID, Float> temp3 = plugin.getDataConfig().loadExperience();

        if (temp == null || temp.isEmpty()) {
            savedInventories = new HashMap<>();
        } else {
            savedInventories = temp;
        }

        if (temp2 == null || temp.isEmpty()) {
            savedLevels = new HashMap<>();
        } else {
            savedLevels = temp2;
        }

        if (temp3 == null || temp3.isEmpty()) {
            savedExperience = new HashMap<>();
        } else {
            savedExperience = temp3;
        }

    }

}
