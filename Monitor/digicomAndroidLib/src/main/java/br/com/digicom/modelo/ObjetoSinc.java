package br.com.digicom.modelo;

import org.json.JSONException;
import org.json.JSONObject;

public interface ObjetoSinc {
	public String getOperacaoSinc();
	public void setOperacaoSinc(String valor);
	public JSONObject JSonSinc() throws JSONException;
}
