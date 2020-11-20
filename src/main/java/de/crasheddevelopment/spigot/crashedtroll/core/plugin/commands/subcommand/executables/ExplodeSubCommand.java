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
import de.crasheddevelopment.spigot.crashedtroll.utils.BukkitUtils;
import de.crasheddevelopment.spigot.crashedtroll.utils.Constants;
import de.crasheddevelopment.spigot.crashedtroll.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Collections;

public class ExplodeSubCommand extends SubCommand
{
    // Constructor.
    public ExplodeSubCommand ()
    {
        super("explode", "Makes the target explode!", "crashedtroll.permissions.troll.explode", "explode <Player>", "crashedtroll explode <Player>", CrashedTroll.ITEM_MANAGER.createItem(Material.TNT, "§cExplode", Collections.singletonList("§eMakes the target explode!")), ItemInventoryType.OTHER);
    }

    // Called method.
    @Override
    public void onCommand (Player player, String[] arguments)
    {
        // Checks if the command arguments equals two.
        if (arguments.length == 2)
        {
            // Initialize variable.
            Player targetPlayer = BukkitUtils.isPlayerOnline(arguments[1].trim());

            // Check if the player is online or not.
            if (targetPlayer != null)
            {
                // Plays the firework launch sound, create an explosion and set the health from the target to 0.
                targetPlayer.playSound(targetPlayer.getLocation(), Sound.FIREWORK_LAUNCH, 100, 200);
                targetPlayer.getWorld().createExplosion(targetPlayer.getLocation(), 3);
                targetPlayer.setHealth(0);
                StringUtils.sendPlayerMessage(player, "§a" + targetPlayer.getName() + " exploded!");
            }
            else
            {
                // Message if the player is offline.
                player.sendMessage(Constants.PLAYER_OFFLINE);
            }
        }
        else
        {
            // Invalid arguments message.
            StringUtils.sendPlayerMessage(player, "§cInvalid command arguments!");
            StringUtils.sendPlayerMessage(player, "§c/ct " + this.getCommandSyntax());
        }
    }
}