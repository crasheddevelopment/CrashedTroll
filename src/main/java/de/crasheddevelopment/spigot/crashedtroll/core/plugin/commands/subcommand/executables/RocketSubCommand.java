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

package de.crasheddevelopment.spigot.crashedtroll.core.plugin.commands.subcommand.executables;

import de.crasheddevelopment.spigot.crashedtroll.CrashedTroll;
import de.crasheddevelopment.spigot.crashedtroll.core.plugin.commands.subcommand.SubCommand;
import de.crasheddevelopment.spigot.crashedtroll.enums.ItemInventoryType;
import de.crasheddevelopment.spigot.crashedtroll.utils.BukkitUtils;
import de.crasheddevelopment.spigot.crashedtroll.utils.Constants;
import de.crasheddevelopment.spigot.crashedtroll.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Collections;

public class RocketSubCommand extends SubCommand
{
    // Constructor.
    public RocketSubCommand ()
    {
        super("rocket", CrashedTroll.LANGUAGE_MANAGER.getLanguageString("ROCKET_DESCRIPTION"), "crashedtroll.permissions.troll.rocket", "rocket <Player>", "crashedtroll rocket <Player>", CrashedTroll.ITEM_MANAGER.createItem(Material.FIREWORK, "§cRocket", Collections.singletonList("§e" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("ROCKET_DESCRIPTION"))), ItemInventoryType.OTHER);
    }

    // Called method.
    @Override
    public void onCommand (Player player, String[] arguments)
    {
        // Checks if the command arguments equals two.
        if (arguments.length == 2)
        {
            // Initialize variable.
            Player targetPlayer = BukkitUtils.isPlayerOnline(arguments[1].trim());

            // Check if the player is online or not.
            if (targetPlayer != null)
            {
                // Boosts the target in the air.
                targetPlayer.setAllowFlight(true);
                targetPlayer.setVelocity(targetPlayer.getLocation().getDirection().multiply(0.5).setY(3.8));
                targetPlayer.playSound(targetPlayer.getLocation(), Sound.BURP, 100, 25);
                targetPlayer.setAllowFlight(false);
                StringUtils.sendPlayerMessage(player, "§a" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("ROCKET_PLAYER_LAUNCH").replace("{PLAYER_NAME}", targetPlayer.getName()));
            }
            else
            {
                // Message if the player is offline.
                player.sendMessage("§c" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("PLAYER_OFFLINE"));
            }
        }
        else
        {
            // Invalid arguments message.
            StringUtils.sendPlayerMessage(player, "§c" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("INVALID_COMMAND_ARGUMENTS"));
            StringUtils.sendPlayerMessage(player, "§c" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("COMMAND_SYNTAX_MESSAGE").replace("{SYNTAX}", this.getCommandSyntax()));
        }
    }
}