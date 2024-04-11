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

public class AtvFuncionario extends AppCompatActivity implements View.OnClickListener {


    Button btnOS;
    Button btnfc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.atv_funcionario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnOS = findViewById(R.id.btnOS);
        btnOS.setOnClickListener(this);

        btnfc = findViewById(R.id.btnfc);
        btnfc.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        if (v == btnOS) {
            finish();
        } else if (v == btnfc) {
            Intent telaclientecad = new Intent(this, AtvClientes.class);
            startActivity(telaclientecad);

        }
    }
}






