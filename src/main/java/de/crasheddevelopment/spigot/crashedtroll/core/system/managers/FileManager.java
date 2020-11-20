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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import de.crasheddevelopment.spigot.crashedtroll.utils.Constants;
import de.crasheddevelopment.spigot.crashedtroll.utils.StringUtils;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager
{
    // Constructor.
    public FileManager ()
    {
        // Checks if the directories exist and if not they will create it.
        if (!Files.exists(Constants.getPluginFolder(null)))
        {
            try
            {
                Files.createDirectories(Constants.getPluginFolder(null));
            }
            catch (IOException ioException)
            {
                StringUtils.sendInformation("IOException while creating directories!");
            }
        }

        if (!Files.exists(Constants.getPluginFolder("\\languages\\")))
        {
            try
            {
                Files.createDirectories(Constants.getPluginFolder("\\languages\\"));
            }
            catch (IOException ioException)
            {
                StringUtils.sendInformation("IOException while creating directories!");
            }
        }
    }

    public JsonObject readFile (Path filePath)
    {
        // Checks if a file exists.
        if (Files.exists(filePath))
        {
            // Initialize variables.
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.setPrettyPrinting().create();

            // Try to create the buffered reader.
            try (BufferedReader bufferedReader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8))
            {
                // Return the JsonObject.
                return gson.fromJson(bufferedReader, JsonObject.class);
            }
            catch (IOException ioException)
            {
                StringUtils.sendInformation("IOException while loading the file!");
                ioException.printStackTrace();
            }
        }
        return null;
    }

    public void saveFile (Path filePath, JsonObject jsonObject)
    {
        // Initialize variables.
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.setPrettyPrinting().create();

        // Try to create the file writer.
        try (FileWriter fileWriter = new FileWriter(filePath.toFile()))
        {
            // Writes the JsonObject to the file.
            gson.toJson(jsonObject, fileWriter);
        }
        catch (IOException ioException)
        {
            StringUtils.sendInformation("IOException while saving the file!");
            ioException.printStackTrace();
        }
    }
}