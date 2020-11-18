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

package de.crasheddevelopment.spigot.crashedtroll;

import de.crasheddevelopment.spigot.crashedtroll.core.plugin.commands.TrollCommand;
import de.crasheddevelopment.spigot.crashedtroll.core.plugin.listeners.EventListener;
import de.crasheddevelopment.spigot.crashedtroll.core.plugin.threads.PluginThread;
import de.crasheddevelopment.spigot.crashedtroll.core.system.managers.ItemManager;
import de.crasheddevelopment.spigot.crashedtroll.core.system.managers.SubCommandManager;
import de.crasheddevelopment.spigot.crashedtroll.core.system.managers.SubListenerManager;
import de.crasheddevelopment.spigot.crashedtroll.core.system.network.PluginUpdater;
import de.crasheddevelopment.spigot.crashedtroll.utils.Constants;
import de.crasheddevelopment.spigot.crashedtroll.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CrashedTroll extends JavaPlugin
{
    // Initialize variables.
    public static final ItemManager ITEM_MANAGER = new ItemManager();
    public static final SubCommandManager SUB_COMMAND_MANAGER = new SubCommandManager();
    public static final SubListenerManager SUB_LISTENER_MANAGER = new SubListenerManager();
    public static final PluginThread PLUGIN_THREAD = new PluginThread();

    // Method called, if the plugin is enabled!
    @Override
    public void onEnable ()
    {
        // Searching for a new update!
        new PluginUpdater().searchUpdate();

        // Console printed stuff.
        System.out.println("\n" +
                "   _____               _              _ _______        _ _ \n" +
                "  / ____|             | |            | |__   __|      | | |\n" +
                " | |     _ __ __ _ ___| |__   ___  __| |  | |_ __ ___ | | |\n" +
                " | |    | '__/ _` / __| '_ \\ / _ \\/ _` |  | | '__/ _ \\| | |\n" +
                " | |____| | | (_| \\__ \\ | | |  __/ (_| |  | | | | (_) | | |\n" +
                "  \\_____|_|  \\__,_|___/_| |_|\\___|\\__,_|  |_|_|  \\___/|_|_|\n" +
                "                                                           \n" +
                "                                                           \n");
        StringUtils.sendInformation("Copyright © 2019 - 2020 CrashedDevelopment");
        StringUtils.sendInformation("This is a non-commercial project.");
        StringUtils.sendInformation("All rights belong to their respective owners.");
        StringUtils.sendEmptyLine();
        StringUtils.sendInformation("Version: " + Constants.VERSION);
        StringUtils.sendInformation("Build version: " + Constants.BUILD);
        StringUtils.sendEmptyLine();
        StringUtils.sendInformation("Loading commands...");
        this.loadCommands();
        StringUtils.sendInformation("Commands loaded!");
        StringUtils.sendInformation("Loading listeners...");
        this.loadListeners();
        StringUtils.sendInformation("Listeners loaded!");
        StringUtils.sendInformation("Start plugin thread...");
        PLUGIN_THREAD.startThread();
        StringUtils.sendInformation("Plugin thread started!");
    }

    // Method called, if the plugin got disabled!
    @Override
    public void onDisable ()
    {
        PLUGIN_THREAD.stopThread();
        StringUtils.sendInformation("Plugin disabled!");
    }

    // Loading commands method.
    private void loadCommands ()
    {
        // Register "crashedtroll" as command.
        Bukkit.getPluginCommand("crashedtroll").setExecutor(new TrollCommand());

        // Reloading the commands.
        SUB_COMMAND_MANAGER.reloadSubCommands();
    }

    // Loading listeners method.
    private void loadListeners ()
    {
        // Register the EventListener as a listener.
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);

        // Reloading the listeners.
        SUB_LISTENER_MANAGER.reloadSubListeners();
    }
}