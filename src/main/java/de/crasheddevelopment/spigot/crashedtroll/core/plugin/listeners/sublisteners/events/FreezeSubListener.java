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
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class FreezeSubListener extends SubListener
{
    // Constructor.
    public FreezeSubListener ()
    {
        super(new ListenerType[] {ListenerType.MOVE, ListenerType.QUIT});
    }

    // Move event.
    @Override
    public void onMoveEvent (PlayerMoveEvent playerMoveEvent)
    {
        // Initialize variable.
        Player player = playerMoveEvent.getPlayer();

        // Check if the player contains in the freeze arraylist.
        if (Constants.FREEZE_ARRAYLIST.contains(player))
        {
            // Teleport the player to the old position.
            player.teleport(playerMoveEvent.getFrom());
            playerMoveEvent.setCancelled(true);
        }
    }

    // Quit event
    @Override
    public void onQuitEvent (PlayerQuitEvent playerQuitEvent)
    {
        // Initialize variable.
        Player player = playerQuitEvent.getPlayer();

        // Removes the player from the arraylist.
        Constants.FREEZE_ARRAYLIST.remove(player);
    }
}