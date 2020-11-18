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

package de.crasheddevelopment.spigot.crashedtroll.core.plugin.listeners.sublisteners;

import de.crasheddevelopment.spigot.crashedtroll.enums.ListenerType;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;

public abstract class SubListener
{
    // Initialize variable.
    private final ListenerType[] listenerTypes;

    // Constructor.
    public SubListener (ListenerType[] listenerTypes)
    {
        this.listenerTypes = listenerTypes;
    }

    // Events
    public void onJoinEvent (PlayerJoinEvent playerJoinEvent) {}
    public void onQuitEvent (PlayerQuitEvent playerQuitEvent) {}
    public void onMoveEvent (PlayerMoveEvent playerMoveEvent) {}
    public void onAsyncPlayerChatEvent (AsyncPlayerChatEvent asyncPlayerChatEvent) {}
    public void onPlayerBlockBreakEvent (BlockBreakEvent blockBreakEvent) {}
    public void onPlayerBlockPlaceEvent (BlockPlaceEvent blockPlaceEvent) {}
    public void onInventoryClickEvent (InventoryClickEvent inventoryClickEvent) {}
    public void onPlayerInteractEvent (PlayerInteractEvent playerInteractEvent) {}
    public void onPlayerDamageEvent (EntityDamageEvent entityDamageEvent) {}

    // Returns the listener type.
    public ListenerType[] getListenerTypes ()
    {
        return this.listenerTypes;
    }
}