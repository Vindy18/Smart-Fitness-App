package com.smartFitness.home.AppCommon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.smartFitness.home.AdminCommon.MainAdminLogin;
import com.smartFitness.home.CustomerCommon.Customer_view_Activity;
import com.smartFitness.home.R;

public class MainActivity extends AppCompatActivity {

    // variables
    Button btn_enter;
    Button btn_logAsAdmin;
    CharSequence message = "Loading";
    int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get elements by id
        btn_enter = (Button) findViewById(R.id.btn_enter);
        btn_logAsAdmin = (Button) findViewById(R.id.btn_logInAsAdmin);

        // event Listener for Enter button
        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Customer_view_Activity.class);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context,message,duration).show();
            }
        });

        // event Listener for Log_as_an_admin button
        btn_logAsAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainAdminLogin.class);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context,message,duration).show();
            }
        });
    }
}