package com.example.cocktail.model.byRandom;

import java.util.List;

public class RandomCockTailModel {

    String name;
    String id;
    List<String> ingrients;
    String instructions;
    String imageURL;
    String alcoholic;

    public RandomCockTailModel(String name, List<String> ingredients, String id, String instructions, String imageURL, String alcoholic) {
        this.name = name;
        this.id = id;
        this.ingrients = ingredients;
        this.imageURL = imageURL;
        this.alcoholic = alcoholic;
        this.instructions = instructions;
    }

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
