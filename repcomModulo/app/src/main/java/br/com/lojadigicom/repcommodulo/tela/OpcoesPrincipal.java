package br.com.lojadigicom.repcommodulo.tela;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.lojadigicom.repcommodulo.R;


/**
 * Created by Paulo on 22/10/2016.
 */

public class OpcoesPrincipal extends Fragment {


    private View btnEscolhedor = null;

    public OpcoesPrincipal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_opcoes_principal, container, false);
        btnEscolhedor = (View) v.findViewById(R.id.btnEscolhedor);
        btnEscolhedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamaVitrine(view);

            }
        });
        return v;
    }

    public void chamaVitrine(View v) {
        //Intent vitrine = new Intent(this.getActivity(),VitrineProdutoActivity.class);
        //startActivity(vitrine);
    }

}

