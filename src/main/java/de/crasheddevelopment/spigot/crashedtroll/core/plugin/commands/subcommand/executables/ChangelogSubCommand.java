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

public class ChangelogSubCommand extends SubCommand
{
    // Constructor.
    public ChangelogSubCommand ()
    {
        super("changelog", CrashedTroll.LANGUAGE_MANAGER.getLanguageString("CHANGELOG_DESCRIPTION"), "crashedtroll.permissions.changelog","changelog", "crashedtroll changelog", CrashedTroll.ITEM_MANAGER.createItem(Material.NETHER_STAR, "§cChangelog", Collections.singletonList("§b" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("CHANGELOG_DESCRIPTION"))), ItemInventoryType.OWN);
    }

    // Called method.
    @Override
    public void onCommand (Player player, String[] arguments)
    {
        // Changelog messages.
        StringUtils.sendPlayerMessage(player, "§cChangelog for the version " + Constants.VERSION + "!");
        StringUtils.sendPlayerMessage(player, "§7[§a+§7]: §eAdded changelog as command.");
        StringUtils.sendPlayerMessage(player, "§7[§a+§7]: §eAdded language as command.");
        StringUtils.sendPlayerMessage(player, "§7[§a+§7]: §eAdded guardian mode in the screen command.");
        StringUtils.sendPlayerMessage(player, "§7[§a+§7]: §eAdded language support.");
        StringUtils.sendPlayerMessage(player, "§7[§e=§7]: §eMakes the godmode more powerful §7(Auto Heal & Feed)");
        StringUtils.sendPlayerMessage(player, "§7[§e=§7]: §eFixed bug in InventoryDrop §7[BUG #1]");
        StringUtils.sendPlayerMessage(player, "§7[§e=§7]: §eFixed false command syntax §7[BUG #2]");
    }
}