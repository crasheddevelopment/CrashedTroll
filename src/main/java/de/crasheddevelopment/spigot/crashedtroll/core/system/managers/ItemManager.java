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

package de.crasheddevelopment.spigot.crashedtroll.core.system.managers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

public class ItemManager
{
    // Initialize variable.
    private final ItemStack trollItemStack = this.createItem(Material.CAKE, "§cTroll Item", Collections.singletonList("§bItem to troll other players."));

    // Creates an item.
    public ItemStack createItem (Material material, String itemStackName, List<String> lore)
    {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(itemStackName);

        if (lore != null)
        {
            itemMeta.setLore(lore);
        }

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    // Returns the troll item.
    public ItemStack getTrollItemStack ()
    {
        return this.trollItemStack;
    }
}