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

package de.crasheddevelopment.spigot.crashedtroll.core.plugin.threads;

import de.crasheddevelopment.spigot.crashedtroll.CrashedTroll;
import de.crasheddevelopment.spigot.crashedtroll.utils.Constants;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PluginThread
{
    // Initialize variable.
    private int bukkitScheduler = Integer.MIN_VALUE;

    // Starts the plugin thread.
    public void startThread ()
    {
        this.bukkitScheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(CrashedTroll.getPlugin(CrashedTroll.class), () ->
        {
            // Closes the inventories from the players.
            for (Player player : Constants.SCREEN_BLOCK_ARRAYLIST)
            {
                player.closeInventory();
            }

            // Heals and feed the players.
            for (Player player : Constants.GODMODE_ARRAYLIST)
            {
                player.setHealth(20);
                player.setFoodLevel(20);
            }
        }, 11, 11);
    }

    // Stops the plugin thread.
    public void stopThread ()
    {
        if (this.bukkitScheduler != Integer.MIN_VALUE)
        {
            Bukkit.getScheduler().cancelTask(this.bukkitScheduler);
        }
    }
}