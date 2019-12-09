package com.example.a721googlemap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 11;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 12;

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

            }
        });

        Button buttonPhoneNumber = findViewById(R.id.buttonPhoneNumber);
        buttonPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // Разрешение не получено. Делаем запрос на добавление разрешения звонка
                    ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                } else { // Разрешение уже получено
                    EditText editText = findViewById(R.id.editTextPhoneNumber);
                    String editTextString = editText.getText().toString();


                    if (editTextString.length() == 0) {
                        Toast.makeText(MainActivity.this, R.string.TypePhoneNumber, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+editTextString));
                    startActivity(intent);
                }

            }
        });

        Button buttonSMS = findViewById(R.id.buttonSMS);
        buttonSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    // Разрешение не получено. Делаем запрос на добавление разрешения звонка
                    ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
                } else {

                    // Разрешение уже получено

                    EditText editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
                    String phoneNumber = editTextPhoneNumber.getText().toString();

                    EditText editTextMessage = findViewById(R.id.editTextMessage);
                    String textMessage = editTextMessage.getText().toString();

                    if (phoneNumber.length() == 0) {
                        Toast.makeText(MainActivity.this, R.string.TypePhoneNumber, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (textMessage.length() == 0) {
                        Toast.makeText(MainActivity.this, R.string.inputTextMessage, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("smsto:"));
                    intent.setType("vnd.android-dir/mms-sms");
//                    intent.setDataAndType(Uri.parse("smsto:"), "vnd.android-dir/mms-sms");
                    intent.putExtra("address",phoneNumber;
                    intent.putExtra("sms_body","Привет от программы 7.2.1.GoogleMap. " + textMessage);
                    startActivity(Intent.createChooser(intent,"Отправить смс с помощью"));

//                    SmsManager smgr = SmsManager.getDefault();
//                    smgr.sendTextMessage("+79053703000",null,editTextString,null,null);

                }
            }
        });

    }



}

