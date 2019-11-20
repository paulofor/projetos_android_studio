package treinoacademia.servico;

import treinoacademia.servico.sqlite.impl.CargaPlanejadaServicoSqliteImpl;
import treinoacademia.servico.sqlite.impl.DiaTreinoServicoSqliteImpl;
import treinoacademia.servico.sqlite.impl.DispositivoUsuarioServicoSqliteImpl;
import treinoacademia.servico.sqlite.impl.ErroExceptionServicoSqliteImpl;
import treinoacademia.servico.sqlite.impl.ExecucaoItemSerieServicoSqliteImpl;
import treinoacademia.servico.sqlite.impl.ExercicioServicoSqliteImpl;
import treinoacademia.servico.sqlite.impl.GrupoMuscularServicoSqliteImpl;
import treinoacademia.servico.sqlite.impl.ItemSerieServicoSqliteImpl;
import treinoacademia.servico.sqlite.impl.SerieTreinoServicoSqliteImpl;
import treinoacademia.servico.sqlite.impl.UsuarioServicoSqliteImpl;
import treinoacademia.servico.wsjson.impl.CargaPlanejadaServicoWsJsonImpl;
import treinoacademia.servico.wsjson.impl.DiaTreinoServicoWsJsonImpl;
import treinoacademia.servico.wsjson.impl.DispositivoUsuarioServicoWsJsonImpl;
import treinoacademia.servico.wsjson.impl.ErroExceptionServicoWsJsonImpl;
import treinoacademia.servico.wsjson.impl.ExecucaoItemSerieServicoWsJsonImpl;
import treinoacademia.servico.wsjson.impl.ExercicioServicoWsJsonImpl;
import treinoacademia.servico.wsjson.impl.GrupoMuscularServicoWsJsonImpl;
import treinoacademia.servico.wsjson.impl.ItemSerieServicoWsJsonImpl;
import treinoacademia.servico.wsjson.impl.SerieTreinoServicoWsJsonImpl;
import treinoacademia.servico.wsjson.impl.UsuarioServicoWsJsonImpl;

//import treinoacademia.servico.*;

public class FabricaServico {

	static FabricaServico fabrica = new FabricaServico();
	public static final int TIPO_SQLITE = 1;
	public static final int TIPO_WSJSON = 2;
	
	public static FabricaServico getInstancia() {
		return fabrica;
	}


	private UsuarioServico usuarioServicoSqlite = null;
	private UsuarioServico usuarioServicoJson = null;
	
	public UsuarioServico getUsuarioServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (usuarioServicoSqlite==null) {
				usuarioServicoSqlite = new UsuarioServicoSqliteImpl();
			}
			return usuarioServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (usuarioServicoJson==null) {
				usuarioServicoJson = new UsuarioServicoWsJsonImpl();
			}
			return usuarioServicoJson;
		}
		return null;
	}
	

	private ExercicioServico exercicioServicoSqlite = null;
	private ExercicioServico exercicioServicoJson = null;
	
	public ExercicioServico getExercicioServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (exercicioServicoSqlite==null) {
				exercicioServicoSqlite = new ExercicioServicoSqliteImpl();
			}
			return exercicioServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (exercicioServicoJson==null) {
				exercicioServicoJson = new ExercicioServicoWsJsonImpl();
			}
			return exercicioServicoJson;
		}
		return null;
	}
	

	private SerieTreinoServico serieTreinoServicoSqlite = null;
	private SerieTreinoServico serieTreinoServicoJson = null;
	
	public SerieTreinoServico getSerieTreinoServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (serieTreinoServicoSqlite==null) {
				serieTreinoServicoSqlite = new SerieTreinoServicoSqliteImpl();
			}
			return serieTreinoServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (serieTreinoServicoJson==null) {
				serieTreinoServicoJson = new SerieTreinoServicoWsJsonImpl();
			}
			return serieTreinoServicoJson;
		}
		return null;
	}
	

	private ItemSerieServico itemSerieServicoSqlite = null;
	private ItemSerieServico itemSerieServicoJson = null;
	
	public ItemSerieServico getItemSerieServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (itemSerieServicoSqlite==null) {
				itemSerieServicoSqlite = new ItemSerieServicoSqliteImpl();
			}
			return itemSerieServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (itemSerieServicoJson==null) {
				itemSerieServicoJson = new ItemSerieServicoWsJsonImpl();
			}
			return itemSerieServicoJson;
		}
		return null;
	}
	

	private CargaPlanejadaServico cargaPlanejadaServicoSqlite = null;
	private CargaPlanejadaServico cargaPlanejadaServicoJson = null;
	
	public CargaPlanejadaServico getCargaPlanejadaServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (cargaPlanejadaServicoSqlite==null) {
				cargaPlanejadaServicoSqlite = new CargaPlanejadaServicoSqliteImpl();
			}
			return cargaPlanejadaServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (cargaPlanejadaServicoJson==null) {
				cargaPlanejadaServicoJson = new CargaPlanejadaServicoWsJsonImpl();
			}
			return cargaPlanejadaServicoJson;
		}
		return null;
	}
	

	private ExecucaoItemSerieServico execucaoItemSerieServicoSqlite = null;
	private ExecucaoItemSerieServico execucaoItemSerieServicoJson = null;
	
	public ExecucaoItemSerieServico getExecucaoItemSerieServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (execucaoItemSerieServicoSqlite==null) {
				execucaoItemSerieServicoSqlite = new ExecucaoItemSerieServicoSqliteImpl();
			}
			return execucaoItemSerieServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (execucaoItemSerieServicoJson==null) {
				execucaoItemSerieServicoJson = new ExecucaoItemSerieServicoWsJsonImpl();
			}
			return execucaoItemSerieServicoJson;
		}
		return null;
	}
	

	private DiaTreinoServico diaTreinoServicoSqlite = null;
	private DiaTreinoServico diaTreinoServicoJson = null;
	
	public DiaTreinoServico getDiaTreinoServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (diaTreinoServicoSqlite==null) {
				diaTreinoServicoSqlite = new DiaTreinoServicoSqliteImpl();
			}
			return diaTreinoServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (diaTreinoServicoJson==null) {
				diaTreinoServicoJson = new DiaTreinoServicoWsJsonImpl();
			}
			return diaTreinoServicoJson;
		}
		return null;
	}
	

	private DispositivoUsuarioServico dispositivoUsuarioServicoSqlite = null;
	private DispositivoUsuarioServico dispositivoUsuarioServicoJson = null;
	
	public DispositivoUsuarioServico getDispositivoUsuarioServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (dispositivoUsuarioServicoSqlite==null) {
				dispositivoUsuarioServicoSqlite = new DispositivoUsuarioServicoSqliteImpl();
			}
			return dispositivoUsuarioServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (dispositivoUsuarioServicoJson==null) {
				dispositivoUsuarioServicoJson = new DispositivoUsuarioServicoWsJsonImpl();
			}
			return dispositivoUsuarioServicoJson;
		}
		return null;
	}
	

	private GrupoMuscularServico grupoMuscularServicoSqlite = null;
	private GrupoMuscularServico grupoMuscularServicoJson = null;
	
	public GrupoMuscularServico getGrupoMuscularServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (grupoMuscularServicoSqlite==null) {
				grupoMuscularServicoSqlite = new GrupoMuscularServicoSqliteImpl();
			}
			return grupoMuscularServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (grupoMuscularServicoJson==null) {
				grupoMuscularServicoJson = new GrupoMuscularServicoWsJsonImpl();
			}
			return grupoMuscularServicoJson;
		}
		return null;
	}
	

	private ErroExceptionServico erroExceptionServicoSqlite = null;
	private ErroExceptionServico erroExceptionServicoJson = null;
	
	public ErroExceptionServico getErroExceptionServico(int tipo) {
		if (tipo==TIPO_SQLITE) {
			if (erroExceptionServicoSqlite==null) {
				erroExceptionServicoSqlite = new ErroExceptionServicoSqliteImpl();
			}
			return erroExceptionServicoSqlite;
		}
		if (tipo==TIPO_WSJSON) {
			if (erroExceptionServicoJson==null) {
				erroExceptionServicoJson = new ErroExceptionServicoWsJsonImpl();
			}
			return erroExceptionServicoJson;
		}
		return null;
	}
	
}

