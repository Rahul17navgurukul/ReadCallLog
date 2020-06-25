package com.rahulkashyap.calldetailsfromrecentcalllog.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rahulkashyap.calldetailsfromrecentcalllog.R;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

    }

    public void btn(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }
}
