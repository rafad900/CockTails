package com.example.cocktail.model;

public class CockTailModel {

    private String ingredientID;
    private String ingredientStr;
    private String descriptionStr;
    private String typeStr;
    private String alcoholStr;
    private String ABVStr;

    public String getIngredientID() {
        return ingredientID;
    }

    public void setIngredianteID(String ingredianteID) {
        this.ingredientID = ingredianteID;
    }

    public String getIngredientStr() {
        return ingredientStr;
    }

    public void setIngredientStr(String ingredianteStr) {
        this.ingredientStr = ingredianteStr;
    }

    public String getDescriptionStr() {
        return descriptionStr;
    }

    public void setDescriptionStr(String descriptionStr) {
        this.descriptionStr = descriptionStr;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public String getAlcoholStr() {
        return alcoholStr;
    }

    public void setAlcoholStr(String alcoholStr) {
        this.alcoholStr = alcoholStr;
    }

    public String getABVStr() {
        return ABVStr;
    }

    public void setABVStr(String ABVStr) {
        this.ABVStr = ABVStr;
    }
}
