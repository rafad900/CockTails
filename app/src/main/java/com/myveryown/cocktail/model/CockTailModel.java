package com.myveryown.cocktail.model;

import android.graphics.Bitmap;

import com.myveryown.cocktail.network.URLImage.ImageCockTailSearchAsyncTask;

import java.util.List;

public class CockTailModel {

    String name;
    String id;
    List<String> ingrients;
    String instructions;
    String imageURL;
    String alcoholic;
    Bitmap mimage;

    public CockTailModel(String name, List<String> ingredients, String id, String instructions, String imageURL, String alcoholic) {
        this.name = name;
        this.id = id;
        this.ingrients = ingredients;
        this.imageURL = imageURL;
        this.alcoholic = alcoholic;
        this.instructions = instructions;
        setImage();
    }

    public CockTailModel(String name, String id) {
        this.name = name;
        this.id = id;
        this.ingrients = null;
        this.imageURL = "";
        this.alcoholic = "";
        this.instructions = "";
        setImage();
    }

    public CockTailModel(String name, String id, String imageURL) {
        this.name = name;
        this.id = id;
        this.imageURL = imageURL;
        this.ingrients = null;
        this.alcoholic = "";
        this.instructions = "";
        setImage();
    }

    private void setImage() {
        if (imageURL != "") {
            ImageCockTailSearchAsyncTask task = new ImageCockTailSearchAsyncTask();
            task.setImageListener(new ImageCockTailSearchAsyncTask.ImageCockTailListener() {
                @Override
                public void ImageContract(Bitmap image) {
                    mimage = image;
                }
            });
            task.execute(this.imageURL);
        }
    }

    public Bitmap getImage() { return mimage; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getIngrients() {
        return ingrients;
    }

    public void setIngrients(List<String> ingrients) {
        this.ingrients = ingrients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getAlcoholic() {
        return alcoholic;
    }

    public void setAlcoholic(String alcoholic) {
        this.alcoholic = alcoholic;
    }
}
