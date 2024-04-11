package com.example.designbordados;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class AtvCadCliente extends AppCompatActivity implements View.OnClickListener {

    Button btnAdd;
    Button btnVoltar;
    Button btnExcluir;
    EditText edtCod;
    EditText edtNome;
    EditText edtFone;
    EditText edtCPF;
    EditText edtEmail;
    String acao;
    Cliente c;
    ClienteDao dao;

    private void criarComponentes() {
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnAdd.setText(acao);

        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(this);

        btnExcluir = findViewById(R.id.btnExcluir);
        btnExcluir.setOnClickListener(this);

        if (acao.equals("INSERIR"))
            btnExcluir.setVisibility(View.INVISIBLE);
        else
            btnExcluir.setVisibility(View.VISIBLE);

        edtCod = findViewById(R.id.edtCod);
        edtNome = findViewById(R.id.edtNome);
        edtFone = findViewById(R.id.edtFone);
        edtCPF = findViewById(R.id.edtCPF);
        edtEmail = findViewById(R.id.edtEmail);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.atv_cad_cliente);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        acao = Objects.requireNonNull(getIntent().getExtras()).getString("acao");
        dao = new ClienteDao(this);
        criarComponentes();

        if (getIntent().getExtras().getSerializable("obj") != null) {
            c = (Cliente) getIntent().getExtras().getSerializable("obj");

            assert c != null;
            edtCod.setText(c.getId().toString());
            edtNome.setText(c.getNome());
            edtFone.setText(c.getFone());
            edtCPF.setText(c.getCpf());
            edtEmail.setText(c.getEmail());
        }

    }

    public void onClick(View v) {

        if (v == btnVoltar) {
            finish();

        }else if (v == btnExcluir) {
            long id = dao.EXCLUIR(c);
            Toast.makeText(this, "Cliente " + c.getNome() + " excluido com sucesso!", Toast.LENGTH_LONG).show();
            finish();

        } else if (v == btnAdd) {
            c.setNome(edtNome.getText().toString());
            c.setFone(edtFone.getText().toString());
            c.setCpf(edtCPF.getText().toString());
            c.setEmail(edtEmail.getText().toString());


            if(acao.equals("INSERIR")){
                long id = dao.INSERIR(c);
                Toast.makeText(this, "Cliente " + c.getNome() + " foi inserido com o id = " + id, Toast.LENGTH_LONG).show();
            }
            else {
                long id = dao.ALTERAR(c);
                Toast.makeText(this, "Cliente " + c.getNome() + " foi alterado com sucesso!", Toast.LENGTH_LONG).show();
            }
            finish();

        }

    }


    }
