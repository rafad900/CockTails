package com.rememberthekey.cocktail.network.URLImage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.net.URL;

public class ImageCockTailSearchHelper {
    private static final String TAG = "ImageCockTailSearchHelp";

    public static Bitmap getImage(String imageURL) {
        try {

            URL newurl = new URL(imageURL);
            Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
            return mIcon_val;

        } catch (IOException e) {
            Log.e(TAG, "searchCockTails: ", e );
            Log.d(TAG, "searchCockTails: HERE IS THE IMAGE URL: " + imageURL);
        }
        return null;
    }
}
