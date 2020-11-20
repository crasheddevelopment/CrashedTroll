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

package de.crasheddevelopment.spigot.crashedtroll.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StringUtils
{
    // Sends an empty line to the console.
    public static void sendEmptyLine ()
    {
        System.out.println(" ");
    }

    // Sends the output.
    public static void sendInformation (String message)
    {
        // Prints the output.
        System.out.println("[CrashedTroll]: " + message);
    }

    // Sends an player message.
    public static void sendPlayerMessage (Player player, String message)
    {
        player.sendMessage(Constants.PREFIX + message);
    }

    // Sends a team chat broadcast.
    public static void sendTeamBroadcast (String message)
    {
        for (Player onlinePlayers : Bukkit.getOnlinePlayers())
        {
            if (Constants.TEAM_CHAT_ARRAYLIST.contains(onlinePlayers))
            {
                onlinePlayers.sendMessage(Constants.TEAM_CHAT_PREFIX + message);
            }
        }
    }

    // Sends a team chat message.
    public static void sendTeamMessage (Player player, String message)
    {
        for (Player onlinePlayers : Bukkit.getOnlinePlayers())
        {
            if (Constants.TEAM_CHAT_ARRAYLIST.contains(onlinePlayers))
            {
                onlinePlayers.sendMessage(Constants.TEAM_CHAT_PREFIX + "§7<§e" + player.getName() + "§7>: §a" + message);
            }
        }
    }

    // Returns the system related path.
    public static Path getOSPath (Path path)
    {
        return Paths.get(path.toString().trim().replace("\\", File.separator));
    }
}