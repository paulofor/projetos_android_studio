package br.com.lojadigicom.coletorprecocliente.app;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.lojadigicom.coletorprecocliente.R;
import br.com.lojadigicom.coletorprecocliente.data.contract.NaturezaProdutoContract;
import br.com.lojadigicom.coletorprecocliente.data.contract.UsuarioPesquisaContract;
import br.com.lojadigicom.coletorprecocliente.framework.data.DCCursorLoader;
import br.com.lojadigicom.coletorprecocliente.modelo.NaturezaProduto;
import br.com.lojadigicom.coletorprecocliente.modelo.UsuarioPesquisa;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ResumoInscricao.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ResumoInscricao#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResumoInscricao extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final int RESUMO_LOADER = 0;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private OnFragmentInteractionListener mListener;


    private TextView mTotalInscritas = null;
    private TextView mTotalGratuita = null;
    private TextView mTotalPaga = null;

    public ResumoInscricao() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResumoInscricao.
     */
    // TODO: Rename and change types and number of parameters
    public static ResumoInscricao newInstance(String param1, String param2) {
        ResumoInscricao fragment = new ResumoInscricao();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View tela = inflater.inflate(R.layout.fragment_resumo_inscricao, container, false);
        mTotalGratuita = (TextView) tela.findViewById(R.id.txtTotalGratuita);
        mTotalInscritas = (TextView) tela.findViewById(R.id.txtTotalInscricao);
        mTotalPaga = (TextView) tela.findViewById(R.id.txtTotalPagas);
        return  tela;
    }


    @Override
    public final void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(RESUMO_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = UsuarioPesquisaContract.buildAll();
        return new DCCursorLoader(getActivity(),uri,null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<UsuarioPesquisa> lista = UsuarioPesquisaContract.converteLista(data);
        int qtde = lista.size();
        mTotalPaga.setText(String.valueOf(0));
        mTotalInscritas.setText(String.valueOf(qtde));
        mTotalGratuita.setText(String.valueOf(qtde));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

}
