package br.com.digicom.layout;

import android.widget.Adapter;
import android.widget.BaseAdapter;

public class ResourceObj {

	private int codigo;
	private String nome;
	
	private BaseAdapter adaptador = null;
	
	public ResourceObj(int codigo,String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
	
	public void setAdapter(BaseAdapter valor) {
		this.adaptador = valor;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public String getNome() {
		return nome;
	}
	
	public String getNomeAdapter() {
		String nome = "";
		if (this.adaptador!=null) {
			nome = this.adaptador.getClass().getSimpleName();
		}
		return nome;
	}
}
