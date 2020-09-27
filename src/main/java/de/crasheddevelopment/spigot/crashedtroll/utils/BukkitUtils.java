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

package de.crasheddevelopment.spigot.crashedtroll.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class BukkitUtils
{
    // Searching an empty slot in an inventory.
    public static int getEmptySlot (Inventory inventory)
    {
        for (int inventorySlot = 0; inventorySlot < inventory.getSize(); inventorySlot ++)
        {
            if (inventory.getItem(inventorySlot) == null)
            {
                return inventorySlot;
            }
        }
        return Integer.MIN_VALUE;
    }

    // Checks if a player is online via his player name.
    public static Player isPlayerOnline (String playerName)
    {
        for (Player player : Bukkit.getOnlinePlayers())
        {
            if (player.getName().equalsIgnoreCase(playerName))
                return player;
        }
        return null;
    }

    // Creates the inventory for multiple modes.
    public static Inventory getModesInventory (InventoryHolder inventoryHolder, InventoryType inventoryType, String title, ItemStack[] modesItemStack)
    {
        ArrayList<ItemStack> alreadyAddedModesItemStackArrayList = new ArrayList<>();
        Inventory inventory = Bukkit.createInventory(inventoryHolder, inventoryType, title);

        for (int inventorySlot = 0; inventorySlot < inventoryType.getDefaultSize(); inventorySlot ++)
        {
            for (ItemStack modeItemStack : modesItemStack)
            {
                if (!(alreadyAddedModesItemStackArrayList.contains(modeItemStack)))
                {
                    alreadyAddedModesItemStackArrayList.add(modeItemStack);
                    inventory.setItem(inventorySlot, modeItemStack);
                    break;
                }
            }
        }
        return inventory;
    }

    // Creates the inventory for online players.
    public static Inventory getOnlinePlayerInventory (InventoryHolder inventoryHolder, InventoryType inventoryType, String title)
    {
        ArrayList<Player> alreadyAddedPlayersArrayList = new ArrayList<>();
        Inventory inventory = Bukkit.createInventory(inventoryHolder, inventoryType, title);

        for (int inventorySlot = 0; inventorySlot < inventoryType.getDefaultSize(); inventorySlot ++)
        {
            for (Player player : Bukkit.getOnlinePlayers())
            {
                if (!(alreadyAddedPlayersArrayList.contains(player)))
                {
                    ItemStack skullItemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                    SkullMeta skullMeta = (SkullMeta) skullItemStack.getItemMeta();
                    skullMeta.setOwner(player.getName());
                    skullMeta.setDisplayName("§a" + player.getName());
                    skullItemStack.setItemMeta(skullMeta);

                    inventory.setItem(inventorySlot, skullItemStack);
                    alreadyAddedPlayersArrayList.add(player);
                    break;
                }
            }
        }
        return inventory;
    }
}