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

import de.crasheddevelopment.spigot.crashedtroll.CrashedTroll;
import de.crasheddevelopment.spigot.crashedtroll.utils.Constants;
import de.crasheddevelopment.spigot.crashedtroll.utils.StringUtils;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigurationManager
{
    // Loads the configurations.
    public void loadConfigurations ()
    {
        // Initialize variables.
        File file = new File(CrashedTroll.getPlugin(CrashedTroll.class).getDataFolder(), "settings.yml");
        YamlConfiguration yamlConfiguration = new YamlConfiguration();

        // Check if the file exists.
        if (file.exists())
        {
            try
            {
                // Loads the file.
                yamlConfiguration.load(file);

                // Check if the language string contains in the configuration and load it.
                if (yamlConfiguration.getString("language") != null)
                    Constants.LANGUAGE = yamlConfiguration.getString("language");

                // Saves the configuration.
                this.saveConfigurations();
            }
            catch (IOException ioException)
            {
                StringUtils.sendInformation("IOException while loading settings.yml!");
                ioException.printStackTrace();
            }
            catch (InvalidConfigurationException invalidConfigurationException)
            {
                StringUtils.sendInformation("InvalidConfigurationException while loading settings.yml!");
                invalidConfigurationException.printStackTrace();
            }
        }
        else
        {
            // Loop if it's not exists.
            this.saveConfigurations();
            this.loadConfigurations();
        }
    }

    // Saves the configurations.
    public void saveConfigurations ()
    {
        // Initialize variables.
        File file = new File(CrashedTroll.getPlugin(CrashedTroll.class).getDataFolder(), "settings.yml");
        YamlConfiguration yamlConfiguration = new YamlConfiguration();

        // Check if the file not exists and creates the directories.
        if (!(file.exists()))
            file.getParentFile().mkdirs();

        // Sets the content.
        yamlConfiguration.set("CrashedTroll", "Settings Configuration");
        yamlConfiguration.set("Version", "1.0.0");
        yamlConfiguration.set("language", Constants.LANGUAGE);

        try
        {
            // Saves the configuration.
            yamlConfiguration.save(file);
        }
        catch (IOException ioException)
        {
            StringUtils.sendInformation("IOException while saving the YamlConfiguration!");
            ioException.printStackTrace();
        }
    }
}