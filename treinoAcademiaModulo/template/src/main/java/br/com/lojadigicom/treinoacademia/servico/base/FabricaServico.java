package br.com.lojadigicom.treinoacademia.servico.base;

import br.com.lojadigicom.treinoacademia.servico.impl.*;

public class FabricaServico {


	private static FabricaServico fabrica = new FabricaServico();

    public static FabricaServico getInstancia() {
        return fabrica;
    }

	private UsuarioServico usuarioServico = null;
    public UsuarioServico getUsuarioServico() {
       if (usuarioServico==null) {
           usuarioServico = new UsuarioServicoImpl();
       }
       return usuarioServico;
    }
	private ExercicioServico exercicioServico = null;
    public ExercicioServico getExercicioServico() {
       if (exercicioServico==null) {
           exercicioServico = new ExercicioServicoImpl();
       }
       return exercicioServico;
    }
	private SerieTreinoServico serieTreinoServico = null;
    public SerieTreinoServico getSerieTreinoServico() {
       if (serieTreinoServico==null) {
           serieTreinoServico = new SerieTreinoServicoImpl();
       }
       return serieTreinoServico;
    }
	private ItemSerieServico itemSerieServico = null;
    public ItemSerieServico getItemSerieServico() {
       if (itemSerieServico==null) {
           itemSerieServico = new ItemSerieServicoImpl();
       }
       return itemSerieServico;
    }
	private CargaPlanejadaServico cargaPlanejadaServico = null;
    public CargaPlanejadaServico getCargaPlanejadaServico() {
       if (cargaPlanejadaServico==null) {
           cargaPlanejadaServico = new CargaPlanejadaServicoImpl();
       }
       return cargaPlanejadaServico;
    }
	private ExecucaoItemSerieServico execucaoItemSerieServico = null;
    public ExecucaoItemSerieServico getExecucaoItemSerieServico() {
       if (execucaoItemSerieServico==null) {
           execucaoItemSerieServico = new ExecucaoItemSerieServicoImpl();
       }
       return execucaoItemSerieServico;
    }
	private DiaTreinoServico diaTreinoServico = null;
    public DiaTreinoServico getDiaTreinoServico() {
       if (diaTreinoServico==null) {
           diaTreinoServico = new DiaTreinoServicoImpl();
       }
       return diaTreinoServico;
    }
	private DispositivoUsuarioServico dispositivoUsuarioServico = null;
    public DispositivoUsuarioServico getDispositivoUsuarioServico() {
       if (dispositivoUsuarioServico==null) {
           dispositivoUsuarioServico = new DispositivoUsuarioServicoImpl();
       }
       return dispositivoUsuarioServico;
    }
	private GrupoMuscularServico grupoMuscularServico = null;
    public GrupoMuscularServico getGrupoMuscularServico() {
       if (grupoMuscularServico==null) {
           grupoMuscularServico = new GrupoMuscularServicoImpl();
       }
       return grupoMuscularServico;
    }
	private ErroExceptionServico erroExceptionServico = null;
    public ErroExceptionServico getErroExceptionServico() {
       if (erroExceptionServico==null) {
           erroExceptionServico = new ErroExceptionServicoImpl();
       }
       return erroExceptionServico;
    }
}