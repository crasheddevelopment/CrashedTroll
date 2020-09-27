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

package de.crasheddevelopment.spigot.crashedtroll.core.plugin.listeners.sublisteners.events;

import de.crasheddevelopment.spigot.crashedtroll.core.plugin.listeners.sublisteners.SubListener;
import de.crasheddevelopment.spigot.crashedtroll.enums.ListenerType;
import de.crasheddevelopment.spigot.crashedtroll.utils.Constants;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class NoBlockBreakSubListener extends SubListener
{
    // Constructor.
    public NoBlockBreakSubListener ()
    {
        super(new ListenerType[] {ListenerType.BREAK, ListenerType.QUIT});
    }

    // Block break event.
    @Override
    public void onPlayerBlockBreakEvent (BlockBreakEvent blockBreakEvent)
    {
        // Initialize variable.
        Player player = blockBreakEvent.getPlayer();

        // Check if the player contains in the noblockbreak arraylist and cancel the block break if it's true.
        if (Constants.NO_BLOCK_BREAK_ARRAYLIST.contains(player))
            blockBreakEvent.setCancelled(true);
    }

    // Quit event.
    @Override
    public void onQuitEvent (PlayerQuitEvent playerQuitEvent)
    {
        // Initialize variable.
        Player player = playerQuitEvent.getPlayer();

        // Removes the player from the arraylist.
        Constants.NO_BLOCK_BREAK_ARRAYLIST.remove(player);
    }
}