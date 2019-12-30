package com.dipak.foodmandu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Model.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {



    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    TextView tvRegister;
    EditText etEmail,etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        tvRegister=findViewById(R.id.tvsignup);
        etEmail=findViewById(R.id.etemail);
        etPassword=findViewById(R.id.etpassword);
        btnLogin=findViewById(R.id.btnlogin);

        openHelper=new DatabaseHelper(this);
        db=openHelper.getReadableDatabase();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=etEmail.getText().toString();
                String password=etPassword.getText().toString();

                    Cursor cursor=db.rawQuery(" SELECT * FROM " +
                            DatabaseHelper.TABLE_NAME+ " WHERE " +
                            DatabaseHelper.email + " =?" + " AND " +
                            DatabaseHelper.password + "=?",
                            new String[]{email,password});

                if (cursor!=null){
                    if (cursor.getCount()>0){
                        cursor.moveToNext();
                    Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(LoginActivity.this,DashboardActivity.class);
                    startActivity(intent);
                    etEmail.setText(null);
                    etPassword.setText(null);

                    }else {
                        Toast.makeText(getApplicationContext(), "Incorrect Email and password", Toast.LENGTH_LONG).show();
                        etEmail.setText(null);
                        etPassword.setText(null);
                    }
                }

            }
        });



        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
