package supermercado.util;

/**
 * Classe responsável por fabricar Daos
 */
public class DAOFactory {
	
	public Object getDao(Class clas) throws InstantiationException, IllegalAccessException{
		return clas.newInstance();
	}
	
}
