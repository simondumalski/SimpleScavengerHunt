package me.simondumalski.simplescavengerhunt;

import me.simondumalski.simplescavengerhunt.configs.DataConfig;
import me.simondumalski.simplescavengerhunt.configs.LocationsConfig;
import me.simondumalski.simplescavengerhunt.listeners.CombatListener;
import me.simondumalski.simplescavengerhunt.listeners.CommandListener;
import me.simondumalski.simplescavengerhunt.managers.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private MessageManager messageManager = new MessageManager(this);
    private DataConfig dataConfig = new DataConfig(this);
    private InventoryManager inventoryManager = new InventoryManager(this);
    private LocationsConfig locationsConfig = new LocationsConfig(this);
    private LocationManager locationManager = new LocationManager(this);

    @Override
    public void onEnable() {

        //Create the default config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Initialize file data.yml and load the saved inventories
        dataConfig.initiateFile();
        inventoryManager.loadInventoriesFromFile();

        //Initialize file locations.yml and load the saved locations
        locationsConfig.initiateFile();
        locationsConfig.loadLocations();

        //Register the event listeners
        getServer().getPluginManager().registerEvents(new CommandListener(this), this);
        getServer().getPluginManager().registerEvents(new CombatListener(this), this);

        //Register the commands
        getCommand("scavengerhunt").setExecutor(new CommandManager(this));

    }

    @Override
    public void onDisable() {

        dataConfig.saveInventories();
        locationsConfig.saveLocations();

    }

    public MessageManager getMessageManager() {
        return messageManager;
    }

    public DataConfig getDataConfig() {
        return dataConfig;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public LocationManager getLocationManager() {
        return locationManager;
    }

}
