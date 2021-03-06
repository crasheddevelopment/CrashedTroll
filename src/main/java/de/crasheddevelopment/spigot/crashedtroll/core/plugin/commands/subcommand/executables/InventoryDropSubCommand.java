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
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

public class InventoryDropSubCommand extends SubCommand
{
    // Constructor.
    public InventoryDropSubCommand ()
    {
        super("inventorydrop", CrashedTroll.LANGUAGE_MANAGER.getLanguageString("INVENTORY_DROP_DESCRIPTION"), "crashedtroll.permissions.troll.inventorydrop", "inventorydrop <Player>", "crashedtroll inventorydrop <Player>", CrashedTroll.ITEM_MANAGER.createItem(Material.PISTON_STICKY_BASE, "§cInventory Drop", Collections.singletonList("§e" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("INVENTORY_DROP_DESCRIPTION"))), ItemInventoryType.OTHER);
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
                // Checks the inventory content for an available itemstack and drops it.
                for (ItemStack itemStack : targetPlayer.getInventory().getContents())
                {
                    if ((itemStack != null) && !(itemStack.getType().equals(Material.AIR)))
                    {
                        targetPlayer.getWorld().dropItemNaturally(targetPlayer.getLocation(), itemStack).setPickupDelay(40);
                        targetPlayer.getInventory().removeItem(itemStack);
                    }
                }

                // Checks the inventory armor content for an available itemstack and drops it.
                for (ItemStack itemStack : targetPlayer.getInventory().getArmorContents())
                {
                    if ((itemStack != null) && !(itemStack.getType().equals(Material.AIR)))
                    {
                        targetPlayer.getWorld().dropItemNaturally(targetPlayer.getLocation(), itemStack).setPickupDelay(40);
                        targetPlayer.getInventory().removeItem(itemStack);
                    }
                }

                // Message.
                StringUtils.sendPlayerMessage(player, "§a" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("INVENTORY_DROP_TARGET_DROP").replace("{PLAYER_NAME}", targetPlayer.getName()));
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