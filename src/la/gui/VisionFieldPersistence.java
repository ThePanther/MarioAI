package la.gui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import la.common.Zone;

public class VisionFieldPersistence {
	private static String FILENAME = "VisionField.ser";

	public static void saveVisionField(ArrayList<Zone> visionField) throws IOException {
        OutputStream fos = new FileOutputStream(FILENAME);
        ObjectOutputStream o = new ObjectOutputStream(fos);

		o.writeObject(visionField);

		fos.close();
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Zone> loadVisionField() throws ClassNotFoundException, IOException {
		ArrayList<Zone> visionField = null;

		InputStream fis = new FileInputStream(FILENAME);
		ObjectInputStream o = new ObjectInputStream(fis);

		visionField = (ArrayList<Zone>) o.readObject();

		fis.close();

		return visionField;
	}
}