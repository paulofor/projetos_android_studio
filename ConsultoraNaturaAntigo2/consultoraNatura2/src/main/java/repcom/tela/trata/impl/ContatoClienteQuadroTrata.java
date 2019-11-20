package repcom.tela.trata.impl;

import repcom.app.R;
import repcom.modelo.ContatoCliente;
import repcom.tela.trata.base.ContatoClienteQuadroTrataBase;
import android.widget.EditText;
import android.widget.TextView;
import br.com.digicom.quadro.ResultadoValidacao;
import br.com.digicom.util.UtilDatas;
import br.com.digicom.widget.util.Mask;

public class ContatoClienteQuadroTrata extends ContatoClienteQuadroTrataBase {

	private TextView txtNomeClienteContato = null;
	private EditText txtDataContato = null;
	@Override
	protected void transfereTelaParaVo(ContatoCliente vo) {
		String data = txtDataContato.getText().toString();
		vo.setDataContato(UtilDatas.convertDD_MM_AAAA2Timestamp(data));
	}
	@Override
	protected void transfereVoParaTela(ContatoCliente vo) {
		txtDataContato.setText(vo.getDataContatoDDMMAAAA());
	}
	@Override
	protected void inicializaItensTela() {
		this.txtNomeClienteContato = (TextView) getTela().findViewById(R.id.txtNomeClienteContato);
		this.txtDataContato = (EditText) getTela().findViewById(R.id.txtDataContato);
		//txtDataContato.addTextChangedListener(Mask.insert("##/##/####", txtDataContato));
		String dataCorrente = UtilDatas.getDataAtual();
		txtDataContato.setText(dataCorrente);
	}
	
	@Override
	protected void carregaElementosTela() {
		if (this.getItem().getCliente_ReferenteA()==null) {
			throw new RuntimeException("getItem().getCliente_ReferenteA()==null");
		}
		this.txtNomeClienteContato.setText("Cliente:" + getItem().getCliente_ReferenteA().getNome());

	}
	
	
	@Override
	protected ResultadoValidacao validaCamposTela() {
		ResultadoValidacao novo = new ResultadoValidacao();
		novo.setProsseguir(true);
		return novo;
	}
	
	
	
	
	
}
