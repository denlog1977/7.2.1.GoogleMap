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
                String location = "";
                String editTextString = editText.getText().toString();

                if (editTextString.length() == 0) {
                    Toast.makeText(MainActivity.this, R.string.address, Toast.LENGTH_SHORT).show();
                    return;
                }

                Boolean isLetter = false;

                for (int i=0; i<editTextString.length(); i++) {
                    char character = editTextString.charAt(i);
                    if (Character.isLetter(character)) {
                        isLetter = true;
                    }
                    if (isLetter) {
                        location = "geo:?q=" + editTextString;
                    } else {
                        location = "geo:" + editTextString;
                    }
                }

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(location));
                startActivity(intent);
//                startActivityForResult(intent, -1);
//                Uri uri = Uri.parse("tel:+7 (495) 152-55-28");
//                Intent intent = new Intent(Intent.ACTION_DIAL,uri);
//                startActivity(intent);
            }
        });

        Button buttonPhoneNumber = findViewById(R.id.buttonPhoneNumber);
        buttonPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editText = findViewById(R.id.editTextPhoneNumber);
                String location = "";
                String editTextString = editText.getText().toString();

                if (editTextString.length() == 0) {
                    Toast.makeText(MainActivity.this, R.string.TypePhoneNumber, Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(editTextString));
                startActivity(intent);
            }
        });

    }



}
