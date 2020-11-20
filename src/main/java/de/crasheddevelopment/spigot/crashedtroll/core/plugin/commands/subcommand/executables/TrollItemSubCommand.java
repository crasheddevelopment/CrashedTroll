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
import org.bukkit.entity.Player;

public class TrollItemSubCommand extends SubCommand
{
    // Constructor.
    public TrollItemSubCommand ()
    {
        super("item", CrashedTroll.LANGUAGE_MANAGER.getLanguageString("TROLL_ITEM_DESCRIPTION"), "crashedtroll.permissions.troll.trollitem", "item", "crashedtroll item", null, ItemInventoryType.NONE);
    }

    // Called method.
    @Override
    public void onCommand (Player player, String[] arguments)
    {
        // Check if the player have the troll item in his inventory.
        if (!(player.getInventory().contains(CrashedTroll.ITEM_MANAGER.getTrollItemStack())))
        {
            // Initialize variable.
            int inventorySlot = BukkitUtils.getEmptySlot(player.getInventory());

            // Check if the inventory slot is not Integer.MIN_VALUE.
            if (inventorySlot != Integer.MIN_VALUE)
            {
                // Sets the troll item to the inventory slot.
                player.getInventory().setItem(inventorySlot, CrashedTroll.ITEM_MANAGER.getTrollItemStack());
            }
            else
            {
                // Message if the inventory is full.
                StringUtils.sendPlayerMessage(player, "§c" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("INVENTORY_FULL"));
            }
        }
        else
        {
            // Message if the player has already this item.
            StringUtils.sendPlayerMessage(player, "§c" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("TROLL_ITEM_ALREADY_OWNED"));
        }
    }
}