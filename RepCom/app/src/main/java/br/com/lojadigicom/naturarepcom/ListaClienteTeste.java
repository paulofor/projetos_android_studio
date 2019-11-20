package br.com.lojadigicom.naturarepcom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.lojadigicom.repcom.MyAdapter;
import br.com.lojadigicom.repcom.modelo.Cliente;
import br.com.lojadigicom.repcom.modelo.ClienteVo;

public class ListaClienteTeste extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Mudança 1 - Layout
        //setContentView(R.layout.activity_lista_cliente_teste);
        //mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        setContentView(R.layout.lista_padrao);
        mRecyclerView = (RecyclerView) findViewById(R.id.rec_lista_padrao);


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<Cliente> listaCliente = new ArrayList<Cliente>();
        Cliente cliente = new ClienteVo();
        cliente.setNome("Cliente 1");
        listaCliente.add(cliente);
        cliente = new ClienteVo();
        cliente.setNome("Cliente 2");
        listaCliente.add(cliente);
        cliente = new ClienteVo();
        cliente.setNome("Cliente 3");
        listaCliente.add(cliente);

        String[] myDataset = {"Linha1","Linha2","Linha3"};
        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(listaCliente);
        mRecyclerView.setAdapter(mAdapter);
    }


}
