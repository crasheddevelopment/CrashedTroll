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

public class InventoryDropSubCommand extends SubCommand
{
    // Constructor.
    public InventoryDropSubCommand ()
    {
        super("inventorydrop", "Drops from the target the whole inventory!", "crashedtroll.permissions.troll.inventorydrop", "inventorydrop <Player>", "crashedtroll inventorydrop <Player>", CrashedTroll.ITEM_MANAGER.createItem(Material.PISTON_STICKY_BASE, "§cInventory Drop", Collections.singletonList("§eDrops from the target the whole inventory!")), ItemInventoryType.OTHER);
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
                // Checks every slot in the inventory, and if it's contain a valid item, it will be automatically dropped.
                for (int inventorySlot = 0; inventorySlot < targetPlayer.getInventory().getSize(); inventorySlot ++)
                {
                    if ((targetPlayer.getInventory().getItem(inventorySlot) != null) && (!(targetPlayer.getInventory().getItem(inventorySlot).getType().equals(Material.AIR))))
                    {
                        targetPlayer.getWorld().dropItemNaturally(targetPlayer.getLocation(), targetPlayer.getInventory().getItem(inventorySlot)).setPickupDelay(40);
                        targetPlayer.getInventory().removeItem(targetPlayer.getInventory().getItem(inventorySlot));
                    }
                }

                StringUtils.sendPlayerMessage(player, "§a" + targetPlayer.getName() + " drops his whole inventory!");
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