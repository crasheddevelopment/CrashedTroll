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

import de.crasheddevelopment.spigot.crashedtroll.CrashedTroll;
import org.bukkit.entity.Player;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Constants
{
    // Version string.
    public static final String VERSION = "3.1.0";

    // Build string.
    public static final String BUILD = "9";

    // Plugin prefix string.
    public static final String PREFIX = "§7[§5§lCrashedTroll§7]: ";

    // Plugin team chat prefix string.
    public static final String TEAM_CHAT_PREFIX = "§7[§5§lCrashedTroll TeamChat§7]: §6";

    public static final HashMap<String, String> LANGUAGE_HASH_MAP = new HashMap<>();

    // Variable for the update listener.
    public static boolean UPDATE_AVAILABLE = false;

    // Variable for the language file outdated listener.
    public static boolean LANGUAGE_FILE_OUTDATED = false;

    // Language variable.
    public static String LANGUAGE = "ENGLISH";

    // Returns the plugin folder.
    public static Path getPluginFolder (String otherPath)
    {
        if (otherPath != null)
        {
            return StringUtils.getOSPath(Paths.get(CrashedTroll.getPlugin(CrashedTroll.class).getDataFolder().getPath() + "\\" + otherPath));
        }
        else
        {
            return StringUtils.getOSPath(Paths.get(CrashedTroll.getPlugin(CrashedTroll.class).getDataFolder().getPath()));
        }
    }

    // Player arraylists for events.
    public static final ArrayList<Player> FREEZE_ARRAYLIST = new ArrayList<>();
    public static final ArrayList<Player> GODMODE_ARRAYLIST = new ArrayList<>();
    public static final ArrayList<Player> NO_BLOCK_BREAK_ARRAYLIST = new ArrayList<>();
    public static final ArrayList<Player> NO_BLOCK_PLACE_ARRAYLIST = new ArrayList<>();
    public static final ArrayList<Player> SCREEN_BLOCK_ARRAYLIST = new ArrayList<>();
    public static final ArrayList<Player> TEAM_CHAT_ARRAYLIST = new ArrayList<>();
}