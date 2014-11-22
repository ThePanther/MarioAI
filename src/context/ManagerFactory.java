package context;

import java.util.HashMap;
import java.util.Map;

import la.application.Fassade.RLGlueService;
import la.application.Fassade.impl.Fassade;
import la.persistence.database.Database;
import la.persistence.database.impl.DatabaseImpl;
import la.persistence.importhandler.ImportHandler;
import la.persistence.importhandler.impl.ImportHandlerImpl;

//	Beispiel: Database db = ManagerFactory.getManager(Database.class); 
public class ManagerFactory {
	private static Map<Class<?>, Object> managerMap = new HashMap<Class<?>, Object>();
	
	static {
		ManagerFactory.managerMap.put(ImportHandler.class, new ImportHandlerImpl()); 
		ManagerFactory.managerMap.put(Database.class, new DatabaseImpl());
        ManagerFactory.managerMap.put(RLGlueService.class, new Fassade());
	}
	
    private ManagerFactory() {
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T getManager(final Class<?> mgrInterface) {
        return (T)ManagerFactory.managerMap.get(mgrInterface);
    }
}
