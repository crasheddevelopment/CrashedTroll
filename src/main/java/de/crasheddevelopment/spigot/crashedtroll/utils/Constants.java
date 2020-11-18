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

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Constants
{
    // Version string.
    public static final String VERSION = "3.0.0";

    // Build string.
    public static final String BUILD = "8";

    // Plugin prefix string.
    public static final String PREFIX = "§7[§5§lCrashedTroll§7]: ";

    // Plugin team chat prefix string.
    public static final String TEAM_CHAT_PREFIX = "§7[§5§lCrashedTroll TeamChat§7]: §6";

    // Plugin no permissions string.
    public static final String NO_PERMISSIONS = PREFIX + "§cYou do not have permissions to execute this command!";

    // Plugin player is offline string.
    public static final String PLAYER_OFFLINE = PREFIX + "§cThis player is not online!";

    // Variable for the UpdateListener.
    public static boolean UPDATE_AVAILABLE = false;

    // Player arraylists for events.
    public static final ArrayList<Player> FREEZE_ARRAYLIST = new ArrayList<>();
    public static final ArrayList<Player> NO_BLOCK_BREAK_ARRAYLIST = new ArrayList<>();
    public static final ArrayList<Player> NO_BLOCK_PLACE_ARRAYLIST = new ArrayList<>();
    public static final ArrayList<Player> GODMODE_ARRAYLIST = new ArrayList<>();
    public static final ArrayList<Player> TEAM_CHAT_ARRAYLIST = new ArrayList<>();
    public static final ArrayList<Player> SCREEN_BLOCK_ARRAYLIST = new ArrayList<>();
}