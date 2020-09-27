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
import org.bukkit.entity.Player;

import java.util.Collections;

public class FreezeSubCommand extends SubCommand
{
    // Constructor.
    public FreezeSubCommand ()
    {
        super("freeze", "Freezes the target!", "crashedtroll.permissions.troll.freeze", "freeze <Player>", "crashedtroll freeze <Player>", CrashedTroll.ITEM_MANAGER.createItem(Material.PACKED_ICE, "§cFreeze", Collections.singletonList("§eFreezes the target!")), ItemInventoryType.OTHER);
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
                // Check if the player contains in the freeze arraylist.
                if (!(Constants.FREEZE_ARRAYLIST.contains(targetPlayer)))
                {
                    // Adds target to the freeze arraylist.
                    Constants.FREEZE_ARRAYLIST.add(targetPlayer);
                    StringUtils.sendPlayerMessage(player, "§a" + targetPlayer.getName() + " is now frozen!");
                }
                else
                {
                    // Removes target from the freeze arraylist.
                    Constants.FREEZE_ARRAYLIST.remove(targetPlayer);
                    StringUtils.sendPlayerMessage(player, "§c" + targetPlayer.getName() + " isn't frozen anymore!");
                }
            }
            else
            {
                // Message if the player is offline.
                player.sendMessage(Constants.PLAYER_OFFLINE);
            }
        }
        else
        {
            // Invalid arguments message.
            StringUtils.sendPlayerMessage(player, "§cInvalid command arguments!");
            StringUtils.sendPlayerMessage(player, "§c/ct " + this.getCommandSyntax());
        }
    }
}