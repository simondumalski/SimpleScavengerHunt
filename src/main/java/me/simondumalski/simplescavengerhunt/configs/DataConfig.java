package me.simondumalski.simplescavengerhunt.configs;

import me.simondumalski.simplescavengerhunt.Main;
import me.simondumalski.simplescavengerhunt.utils.ConsoleMessage;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class DataConfig {

    private Main plugin;
    private File dataFile;
    private YamlConfiguration dataConfig;
    private ConfigurationSection dataSection;

    public DataConfig(Main plugin) {
        this.plugin = plugin;
    }

    /**
     * Saves the inventories in the savedInventory hashmap to file data.yml
     */
    public void saveInventories() {

        HashMap<UUID, ItemStack[]> savedInventories = plugin.getInventoryManager().getSavedInventories();
        HashMap<UUID, Integer> savedLevels = plugin.getInventoryManager().getSavedLevels();
        HashMap<UUID, Float> savedExperience = plugin.getInventoryManager().getSavedExperience();

        try {

            dataConfig.set("data", null);

            //Get the data configuration section, creating it if it doesn't already exist
            ConfigurationSection data;

            if (!dataConfig.isConfigurationSection("data")) {
                data = dataConfig.createSection("data");
            } else {
                data = dataConfig.getConfigurationSection("data");
            }

            if (data == null) {
                plugin.getMessageManager().sendConsoleMessage(ConsoleMessage.ERROR_GETTING_DATA_FILE);
                return;
            }

            for (UUID uuid : savedInventories.keySet()) {

                //Get the player's inventory
                ItemStack[] inventory = savedInventories.get(uuid);

                //Set the player's section in the data.yml to their items
                data.set(uuid + ".items", inventory);

            }

            for (UUID uuid : savedLevels.keySet()) {

                //Get the player's level
                int level = savedLevels.get(uuid);

                //Set the player's section in the data.yml to their level
                data.set(uuid + ".level", level);

            }

            for (UUID uuid : savedExperience.keySet()) {

                //Get the player's experience
                float experience = savedExperience.get(uuid);

                //Set the player's section in the data.yml to their experience
                data.set(uuid + ".experience", experience);

            }

            dataConfig.save(dataFile);

        } catch (Exception ex) {
            ex.printStackTrace();
            plugin.getMessageManager().sendConsoleMessage(ConsoleMessage.ERROR_SAVING_INVENTORIES);
        }

    }

    public HashMap<UUID, ItemStack[]> loadInventories() {

        HashMap<UUID, ItemStack[]> savedInventories = new HashMap<>();

        Set<String> keySet = dataSection.getKeys(false);

        if (keySet.isEmpty()) {
            plugin.getMessageManager().sendConsoleMessage(ConsoleMessage.NO_INVENTORIES);
            return null;
        }

        //41 inventory slots
        for (String key : keySet) {

            //Get the items from the data file as a list
            List<ItemStack> items = (List<ItemStack>) dataSection.getList(key + ".items");

            //Convert the list to an array
            ItemStack[] itemsArray = new ItemStack[41];

            for (int i = 0; i < itemsArray.length; i++) {
                itemsArray[i] = items.get(i);
            }

            //Add the inventory to the HashMap
            savedInventories.put(UUID.fromString(key), itemsArray);

        }

        return savedInventories;
    }

    public HashMap<UUID, Integer> loadLevels() {

        HashMap<UUID, Integer> savedLevels = new HashMap<>();

        Set<String> keySet = dataSection.getKeys(false);

        if (keySet.isEmpty()) {
            plugin.getMessageManager().sendConsoleMessage(ConsoleMessage.NO_LEVELS);
            return null;
        }

        for (String key : keySet) {

            int level = dataSection.getInt(key + ".level");
            savedLevels.put(UUID.fromString(key), level);

        }

        return savedLevels;
    }

    public HashMap<UUID, Float> loadExperience() {

        HashMap<UUID, Float> savedExperience = new HashMap<>();

        Set<String> keySet = dataSection.getKeys(false);

        if (keySet.isEmpty()) {
            plugin.getMessageManager().sendConsoleMessage(ConsoleMessage.NO_EXPERIENCE);
            return null;
        }

        for (String key : keySet) {

            float experience = (float) dataSection.getDouble(key + ".experience");
            savedExperience.put(UUID.fromString(key), experience);

        }

        return savedExperience;
    }

    /**
     * Creates the data.yml file for storing player's inventory contents
     */
    public void initiateFile() {

        try {

            File file = new File(plugin.getDataFolder(), "data.yml");

            if (file.createNewFile()) {
                plugin.getMessageManager().sendConsoleMessage(ConsoleMessage.CREATED_DATA_FILE);
            }

            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

            ConfigurationSection data;

            if (!config.isConfigurationSection("data")) {
                data = config.createSection("data");
            } else {
                data = config.getConfigurationSection("data");
            }

            config.save(file);

            this.dataFile = file;
            this.dataConfig = config;
            this.dataSection = data;

            plugin.getMessageManager().sendConsoleMessage(ConsoleMessage.LOADED_DATA_FILE);

        } catch (Exception ex) {
            ex.printStackTrace();
            plugin.getMessageManager().sendConsoleMessage(ConsoleMessage.ERROR_GETTING_DATA_FILE);
        }

    }

}
