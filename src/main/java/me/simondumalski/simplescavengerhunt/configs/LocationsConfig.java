package me.simondumalski.simplescavengerhunt.configs;

import me.simondumalski.simplescavengerhunt.Main;
import me.simondumalski.simplescavengerhunt.utils.ConsoleMessage;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LocationsConfig {

    private Main plugin;
    private File locationsFile;
    private YamlConfiguration locationsConfig;
    private ConfigurationSection locationsSection;

    public LocationsConfig(Main plugin) {
        this.plugin = plugin;
    }

    public void loadLocations() {

        Location joinLocation = locationsSection.getLocation("join");
        Location leaveLocation = locationsSection.getLocation("leave");

        if (joinLocation == null) {
            plugin.getMessageManager().sendConsoleMessage(ConsoleMessage.NO_JOIN_LOCATION_LOADED);
        } else {
            plugin.getLocationManager().setJoinLocation(joinLocation);
        }

        if (leaveLocation == null) {
            plugin.getMessageManager().sendConsoleMessage(ConsoleMessage.NO_LEAVE_LOCATION_LOADED);
        } else {
            plugin.getLocationManager().setLeaveLocation(leaveLocation);
        }

    }

    public void saveLocations() {

        try {

            Location joinLocation = plugin.getLocationManager().getJoinLocation();
            Location leaveLocation = plugin.getLocationManager().getLeaveLocation();

            if (joinLocation == null) {
                plugin.getMessageManager().sendConsoleMessage(ConsoleMessage.NO_JOIN_LOCATION_SAVED);
            } else {
                locationsSection.set("join", joinLocation);
            }

            if (leaveLocation == null) {
                plugin.getMessageManager().sendConsoleMessage(ConsoleMessage.NO_LEAVE_LOCATION_SAVED);
            } else {
                locationsSection.set("leave", leaveLocation);
            }

            locationsConfig.save(locationsFile);

        } catch (Exception ex) {
            ex.printStackTrace();
            plugin.getMessageManager().sendConsoleMessage(ConsoleMessage.ERROR_SAVING_LOCATIONS);
        }

    }

    public void initiateFile() {

        try {

            File file = new File(plugin.getDataFolder(), "locations.yml");

            if (file.createNewFile()) {
                plugin.getMessageManager().sendConsoleMessage(ConsoleMessage.CREATED_LOCATIONS_FILE);
            }

            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

            ConfigurationSection locations;

            if (!config.isConfigurationSection("locations")) {
                locations = config.createSection("locations");
            } else {
                locations = config.getConfigurationSection("locations");
            }

            config.save(file);

            this.locationsFile = file;
            this.locationsConfig = config;
            this.locationsSection = locations;

            plugin.getMessageManager().sendConsoleMessage(ConsoleMessage.LOADED_LOCATIONS_FILE);


        } catch (Exception ex) {
            ex.printStackTrace();
            plugin.getMessageManager().sendConsoleMessage(ConsoleMessage.ERROR_LOADING_LOCATIONS_FILE);
        }

    }

}
