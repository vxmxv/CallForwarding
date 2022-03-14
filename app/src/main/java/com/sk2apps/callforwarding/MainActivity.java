package com.sk2apps.callforwarding;

import static android.Manifest.permission.CALL_PHONE;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editTextMobileNumber;
    private Button buttonCallForward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMobileNumber = (EditText)findViewById(R.id.editTextMobileNumber);
        buttonCallForward = (Button) findViewById(R.id.buttonCallForward);

        buttonCallForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String callForwardString = "**21*"+editTextMobileNumber.getText().toString()+"#";
                Intent intentCallForward = new Intent(Intent.ACTION_CALL);
                Uri uri2 = Uri.fromParts("tel", callForwardString, "#");
                intentCallForward.setData(uri2);
                if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intentCallForward);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{CALL_PHONE}, 1);
                    }
                }
            }
        });
    }
}