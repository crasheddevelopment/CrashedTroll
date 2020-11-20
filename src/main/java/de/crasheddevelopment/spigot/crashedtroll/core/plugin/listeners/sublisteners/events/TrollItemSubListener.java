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

package de.crasheddevelopment.spigot.crashedtroll.core.plugin.listeners.sublisteners.events;

import de.crasheddevelopment.spigot.crashedtroll.CrashedTroll;
import de.crasheddevelopment.spigot.crashedtroll.core.plugin.commands.subcommand.SubCommand;
import de.crasheddevelopment.spigot.crashedtroll.core.plugin.listeners.sublisteners.SubListener;
import de.crasheddevelopment.spigot.crashedtroll.enums.ItemInventoryType;
import de.crasheddevelopment.spigot.crashedtroll.enums.ListenerType;
import de.crasheddevelopment.spigot.crashedtroll.utils.BukkitUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TrollItemSubListener extends SubListener
{
    // Initialize variables.
    private final HashMap<HashMap<Player, ItemStack>, Inventory> playerActionHashMap = new HashMap<>();
    private final HashMap<HashMap<Player, String>, Inventory> modesActionHashMap = new HashMap<>();

    private final Inventory inventory = Bukkit.createInventory(null, InventoryType.HOPPER,"§cCrashedTroll Menu");
    private final Inventory ownPlayerInventory = Bukkit.createInventory(null, InventoryType.CHEST, "§cCrashedTroll §7- §eYou");
    private final Inventory otherPlayersInventory = Bukkit.createInventory(null, InventoryType.CHEST, "§cCrashedTroll §7- §eOther");

    // Constructor.
    public TrollItemSubListener ()
    {
        super(new ListenerType[] {ListenerType.JOIN, ListenerType.INVENTORY_CLICK, ListenerType.PLAYER_INTERACT});
        this.setInventoryItems();
    }

    // Set inventory items.
    private void setInventoryItems ()
    {
        // Set items.
        this.inventory.setItem(1, CrashedTroll.ITEM_MANAGER.createItem(Material.SNOW_BALL, "§5Player", Collections.singletonList("§6" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("TROLL_ITEM_PLAYER_ITEM_DESCRIPTION"))));
        this.inventory.setItem(3, CrashedTroll.ITEM_MANAGER.createItem(Material.SNOW_BALL, "§eOther Players", Collections.singletonList("§6" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("TROLL_ITEM_OTHER_PLAYER_ITEM_DESCRIPTION"))));

        // Set items from the subcommands to the inventory types.
        for (SubCommand subCommand : CrashedTroll.SUB_COMMAND_MANAGER.getSubCommandArrayList())
        {
            if (!(subCommand.getItemInventoryType().equals(ItemInventoryType.NONE)))
            {
                if (subCommand.getItemInventoryType().equals(ItemInventoryType.OWN))
                {
                    int itemSlot = BukkitUtils.getEmptySlot(this.ownPlayerInventory);
                    this.ownPlayerInventory.setItem(itemSlot, subCommand.getItemStack());
                }
                else if (subCommand.getItemInventoryType().equals(ItemInventoryType.OTHER))
                {
                    int itemSlot = BukkitUtils.getEmptySlot(this.otherPlayersInventory);;
                    this.otherPlayersInventory.setItem(itemSlot, subCommand.getItemStack());
                }
            }
        }
    }

    // Join event
    @Override
    public void onJoinEvent (PlayerJoinEvent playerJoinEvent)
    {
        // Initialize variable.
        Player player = playerJoinEvent.getPlayer();

        // Check if the player has not the permission to have the troll item.
        if (!(player.hasPermission("crashedtroll.permissions.troll.item")))
        {
            // Check if the player has the item.
            if (player.getInventory().contains(CrashedTroll.ITEM_MANAGER.getTrollItemStack()))
            {
                // Removes the item.
                player.getInventory().removeItem(CrashedTroll.ITEM_MANAGER.getTrollItemStack());
                player.sendMessage("§c" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("NO_PERMISSIONS"));
            }
        }
    }

    // Player interact event.
    @Override
    public void onPlayerInteractEvent (PlayerInteractEvent playerInteractEvent)
    {
        // Initialize variables.
        Action action = playerInteractEvent.getAction();
        Player player = playerInteractEvent.getPlayer();

        // Check if the player has the troll item in his hand.
        if (player.getItemInHand().equals(CrashedTroll.ITEM_MANAGER.getTrollItemStack()))
        {
            // Check if the player press right click with the item.
            if ((action.equals(Action.RIGHT_CLICK_AIR)) || (action.equals(Action.RIGHT_CLICK_BLOCK)))
            {
                // Check if the player has the permission.
                if (player.hasPermission("crashedtroll.permissions.troll.item"))
                {
                    // Opens the troll inventory.
                    player.openInventory(this.inventory);
                    player.playSound(player.getLocation(), Sound.CLICK, 200, 100);
                    playerInteractEvent.setCancelled(true);
                }
                else
                {
                    // Removes the item.
                    player.getInventory().removeItem(player.getInventory().getItemInHand());
                    player.sendMessage("§c" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("NO_PERMISSIONS"));
                }
            }
        }
    }

    // Inventory click event.
    @Override
    public void onInventoryClickEvent (InventoryClickEvent inventoryClickEvent)
    {
        // Check if the person who clicked in a inventory is a player.
        if (inventoryClickEvent.getWhoClicked() instanceof Player)
        {
            // Initialize variables.
            Inventory inventory = inventoryClickEvent.getClickedInventory();
            Player player = ((Player) inventoryClickEvent.getWhoClicked()).getPlayer();

            // Method for player action hashmap.
            for (Map.Entry<HashMap<Player, ItemStack>, Inventory> hashMapInventoryEntry : this.playerActionHashMap.entrySet())
            {
                // Initialize variables.
                HashMap<Player, ItemStack> playerItemStackHashMap = hashMapInventoryEntry.getKey();
                Inventory onlinePlayersInventory = hashMapInventoryEntry.getValue();

                // Check if the inventory is the online players inventory.
                if (inventory.equals(onlinePlayersInventory))
                {
                    // Check if the player is contains in the hashmap.
                    if (playerItemStackHashMap.containsKey(player))
                    {
                        // Check if the player has the permission.
                        if (player.hasPermission("crashedtroll.permissions.troll.item"))
                        {
                            // Check if the selected item is not null.
                            if ((inventory.getItem(inventoryClickEvent.getRawSlot()) != null) && !(inventory.getItem(inventoryClickEvent.getRawSlot()).getType().equals(Material.AIR)))
                            {
                                // Initialize variables.
                                ItemStack subCommandItemStack = playerItemStackHashMap.get(player);
                                ItemStack skullItemStack = inventory.getItem(inventoryClickEvent.getRawSlot());
                                String playerName = skullItemStack.getItemMeta().getDisplayName().replace("§a", "").trim();

                                // Check if the subcommand itemstack is not null.
                                if (subCommandItemStack != null)
                                {
                                    // Check the itemstack from the subcommand.
                                    for (SubCommand subCommand : CrashedTroll.SUB_COMMAND_MANAGER.getSubCommandArrayList())
                                    {
                                        if ((subCommand.getItemStack() != null) && (subCommand.getItemStack().equals(subCommandItemStack)))
                                        {
                                            // Check if the subcommand has modes.
                                            if (subCommand.hasModes())
                                            {
                                                // Initialize variables.
                                                Inventory modesInventory = BukkitUtils.getModesInventory(null, InventoryType.CHEST, "§cCrashedTroll §7- §eModes", subCommand.getCommandModesItemStackArray());
                                                HashMap<Player, String> modesPlayerItemStackHashMap = new HashMap<>();

                                                // Select modes hashmap.
                                                modesPlayerItemStackHashMap.put(player, subCommand.getCommand().replace("<Player>", playerName).trim());
                                                this.modesActionHashMap.put(modesPlayerItemStackHashMap, modesInventory);
                                                player.openInventory(modesInventory);
                                            }
                                            else
                                            {
                                                // Perform the command and closes the inventory.
                                                player.performCommand(subCommand.getCommand().replace("<Player>", playerName).trim());
                                                player.closeInventory();
                                            }
                                            break;
                                        }
                                    }
                                }

                                // Removes the player from the action hashmap.
                                this.playerActionHashMap.remove(playerItemStackHashMap);
                                inventoryClickEvent.setCancelled(true);
                            }
                        }
                        else
                        {
                            // Removes the item from the player.
                            player.getInventory().removeItem(player.getInventory().getItemInHand());
                            player.sendMessage("§c" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("NO_PERMISSIONS"));

                            // Closes the inventory.
                            player.closeInventory();

                            // Removes the player from the action hashmap.
                            this.playerActionHashMap.remove(playerItemStackHashMap);
                        }
                        return;
                    }
                }
            }

            // Modes action hashmap.
            for (Map.Entry<HashMap<Player, String>, Inventory> playerStringInventoryMapEntry : this.modesActionHashMap.entrySet())
            {
                // Initialize variables.
                HashMap<Player, String> playerStringHashMap = playerStringInventoryMapEntry.getKey();
                Inventory modesInventory = playerStringInventoryMapEntry.getValue();

                // Checks if the inventory is the modes inventory.
                if (inventory.equals(modesInventory))
                {
                    // Check if the player contains in the hashmap.
                    if (playerStringHashMap.containsKey(player))
                    {
                        // Check if the player has the permission.
                        if (player.hasPermission("crashedtroll.permissions.troll.item"))
                        {
                            // Check if the selected item is not null.
                            if ((inventory.getItem(inventoryClickEvent.getRawSlot()) != null) && !(inventory.getItem(inventoryClickEvent.getRawSlot()).getType().equals(Material.AIR)))
                            {
                                // Initialize variables.
                                String command = playerStringHashMap.get(player);
                                ItemStack modeItemStack = inventory.getItem(inventoryClickEvent.getRawSlot());

                                // Check if the mode itemstack is not null.
                                if (modeItemStack != null)
                                {
                                    // Perform the command and closes the inventory.
                                    player.performCommand(command.replace("<Help | Mode>", modeItemStack.getItemMeta().getDisplayName().replace("§c", "").trim().toLowerCase()));
                                    player.closeInventory();
                                }

                                // Removes the player from the modes action hashmap.
                                this.modesActionHashMap.remove(playerStringHashMap);
                                inventoryClickEvent.setCancelled(true);
                            }
                        }
                        else
                        {
                            // Removes the item from the player.
                            player.getInventory().removeItem(player.getInventory().getItemInHand());
                            player.sendMessage("§c" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("NO_PERMISSIONS"));

                            // Closes the inventory.
                            player.closeInventory();

                            // Removes the player from the modes action hashmap.
                            this.modesActionHashMap.remove(playerStringHashMap);
                        }
                        return;
                    }
                }
            }

            // Check which inventory.
            if (inventory.equals(this.inventory))
            {
                // Check if the player has the permission.
                if (player.hasPermission("crashedtroll.permissions.troll.item"))
                {
                    // Opens the inventory which is selected.
                    if (inventoryClickEvent.getRawSlot() == 1)
                    {
                        player.openInventory(this.ownPlayerInventory);
                        inventoryClickEvent.setCancelled(true);
                    }
                    else if (inventoryClickEvent.getRawSlot() == 3)
                    {
                        player.openInventory(this.otherPlayersInventory);
                        inventoryClickEvent.setCancelled(true);
                    }
                }
                else
                {
                    // Removes the item from the player and closes the inventory.
                    player.getInventory().removeItem(player.getInventory().getItemInHand());
                    player.sendMessage("§c" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("NO_PERMISSIONS"));
                    player.closeInventory();
                }
            }
            else if (inventory.equals(this.ownPlayerInventory))
            {
                // Check if the player has the permission.
                if (player.hasPermission("crashedtroll.permissions.troll.item"))
                {
                    // Check if the selected item is not null.
                    if ((inventory.getItem(inventoryClickEvent.getRawSlot()) != null) && !(inventory.getItem(inventoryClickEvent.getRawSlot()).getType().equals(Material.AIR)))
                    {
                        // Initialize variable.
                        ItemStack itemStack = inventory.getItem(inventoryClickEvent.getRawSlot());

                        // Check the itemstack from the subcommands.
                        for (SubCommand subCommand : CrashedTroll.SUB_COMMAND_MANAGER.getSubCommandArrayList())
                        {
                            if (itemStack.equals(subCommand.getItemStack()))
                            {
                                // Perform the command and closes the inventory.
                                player.performCommand(subCommand.getCommand());
                                player.closeInventory();
                            }
                        }

                        inventoryClickEvent.setCancelled(true);
                    }
                }
                else
                {
                    // Removes the item from the player and closes the inventory.
                    player.getInventory().removeItem(player.getInventory().getItemInHand());
                    player.sendMessage("§c" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("NO_PERMISSIONS"));
                    player.closeInventory();
                }
            }
            else if (inventory.equals(this.otherPlayersInventory))
            {
                // Check if the player has the permission.
                if (player.hasPermission("crashedtroll.permissions.troll.item"))
                {
                    // Check if the selected item is not null.
                    if ((inventory.getItem(inventoryClickEvent.getRawSlot()) != null) && !(inventory.getItem(inventoryClickEvent.getRawSlot()).getType().equals(Material.AIR)))
                    {
                        // Initialize variables.
                        HashMap<Player, ItemStack> playerItemStackHashMap = new HashMap<>();
                        ItemStack itemStack = inventory.getItem(inventoryClickEvent.getRawSlot());
                        Inventory onlinePlayerInventory = BukkitUtils.getOnlinePlayerInventory(null, InventoryType.CHEST, "§cCrashedTroll §7- §ePlayers");

                        // Adds the player to the action hashmap and opens the inventory.
                        playerItemStackHashMap.put(player, itemStack);
                        this.playerActionHashMap.put(playerItemStackHashMap, onlinePlayerInventory);
                        player.openInventory(onlinePlayerInventory);
                    }

                    inventoryClickEvent.setCancelled(true);
                }
                else
                {
                    // Removes the item from the player and closes the inventory.
                    player.getInventory().removeItem(player.getInventory().getItemInHand());
                    player.sendMessage("§c" + CrashedTroll.LANGUAGE_MANAGER.getLanguageString("NO_PERMISSIONS"));
                    player.closeInventory();
                }
            }
        }
    }
}