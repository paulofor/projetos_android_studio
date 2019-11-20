package treinoacademia.dao.datasource;

import br.com.digicom.servico.DataSourceRemotoI;

public class DataSourceRemoto implements DataSourceRemotoI{
	
	public final String SERVER = "digicom.kinghost.net";
	public final String APLICACAO = "TreinoAcademia";
	//public final String APLICACAO = "TreinoAcademiaDesen";
	
	//public final String SERVER = "10.0.2.2:61600";
	//public final String APLICACAO = null;
	
	@Override
	public String getServer() {
		return SERVER;
	}
	@Override
	public String getAplicacao() {
		return APLICACAO;
	}
}
