
package br.com.lojadigicom.precomed.framework.data;


import android.database.Cursor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import br.com.lojadigicom.precomed.framework.dao.DaoException;
import br.com.lojadigicom.precomed.framework.dao.DaoItemRetorno;
import br.com.lojadigicom.precomed.framework.dao.MontadorDaoI;
import br.com.lojadigicom.precomed.framework.modelo.DCIObjetoDominio;

public class MontadorDaoComposite implements MontadorDaoI {

	private List classes;
	private List<MontadorDaoI> montadores;
	private List<String> propriedades;
	
	public MontadorDaoComposite() {
		classes = new LinkedList();
		montadores = new LinkedList<MontadorDaoI>();
		propriedades = new LinkedList<String>();
	}
	
	public void desligaSinc() {
		for (MontadorDaoI montador:montadores) {
			montador.desligaSinc();
		}
	}
	
	public void adicionaMontador(MontadorDaoI montador, String nomePropriedade) {
		montadores.add(montador);
		propriedades.add(nomePropriedade);
	}
	
	@Override
	public DCIObjetoDominio getItem(Cursor c, int posicao) {
		return null;
	}
	
	private MontadorDaoI getMontador(int idx) {
		MontadorDaoI montador = montadores.get(idx);
		if (montador instanceof MontadorDaoComposite) 
			throw new RuntimeException("Montador composto nao pode obter outro montador composto");
		return montador;
	}
	
	@Override
	public DCIObjetoDominio getItem(Cursor c) {
		Object objFinal = null;
		int posicao = 0;
		String propriedade = null;
		MontadorDaoI oi = getMontador(0);
		DCIObjetoDominio objCorrente = oi.getItem(c);
		posicao += oi.quantidadeCampos();
		for (int i = 1; i < this.montadores.size(); i++)
		{
			propriedade = (String) this.propriedades.get(i);
			oi = getMontador(i);
			try {

				Class classeVo = obtemClasseVo(objCorrente);
				DCIObjetoDominio dominio2 = oi.getItem(c, posicao);
				Class classeDominio2 = obtemClasseInterface(dominio2);
				Method metodo = null;
				try {
					metodo = classeVo.getMethod("set" + propriedade, new Class[] {classeDominio2});
				} catch (NoSuchMethodException e) {
					// Uma visao mais simplificada do problema, desenvolver melhor depois
					metodo = classeVo.getMethod("addLista" + propriedade, new Class[] {classeDominio2});
				}
				metodo.invoke(objCorrente, dominio2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//PropertyInfo info = this.posicionaObjeto(objCorrente, propriedade, objFinal);
			//DCIObjetoDominio dominio2 = oi.getItem(c, posicao);
			//info.SetValue(objFinal, dominio2, null);
			posicao += oi.quantidadeCampos();
		}
		return objCorrente;
	}
	
	private Class obtemClasseVo(Object objeto) throws ClassNotFoundException{
		//Class classe = objeto.getClass();
		//String nome = classe.getName();
		//String nome2 = nome.replaceAll("derivada.impl", "vo");
		//String nomeVo = nome2.replaceAll("Derivada", "Vo");
		//Class classeVo = Class.forName(nomeVo);
		//return classeVo;
		return objeto.getClass();
	}
	private Class obtemClasseInterface(Object objeto) throws ClassNotFoundException{
		Class classe = objeto.getClass();
		String nome = classe.getName();
		String nome2 = nome.replaceAll(".vo", "");
		String nomeVo = nome2.replaceAll("Vo", "");
		Class classeVo = Class.forName(nomeVo);
		return classeVo;
	}
	
	public int quantidadeCampos()
	{
		return 0;
	}

	@Override
	public DCIObjetoDominio getItemSinc(Cursor c) {
		throw new UnsupportedOperationException("MontadorDaoComposite nao trabalha com getItemSinc");
	}
	
	
	@Override
	public DaoItemRetorno extraiRegistroListaInterna(
			Cursor paramResultadoLista, DCIObjetoDominio objeto)
			throws DaoException, ClassNotFoundException, NoSuchMethodException,
			InvocationTargetException, IllegalAccessException {
		DaoItemRetorno itemRetorno = new DaoItemRetorno();
		boolean saida = false;

		Object objAux = null;
		if (objeto == null) {
			// Objeto Principal - Inicial
			int posicao = 0;
			String propriedade = null;
			MontadorDaoI oi = getMontador(0);
			// Obtem o primeiro objeto
			objeto = oi.getItem(paramResultadoLista);
			posicao += oi.quantidadeCampos();
			for (int i = 1; i < this.montadores.size(); i++) {
				// loop nos montadores
				propriedade = (String) this.propriedades.get(i);
				String listaProp[] = propriedade.split("\\.");
				String propriedadeCorrente = listaProp[listaProp.length-1];
				oi = getMontador(i);
				// posiciona objeto que vai ter o metodo set acionado
				objAux = posicionaObjeto(objeto, propriedade);
				// novo objeto
				DCIObjetoDominio obj2 = oi.getItem(paramResultadoLista,posicao);
				posicao += oi.quantidadeCampos();
				// seta objeto
				if (obj2.getIdObj()!=0) {
					Class classeVo = obtemClasseVo(objAux);
					Class classeDominio2 = obtemClasseInterface(obj2);
					Method metodo = this.getMetodoSet(classeVo, propriedadeCorrente, classeDominio2);
					metodo.invoke(objAux, obj2);
				}
				// Insercaoo
				saida = true;
			}
		} else {
			// Ja existe um objeto
			int posicao = 0;
			String propriedade = null;
			MontadorDaoI oi = getMontador(0);
			// Obteve primeiro objeto do pr?ximo registro
			DCIObjetoDominio objetoNovo = oi.getItem(paramResultadoLista);
			if (objetoNovo.getIdObj() == objeto.getIdObj()) {
				// Se for o mesmo significa que ? um objeto para adicionar em
				// lista
				posicao += oi.quantidadeCampos();
				for (int i = 1; i < this.montadores.size(); i++) {
					// posiciona objeto que vai ter o metodo set acionado
					propriedade = (String) this.propriedades.get(i);
					oi = getMontador(i);
					objAux = posicionaObjeto(objeto, propriedade);
					// novo objeto
					DCIObjetoDominio obj2 = oi.getItem(paramResultadoLista, posicao);
					// seta objeto dentro do que ja esta na lista
					if (obj2.getIdObj()!=0) {
						Class classeVo = obtemClasseVo(objAux);
						Class classeDominio2 = obtemClasseInterface(obj2);
						String[] prop = propriedade.split("\\.");
						Method metodo = this.getMetodoSet(classeVo, prop[prop.length-1], classeDominio2);
						metodo.invoke(objAux, obj2);
					}
					posicao += oi.quantidadeCampos();
				}
				saida = false;
			} else {
				// E um novo objeto principal para ser incluido na lista
				objeto = objetoNovo;
				posicao += oi.quantidadeCampos();
				for (int i = 1; i < this.montadores.size(); i++) {
					// loop nos montadores
					propriedade = (String) this.propriedades.get(i);
					String listaProp[] = propriedade.split("\\.");
					String propriedadeCorrente = listaProp[listaProp.length-1];
					oi = getMontador(i);
					// posiciona objeto que vai ter o metodo set acionado
					objAux = posicionaObjeto(objeto, propriedade);
					// novo objeto
					DCIObjetoDominio obj2 = oi.getItem(paramResultadoLista,posicao);
					posicao += oi.quantidadeCampos();
					// seta objeto
					if (obj2.getIdObj()!=0) {
						Class classeVo = obtemClasseVo(objAux);
						Class classeDominio2 = obtemClasseInterface(obj2);
						Method metodo = this.getMetodoSet(classeVo, propriedadeCorrente, classeDominio2);
						metodo.invoke(objAux, obj2);
					}
				}
				saida = true;
			}

		}
		itemRetorno.setInsere(saida);
		itemRetorno.setObjeto(objeto);
		return itemRetorno;
	}

	private Object posicionaObjeto(Object objetoRaiz, String propriedade)
			throws ClassNotFoundException, NoSuchMethodException,
			InvocationTargetException, IllegalAccessException {
		// Percorrendo a arvore de objetos ate chegar no desejado.
		DCIObjetoDominio proximoObjeto;
		Object saida = objetoRaiz;
		if (propriedade==null)  return saida;
		String[] prop = propriedade.split("\\.");
		for (int i = 0; i < prop.length-1; i++) {
			String propCorrente = prop[i];
			Class classeVo = saida.getClass();
			Method metodo = classeVo.getMethod("get" + propCorrente);
			//Method metodo = classeVo.getMethod("getCorrente" + propCorrente);
			Object obj = metodo.invoke(saida,null);
			saida = obj;
		}

		return saida;

	}

	private Method getMetodoSet(Class classeVo, String propriedade, Class classeSet) throws NoSuchMethodException {
		Method saida = null;
		try {
			saida = classeVo.getMethod("set" + propriedade,	new Class[] { classeSet });
		} catch (NoSuchMethodException e) {
			saida = classeVo.getMethod("addLista" + propriedade,	new Class[] { classeSet });
		}
		return saida;
	}


	
}
