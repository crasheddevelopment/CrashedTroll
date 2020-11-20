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
import org.bukkit.event.player.PlayerQuitEvent;

public class ScreenSubListener extends SubListener
{
    // Constructor.
    public ScreenSubListener ()
    {
        super(new ListenerType[] {ListenerType.QUIT});
    }

    // Quit event.
    @Override
    public void onQuitEvent (PlayerQuitEvent playerQuitEvent)
    {
        Player player = playerQuitEvent.getPlayer();
        Constants.SCREEN_BLOCK_ARRAYLIST.remove(player);
    }
}