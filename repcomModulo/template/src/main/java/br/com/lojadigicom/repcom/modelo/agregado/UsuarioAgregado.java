package br.com.lojadigicom.repcom.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.repcom.framework.log.DCLog;
import br.com.lojadigicom.repcom.modelo.*;


public class UsuarioAgregado implements UsuarioAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private UsuarioCarregador carregador = null;
	private UsuarioCarregador getCarregador() {
		if (carregador==null) {
			carregador = new UsuarioCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		
	}
	*/
	
	private Usuario vo;
	public UsuarioAgregado(Usuario item) {
		vo = item;
	}
	
	
 	
 	
 	
 	
 	
}
