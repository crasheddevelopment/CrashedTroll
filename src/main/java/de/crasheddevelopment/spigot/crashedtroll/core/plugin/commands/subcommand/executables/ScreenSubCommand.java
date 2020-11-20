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
import de.crasheddevelopment.spigot.crashedtroll.core.plugin.nms.NMSReflection;
import de.crasheddevelopment.spigot.crashedtroll.enums.ItemInventoryType;
import de.crasheddevelopment.spigot.crashedtroll.utils.BukkitUtils;
import de.crasheddevelopment.spigot.crashedtroll.utils.Constants;
import de.crasheddevelopment.spigot.crashedtroll.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

public class ScreenSubCommand extends SubCommand
{
    // Constructor.
    public ScreenSubCommand ()
    {
        super("screen", "Make weird things on the target screen!", "crashedtroll.permissions.troll.screen", "screen <Player> <Help | Mode>", "crashedtroll screen <Player> <Help | Mode>", CrashedTroll.ITEM_MANAGER.createItem(Material.COMMAND, "§cScreen", Collections.singletonList("§eMake weird things on the target screen!")), ItemInventoryType.OTHER, new ItemStack[] {CrashedTroll.ITEM_MANAGER.createItem(Material.BARRIER, "§cBlock", Collections.singletonList("§eBlocks his actions with containers or inventories on his screen!")), CrashedTroll.ITEM_MANAGER.createItem(Material.DIRT, "§cDemo", Collections.singletonList("§eOpens the demo screen!")), CrashedTroll.ITEM_MANAGER.createItem(Material.EYE_OF_ENDER, "§cCredits", Collections.singletonList("§eShows the credits screen!"))});
    }

    // Called method.
    @Override
    public void onCommand (Player player, String[] arguments)
    {
        // Checks if the command arguments equals three.
        if (arguments.length == 3)
        {
            // Initialize variables.
            Player targetPlayer = BukkitUtils.isPlayerOnline(arguments[1].trim());
            String mode = arguments[2].trim();

            // Check if the player is online or not.
            if (targetPlayer != null)
            {
                // Checks if the mode is not help.
                if (!(mode.equalsIgnoreCase("help")))
                {
                    // Check the mode.
                    if (mode.equalsIgnoreCase("block"))
                    {
                        // If the mode is block, it'll check if the player contains in the screen block arraylist.
                        if (!(Constants.SCREEN_BLOCK_ARRAYLIST.contains(targetPlayer)))
                        {
                            // Adds the player to the screen block arraylist.
                            Constants.SCREEN_BLOCK_ARRAYLIST.add(targetPlayer);
                            StringUtils.sendPlayerMessage(player, "§a" + targetPlayer.getName() + " cannot use containers anymore!");
                        }
                        else
                        {
                            // Removes the player from the screen block arraylist.
                            Constants.SCREEN_BLOCK_ARRAYLIST.remove(targetPlayer);
                            StringUtils.sendPlayerMessage(player, "§c" + targetPlayer.getName() + " can use containers again!");
                        }
                    }
                    else if (mode.equalsIgnoreCase("demo"))
                    {
                        // If the mode is demo, it'll send a game packet to the player, and he get the demo screen.
                        NMSReflection.sendPacketPlayOutGameStateChange(targetPlayer, 5, 0);
                        StringUtils.sendPlayerMessage(player, "§aThis player sees now the demo screen!");
                    }
                    else if (mode.equalsIgnoreCase("credits"))
                    {
                        // If the mode is credits, it'll send a game packet to the player, and he get the credits screen.
                        NMSReflection.sendPacketPlayOutGameStateChange(targetPlayer, 4, 1);
                        StringUtils.sendPlayerMessage(player, "§aThis player sees now the credits screen!");
                    }
                    else
                    {
                        // Message if the mode not found.
                        StringUtils.sendPlayerMessage(player, "§cMode not found!");
                    }
                }
                else
                {
                    // Messages with the available modes.
                    StringUtils.sendPlayerMessage(player, "§eModes for screen command:");
                    StringUtils.sendPlayerMessage(player, "§ablock §7|§e Blocks his actions with containers or inventories on his screen!");
                    StringUtils.sendPlayerMessage(player, "§ademo §7|§e Opens the demo screen!");
                    StringUtils.sendPlayerMessage(player, "§acredits §7|§e Shows the credits screen!");
                }
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