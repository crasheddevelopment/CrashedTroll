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
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class TeamChatSubListener extends SubListener
{
    // Constructor.
    public TeamChatSubListener ()
    {
        super(new ListenerType[] {ListenerType.ASYNCPLAYERCHAT, ListenerType.QUIT});
    }

    // AsyncPlayerChat event.
    @Override
    public void onAsyncPlayerChatEvent (AsyncPlayerChatEvent asyncPlayerChatEvent)
    {
        // Initialize variables.
        Player player = asyncPlayerChatEvent.getPlayer();
        String message = asyncPlayerChatEvent.getMessage();

        // Check if the player contains in the team chat arraylist.
        if (Constants.TEAM_CHAT_ARRAYLIST.contains(player))
        {
            // Sends the team message.
            StringUtils.sendTeamMessage(player, message);
            asyncPlayerChatEvent.setCancelled(true);
        }
    }

    // Quit event
    @Override
    public void onQuitEvent (PlayerQuitEvent playerQuitEvent)
    {
        // Initialize variable.
        Player player = playerQuitEvent.getPlayer();

        // Check if the player contains in the team chat arraylist.
        if (Constants.TEAM_CHAT_ARRAYLIST.contains(player))
        {
            // Removes the player from the team chat arraylist.
            StringUtils.sendTeamBroadcast("§c" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("TEAM_CHAT_BROADCAST_LOGOUT").replace("{PLAYER_NAME}", player.getName()));
            Constants.TEAM_CHAT_ARRAYLIST.remove(player);
        }
    }
}