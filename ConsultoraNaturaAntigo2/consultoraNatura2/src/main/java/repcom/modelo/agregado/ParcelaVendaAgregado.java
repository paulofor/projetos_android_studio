package repcom.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.digicom.log.DCLog;
import repcom.modelo.*;
import repcom.servico.*;

public class ParcelaVendaAgregado implements ParcelaVendaAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private ParcelaVendaCarregador carregador = null;
	private ParcelaVendaCarregador getCarregador() {
		if (carregador==null) {
			carregador = new ParcelaVendaCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		
	}
	*/
	
	private ParcelaVenda vo;
	public ParcelaVendaAgregado(ParcelaVenda item) {
		vo = item;
	}
	
	
 	
 	
 	
 	
 	
}
