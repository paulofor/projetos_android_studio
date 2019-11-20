package br.com.lojadigicom.coletorprecocliente.app;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.data.contract.InteresseProdutoContract;
import br.com.lojadigicom.coletorprecocliente.framework.data.DCCursorLoader;


public class ContadorInteresseProdutoFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{


    private static final int DETAIL_LOADER = 99999;

    private int quantidadeMonitorada = 0;
    private int quantidadeEspera = 0;


    //private TextView txtContaMonitora = null;
    //private TextView txtContaEspera = null;

    private Button btnQtdeEspera = null;
    private Button btnQtdeMonitora = null;


    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contador_interesse_produto_rascunho, container, false);
        this.getActivity().getSupportLoaderManager().initLoader(DETAIL_LOADER, null, this);
        //txtContaEspera = (TextView) rootView.findViewById(R.id.txtContaEspera);
        //txtContaMonitora = (TextView) rootView.findViewById(R.id.txtContaMonitora);
        btnQtdeEspera = (Button) rootView.findViewById(R.id.btnQtdeEspera);
        btnQtdeEspera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamaEspera();
            }
        });
        btnQtdeMonitora = (Button) rootView.findViewById(R.id.btnQtdeMonitora);
        btnQtdeMonitora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamaMonitora();
            }
        });

        return rootView;
    }

    private void chamaEspera() {
        Intent mIntent = new Intent(this.getActivity(), ListaEsperaActivity.class);
        startActivity(mIntent);
    }
    private void chamaMonitora() {
        Intent mIntent = new Intent(this.getActivity(), ListaMonitoramentoActivity.class);
        startActivity(mIntent);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = InteresseProdutoContract.buildQuantidadeMonitorado();
        return new DCCursorLoader(this.getActivity(),uri,null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        quantidadeMonitorada = 0;
        quantidadeEspera = 0;
        if (data.moveToFirst()) {
            quantidadeMonitorada = data.getInt(data.getColumnIndex("qtde_monitora"));
            quantidadeEspera = data.getInt(data.getColumnIndex("qtde_espera"));
        }
        //txtContaMonitora.setText("" + quantidadeMonitorada);
        //txtContaEspera.setText("" + quantidadeEspera);
        String espera = getResources().getString(R.string.label_esperando);
        String monitora = getResources().getString(R.string.label_monitorando);
        btnQtdeEspera.setText(espera + "  " + quantidadeEspera);
        btnQtdeMonitora.setText(monitora + "  " + quantidadeMonitorada);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
