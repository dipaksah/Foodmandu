package com.dipak.foodmandu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Model.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener  {


    DatabaseHelper db;
    TextView tvBackToLogin;
    EditText etFname,etLname,etAddress,etContact,etEmail,etPassword,etRePassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        tvBackToLogin=findViewById(R.id.tvback);
        etFname=findViewById(R.id.etfirstname);
        etLname=findViewById(R.id.etlastname);
        etAddress=findViewById(R.id.etaddress);
        etContact=findViewById(R.id.etnumber);
        etEmail=findViewById(R.id.etemail);
        etPassword=findViewById(R.id.etpassword);
        etRePassword=findViewById(R.id.etrepassword);
        btnRegister=findViewById(R.id.btnregister);


        db=new DatabaseHelper(this);
        tvBackToLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvback:{
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btnregister:{
                String fname=etFname.getText().toString();
                String lname=etLname.getText().toString();
                String address=etAddress.getText().toString();
                String contact=etContact.getText().toString();
                String email=etEmail.getText().toString();
                String password=etPassword.getText().toString();
                String Repassword=etRePassword.getText().toString();

                if (fname.equals("")||lname.equals("")||address.equals("")||contact.equals("")||email.equals("")||password.equals("")||Repassword.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_LONG).show();
                }else {
                    if (password.equals(Repassword)){
                        boolean CheckAlreadyEmail=db.CheckAlreadyEmail(password);
                        if (CheckAlreadyEmail==true){
                            boolean insert=db.insert(fname,lname,address,contact,email,password);
                            if (insert==true){
                                Toast.makeText(getApplicationContext(), "Register Successfully", Toast.LENGTH_LONG).show();
                            }
                        }else {
                            Toast.makeText(getApplicationContext(), "Email Address Already Exists", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                  break;
            }
        }
    }


}
