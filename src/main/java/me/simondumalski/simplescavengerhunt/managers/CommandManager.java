package me.simondumalski.simplescavengerhunt.managers;

import me.simondumalski.simplescavengerhunt.Main;
import me.simondumalski.simplescavengerhunt.commands.JoinCommand;
import me.simondumalski.simplescavengerhunt.commands.LeaveCommand;
import me.simondumalski.simplescavengerhunt.commands.SetJoinCommand;
import me.simondumalski.simplescavengerhunt.commands.SetLeaveCommand;
import me.simondumalski.simplescavengerhunt.utils.Message;
import me.simondumalski.simplescavengerhunt.utils.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements TabExecutor {

    private Main plugin;
    private List<SubCommand> subCommands = new ArrayList<>();

    public CommandManager(Main plugin) {
        this.plugin = plugin;

        subCommands.add(new JoinCommand(plugin));
        subCommands.add(new LeaveCommand(plugin));
        subCommands.add(new SetJoinCommand(plugin));
        subCommands.add(new SetLeaveCommand(plugin));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            //Check if the player is running a subcommand
            if (args.length < 1) {
                plugin.getMessageManager().sendHelpMessage(p);
                return true;
            }

            //Loop through the subcommands to find a matching one
            for (SubCommand subCommand : subCommands) {
                if (args[0].equalsIgnoreCase(subCommand.getCommand()) && p.hasPermission(subCommand.getPermission())) {
                    return subCommand.perform(p, args);
                }
            }

            //If no matching subcommand was found, send the unknown command message
            plugin.getMessageManager().sendConfigMessage(p, Message.UNKNOWN_COMMAND);

        } else {

            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;

        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        List<String> arguments = new ArrayList<>();

        if (args.length == 1) {
            arguments.add("join");
            arguments.add("leave");

            if (sender.hasPermission("scavengerhunt.admin")) {
                arguments.add("setjoin");
                arguments.add("setleave");
            }
        }

        return arguments;
    }

}
