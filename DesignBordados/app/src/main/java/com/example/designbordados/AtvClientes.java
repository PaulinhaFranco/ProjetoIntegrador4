package com.example.designbordados;

import static android.widget.AdapterView.*;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class AtvClientes extends AppCompatActivity implements View.OnClickListener, OnItemClickListener {

    Button btnAdd;
    EditText editTextClientId;
    ListView lstCliente;
    List<Cliente> listaClientes = new ArrayList<Cliente>();
    ListAdapter listAdapter;
    int indice;
    ClienteDao dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.atv_clientes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        lstCliente = findViewById(R.id.lstCliente);
        lstCliente.setOnItemClickListener(this);

        editTextClientId = findViewById(R.id.editTextClientId);


        dao = new ClienteDao(this);
        atualizarLista();

        // Adicionando o TextWatcher apenas uma vez, geralmente no onCreate
        editTextClientId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Aqui você pode implementar a lógica de busca do cliente
                String nomeCliente = s.toString().toLowerCase(); // Convertendo para minúsculas para uma busca insensível a maiúsculas e minúsculas
                List<Cliente> clientesFiltrados = new ArrayList<>();

                // Iterando sobre a lista de clientes para filtrar
                for (Cliente cliente : listaClientes) {
                    if (cliente.getNome().toLowerCase().contains(nomeCliente)) { // Verificando se o nome do cliente contém o texto digitado
                        clientesFiltrados.add(cliente); // Adicionando o cliente filtrado à lista de clientes filtrados
                    }
                }

                // Criando um novo ArrayAdapter com a lista de clientes filtrados
                ArrayAdapter<Cliente> adapter = new ArrayAdapter<>(AtvClientes.this, android.R.layout.simple_list_item_1, clientesFiltrados);

                // Definindo o novo adapter para a ListView
                lstCliente.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }




    @Override
    protected void onResume(){
        super.onResume();
        atualizarLista();
    }

    private void atualizarLista() {
        listaClientes = dao.listar();
        listAdapter = new ArrayAdapter<Cliente>(this, android.R.layout.simple_list_item_1, listaClientes);
        lstCliente.setAdapter(listAdapter);

    }

    @Override
    public void onClick(View v) {

        if (v == btnAdd) {
            Cliente c = new Cliente();
            c.setId(0L);
            abrirCadastro("INSERIR", c);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        indice = position;
        Cliente c = (Cliente) lstCliente.getAdapter().getItem(position);
        abrirCadastro("ALTERAR", c);
    }

    private void abrirCadastro(String acao, Cliente obj) {
        Intent telaCad = new Intent(this, AtvCadCliente.class);
        Bundle extras = new Bundle();
        extras.putString("acao",acao);
        extras.putSerializable("obj",obj);
        telaCad.putExtras(extras);
        startActivity(telaCad);
    }



}