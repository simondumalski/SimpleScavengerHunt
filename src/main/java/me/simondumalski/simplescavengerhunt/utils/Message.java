package me.simondumalski.simplescavengerhunt.utils;

public enum Message {

    PREFIX("messages.plugin.prefix"),

    INSUFFICIENT_PERMISSIONS("messages.errors.insufficient-permissions"),
    UNKNOWN_COMMAND("messages.errors.unknown-command"),

    COMMAND_BLOCKED("messages.errors.command-blocked"),
    TELEPORT_FAILED("messages.errors.teleport-failed"),

    JOIN_LOCATION_NOT_SET("messages.errors.no-join-location"),
    LEAVE_LOCATION_NOT_SET("messages.errors.no-leave-location"),

    ALREADY_IN_SCAVENGER_HUNT("messages.errors.already-in-scavenger-hunt"),
    NOT_IN_SCAVENGER_HUNT("messages.errors.not-in-scavenger-hunt"),

    PVP_DISABLED("messages.error.pvp-disabled"),

    JOIN_LOCATION_SET("messages.commands.join-location-set"),
    LEAVE_LOCATION_SET("messages.commands.leave-location-set"),

    JOIN_SCAVENGER_HUNT("messages.commands.join-scavenger-hunt"),
    LEAVE_SCAVENGER_HUNT("messages.commands.leave-scavenger-hunt");

    private String configValue;

    Message(String configValue) {
        this.configValue = configValue;
    }

    public String getConfigValue() {
        return configValue;
    }

}
