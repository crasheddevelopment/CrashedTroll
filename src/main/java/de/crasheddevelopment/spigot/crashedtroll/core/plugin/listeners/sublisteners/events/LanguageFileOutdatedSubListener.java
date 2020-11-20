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

import de.crasheddevelopment.spigot.crashedtroll.CrashedTroll;
import de.crasheddevelopment.spigot.crashedtroll.core.plugin.listeners.sublisteners.SubListener;
import de.crasheddevelopment.spigot.crashedtroll.enums.ListenerType;
import de.crasheddevelopment.spigot.crashedtroll.utils.Constants;
import de.crasheddevelopment.spigot.crashedtroll.utils.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

public class LanguageFileOutdatedSubListener extends SubListener
{
    // Constructor.
    public LanguageFileOutdatedSubListener ()
    {
        super(new ListenerType[] {ListenerType.JOIN});
    }

    // Join event.
    @Override
    public void onJoinEvent (PlayerJoinEvent playerJoinEvent)
    {
        // Initialize variable.
        Player player = playerJoinEvent.getPlayer();

        // Check if the player has the permission and if the language file is outdated.
        if (player.hasPermission("crashedtroll.permissions.language.outdated"))
        {
            if (Constants.LANGUAGE_FILE_OUTDATED)
            {
                // Shows message.
                StringUtils.sendPlayerMessage(player, "§c" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("LANGUAGE_FILE_OUTDATED"));
            }
        }
    }
}