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

import de.crasheddevelopment.spigot.crashedtroll.core.plugin.listeners.sublisteners.SubListener;
import de.crasheddevelopment.spigot.crashedtroll.core.plugin.listeners.sublisteners.events.*;

import java.util.ArrayList;

public class SubListenerManager
{
    // Initialize variable.
    private final ArrayList<SubListener> subListenerArrayList = new ArrayList<>();

    // Reload the sub listeners.
    public void reloadSubListeners ()
    {
        this.subListenerArrayList.add(new FreezeSubListener());
        this.subListenerArrayList.add(new GodmodeSubListener());
        this.subListenerArrayList.add(new NoBlockBreakSubListener());
        this.subListenerArrayList.add(new NoBlockPlaceSubListener());
        this.subListenerArrayList.add(new ScreenSubListener());
        this.subListenerArrayList.add(new TeamChatSubListener());
        this.subListenerArrayList.add(new TrollItemSubListener());
        this.subListenerArrayList.add(new LanguageFileOutdatedSubListener());
        this.subListenerArrayList.add(new UpdateSubListener());
    }

    // Returns the arraylist.
    public ArrayList<SubListener> getSubListenerArrayList ()
    {
        return this.subListenerArrayList;
    }
}