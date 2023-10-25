package com.example.spendsaver;

import java.util.ArrayList;
import java.util.Date;

public class Depense {
    private float Montant;
    private String Description;
    private Date date;
    public static ArrayList<String> Categories = new ArrayList<>();

    public Depense(float montant, String description, Date date) {
        Montant = montant;
        Description = description;
        this.date = date;
    }

    public float getMontant() {
        return Montant;
    }

    public void setMontant(float montant) {
        Montant = montant;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static ArrayList<String> getCategories() {
        return Categories;
    }

    public static void setCategories(ArrayList<String> categories) {
        Categories = categories;
    }


}
