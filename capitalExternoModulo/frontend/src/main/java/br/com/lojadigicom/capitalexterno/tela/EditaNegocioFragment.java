package br.com.lojadigicom.capitalexterno.tela;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Timestamp;

import br.com.lojadigicom.capitalexterno.framework.util.UtilDatas;
import br.com.lojadigicom.capitalexterno.modelo.Negocio;
import br.com.lojadigicom.capitalexterno.tela.base.fragment.NegocioFragmentEditaBase;
import lojadigicom.com.br.frontend.R;


public class EditaNegocioFragment extends NegocioFragmentEditaBase {

    private EditText txtNomeNegocio = null;



    public EditaNegocioFragment() {
    }


    @Override
    protected void copiaTelaParaVo(Negocio item) {
        item.setDescricao(txtNomeNegocio.getText().toString());
        item.setDataCriacao(UtilDatas.getDataHora());
    }

    @Override
    protected void inicializaCamposTela(View v) {
        txtNomeNegocio = getEditText(v, R.id.txtNomeNegocio,"R.id.txtNomeNegocio");
    }

    @Override
    protected int getIdLayout() {
        return R.layout.fragment_edita_negocio;
    }

    @Override
    protected int getIdBtnOk() {
        return R.id.btnOk;
    }
}
