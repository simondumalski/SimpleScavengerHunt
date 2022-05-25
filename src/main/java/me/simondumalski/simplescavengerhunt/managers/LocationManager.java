package me.simondumalski.simplescavengerhunt.managers;

import me.simondumalski.simplescavengerhunt.Main;
import org.bukkit.Location;

public class LocationManager {

    private Main plugin;
    private Location joinLocation;
    private Location leaveLocation;

    public LocationManager(Main plugin) {
        this.plugin = plugin;
    }

    public Location getJoinLocation() {
        return joinLocation;
    }

    public void setJoinLocation(Location joinLocation) {
        this.joinLocation = joinLocation;
    }

    public Location getLeaveLocation() {
        return leaveLocation;
    }

    public void setLeaveLocation(Location leaveLocation) {
        this.leaveLocation = leaveLocation;
    }

}
