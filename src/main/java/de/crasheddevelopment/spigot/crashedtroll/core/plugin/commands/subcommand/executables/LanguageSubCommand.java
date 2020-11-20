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
import de.crasheddevelopment.spigot.crashedtroll.utils.StringUtils;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class LanguageSubCommand extends SubCommand
{
    // Constructor.
    public LanguageSubCommand ()
    {
        super("language", CrashedTroll.LANGUAGE_MANAGER.getLanguageString("LANGUAGE_DESCRIPTION"), "crashedtroll.permissions.language", "language <list | language | update>", "crashedtroll language", null, ItemInventoryType.NONE);
    }

    // Called method.
    @Override
    public void onCommand (Player player, String[] arguments)
    {
        // Initialize variable.
        ArrayList<String> languagesArrayList = CrashedTroll.LANGUAGE_MANAGER.getLanguages();

        // Checks if the command arguments equals two.
        if (arguments.length == 2)
        {
            // Initialize variable.
            String argument = arguments[1].trim();

            // Check what the argument exactly is.
            if (argument.equalsIgnoreCase("list"))
            {
                // List all languages.
                StringUtils.sendPlayerMessage(player, "§c" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("LANGUAGE_LISTING"));
                languagesArrayList.forEach(language -> StringUtils.sendPlayerMessage(player, "§c" + language));
            }
            else if (argument.equalsIgnoreCase("update"))
            {
                // Downloading all languages.
                StringUtils.sendPlayerMessage(player, "§c" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("LANGUAGE_FILE_SEARCH_UPDATE"));
                CrashedTroll.LANGUAGE_MANAGER.searchUpdates();
                StringUtils.sendPlayerMessage(player, "§a" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("LANGUAGE_FILE_SEARCH_UPDATE_DONE"));
            }
            else
            {
                for (String language : languagesArrayList)
                {
                    // Check if the language exists.
                    if (argument.equalsIgnoreCase(language))
                    {
                        // Switch the language.
                        CrashedTroll.LANGUAGE_MANAGER.switchLanguages(language);
                        StringUtils.sendPlayerMessage(player, "§a" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("LANGUAGE_SWITCHED"));
                        StringUtils.sendPlayerMessage(player, "§e" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("LANGUAGE_FILE_CREDITS"));
                        return;
                    }
                }

                // Message if the language is not found!
                StringUtils.sendPlayerMessage(player, "§c" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("LANGUAGE_NOT_FOUND"));
            }
        }
        else
        {
            // Invalid arguments message.
            StringUtils.sendPlayerMessage(player, "§c" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("INVALID_COMMAND_ARGUMENTS"));
            StringUtils.sendPlayerMessage(player, "§c" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("COMMAND_SYNTAX_MESSAGE").replace("{SYNTAX}", this.getCommandSyntax()));
        }
    }
}