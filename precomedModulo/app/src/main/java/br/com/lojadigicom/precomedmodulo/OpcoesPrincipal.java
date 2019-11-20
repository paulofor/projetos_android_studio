package br.com.lojadigicom.precomedmodulo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * A simple {@link Fragment} subclass.
 */
public class OpcoesPrincipal extends Fragment {


    private View btnEscolhedor = null;
    private View btnEscolhedorListas = null;
    private View btnOportunidade = null;

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
                //chamaVitrine(view);

            }
        });
        btnEscolhedorListas = (View) v.findViewById(R.id.btnEscolhedorListas);
        btnEscolhedorListas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamaListaNomes(view);

            }
        });
        btnOportunidade = (View) v.findViewById(R.id.btnOportunidade);
        btnOportunidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //chamaOportunidade(view);

            }
        });
        return v;
    }

    public void chamaListaNomes(View v) {
        //Intent vitrine = new Intent(this.getActivity(),VitrineProdutoActivity.class);
        //vitrine.putExtra(VitrineProdutoActivity.CHAVE_TOOLBAR,"Shopping");
        //startActivity(vitrine);
    }

}
