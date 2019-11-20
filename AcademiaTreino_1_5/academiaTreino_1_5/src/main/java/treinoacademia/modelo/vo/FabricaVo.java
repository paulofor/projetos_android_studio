package treinoacademia.modelo.vo;

import android.content.Context;
import org.json.JSONObject;
import org.json.JSONException;
import treinoacademia.modelo.*;
import treinoacademia.modelo.derivada.impl.*;

public class FabricaVo {

	public static Usuario criaUsuario() {
		return new UsuarioVo();
	}
	public static Usuario criaUsuario(JSONObject json) throws JSONException {
		return new UsuarioVo(json);
	}
	public static Exercicio criaExercicio() {
		return new ExercicioVo();
	}
	public static Exercicio criaExercicio(JSONObject json) throws JSONException {
		return new ExercicioVo(json);
	}
	public static SerieTreino criaSerieTreino() {
		return new SerieTreinoVo();
	}
	public static SerieTreino criaSerieTreino(JSONObject json) throws JSONException {
		return new SerieTreinoVo(json);
	}
	public static ItemSerie criaItemSerie() {
		return new ItemSerieVo();
	}
	public static ItemSerie criaItemSerie(JSONObject json) throws JSONException {
		return new ItemSerieVo(json);
	}
	public static CargaPlanejada criaCargaPlanejada() {
		return new CargaPlanejadaVo();
	}
	public static CargaPlanejada criaCargaPlanejada(JSONObject json) throws JSONException {
		return new CargaPlanejadaVo(json);
	}
	public static ExecucaoItemSerie criaExecucaoItemSerie() {
		return new ExecucaoItemSerieVo();
	}
	public static ExecucaoItemSerie criaExecucaoItemSerie(JSONObject json) throws JSONException {
		return new ExecucaoItemSerieVo(json);
	}
	public static DiaTreino criaDiaTreino() {
		return new DiaTreinoVo();
	}
	public static DiaTreino criaDiaTreino(JSONObject json) throws JSONException {
		return new DiaTreinoVo(json);
	}
	public static DispositivoUsuario criaDispositivoUsuario() {
		return new DispositivoUsuarioVo();
	}
	public static DispositivoUsuario criaDispositivoUsuario(JSONObject json) throws JSONException {
		return new DispositivoUsuarioVo(json);
	}
	public static GrupoMuscular criaGrupoMuscular() {
		return new GrupoMuscularVo();
	}
	public static GrupoMuscular criaGrupoMuscular(JSONObject json) throws JSONException {
		return new GrupoMuscularVo(json);
	}
	public static ErroException criaErroException() {
		return new ErroExceptionVo();
	}
	public static ErroException criaErroException(JSONObject json) throws JSONException {
		return new ErroExceptionVo(json);
	}
}

