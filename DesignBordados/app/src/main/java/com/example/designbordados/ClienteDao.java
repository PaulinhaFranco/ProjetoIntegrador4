package com.example.designbordados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ClienteDao {
    private final String TABELA = "cliente";
    private final String[] CAMPOS = {"id", "nome", "fone", "cpf", "email"};
    private final Conexao conexao;
    private final SQLiteDatabase banco;

    public ClienteDao(Context context) {
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    private ContentValues preencherValores(Cliente cliente){
        ContentValues values = new ContentValues();

        values.put("nome", cliente.getNome());
        values.put("fone", cliente.getFone());
        values.put("cpf", cliente.getCpf());
        values.put("email", cliente.getEmail());

        return values;
    }

    public long INSERIR (Cliente cliente) {
        ContentValues values = preencherValores(cliente);
        return banco.insert(TABELA, null, values);
    }
    public long ALTERAR (Cliente cliente) {
        ContentValues values = preencherValores(cliente);
        String idString = String.valueOf(cliente.getId());
        return banco.update(TABELA, values,"id = ?", new String[]{idString});

    }

    public long EXCLUIR (Cliente cliente){
        String idString = String.valueOf(cliente.getId());
        return banco.delete(TABELA, "id = ?", new String[]{idString});
    }


    public List<Cliente> listar() {
        Cursor c = banco.query(TABELA, CAMPOS, null,null,null,null,"nome COLLATE NOCASE ASC");

        List<Cliente> lista = new ArrayList<Cliente>();
        while (c.moveToNext()){
            Cliente cliente = new Cliente();
            cliente.setId(c.getLong(0));
            cliente.setNome(c.getString(1));
            cliente.setFone(c.getString(2));
            cliente.setCpf(c.getString(3));
            cliente.setEmail(c.getString(4));
            lista.add(cliente);
        }
        return lista;
    }

}

