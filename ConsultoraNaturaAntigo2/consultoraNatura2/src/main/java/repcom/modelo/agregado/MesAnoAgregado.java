package repcom.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.digicom.log.DCLog;
import repcom.modelo.*;
import repcom.servico.*;

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
