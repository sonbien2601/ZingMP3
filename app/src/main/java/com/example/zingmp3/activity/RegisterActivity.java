package com.example.zingmp3.activity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zingmp3.R;

import java.io.InputStream;

public class RegisterActivity extends AppCompatActivity {
    EditText edtRePW, edtTen, edtPW;
    Button btnRegisterIn, btnBackRegis;
    DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addControl();
        addEvent();
    }
    private void addControl() {
        btnBackRegis = findViewById(R.id.btnBackRegis);
        btnRegisterIn = findViewById(R.id.btnRegisterIn);
        edtRePW = findViewById(R.id.edtRePW);
        edtPW = findViewById(R.id.edtPW);
        edtTen = findViewById(R.id.edtTen);
        dbHelper = new DBHelper(this);
    }
    private void addEvent() {
        btnBackRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        btnRegisterIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user, pw, repw;
                user = edtTen.getText().toString();
                pw = edtPW.getText().toString();
                repw = edtRePW.getText().toString();
                if (user.equals("") || pw.equals("") || repw.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Hãy nhập vào các ô", Toast.LENGTH_LONG).show();
                } else {
                    if (pw.equals(repw)) {
                        if (dbHelper.checkUserName(user)){
                            Toast.makeText(RegisterActivity.this, "Tên đăng nhập đã tồn tại", Toast.LENGTH_LONG).show();
                            return;
                        }
                        boolean registerSucces = dbHelper.insertData(user, pw);
                        if (registerSucces)
                            Toast.makeText(RegisterActivity.this, "Đăng kí tài khoản thành công", Toast.LENGTH_LONG).show();
                        else {
                            Toast.makeText(RegisterActivity.this, "Đăng kí tài khoản thất bại", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Mật khẩu không khớp", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}

