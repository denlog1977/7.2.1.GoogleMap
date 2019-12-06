package com.example.a721googlemap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editText = findViewById(R.id.editText);
                TextView textView = findViewById(R.id.textView);
                String editTextString = editText.getText().toString();

                editTextString = "55.643747, 51.003607";

                Boolean isLetter = false;
                String toastMessage = "";

                for (int i=0; i<editTextString.length(); i++) {
                    char character = editTextString.charAt(i);
                    toastMessage = character + "-";
                    textView.append(toastMessage);
                    Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
                    if (Character.isLetter(character)) {
                        isLetter = true;
                        toastMessage = character  + " - !!! Это  буква !!!";
                        textView.append(toastMessage);
                        Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
                    }
                    editTextString = "geo:" + editTextString;
                    if (isLetter) {
                        editTextString = "?q=" + editTextString;
                    }

                }

//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(editTextString));
//                startActivityForResult(intent, -1);

//                Uri uri = Uri.parse("tel:+7 (495) 152-55-28");
//                Intent intent = new Intent(Intent.ACTION_DIAL,uri);
//                startActivity(intent);
            }
        });


    }
}
