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

package de.crasheddevelopment.spigot.crashedtroll.core.plugin.commands.subcommand;

import de.crasheddevelopment.spigot.crashedtroll.enums.ItemInventoryType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class SubCommand
{
    // Initialize variables.
    private final String commandName;
    private final String commandDescription;
    private final String commandPermission;
    private final String commandSyntax;
    private final String command;

    private final ItemStack itemStack;
    private final ItemInventoryType itemInventoryType;

    private ItemStack[] commandModesItemStackArray;

    // Constructors.
    public SubCommand (String commandName, String commandDescription, String commandPermission, String commandSyntax, String command, ItemStack itemStack, ItemInventoryType itemInventoryType)
    {
        this.commandName = commandName;
        this.commandDescription = commandDescription;
        this.commandPermission = commandPermission;
        this.commandSyntax = commandSyntax;
        this.command = command;
        this.itemStack = itemStack;
        this.itemInventoryType = itemInventoryType;
    }

    public SubCommand (String commandName, String commandDescription, String commandPermission, String commandSyntax, String command, ItemStack itemStack, ItemInventoryType itemInventoryType, ItemStack[] commandModes)
    {
        this.commandName = commandName;
        this.commandDescription = commandDescription;
        this.commandPermission = commandPermission;
        this.commandSyntax = commandSyntax;
        this.command = command;
        this.itemStack = itemStack;
        this.itemInventoryType = itemInventoryType;
        this.commandModesItemStackArray = commandModes;
    }

    // onCommand method.
    public abstract void onCommand (Player player, String[] arguments);

    // Returnable variables.
    public boolean hasModes ()
    {
        return (this.getCommandModesItemStackArray() != null);
    }

    public String getCommandName ()
    {
        return this.commandName;
    }

    public String getCommandDescription ()
    {
        return this.commandDescription;
    }

    public String getCommandPermission ()
    {
        return this.commandPermission;
    }

    public String getCommandSyntax ()
    {
        return this.commandSyntax;
    }

    public String getCommand ()
    {
        return this.command;
    }

    public ItemStack getItemStack ()
    {
        return this.itemStack;
    }

    public ItemInventoryType getItemInventoryType ()
    {
        return this.itemInventoryType;
    }

    public ItemStack[] getCommandModesItemStackArray ()
    {
        return this.commandModesItemStackArray;
    }
}