package com.rememberthekey.cocktail.network.URLImage;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

public class ImageCockTailSearchAsyncTask extends AsyncTask<String, Void, Bitmap> {
    private static final String TAG = "ImageCockTailSearchAsyn";
    private ImageCockTailListener listener;

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap response = ImageCockTailSearchHelper.getImage(strings[0]);

        if (response != null) {
            return response;
        } else {
            Log.d(TAG, "THe response was null");
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap image) {
        super.onPostExecute(image);
        listener.ImageContract(image);
    }

    public interface ImageCockTailListener {
        void ImageContract(Bitmap image);
    }

    public void setImageListener(ImageCockTailListener listener) {
        this.listener = listener;
    }
}
