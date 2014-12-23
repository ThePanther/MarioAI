package la.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.filechooser.FileNameExtensionFilter;

import la.common.Zone;

public class VisionFieldPersistence {
	public static String PATH = "visionfields/";
	public static String EXTENSION = "visionfield";

	public static void saveVisionField(ArrayList<Zone> visionField) throws IOException {
		DateFormat simpleDateFormat = new SimpleDateFormat("HH-mm-ss-dd-MM-YY");
		String fileName = String.format("%s%d-zones-%s.%s", PATH, visionField.size(), simpleDateFormat.format(new Date()), EXTENSION);
        OutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream o = new ObjectOutputStream(fos);

		o.writeObject(visionField);

		fos.close();
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Zone> loadVisionField(File visionFieldFile) throws ClassNotFoundException, IOException {
		ArrayList<Zone> visionField = null;

		if(visionFieldFile == null) {
			throw new IOException();
		} else {
			InputStream fis = new FileInputStream(visionFieldFile);
			ObjectInputStream o = new ObjectInputStream(fis);

			visionField = (ArrayList<Zone>) o.readObject();

			fis.close();
			visionFieldFile.setLastModified(new Date().getTime());
		}

		return visionField;
	}

	public static ArrayList<Zone> loadLastModifiedVisionField() throws ClassNotFoundException, IOException {
		return loadVisionField(getLastModifiedVisionField());
	}

	private static File getLastModifiedVisionField() {
		File returnFile = null;

		for(File file : new File(PATH).listFiles()) {
			if((returnFile == null || file.lastModified() > returnFile.lastModified()) && new FileNameExtensionFilter(null, EXTENSION).accept(file)) {
				returnFile = file;
			}
		}

		return returnFile;
	}
}