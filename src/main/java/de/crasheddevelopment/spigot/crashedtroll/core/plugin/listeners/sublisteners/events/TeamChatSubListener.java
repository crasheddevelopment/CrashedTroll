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
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class TeamChatSubListener extends SubListener
{
    public TeamChatSubListener ()
    {
        super(new ListenerType[] {ListenerType.ASYNCPLAYERCHAT, ListenerType.QUIT});
    }

    @Override
    public void onAsyncPlayerChatEvent (AsyncPlayerChatEvent asyncPlayerChatEvent)
    {
        Player player = asyncPlayerChatEvent.getPlayer();
        String message = asyncPlayerChatEvent.getMessage();

        if (Constants.TEAM_CHAT_ARRAYLIST.contains(player))
        {
            StringUtils.sendTeamMessage(player, message);
            asyncPlayerChatEvent.setCancelled(true);
        }
    }

    @Override
    public void onQuitEvent (PlayerQuitEvent playerQuitEvent)
    {
        Player player = playerQuitEvent.getPlayer();

        if (Constants.TEAM_CHAT_ARRAYLIST.contains(player))
        {
            StringUtils.sendTeamBroadcast("§c" + player.getName() + " logged out from the teamchat!");
            Constants.TEAM_CHAT_ARRAYLIST.remove(player);
        }
    }
}