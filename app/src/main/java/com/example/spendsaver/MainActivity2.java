package com.example.spendsaver;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnDel = findViewById(R.id.btnDel);
        btnBack = findViewById(R.id.btnBack);
        btnEdit = findViewById(R.id.btnEdit);
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
                    Ddepense.depenses.remove(selectedPosition);
                    arrayAdapter.notifyDataSetChanged();
                    selectedPosition = -1;
                    Toast.makeText(MainActivity2.this, "Item deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity2.this, "No item selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveDataToFile() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(openFileOutput("Data.txt", MODE_PRIVATE));
            outputStream.writeObject(Ddepense.depenses);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDataFromFile() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(openFileInput("Data.txt"));
            Ddepense.depenses = (ArrayList<Ddepense>) inputStream.readObject();
            inputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveDataToFile();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadDataFromFile();
    }
}