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

package de.crasheddevelopment.spigot.crashedtroll.core.plugin.nms;

import de.crasheddevelopment.spigot.crashedtroll.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class NMSReflection
{
    // Initialize variable.
    private final static String nmsVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].trim();

    // Returns a nms class.
    private static Class<?> getNMSClass (String className)
    {
        String nmsClassName = "net.minecraft.server." + nmsVersion + "." + className;

        try
        {
            return Class.forName(nmsClassName);
        }
        catch (ClassNotFoundException classNotFoundException)
        {
            StringUtils.sendInformation("ClassNotFoundException while searching for " + nmsClassName + "!");
            classNotFoundException.printStackTrace();
        }

        return null;
    }

    // Returns the craftplayer object.
    private static Object getCraftPlayerHandleObject (Player player)
    {
        try
        {
            return player.getClass().getMethod("getHandle").invoke(player);
        }
        catch (NoSuchMethodException noSuchMethodException)
        {
            StringUtils.sendInformation("NoSuchMethodException while searching getHandle method!");
            noSuchMethodException.printStackTrace();
        }
        catch (IllegalAccessException illegalAccessException)
        {
            StringUtils.sendInformation("IllegalAccessException while invoke player in the getHandle method!");
            illegalAccessException.printStackTrace();
        }
        catch (InvocationTargetException invocationTargetException)
        {
            StringUtils.sendInformation("InvocationTargetException while invoke player in the getHandle method!");
            invocationTargetException.printStackTrace();
        }
        return null;
    }

    // Method to send a packet.
    private static void sendPacket (Player player, Object packetClassObject)
    {
        try
        {
            Object craftPlayerObject = getCraftPlayerHandleObject(player);

            if (craftPlayerObject != null)
            {
                Object playerConnectionObject = craftPlayerObject.getClass().getField("playerConnection").get(craftPlayerObject);

                if (playerConnectionObject != null)
                {
                    playerConnectionObject.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnectionObject, packetClassObject);
                }
                else
                {
                    StringUtils.sendInformation("[EXCEPTION]: playerConnectionObject object is null!");
                    StringUtils.sendPlayerMessage(player, "§cInternal exception!");
                }
            }
            else
            {
                StringUtils.sendInformation("[EXCEPTION]: craftPlayerObject is null!");
                StringUtils.sendPlayerMessage(player, "§cInternal exception!");
            }
        }
        catch (IllegalAccessException illegalAccessException)
        {
            StringUtils.sendInformation("IllegalAccessException while sending packet!");
            illegalAccessException.printStackTrace();
        }
        catch (NoSuchFieldException noSuchFieldException)
        {
            StringUtils.sendInformation("NoSuchFieldException while sending packet!");
            noSuchFieldException.printStackTrace();
        }
        catch (NoSuchMethodException noSuchMethodException)
        {
            StringUtils.sendInformation("NoSuchMethodException while sending packet!");
            noSuchMethodException.printStackTrace();
        }
        catch (InvocationTargetException invocationTargetException)
        {
            StringUtils.sendInformation("InvocationTargetException while sending packet!");
            invocationTargetException.printStackTrace();
        }
    }

    // Method to send a PacketPlayOutGameStateChange packet.
    public static void sendPacketPlayOutGameStateChange (Player player, int integerValue, float floatValue)
    {
        try
        {
            Object packetPlayOutGameStateChangeObject = getNMSClass("PacketPlayOutGameStateChange").getConstructor(int.class, float.class).newInstance(integerValue, floatValue);
            sendPacket(player, packetPlayOutGameStateChangeObject);
        }
        catch (NoSuchMethodException noSuchMethodException)
        {
            StringUtils.sendInformation("NoSuchMethodException while sending PacketPlayOutGameStateChange packet to the player " + player.getName() + " (Integer: " + integerValue + " Float: " + floatValue + ")!");
            noSuchMethodException.printStackTrace();
        }
        catch (IllegalAccessException illegalAccessException)
        {
            StringUtils.sendInformation("IllegalAccessException while sending PacketPlayOutGameStateChange packet to the player " + player.getName() + " (Integer: " + integerValue + " Float: " + floatValue + ")!");
            illegalAccessException.printStackTrace();
        }
        catch (InstantiationException instantiationException)
        {
            StringUtils.sendInformation("InstantiationException while sending PacketPlayOutGameStateChange packet to the player " + player.getName() + " (Integer: " + integerValue + " Float: " + floatValue + ")!");
            instantiationException.printStackTrace();
        }
        catch (InvocationTargetException invocationTargetException)
        {
            StringUtils.sendInformation("InvocationTargetException while sending PacketPlayOutGameStateChange packet to the player " + player.getName() + " (Integer: " + integerValue + " Float: " + floatValue + ")!");
            invocationTargetException.printStackTrace();
        }
    }
}