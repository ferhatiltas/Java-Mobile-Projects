package com.ferhatiltas.sharedpreference_benihatirla;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText etUserName, etPassword;
    CheckBox chkREmemberMe;
    Button btnLogin;
    SharedPref sharedPref;
    Context context=this;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        idTanimla();
        if (sharedPref.getValueBoolean(context, "remember")) {
            //BEni hatırlar işaretlenmiişse
            etUserName.setText(sharedPref.getValue(context,"username"));
            chkREmemberMe.setChecked(sharedPref.getValueBoolean(context,"remember"));
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUserName.getText().toString().equals(getString(R.string.test_username))// username== deneme , password==deneme
                        && etPassword.getText().toString().equals(getString(R.string.test_password))){
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    if (chkREmemberMe.isChecked()) {
                        sharedPref.save(context,"usernamme",etUserName.getText().toString());
                    }else {sharedPref.save(context,"usernamme","");}
                        sharedPref.saveBoolean(context,"remember",chkREmemberMe.isChecked());//Checkbox seçili ise tru değilse false değeri ver
                }else Toast.makeText(context, "Kullanıcı adi veya sifre hatalı", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void idTanimla() {
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        chkREmemberMe = findViewById(R.id.chkRememberMe);
        btnLogin = findViewById(R.id.btnLogin);
        sharedPref = new SharedPref();

    }
}
