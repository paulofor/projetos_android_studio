package br.com.lojadigicom.tela;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.lojadigicom.coletorprecocliente.app.ListaEsperaActivity;
import br.com.lojadigicom.coletorprecocliente.app.ListaMonitoramentoActivity;
import br.com.lojadigicom.coletorprecocliente.app.NaturezaProdutoDetalheImagemActivity;
import br.com.lojadigicom.coletorprecocliente.app.VitrineProdutoActivity;
import br.com.lojadigicom.coletorprecocliente.app.VitrineProdutoEscolhaActivity;
import br.com.lojadigicom.coletorprecocliente.tela.base.activity.Constantes;
import br.com.lojadigicom.olhonoprecosapato.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class OpcoesPrincipal extends Fragment {


    private View btnVitrine = null;
    private View btnListaEspera = null;
    private View btnListaMonitoramento = null;

    public OpcoesPrincipal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_opcoes_principal, container, false);
        btnVitrine = (View) v.findViewById(R.id.btnVitrine);
        btnVitrine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamaVitrineListas(view);

            }
        });

        btnListaEspera = (View) v.findViewById(R.id.btnListaEspera);
        btnListaEspera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamaListaEspera();
            }
        });

        btnListaMonitoramento = (View) v.findViewById(R.id.btnListaMonitoramento);
        btnListaMonitoramento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamaListaMonitoramento();
            }
        });


        return v;
    }




    private void chamaListaEspera() {
        Intent vitrine = new Intent(this.getActivity(), ListaEsperaActivity.class);
        //vitrine.putExtra(VitrineProdutoActivity.CHAVE_TOOLBAR,"Shopping");
        startActivity(vitrine);
    }

    private void chamaListaMonitoramento() {
        Intent vitrine = new Intent(this.getActivity(), ListaMonitoramentoActivity.class);
        //vitrine.putExtra(VitrineProdutoActivity.CHAVE_TOOLBAR,"Shopping");
        startActivity(vitrine);
    }


    public void chamaVitrineListas(View v) {
        Intent vitrine = new Intent(this.getActivity(),VitrineProdutoEscolhaActivity.class);
        vitrine.putExtra(VitrineProdutoEscolhaActivity.CHAVE_TOOLBAR,"Shopping Escolha");
        startActivity(vitrine);
    }


}
