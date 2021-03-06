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

public class TeamChatSubCommand extends SubCommand
{
    // Constructor.
    public TeamChatSubCommand ()
    {
        super("teamchat", CrashedTroll.LANGUAGE_MANAGER.getLanguageString("TEAM_CHAT_DESCRIPTION"), "crashedtroll.permissions.troll.teamchat", "teamchat", "crashedtroll teamchat", CrashedTroll.ITEM_MANAGER.createItem(Material.COMMAND, "§cTeamchat", Collections.singletonList("§e" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("TEAM_CHAT_DESCRIPTION"))), ItemInventoryType.OWN);
    }

    // Called method.
    @Override
    public void onCommand (Player player, String[] arguments)
    {
        // Checks if the player contains the teamchat arraylist.
        if (!(Constants.TEAM_CHAT_ARRAYLIST.contains(player)))
        {
            // Adds the player to the teamchat arraylist.
            StringUtils.sendTeamBroadcast("§a" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("TEAM_CHAT_BROADCAST_LOGIN").replace("{PLAYER_NAME}", player.getName()));
            Constants.TEAM_CHAT_ARRAYLIST.add(player);
            StringUtils.sendPlayerMessage(player, "§a" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("TEAM_CHAT_PLAYER_LOGIN"));
        }
        else
        {
            // Removes the player from the teamchat arraylist.
            Constants.TEAM_CHAT_ARRAYLIST.remove(player);
            StringUtils.sendTeamBroadcast("§c" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("TEAM_CHAT_BROADCAST_LOGOUT").replace("{PLAYER_NAME}", player.getName()));
            StringUtils.sendPlayerMessage(player, "§c" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("TEAM_CHAT_PLAYER_LOGOUT"));
        }
    }
}