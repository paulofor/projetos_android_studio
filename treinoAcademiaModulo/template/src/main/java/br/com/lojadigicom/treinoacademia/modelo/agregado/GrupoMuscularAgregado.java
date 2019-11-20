package br.com.lojadigicom.treinoacademia.modelo.agregado;

import java.util.List;
import java.util.ArrayList;
import br.com.lojadigicom.treinoacademia.framework.log.DCLog;
import br.com.lojadigicom.treinoacademia.modelo.*;


public class GrupoMuscularAgregado implements GrupoMuscularAgregadoI
{

	private final int QTDE_LOG_LAZY_LOADER = 4;
	/*
	private GrupoMuscularCarregador carregador = null;
	private GrupoMuscularCarregador getCarregador() {
		if (carregador==null) {
			carregador = new GrupoMuscularCarregador();
		}
		return carregador;
	}
	
	public void setConexaoCarregador(DaoConexao conexao) {
		getCarregador().setConexao(conexao);
		
	}
	*/
	
	private GrupoMuscular vo;
	public GrupoMuscularAgregado(GrupoMuscular item) {
		vo = item;
	}
	
	
 	
 	
 	
 	
 	
	public boolean existeListaExercicio_ReferenteA() {
		return listaexercicioReferenteA!= null; 
	}
	private List<Exercicio> listaexercicioReferenteA;
	private boolean daoListaexercicioReferenteA = false;
	public void setListaExercicio_ReferenteA(List<Exercicio> value)
	{	
		listaexercicioReferenteA = value;
	}
	public void setListaExercicio_ReferenteAByDao(List<Exercicio> value)
	{	
	//	listaexercicioReferenteA = value;
	//	daoListaexercicioReferenteA = (value!=null);
	}  
	public List<Exercicio> getListaExercicio_ReferenteA()
	{	
	//	if (!daoListaexercicioReferenteA)
    //    {
    //    ExercicioServico srv = FabricaServico.getInstancia().getExercicioServico(FabricaServico.TIPO_SQLITE);
	//	listaexercicioReferenteA = srv.getPorParaGrupoMuscular(vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "GrupoMuscular.getListaExercicio_ReferenteA()",QTDE_LOG_LAZY_LOADER);
    //    }
	 	return listaexercicioReferenteA;
	} 
	public List<Exercicio> getListaExercicio_ReferenteAOriginal()
	{	
		return listaexercicioReferenteA;
	} 
	public List<Exercicio> getListaExercicio_ReferenteA(int qtde)
	{	
    //    ExercicioServico srv = FabricaServico.getInstancia().getExercicioServico(FabricaServico.TIPO_SQLITE);
	//	listaexercicioReferenteA = srv.getPorParaGrupoMuscular(vo.getContexto().getContext(), vo.getId());
	//	DCLog.dStack(DCLog.LAZY_LOADER, this, "GrupoMuscular.getListaExercicio_ReferenteA()",QTDE_LOG_LAZY_LOADER);
		return listaexercicioReferenteA;
	} 
	public void addListaExercicio_ReferenteA(Exercicio value) 
	{	
		criaVaziaListaExercicio_ReferenteA();
		listaexercicioReferenteA.add(value);
	//	daoListaexercicioReferenteA = true;
	} 
	public Exercicio getCorrenteExercicio_ReferenteA()
	{	
		if (listaexercicioReferenteA==null) return null;
		return listaexercicioReferenteA.get(listaexercicioReferenteA.size()-1);
	} 
	public void criaVaziaListaExercicio_ReferenteA() {
		if (listaexercicioReferenteA == null)
        {
        	listaexercicioReferenteA = new ArrayList<Exercicio>();
        }
	}
	
}
