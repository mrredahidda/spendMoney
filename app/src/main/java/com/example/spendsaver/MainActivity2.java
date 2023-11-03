package com.example.spendsaver;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    Button btnBack, btnDel, btnEdit;
    ListView lv;
    int selectedPosition = -1;
    private static final String FILE_NAME = "expenses_data.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnDel = findViewById(R.id.btnDel);
        btnBack = findViewById(R.id.btnBack);
        lv = findViewById(R.id.listView);

        ArrayAdapter<Ddepense> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Ddepense.depenses);
        lv.setAdapter(arrayAdapter);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition != -1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                    builder.setTitle("Confirmation de la suppression")
                            .setMessage("Êtes-vous sûr de vouloir supprimer cet élément ?")
                            .setPositiveButton("Oui", new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Ddepense.depenses.remove(selectedPosition);
                                    arrayAdapter.notifyDataSetChanged();
                                    selectedPosition = -1;
                                    Toast.makeText(MainActivity2.this, "Élément supprimé", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                } else {
                    Toast.makeText(MainActivity2.this, "Aucun élément sélectionné.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}