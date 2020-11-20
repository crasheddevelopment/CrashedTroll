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

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;

public class DownloadUtils
{
    // Download a file.
    public static void downloadFile (String url, Path path)
    {
        try
        {
            // Initialize and setup the variables.
            CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();

            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader(HttpHeaders.USER_AGENT, "CrashedTroll Plugin");

            CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);
            InputStream inputStream = closeableHttpResponse.getEntity().getContent();
            ReadableByteChannel readableByteChannel = Channels.newChannel(inputStream);

            FileOutputStream fileOutputStream = new FileOutputStream(path.toFile());
            fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);

            // Closes.
            closeableHttpClient.close();
            inputStream.close();
            readableByteChannel.close();
            fileOutputStream.close();
        }
        catch (IOException ioException)
        {
            StringUtils.sendInformation("IOException while downloading the file!");
            ioException.printStackTrace();
        }
    }
}