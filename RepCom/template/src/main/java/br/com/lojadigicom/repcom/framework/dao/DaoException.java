
package  br.com.lojadigicom.repcom.framework.dao;

public class DaoException extends Exception {
	private Exception _original;
	private String _mensagemAdicional;

	public DaoException(Exception original) {
		this._original = original;
		this._mensagemAdicional = null;
	}

	public DaoException(Exception original, String mensagem) {
		this._mensagemAdicional = mensagem;
		this._original = original;
	}

	public String getMessage() {
		return (this._mensagemAdicional == null ? "" : this._mensagemAdicional)
				+ this._original.getMessage();
	}

	public Exception getOriginal() {
		return this._original;
	}

	public void printStackTrace() {
		this._original.printStackTrace();
	}
}
