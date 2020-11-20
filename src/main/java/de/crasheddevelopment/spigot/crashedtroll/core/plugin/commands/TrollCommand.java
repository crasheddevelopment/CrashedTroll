/*
 * Copyright © 2020 CrashedDevelopment
 *
 * This is a non-commercial project.
 * All rights belong to their respective owners.
 * You are allowed to change the software as long as no illegal content is created and / or distributed.
 * More information is available in PROJECT_LICENSE.txt!
 *
 * For news, updates and support:
 * https://discord.gg/CBkXXKa
 */

package de.crasheddevelopment.spigot.crashedtroll.core.plugin.commands;

import de.crasheddevelopment.spigot.crashedtroll.CrashedTroll;
import de.crasheddevelopment.spigot.crashedtroll.core.plugin.commands.subcommand.SubCommand;
import de.crasheddevelopment.spigot.crashedtroll.utils.Constants;
import de.crasheddevelopment.spigot.crashedtroll.utils.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TrollCommand implements CommandExecutor
{
    // Called command executor method.
    @Override
    public boolean onCommand (CommandSender commandSender, Command command, String string, String[] arguments)
    {
        // Checks if the commandSender is a player.
        if (commandSender instanceof Player)
        {
            // Initialize variable.
            Player player = (Player) commandSender;

            // Check if the player has the permission "crashedtroll.permissions.troll"
            if (player.hasPermission("crashedtroll.permissions.troll"))
            {
                // Check if the arguments is more than 1
                if (arguments.length >= 1)
                {
                    // If the argument is "help" it'll display the help for the commands.
                    if (arguments[0].equalsIgnoreCase("help"))
                    {
                        for (SubCommand subCommand : CrashedTroll.SUB_COMMAND_MANAGER.getSubCommandArrayList())
                        {
                            StringUtils.sendPlayerMessage(player, "§c/ct " + subCommand.getCommandSyntax() + " §7| §e" + subCommand.getCommandDescription());
                        }

                        return true;
                    }

                    // Calling subcommands.
                    for (SubCommand subCommand : CrashedTroll.SUB_COMMAND_MANAGER.getSubCommandArrayList())
                    {
                        // If the argument matches with the subcommand it'll check if the player has the permission.
                        if (arguments[0].equalsIgnoreCase(subCommand.getCommandName()))
                        {
                            // If the player has the permission, it'll execute the onCommand method.
                            if (player.hasPermission(subCommand.getCommandPermission()))
                            {
                                // onCommand method.
                                subCommand.onCommand(player, arguments);
                            }
                            else
                            {
                                // Message if the player has no permissions.
                                player.sendMessage("§c" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("NO_PERMISSIONS"));
                            }
                            return true;
                        }
                    }

                    // Message if the command not found.
                    StringUtils.sendPlayerMessage(player, "§c" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("COMMAND_NOT_FOUND"));
                    StringUtils.sendPlayerMessage(player, "§c" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("HELP_MESSAGE"));
                }
                else
                {
                    // Version info.
                    StringUtils.sendPlayerMessage(player, "§aCrashedTroll §ev" + Constants.VERSION + " §7| §eb" + Constants.BUILD + " §edeveloped by CrashedDevelopment");
                }
            }
            else
            {
                // Version info.
                StringUtils.sendPlayerMessage(player, "§aCrashedTroll §ev" + Constants.VERSION + " §7| §eb" + Constants.BUILD + " §edeveloped by CrashedDevelopment");
            }
        }
        return true;
    }
}