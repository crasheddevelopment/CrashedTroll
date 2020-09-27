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
import de.crasheddevelopment.spigot.crashedtroll.utils.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateSubListener extends SubListener
{
    // Constructor.
    public UpdateSubListener ()
    {
        super(new ListenerType[] {ListenerType.JOIN});
    }

    // Join event.
    @Override
    public void onJoinEvent (PlayerJoinEvent playerJoinEvent)
    {
        // Check if a update is available.
        if (Constants.UPDATE_AVAILABLE)
        {
            // Initialize variable.
            Player player = playerJoinEvent.getPlayer();

            // Check if the player has the permission.
            if (player.hasPermission("crashedtroll.permissions.update.notification"))
            {
                // Message for new a new update.
                StringUtils.sendPlayerMessage(player, "§aUpdate found! Please update this plugin to the latest version, for new cool features and improvements!");
            }
        }
    }
}