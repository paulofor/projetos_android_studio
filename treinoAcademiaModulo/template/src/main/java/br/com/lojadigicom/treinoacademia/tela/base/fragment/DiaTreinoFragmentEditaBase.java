
package  br.com.lojadigicom.treinoacademia.tela.base.fragment;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.treinoacademia.modelo.DiaTreino;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import br.com.lojadigicom.treinoacademia.servico.base.DiaTreinoServico;
import br.com.lojadigicom.treinoacademia.servico.base.FabricaServico;
import br.com.lojadigicom.treinoacademia.modelo.DiaTreinoVo;
import br.com.lojadigicom.treinoacademia.framework.log.DCLog;

public abstract class DiaTreinoFragmentEditaBase extends Fragment{

	private Button btnOk = null;

	private DiaTreino item = null;
	
	protected DiaTreinoServico srv = FabricaServico.getInstancia().getDiaTreinoServico();
	
	protected DiaTreino getItem() {
		return item;
	}
	public void setItem(DiaTreino valor) {
		item = valor;
	}
	
	@Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DCLog.d(DCLog.TRACE_DISPLAY,this,this.getClass().getSimpleName());
         if (getIdLayout()==0) {
            throw new RuntimeException("Voce precisa implementar getIdLayout() em " + this.getClass().getSimpleName());
        }
        View v = inflater.inflate(getIdLayout(), container, false);
        inicializaCamposTela(v);
        btnOk = getButton(v,getIdBtnOk(),"btnOk");
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviaDados();
            }
        });
        return v;
    }
	
	private void enviaDados() {
		DCLog.d(DCLog.TRACE_CRUD,this,"enviaDados click");
		if (item==null) {
            // Insercao
            item = new DiaTreinoVo();
            DCLog.d(DCLog.TRACE_CRUD,this,"Insercao");
        }
        DCLog.d(DCLog.TRACE_CRUD,this,"antes  copiaTelaParaVo: " + item.getContentValues().toString());
        copiaTelaParaVo(item);
        DCLog.d(DCLog.TRACE_CRUD,this,"depois copiaTelaParaVo: " + item.getContentValues().toString());
        srv.insereAtualiza(item,this.getContext());
        fechar();
    }
	private void fechar() {
	}


    protected abstract void copiaTelaParaVo(DiaTreino item);
	
	protected abstract void inicializaCamposTela(View v);
	
	protected abstract int getIdLayout();
	protected abstract int getIdBtnOk();

	// Tem em varios lugares, pode depois ficar centralizada.
    // (28-11-2016) ate 28-01-2017
    
    protected final EditText getEditText(View v, int id, String nome) {
        EditText saida = (EditText) v.findViewById(id);
        if (saida==null) throw new RuntimeException("EditText " + nome + " nao encontrado em " + v);
        return saida;
    }
    protected final Button getButton(View v, int id, String nome) {
        Button saida = (Button) v.findViewById(id);
        if (saida==null) throw new RuntimeException("Button " + nome + " nao encontrado em " + v);
        return saida;
    }
	
}