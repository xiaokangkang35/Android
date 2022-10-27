package com.example.course3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecActivity extends AppCompatActivity {
    private TextView textView;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        textView = findViewById(R.id.tv);
        intent = getIntent();
        String text = intent.getStringExtra("data");
        textView.setText(text);
    }
}
