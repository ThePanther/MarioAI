package context;

import java.util.HashMap;
import java.util.Map;

import persistence.database.Database;
import persistence.database.impl.DatabaseImpl;
import persistence.importhandler.ImportHandler;
import persistence.importhandler.impl.ImportHandlerImpl;

//	Beispiel: Database db = ManagerFactory.getManager(Database.class); 
public class ManagerFactory {
	private static Map<Class<?>, Object> managerMap = new HashMap<Class<?>, Object>();
	
	static {
		ManagerFactory.managerMap.put(ImportHandler.class, new ImportHandlerImpl()); 
		ManagerFactory.managerMap.put(Database.class, new DatabaseImpl()); 
	}
	
    private ManagerFactory() {
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T getManager(final Class<?> mgrInterface) {
        return (T)ManagerFactory.managerMap.get(mgrInterface);
    }
}
