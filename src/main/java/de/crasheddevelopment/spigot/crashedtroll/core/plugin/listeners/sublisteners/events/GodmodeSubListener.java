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
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GodmodeSubListener extends SubListener
{
    // Constructor.
    public GodmodeSubListener ()
    {
        super(new ListenerType[] {ListenerType.QUIT, ListenerType.DAMAGE});
    }

    // Damage event
    @Override
    public void onPlayerDamageEvent (EntityDamageEvent entityDamageEvent)
    {
        // Initialize variable.
        Player player = (Player) entityDamageEvent.getEntity();

        // If the player contains in the arraylist, it'll cancel the damage.
        if (Constants.GODMODE_ARRAYLIST.contains(player))
        {
            entityDamageEvent.setCancelled(true);
        }
    }

    // Quit event
    @Override
    public void onQuitEvent (PlayerQuitEvent playerQuitEvent)
    {
        // Initialize variable.
        Player player = playerQuitEvent.getPlayer();

        // Removes the player from the arraylist.
        Constants.GODMODE_ARRAYLIST.remove(player);
    }
}