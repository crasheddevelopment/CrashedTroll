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

package de.crasheddevelopment.spigot.crashedtroll.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class JsonUtils
{
    // Resolves the json object from an url.
    public static JsonObject getJsonObjectFromURL (String url)
    {
        try
        {
            JsonParser jsonParser = new JsonParser();
            URLConnection urlConnection = new URL(url).openConnection();
            urlConnection.connect();

            return jsonParser.parse(new InputStreamReader((InputStream) urlConnection.getContent())).getAsJsonObject();
        }
        catch (MalformedURLException malformedURLException)
        {
            StringUtils.sendInformation("MalformedURLException while reading json object from " + url + "!");
            malformedURLException.printStackTrace();
        }
        catch (IOException ioException)
        {
            StringUtils.sendInformation("IOException while reading json object from " + url + "!");
            ioException.printStackTrace();
        }
        return null;
    }
}