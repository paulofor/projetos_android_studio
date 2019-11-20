package br.com.digicom.quadro;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.util.UtilDatas;
import br.com.digicom.widget.util.SpinnerUtil;

public class GerenciadorItensTela {


	private IFragment frag;
	private View tela;
	
	public GerenciadorItensTela(IFragment frag) {
		this.frag = frag;
	}
	public GerenciadorItensTela(View tela) {
		this.tela = tela;
	}
	
	private View getViewTela() {
		if (frag!=null) return frag.getTela();
		else return tela;
	}
	
	public EditText getEditText(int codigo, String nome) {
		EditText campo = null;
		try {
			campo = (EditText) getViewTela().findViewById(codigo);
		} catch (ClassCastException e) {
			throw new RuntimeException(e.getMessage() + " de " + nome + " em " + frag.getClass().getSimpleName() + "  (" + frag.getRecurso().getMensagemParaErro() + ")");
		}
		if (campo==null) {
			if (frag!=null)
				throw new RuntimeException(nome + " não encontrado em " + frag.getRecurso().getMensagemParaErro());
			else
				throw new RuntimeException(nome + " não encontrado no layout do adaptador.");
		}
		return campo;
	}
	public TextView getTextView(int codigo, String nome) {
		TextView campo = null;
		try {
			campo = (TextView) getViewTela().findViewById(codigo);
		} catch (ClassCastException e) {
			throw new RuntimeException(e.getMessage() + " de " + nome + " em " + frag.getRecurso().getMensagemParaErro());
		}
		if (campo==null) {
			throw new RuntimeException(nome + " não encontrado em " + frag.getRecurso().getMensagemParaErro());
		}
		return campo;
	}
	public View getView(int codigo, String nome) {
		View campo = null;
		try {
			campo = (View) getViewTela().findViewById(codigo);
		} catch (ClassCastException e) {
			throw new RuntimeException(e.getMessage() + " de " + nome + " em " + frag.getRecurso().getMensagemParaErro());
		}
		if (campo==null) {
			throw new RuntimeException(nome + " não encontrado em " + frag.getRecurso().getMensagemParaErro());
		}
		return campo;
	}
	public RadioButton getRadio(int codigo, String nome) {
		RadioButton campo = null;
		try {
			campo = (RadioButton) getViewTela().findViewById(codigo);
		} catch (ClassCastException e) {
			throw new RuntimeException(e.getMessage() + " de " + nome + " em " + frag.getRecurso().getMensagemParaErro());
		}
		if (campo==null) {
			throw new RuntimeException(nome + " não encontrado em " + frag.getRecurso().getMensagemParaErro());
		}
		return campo;
	}
	public void setTexto(int codigo, String nome, String texto) {
		TextView campo = getTextView(codigo,nome);
		campo.setText(texto);
	}
	public void setTexto(int codigo, String nome, long texto) {
		TextView campo = getTextView(codigo,nome);
		campo.setText(String.valueOf(texto));
	}
	public boolean estaPreenchido(int codigo, String nome) {
		TextView campo = getTextView(codigo,nome);
		return !(campo.getText().toString() == null || "".equals(campo.getText().toString()));
	}
	public boolean spinnerPreenchido(int codigo, String nome) {
		Spinner campo = this.getSpinner(codigo, nome);
		return (campo.getSelectedItem()!=null);
	}
	public void spinnerSetLista(int codigo, String nome, List lista, Context contexto) {
		this.getSpinner(codigo, nome, lista, contexto);
	}
	public boolean spinnerCompara(int codigo, String nome, Object comparacao) {
		Spinner campo = this.getSpinner(codigo, nome);
		return (campo==comparacao);
	}
	
	
	public Button getButton(int codigo, String nome) {
		Button campo = null;
		try {
			campo = (Button) this.getViewTela().findViewById(codigo);
		} catch (ClassCastException e) {
			throw new RuntimeException(e.getMessage() + " de " + nome + " em " + frag.getRecurso().getMensagemParaErro());
		}
		if (campo==null) {
			throw new RuntimeException(nome + " n�o encontrado em " + frag.getRecurso().getMensagemParaErro());
		}
		return campo;
	}
	
	public Spinner getSpinner(int codigo, String nome, List lista, Context contexto) {
		Spinner campo = getSpinner(codigo, nome);
		SpinnerUtil.setLista(lista,campo,contexto);
		return campo;
	}
		
	public Spinner getSpinner(int codigo, String nome) {
		Spinner campo = (Spinner) getViewTela().findViewById(codigo);
		if (campo==null) {
			throw new RuntimeException(nome + " n�o encontrado em " + frag.getRecurso().getMensagemParaErro());
		}
		return campo;
	}
	
	public Object getSpinnerObjeto(int codigo, String nome) {
		Spinner campo = getSpinner(codigo, nome);
		Object obj = campo.getSelectedItem();
		return obj;
	}
	public long getSpinnerIdObjeto(int codigo, String nome) {
		DCIObjetoDominio obj = (DCIObjetoDominio) getSpinnerObjeto(codigo,nome);
		return obj.getId();
	}
	
	
	
	public final boolean getConteudoRadio(int codigo, String nome) {
		RadioButton radio = this.getRadio(codigo, nome);
		return radio.isChecked();
	}
	
	public final String getConteudoEditText(int codigo, String nome) {
		String saida = null;
		TextView item = getTextView(codigo,nome);
		saida = item.getText().toString();
		return saida;
	}
	public final long getRatingBarNota(int codigo, String nome) {
		RatingBar campo = (RatingBar) getViewTela().findViewById(codigo);
		if (campo==null) {
			throw new RuntimeException(nome + " n�o encontrado em " + frag.getRecurso().getMensagemParaErro());
		}
		float nota = campo.getRating();
		long notaInt = (long) Math.ceil(nota);
		return notaInt;
	}
	public final long getConteudoNumerico(int codigo, String nome) {
		TextView texto = this.getTextView(codigo, nome);
		String valor = texto.getText().toString();
		return Long.parseLong(valor);
	}
	public final float getConteudoMoeda(int codigo, String nome) {
		TextView texto = this.getTextView(codigo, nome);
		String valor = texto.getText().toString();
		return Float.parseFloat(valor);
	}
	public final float getConteudoDecimal(int codigo, String nome) {
		TextView texto = this.getTextView(codigo, nome);
		String valor = texto.getText().toString();
		return Float.parseFloat(valor);
	}
	
	public final void setConteudoEditText(int codigo, String nome, String conteudo) {
		TextView item = getTextView(codigo,nome);
		item.setText(conteudo);
	}
	
	public final void setTempo(int codigo, String nome, long tempo) {
		//SimpleDateFormat formato2 = new SimpleDateFormat("HH:mm:ss");
		//String tempoStr = formato2.format(tempo);
		String tempoStr = UtilDatas.converte2MMSS(tempo);
		this.setConteudoEditText(codigo, nome, tempoStr);
	}
	public final void setTempo(int codigo, String nome, Timestamp tempo) {
		SimpleDateFormat formato2 = new SimpleDateFormat("HH:mm:ss");
		String dataStr = formato2.format(tempo);
		this.setConteudoEditText(codigo, nome, dataStr);
	}
	public final void setData(int codigo, String nome, Timestamp tempo) {
		SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
		String dataStr = formato2.format(tempo);
		this.setConteudoEditText(codigo, nome, dataStr);
	}
	public final void setRatingBarNota(int codigo, String nome, long conteudo) {
		RatingBar campo = (RatingBar) getViewTela().findViewById(codigo);
		if (campo==null) {
			throw new RuntimeException(nome + " n�o encontrado em " + frag.getRecurso().getMensagemParaErro());
		}
		campo.setRating(conteudo);
	}
	public final void setConteudoNumerico(int codigo, String nome, long conteudo) {
		TextView texto = this.getTextView(codigo, nome);
		texto.setText(Long.toString(conteudo));
	}
	public void setMoeda(int codigo, String nome, float valor) {
		View elemento = this.getView(codigo, nome);
		if (elemento instanceof EditText) {
			String formattedString = String.format("%.02f", valor);
			formattedString = formattedString.replaceFirst(",",".");
			TextView texto = (TextView) elemento;
			texto.setText(formattedString);
			return;
		}
		if (elemento instanceof TextView) {
			String formattedString = "R$" + String.format("%.02f", valor);
			TextView texto = (TextView) elemento;
			texto.setText(formattedString);
			return;
		}
		
	}
	public void setSpinnerIdObjeto(int codigo, String nome, long idObjeto) {
		Spinner campo = getSpinner(codigo, nome);
		SpinnerUtil.setIdObj(idObjeto, campo);
	}
	public void setSpinnerLabel(int codigo, String nome, String label) {
		// TODO Auto-generated method stub
		Spinner campo = getSpinner(codigo, nome);
		SpinnerUtil.setLabel(label, campo);
	}
	public void setSpinnerLabel(int codigo, String nome, long label) {
		// TODO Auto-generated method stub
		Spinner campo = getSpinner(codigo, nome);
		SpinnerUtil.setLabel("" + label, campo);
	}
}
