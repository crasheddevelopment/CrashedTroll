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

import com.google.gson.JsonObject;
import de.crasheddevelopment.spigot.crashedtroll.CrashedTroll;
import de.crasheddevelopment.spigot.crashedtroll.utils.Constants;
import de.crasheddevelopment.spigot.crashedtroll.utils.DownloadUtils;
import de.crasheddevelopment.spigot.crashedtroll.utils.JsonUtils;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class LanguageManager
{
    // Checks the language file.
    public void checkLanguageFile (String language)
    {
        // Check if the file exists.
        if (Files.exists(Constants.getPluginFolder("\\languages\\" + language.toLowerCase() + ".json")))
        {
            // Initialize variable.
            JsonObject languageJsonObject = CrashedTroll.FILE_MANAGER.readFile(Constants.getPluginFolder("\\languages\\" + language.toLowerCase() + ".json"));

            // Check if the json object is not null.
            if (languageJsonObject != null)
            {
                // Initialize variable.
                boolean found = false;

                // Check if the json object has the key.
                if (languageJsonObject.has("SUPPORTED_PLUGIN_VERSIONS"))
                {
                    // Initialize variable.
                    String[] supportedPluginVersions = languageJsonObject.get("SUPPORTED_PLUGIN_VERSIONS").getAsString().replace("[", "").replace("]", "").trim().split(":");

                    // Checks if the version is supported.
                    for (String supportedPluginVersion : supportedPluginVersions)
                    {
                        if (supportedPluginVersion.equals(Constants.VERSION))
                        {
                            // Set found to true.
                            found = true;
                            break;
                        }
                    }
                }

                // Returns.
                if (found)
                    return;
            }
        }

        // Set the language file as outdated.
        Constants.LANGUAGE_FILE_OUTDATED = true;
    }

    // Loads the language file.
    public void loadLanguageFile ()
    {
        // Initialize variable.
        JsonObject languageJsonObject = Files.exists(Constants.getPluginFolder("\\languages\\" + Constants.LANGUAGE.toLowerCase() + ".json")) ? CrashedTroll.FILE_MANAGER.readFile(Constants.getPluginFolder("\\languages\\" + Constants.LANGUAGE.toLowerCase() + ".json")) : CrashedTroll.FILE_MANAGER.readFile(Constants.getPluginFolder("\\languages\\english.json"));

        // Clears the hashmap.
        Constants.LANGUAGE_HASH_MAP.clear();

        // Check if the json object is not null and import the keys and strings to the hashmap.
        if (languageJsonObject != null)
            languageJsonObject.entrySet().forEach(jsonElementEntry -> Constants.LANGUAGE_HASH_MAP.put(jsonElementEntry.getKey(), jsonElementEntry.getValue().getAsString()));
    }

    // Switch the languages.
    public void switchLanguages (String language)
    {
        // Check if the language exists and save and reload the configurations.
        Constants.LANGUAGE = Files.exists(Constants.getPluginFolder("\\languages\\" + language.toLowerCase() + ".json")) ? language : "ENGLISH";
        this.checkLanguageFile(Constants.LANGUAGE);
        CrashedTroll.CONFIGURATION_MANAGER.saveConfigurations();
        this.loadLanguageFile();
    }

    public void searchUpdates ()
    {
        // Initialize variable.
        JsonObject jsonObject = JsonUtils.getJsonObjectFromURL("https://raw.githubusercontent.com/crasheddevelopment/CrashedTroll/main/LanguagePack/index.json");

        // Check if the json object is not null.
        if (jsonObject != null)
        {
            // Gets the content of the json and downloads the language files.
            jsonObject.entrySet().forEach(jsonElementEntry -> DownloadUtils.downloadFile("https://raw.githubusercontent.com/crasheddevelopment/CrashedTroll/main/LanguagePack/" + jsonElementEntry.getValue().getAsString(), Constants.getPluginFolder("\\languages\\" + jsonElementEntry.getValue().getAsString())));
        }

        // Loads the language file.
        this.loadLanguageFile();
    }

    // Returns the languages.
    public ArrayList<String> getLanguages ()
    {
        return Arrays.stream(Objects.requireNonNull(Constants.getPluginFolder("\\languages\\").toFile().listFiles())).map(languageFile -> languageFile.getName().replace(".json", "").toUpperCase()).collect(Collectors.toCollection(ArrayList::new));
    }

    // Returns the language string.
    public String getLanguageString (String key)
    {
        return Constants.LANGUAGE_HASH_MAP.containsKey(key) ? Constants.LANGUAGE_HASH_MAP.get(key) : "Key not found! Outdated language file? (Key: " + key + ")";
    }
}