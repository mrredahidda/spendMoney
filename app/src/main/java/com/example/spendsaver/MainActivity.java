package com.example.spendsaver;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btnShow , btnAdd;
    Spinner spinner;
    ImageView cal;
    TextView txtDate;
    EditText montant , description;
    private Date date;
    private static final String FILE_NAME = "expenses_data.ser";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadExpenses();

        spinner = findViewById(R.id.spinner);

        btnShow = findViewById(R.id.btnShow);
        btnAdd = findViewById(R.id.btnAdd);

        txtDate = findViewById(R.id.txtDate);
        cal = findViewById(R.id.cal);
        montant = findViewById(R.id.montant);
        description = findViewById(R.id.description);


        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(montant.getText().toString().isEmpty() || description.getText().toString().isEmpty()){
                        Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs obligatoires", Toast.LENGTH_SHORT).show();
                    } else {
                        Ddepense d = new Ddepense(spinner.getSelectedItem().toString(),Float.parseFloat(montant.getText().toString()),description.getText().toString(),date);
                        Ddepense.depenses.add(d);
                        montant.setText("");
                        description.setText("");
                        Toast.makeText(MainActivity.this, "C'est fait", Toast.LENGTH_SHORT).show();
                    }

                    saveExpenses();
            }
        });

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        txtDate.setText(day + " - " + (month + 1) + " - " + year);
        date = calendar.getTime();
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        txtDate.setText(day + " - " + (month + 1) + " - " + year);
                        calendar.set(year, month, day);
                        date = calendar.getTime();
                    }
                },
                        year, month, day);
                datePickerDialog.show();
            }
        });
    }

    private void saveExpenses() {
        try (FileOutputStream fos = openFileOutput(FILE_NAME, getApplicationContext().MODE_PRIVATE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(Ddepense.depenses);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Erreur", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadExpenses() {
        File file = new File(getFilesDir(), FILE_NAME);
        if (file.exists()) {
            try (FileInputStream fis = openFileInput(FILE_NAME);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                Ddepense.depenses = (ArrayList<Ddepense>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}