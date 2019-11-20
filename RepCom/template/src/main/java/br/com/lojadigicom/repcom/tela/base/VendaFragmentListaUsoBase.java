
package  br.com.lojadigicom.repcom.tela.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.lojadigicom.repcom.MyAdapter;
import br.com.lojadigicom.repcom.modelo.Venda;
import br.com.lojadigicom.repcom.template.R;

public abstract class VendaFragmentListaUsoBase extends Fragment{

	private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
	
	
	public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.lista_padrao, container, false);
        super.onCreate(savedInstanceState);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rec_lista_padrao);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

		/*
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
        */
        mAdapter = new MyAdapter(getLista());
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }
    
    protected abstract List<Venda> getLista();
	
}