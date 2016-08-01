package com.quandoo.restaurants.ui.utils;

import android.content.Context;

import com.quandoo.restaurants.RestaurantsApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by hendabdel-latif on 7/27/16.
 */
public class Utilities {

    /**
     * Read file from asset folder and return input stream
     *
     * @param filePath
     */
    public static InputStream readFileFromAssetsWithInResult(Context context, String filePath) throws IOException {
        return context.getAssets().open(filePath);
    }


    /**
     * Read file from asset folder and return String
     *
     * @param filePath
     */
    public static String readFileFromAssetsWithStringResult(Context context, String filePath) throws IOException {
        InputStream inputStream = readFileFromAssetsWithInResult(context, filePath);
        return getContent(inputStream);

    }

    /**
     * Read the content of {@link InputStream}
     *
     * @param inputStream
     * @return
     */
    public static String getContent(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }

    /**
     * Read the content of asset file
     *
     * @param fileName
     * @return
     */
    public static String getDataFromAsset(String fileName) {

        String content = null;

        try {
            content = Utilities.readFileFromAssetsWithStringResult(
                    RestaurantsApplication.sInstance, fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }


}
