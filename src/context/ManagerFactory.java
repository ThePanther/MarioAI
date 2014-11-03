package context;

import java.util.HashMap;
import java.util.Map;

import la.rlGlue.application.Fassade.RLGlueService;
import la.rlGlue.application.Fassade.impl.Fassade;
import la.rlGlue.persistence.database.Database;
import la.rlGlue.persistence.database.impl.DatabaseImpl;
import la.rlGlue.persistence.importhandler.ImportHandler;
import la.rlGlue.persistence.importhandler.impl.ImportHandlerImpl;

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
