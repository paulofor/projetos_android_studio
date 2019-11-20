package treinoacademia.dao;

import android.content.Context;

public class FabricaDao {

	static FabricaDao fabrica = new FabricaDao();
	
	public static FabricaDao getInstancia() {
		return fabrica;
	}


	private UsuarioDBHelper usuarioDBHelper = null;
	
	public UsuarioDBHelper getUsuarioDBHelper() {
		if (usuarioDBHelper==null) {
			usuarioDBHelper = new UsuarioDBHelper();
		}
		return usuarioDBHelper;
	}
	

	private ExercicioDBHelper exercicioDBHelper = null;
	
	public ExercicioDBHelper getExercicioDBHelper() {
		if (exercicioDBHelper==null) {
			exercicioDBHelper = new ExercicioDBHelper();
		}
		return exercicioDBHelper;
	}
	

	private SerieTreinoDBHelper serieTreinoDBHelper = null;
	
	public SerieTreinoDBHelper getSerieTreinoDBHelper() {
		if (serieTreinoDBHelper==null) {
			serieTreinoDBHelper = new SerieTreinoDBHelper();
		}
		return serieTreinoDBHelper;
	}
	

	private ItemSerieDBHelper itemSerieDBHelper = null;
	
	public ItemSerieDBHelper getItemSerieDBHelper() {
		if (itemSerieDBHelper==null) {
			itemSerieDBHelper = new ItemSerieDBHelper();
		}
		return itemSerieDBHelper;
	}
	

	private CargaPlanejadaDBHelper cargaPlanejadaDBHelper = null;
	
	public CargaPlanejadaDBHelper getCargaPlanejadaDBHelper() {
		if (cargaPlanejadaDBHelper==null) {
			cargaPlanejadaDBHelper = new CargaPlanejadaDBHelper();
		}
		return cargaPlanejadaDBHelper;
	}
	

	private ExecucaoItemSerieDBHelper execucaoItemSerieDBHelper = null;
	
	public ExecucaoItemSerieDBHelper getExecucaoItemSerieDBHelper() {
		if (execucaoItemSerieDBHelper==null) {
			execucaoItemSerieDBHelper = new ExecucaoItemSerieDBHelper();
		}
		return execucaoItemSerieDBHelper;
	}
	

	private DiaTreinoDBHelper diaTreinoDBHelper = null;
	
	public DiaTreinoDBHelper getDiaTreinoDBHelper() {
		if (diaTreinoDBHelper==null) {
			diaTreinoDBHelper = new DiaTreinoDBHelper();
		}
		return diaTreinoDBHelper;
	}
	

	private DispositivoUsuarioDBHelper dispositivoUsuarioDBHelper = null;
	
	public DispositivoUsuarioDBHelper getDispositivoUsuarioDBHelper() {
		if (dispositivoUsuarioDBHelper==null) {
			dispositivoUsuarioDBHelper = new DispositivoUsuarioDBHelper();
		}
		return dispositivoUsuarioDBHelper;
	}
	

	private GrupoMuscularDBHelper grupoMuscularDBHelper = null;
	
	public GrupoMuscularDBHelper getGrupoMuscularDBHelper() {
		if (grupoMuscularDBHelper==null) {
			grupoMuscularDBHelper = new GrupoMuscularDBHelper();
		}
		return grupoMuscularDBHelper;
	}
	

	private ErroExceptionDBHelper erroExceptionDBHelper = null;
	
	public ErroExceptionDBHelper getErroExceptionDBHelper() {
		if (erroExceptionDBHelper==null) {
			erroExceptionDBHelper = new ErroExceptionDBHelper();
		}
		return erroExceptionDBHelper;
	}
	
}

