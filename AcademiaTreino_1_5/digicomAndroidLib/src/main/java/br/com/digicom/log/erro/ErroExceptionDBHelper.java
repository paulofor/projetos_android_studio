package br.com.digicom.log.erro;

import br.com.digicom.dao.DataSource;
import br.com.digicom.dao.MontadorDaoI;

public class ErroExceptionDBHelper extends ErroExceptionDBHelperBase{

   public void setDataSource(DataSource ds) {
	   super.setDataSource(ds);
   }


}
