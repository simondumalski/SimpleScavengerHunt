package me.simondumalski.simplescavengerhunt.utils;

public enum ConsoleMessage {

    /* Plugin Utilities */
    HELP_HEADER("&8-----[ &6ScavengerHunt &8(&av%version%&8) ]-----"),
    HELP_FOOTER("&8----------------------------------"),

    /* Inventory Data File */
    ERROR_SAVING_INVENTORIES("&cError saving inventories to file data.yml!"),
    ERROR_SAVING_EXPERIENCE("&cError saving experience to file data.yml!"),
    ERROR_SAVING_LEVELS("&cError saving levels to file data.yml!"),
    ERROR_GETTING_DATA_FILE("&cError loading file data.yml!"),
    CREATED_DATA_FILE("&aSuccessfully created file data.yml!"),
    LOADED_DATA_FILE("&aSuccessfully loaded file data.yml!"),
    NO_INVENTORIES("&cNo inventories to load."),
    NO_EXPERIENCE("&cNo experience to load."),
    NO_LEVELS("&cNo levels to load."),

    /* Location Data File */
    CREATED_LOCATIONS_FILE("&aSuccessfully created file locations.yml!"),
    LOADED_LOCATIONS_FILE("&aSuccessfully loaded file locations.yml!"),
    ERROR_LOADING_LOCATIONS_FILE("&cError loading file locations.yml!"),

    NO_JOIN_LOCATION_LOADED("&cNo scavenger hunt join location was loaded."),
    NO_LEAVE_LOCATION_LOADED("&cNo scavenger hunt leave location was loaded."),

    NO_JOIN_LOCATION_SAVED("&cNo scavenger hunt join location was saved."),
    NO_LEAVE_LOCATION_SAVED("&cNo scavenger hunt leave location was saved."),

    ERROR_SAVING_LOCATIONS("&cError savings join/leave locations to file locations.yml!");


    private String message;

    ConsoleMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
