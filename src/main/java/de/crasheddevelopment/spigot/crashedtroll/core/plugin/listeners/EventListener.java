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

package de.crasheddevelopment.spigot.crashedtroll.core.plugin.listeners;

import de.crasheddevelopment.spigot.crashedtroll.CrashedTroll;
import de.crasheddevelopment.spigot.crashedtroll.core.plugin.listeners.sublisteners.SubListener;
import de.crasheddevelopment.spigot.crashedtroll.enums.ListenerType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;

public class EventListener implements Listener
{
    // Methods to call the events from the sublisteners.
    @EventHandler
    public void onPlayerJoinEvent (PlayerJoinEvent playerJoinEvent)
    {
        for (SubListener subListener : CrashedTroll.SUB_LISTENER_MANAGER.getSubListenerArrayList())
        {
            for (ListenerType listenerType : subListener.getListenerTypes())
            {
                if (listenerType.equals(ListenerType.JOIN))
                {
                    subListener.onJoinEvent(playerJoinEvent);
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onPlayerQuitEvent (PlayerQuitEvent playerQuitEvent)
    {
        for (SubListener subListener : CrashedTroll.SUB_LISTENER_MANAGER.getSubListenerArrayList())
        {
            for (ListenerType listenerType : subListener.getListenerTypes())
            {
                if (listenerType.equals(ListenerType.QUIT))
                {
                    subListener.onQuitEvent(playerQuitEvent);
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onPlayerMoveEvent (PlayerMoveEvent playerMoveEvent)
    {
        for (SubListener subListener : CrashedTroll.SUB_LISTENER_MANAGER.getSubListenerArrayList())
        {
            for (ListenerType listenerType : subListener.getListenerTypes())
            {
                if (listenerType.equals(ListenerType.MOVE))
                {
                    subListener.onMoveEvent(playerMoveEvent);
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onAsyncPlayerChatEvent (AsyncPlayerChatEvent asyncPlayerChatEvent)
    {
        for (SubListener subListener : CrashedTroll.SUB_LISTENER_MANAGER.getSubListenerArrayList())
        {
            for (ListenerType listenerType : subListener.getListenerTypes())
            {
                if (listenerType.equals(ListenerType.ASYNCPLAYERCHAT))
                {
                    subListener.onAsyncPlayerChatEvent(asyncPlayerChatEvent);
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onPlayerBlockBreak (BlockBreakEvent blockBreakEvent)
    {
        for (SubListener subListener : CrashedTroll.SUB_LISTENER_MANAGER.getSubListenerArrayList())
        {
            for (ListenerType listenerType : subListener.getListenerTypes())
            {
                if (listenerType.equals(ListenerType.BREAK))
                {
                    subListener.onPlayerBlockBreakEvent(blockBreakEvent);
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onPlayerBlockPlace (BlockPlaceEvent blockPlaceEvent)
    {
        for (SubListener subListener : CrashedTroll.SUB_LISTENER_MANAGER.getSubListenerArrayList())
        {
            for (ListenerType listenerType : subListener.getListenerTypes())
            {
                if (listenerType.equals(ListenerType.PLACE))
                {
                    subListener.onPlayerBlockPlaceEvent(blockPlaceEvent);
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClick (InventoryClickEvent inventoryClickEvent)
    {
        for (SubListener subListener : CrashedTroll.SUB_LISTENER_MANAGER.getSubListenerArrayList())
        {
            for (ListenerType listenerType : subListener.getListenerTypes())
            {
                if (listenerType.equals(ListenerType.INVENTORY_CLICK))
                {
                    subListener.onInventoryClickEvent(inventoryClickEvent);
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onPlayerInteract (PlayerInteractEvent playerInteractEvent)
    {
        for (SubListener subListener : CrashedTroll.SUB_LISTENER_MANAGER.getSubListenerArrayList())
        {
            for (ListenerType listenerType : subListener.getListenerTypes())
            {
                if (listenerType.equals(ListenerType.PLAYER_INTERACT))
                {
                    subListener.onPlayerInteractEvent(playerInteractEvent);
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onEntityDamageEvent (EntityDamageEvent entityDamageEvent)
    {
        if (entityDamageEvent.getEntity() instanceof Player)
        {
            for (SubListener subListener : CrashedTroll.SUB_LISTENER_MANAGER.getSubListenerArrayList())
            {
                for (ListenerType listenerType : subListener.getListenerTypes())
                {
                    if (listenerType.equals(ListenerType.DAMAGE))
                    {
                        subListener.onPlayerDamageEvent(entityDamageEvent);
                        break;
                    }
                }
            }
        }
    }
}