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
import de.crasheddevelopment.spigot.crashedtroll.utils.Constants;
import de.crasheddevelopment.spigot.crashedtroll.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Collections;

public class GodmodeCommand extends SubCommand
{
    // Constructor.
    public GodmodeCommand ()
    {
        super("godmode", "You are the god!", "crashedtroll.permissions.troll.godmode", "godmode", "crashedtroll godmode", CrashedTroll.ITEM_MANAGER.createItem(Material.BEDROCK, "§cGodmode", Collections.singletonList("§eYou are the god!")), ItemInventoryType.OWN);
    }

    // Called method.
    @Override
    public void onCommand (Player player, String[] arguments)
    {
        // Check if the player contains in the godmode arraylist.
        if (!(Constants.GODMODE_ARRAYLIST.contains(player)))
        {
            // Adds the player to the godmode arraylist.
            Constants.GODMODE_ARRAYLIST.add(player);
            StringUtils.sendPlayerMessage(player, "§aYou have enabled the godmode!");
        }
        else
        {
            // Removes the player from the godmode arraylist.
            Constants.GODMODE_ARRAYLIST.remove(player);
            StringUtils.sendPlayerMessage(player, "§cYou have disabled the godmode!");
        }
    }
}