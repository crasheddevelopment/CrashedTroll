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
import de.crasheddevelopment.spigot.crashedtroll.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Collections;

public class DropSubCommand extends SubCommand
{
    // Constructor.
    public DropSubCommand ()
    {
        super("drop", CrashedTroll.LANGUAGE_MANAGER.getLanguageString("DROP_DESCRIPTION"), "crashedtroll.permissions.troll.drop", "drop <Player>", "crashedtroll drop <Player>", CrashedTroll.ITEM_MANAGER.createItem(Material.PISTON_BASE, "§cDrop", Collections.singletonList("§e" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("DROP_DESCRIPTION"))), ItemInventoryType.OTHER);
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
                // Checks if the player has an item in his hand, and if it is not equals with the material air.
                if ((targetPlayer.getItemInHand() != null) && (!(targetPlayer.getItemInHand().getType().equals(Material.AIR))))
                {
                    // Drops the item, and remove it from the inventory.
                    targetPlayer.getWorld().dropItemNaturally(targetPlayer.getLocation(), targetPlayer.getItemInHand()).setPickupDelay(40);
                    targetPlayer.getInventory().removeItem(targetPlayer.getItemInHand());
                    StringUtils.sendPlayerMessage(player, "§a" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("DROP_PLAYER_ITEM_DROP").replace("{PLAYER_NAME}", targetPlayer.getName()));
                }
                else
                {
                    // Message if the player not has any valid item in his hand!
                    StringUtils.sendPlayerMessage(player, "§c" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("DROP_PLAYER_NOT_HOLD_ANY_ITEM").replace("{PLAYER_NAME}", targetPlayer.getName()));
                }
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