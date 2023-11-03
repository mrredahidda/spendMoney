package com.example.spendsaver;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Ddepense implements Serializable {
    private float Montant;
    private String Description;
    private String Categorie;
    private Date date;
    public static ArrayList<Ddepense> depenses = new ArrayList<>();

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return "Categorie : " +getCategorie()+ " | " + " Montant : " +getMontant()+ " | " + " Description : " +getDescription()+ " | " + " Date : " +simpleDateFormat.format(date);
    }

    public Ddepense(String categorie,float montant, String description, Date date) {
        Montant = montant;
        Description = description;
        Categorie = categorie;
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

    public Date getDate() {return date;
    }

    public void setDate(Date date) {
        this.date = date;

    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String categorie) {
        Categorie = categorie;
    }
}
