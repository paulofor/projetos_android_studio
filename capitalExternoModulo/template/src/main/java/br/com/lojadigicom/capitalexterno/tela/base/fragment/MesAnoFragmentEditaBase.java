
package  br.com.lojadigicom.capitalexterno.tela.base.fragment;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.capitalexterno.modelo.MesAno;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import br.com.lojadigicom.capitalexterno.servico.base.MesAnoServico;
import br.com.lojadigicom.capitalexterno.servico.base.FabricaServico;
import br.com.lojadigicom.capitalexterno.modelo.MesAnoVo;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;

public abstract class MesAnoFragmentEditaBase extends Fragment{

	private Button btnOk = null;

	private MesAno item = null;
	
	protected MesAnoServico srv = FabricaServico.getInstancia().getMesAnoServico();
	
	protected MesAno getItem() {
		return item;
	}
	public void setItem(MesAno valor) {
		item = valor;
	}
	
	@Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DCLog.d(DCLog.TRACE_DISPLAY,this,this.getClass().getSimpleName());
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
            item = new MesAnoVo();
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


    protected abstract void copiaTelaParaVo(MesAno item);
	
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