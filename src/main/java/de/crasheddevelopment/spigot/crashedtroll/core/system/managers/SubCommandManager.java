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

package de.crasheddevelopment.spigot.crashedtroll.core.system.managers;

import de.crasheddevelopment.spigot.crashedtroll.core.plugin.commands.subcommand.SubCommand;
import de.crasheddevelopment.spigot.crashedtroll.core.plugin.commands.subcommand.executables.*;

import java.util.ArrayList;

public class SubCommandManager
{
    private final ArrayList<SubCommand> subCommandArrayList = new ArrayList<>();

    public void reloadSubCommands ()
    {
        this.subCommandArrayList.add(new GodmodeCommand());
        this.subCommandArrayList.add(new DropSubCommand());
        this.subCommandArrayList.add(new ExplodeSubCommand());
        this.subCommandArrayList.add(new FakeOPSubCommand());
        this.subCommandArrayList.add(new FreezeSubCommand());
        this.subCommandArrayList.add(new InventoryDropSubCommand());
        this.subCommandArrayList.add(new NoBlockBreakSubCommand());
        this.subCommandArrayList.add(new NoBlockPlaceSubCommand());
        this.subCommandArrayList.add(new RocketSubCommand());
        this.subCommandArrayList.add(new ScreenSubCommand());
        this.subCommandArrayList.add(new TeamChatSubCommand());
        this.subCommandArrayList.add(new TrollItemSubCommand());
    }

    public ArrayList<SubCommand> getSubCommandArrayList ()
    {
        return this.subCommandArrayList;
    }
}