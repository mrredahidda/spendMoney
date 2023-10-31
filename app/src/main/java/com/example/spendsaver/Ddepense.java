package com.example.spendsaver;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Ddepense {
    private float Montant;
    private String Description;
    private Date date;
    public static ArrayList<String> Categories = new ArrayList<>();
    public static ArrayList<Ddepense> depenses = new ArrayList<>();

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return "Montant : " +getMontant()+ " | " + " Description : " +getDescription()+ " | " + " Date : " +simpleDateFormat.format(date);
    }

    public Ddepense(float montant, String description, Date date) {
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
