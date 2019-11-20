package br.com.lojadigicom.capitalexterno.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.capitalexterno.framework.log.DCLog;
import br.com.lojadigicom.capitalexterno.modelo.*;


public class MesAnoAgregado implements MesAnoAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private MesAnoCarregador carregador = null;
	private MesAnoCarregador getCarregador() {
		if (carregador==null) {
			carregador = new MesAnoCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		
	}
	*/
	
	private MesAno vo;
	public MesAnoAgregado(MesAno item) {
		vo = item;
	}
	
	
 	
 	
 	
 	
 	
}
