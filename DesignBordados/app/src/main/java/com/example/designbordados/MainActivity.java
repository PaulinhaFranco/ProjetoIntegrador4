package com.example.designbordados;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLoja;
    Button btnSC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnLoja = findViewById(R.id.btnLoja);
        btnLoja.setOnClickListener(this);

        btnSC = findViewById(R.id.btnSC);
        btnSC.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == btnLoja){
            Intent telafuncionario = new Intent(this, AtvFuncionario.class);
            startActivity(telafuncionario);
        }

    }
}