package br.com.digicom.modelo;

public interface DCIObjetoDominio {


	public String getNomeClasse();
	public long getId();
	public String getTituloTela();
	
	public void setSalvaPreliminar(boolean valor);
	public boolean getSalvaPreliminar();
}
