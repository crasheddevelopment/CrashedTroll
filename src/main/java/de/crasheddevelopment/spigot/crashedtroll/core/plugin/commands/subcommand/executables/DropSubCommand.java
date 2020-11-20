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

public class DropSubCommand extends SubCommand
{
    // Constructor.
    public DropSubCommand ()
    {
        super("drop", "Makes the target drop his item, which he hold in his hand!", "crashedtroll.permissions.troll.drop", "drop <Player>", "crashedtroll drop <Player>", CrashedTroll.ITEM_MANAGER.createItem(Material.PISTON_BASE, "§cDrop", Collections.singletonList("§eMakes the target drop his item, which he hold in his hand!")), ItemInventoryType.OTHER);
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
                    StringUtils.sendPlayerMessage(player, "§a" + targetPlayer.getName() + " drops his item now!");
                }
                else
                {
                    // Message if the player not has any valid item in his hand!
                    StringUtils.sendPlayerMessage(player, "§c" + targetPlayer.getName() + " didn't hold any item!");
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