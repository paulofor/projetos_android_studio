
package repcom.servico.sqlite.impl;

import java.util.ArrayList;
import java.util.List;

import repcom.dao.ClienteInteresseCategoriaDBHelper;
import repcom.modelo.CategoriaProduto;
import repcom.modelo.Cliente;
import repcom.modelo.ClienteInteresseCategoria;
import repcom.modelo.vo.FabricaVo;
import repcom.servico.sqlite.base.ClienteInteresseCategoriaServicoSqliteBase;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.log.DCLog;
import br.com.digicom.modelo.DCIObjetoDominio;

public class ClienteInteresseCategoriaServicoSqliteImpl extends ClienteInteresseCategoriaServicoSqliteBase {

	@Override
	protected ClienteInteresseCategoria inicializaItemTelaImpl(ClienteInteresseCategoria interesse, DigicomContexto contexto) {
		return interesse;
	}

	/*
	public void atualizaRelacionamento(Cliente item, List<DCIObjetoDominio> listaEscolhidos) {
		ClienteInteresseCategoria novo = FabricaVo.criaClienteInteresseCategoria();
		List<ClienteInteresseCategoria> listaBanco = this.getPorAssociadaCliente(null, item.getId());
		// lista insercao
		List<ClienteInteresseCategoria> listaInsercao = new ArrayList<ClienteInteresseCategoria>();
		for (DCIObjetoDominio obj : listaEscolhidos) {
			boolean existe = false;
			for (ClienteInteresseCategoria rel : listaBanco) {
				if (obj.getId()==rel.getIdCategoriaProdutoA()) {
					existe = true;
					break;
				}
			}
			if (!existe) {
				ClienteInteresseCategoria novoRel = FabricaVo.criaClienteInteresseCategoria();
				novoRel.setCliente_Associada(item);
				novoRel.setCategoriaProduto_Associada((CategoriaProduto)obj);
				listaInsercao.add(novoRel);
			}
		}
		// *********************************************************************************************
		// Lista Exclusao
		List<ClienteInteresseCategoria> listaExclusao = new ArrayList<ClienteInteresseCategoria>();
		for (ClienteInteresseCategoria itemBanco : listaBanco) {
			boolean existe = false;
			for (DCIObjetoDominio obj : listaEscolhidos) {
				if (obj.getId()==itemBanco.getIdCategoriaProdutoA()) {
					existe = true;
					break;
				}
			}
			if (!existe) {
				listaExclusao.add(itemBanco);
			}
		}
		//  ***********************************************************************************************
		ClienteInteresseCategoriaDBHelper dao = getDao();
		//dao.deletePorAssociadaCliente(item.getId());
		for (DCIObjetoDominio obj : listaInsercao) {
			ClienteInteresseCategoria novoRel = (ClienteInteresseCategoria) obj;
			novoRel.setCliente_Associada(item);
			dao.insertSinc(novoRel);
		}	
		for (ClienteInteresseCategoria obj : listaExclusao) {
			//ClienteInteresseCategoria rel = dao.obtemPorIdsAssociadaCategoriaProduto(obj.getIdClienteA(), obj.getIdCategoriaProdutoA());
			dao.deleteSinc(obj);
		}	
	}
	*/
	
	
	
}