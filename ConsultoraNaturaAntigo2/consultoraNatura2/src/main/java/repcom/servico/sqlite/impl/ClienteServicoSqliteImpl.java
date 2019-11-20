
package repcom.servico.sqlite.impl;

import java.util.ArrayList;
import java.util.List;

import repcom.dao.ClienteDBHelper;
import repcom.modelo.Cliente;
import repcom.modelo.Usuario;
import repcom.modelo.vo.FabricaVo;
import repcom.servico.FabricaServico;
import repcom.servico.UsuarioServico;
import repcom.servico.sqlite.base.ClienteServicoSqliteBase;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.dao.DaoException;
import br.com.digicom.log.DCLog;

public class ClienteServicoSqliteImpl extends ClienteServicoSqliteBase {


	@Override
	protected List<Cliente> ObtemListaAgendaTelImpl(DigicomContexto contexto) {
		List<Cliente> listaContato = new ArrayList<Cliente>();
		ContentResolver cr = contexto.getContext().getContentResolver();
		Cursor cursor = contexto.getContext().getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		while (cursor.moveToNext()) {
			Cliente cliente = FabricaVo.criaCliente();
			cliente.setContexto(contexto);
			try {
				String lookupKey = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY ));
				String nome = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				//String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
				cliente.setCodigoListaContato(lookupKey);
				cliente.setNome(nome);
				listaContato.add(cliente);
			} catch (Exception e) {
			}
		}
		return listaContato;
		/*
		List<Cliente> contactData = new ArrayList<Cliente>();
		ContentResolver cr = contexto.getContext().getContentResolver();
		Cursor cursor = contexto.getContext().getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		while (cursor.moveToNext()) {
			Cliente cliente = FabricaVo.criaCliente();
			try {
				String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
				String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				cliente.setNome(name);
				String hasPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
				if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
					Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
							ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
					while (phones.moveToNext()) {
						String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
						cliente.setTelefoneCelular(phoneNumber);
					}
					phones.close();
				}
			contactData.add(cliente);
			} catch (Exception e) {
			}
		}
		return contactData;
		*/
	}

	@Override
	protected List<Cliente> SincronizaAgendaTelImpl(DigicomContexto contexto) {
		List<Cliente> listaAgenda = this.ObtemListaAgendaTelImpl(contexto);
		UsuarioServico usuarioSrv = FabricaServico.getInstancia().getUsuarioServico(FabricaServico.TIPO_SQLITE);
		Usuario usuario = usuarioSrv.getCorrente();
		// Incluindo pessoas novas - não estou considerando alterações
		for (Cliente itemAgenda : listaAgenda) {
			getFiltro().setCodigoAgendaTel(itemAgenda.getCodigoListaContato());
			Cliente itemLocal = this.ObtemPorIdAgendaTelImpl(contexto);
			if (itemLocal==null) {
				itemAgenda.setIdUsuarioS(usuario.getId());
				itemAgenda.setAtivo(true);
				this.insereParaSincronizacao(itemAgenda);
			}
		}
		return listaAgenda;
	}

	
	
	

	@Override
	protected Cliente ObtemPorIdAgendaTelImpl(DigicomContexto contexto) {
		Cliente saida = null; 
		saida = getDao().obtemPorIdAgendaTel(getFiltro().getCodigoAgendaTel());
		return saida;
	}

	protected Cliente ObtemPorIdAgendaTelImpl2(DigicomContexto contexto) {
		Cliente cliente = getFiltro().getItem();
		String[] projection = new String[] {
								ContactsContract.Contacts.DISPLAY_NAME
		                      };

		Uri uri = Uri.parse (ContactsContract.Contacts.CONTENT_LOOKUP_URI + "/" + getFiltro().getCodigoAgendaTel());
		ContentResolver cr = contexto.getContext().getContentResolver();
		Cursor cursor = cr.query(uri, null, null, null, null);
		if (cursor.moveToFirst()) {
  			cliente = FabricaVo.criaCliente();
  			cliente.setCodigoListaContato(getFiltro().getCodigoAgendaTel());
// 			cliente.setNome(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
  		}
  		return cliente;
	}

	@Override
	protected Cliente PreencheDerivadaAgendaTelImpl(DigicomContexto contexto) {
		Cliente item = getFiltro().getItem();
		String[] projection = new String[] {
				ContactsContract.Contacts.DISPLAY_NAME
              };
		String codigo = item.getCodigoListaContato();
		Uri uri = Uri.parse (ContactsContract.Contacts.CONTENT_LOOKUP_URI + "/" + codigo);
		ContentResolver cr = contexto.getContext().getContentResolver();
		Cursor cursor = cr.query(uri, null, null, null, null);
		if (cursor.moveToFirst()) {
			item.setNome(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
		}
		return item;
	}

	@Override
	protected Cliente DesativaPorIdImpl(DigicomContexto contexto) {
		long id = getFiltro().getItem().getId();
		Cliente clienteBd = getDao().getById(id);
		if (clienteBd!=null) {
			clienteBd.setAtivo(false);
			this.alteraParaSincronizacao(clienteBd);
		}
		return clienteBd;
	}

	

	@Override
	protected List<Cliente> ListaAtivosImpl(DigicomContexto contexto) {
		ClienteDBHelper dao = getDao();
		List<Cliente> saida = dao.listaAtivos();
		return saida;
	}

	@Override
	protected List<Cliente> PesquisaTrechoNomeImpl(DigicomContexto contexto) {
		ClienteDBHelper dao = getDao();
		String trecho = getFiltro().getItem().getNome();
		List<Cliente> saida = dao.PesquisaTrechoNome(trecho);
		return saida;
	}

}