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

package de.crasheddevelopment.spigot.crashedtroll.core.system.network;

import de.crasheddevelopment.spigot.crashedtroll.utils.Constants;
import de.crasheddevelopment.spigot.crashedtroll.utils.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class PluginUpdater
{
    // Method to search an update.
    public void searchUpdate ()
    {
        try
        {
            // Initialize variables.
            // Opens the url and read the website content.
            URL url = new URL("https://api.github.com/repos/CrashedDevelopment/CrashedTroll/releases/latest");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String source;
            while ((source = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(source);
            }

            String[] source1 = stringBuilder.toString().split("\"browser_download_url\":");
            String[] source2 = source1[1].split("}],\"tarball_url\":");
            String source3 = source2[0].replace("\"", "");

            // Check if the latest release version equals the plugin version.
            if (!source3.contains(Constants.VERSION))
            {
                StringUtils.sendInformation("Update available!");
                StringUtils.sendInformation("Download it under: https://github.com/crasheddevelopment/CrashedTroll/releases/latest");
                Constants.UPDATE_AVAILABLE = true;
            }
            else
            {
                StringUtils.sendInformation("No update is available!");
            }
        }
        catch (IOException ioException)
        {
            StringUtils.sendInformation("IOException while searching a new update for this plugin!");
            ioException.printStackTrace();
        }
    }
}