package persistence.importhandler.impl;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import persistence.importhandler.ImportHandler;

public class ImportHandlerImpl implements ImportHandler{
	@Override
	public DBConfig getDBConfig() {
		DBConfig dbConfig = null;
		try {
			File fXmlFile = new File("config/db.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("datenbank");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				// System.out.println("\nCurrent Element :" +
				// nNode.getNodeName());
				Element eElement = (Element) nNode;
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					String dbname = eElement.getElementsByTagName("dbname").item(0).getTextContent();
					String dbhost = eElement.getElementsByTagName("host").item(0).getTextContent();
					String user = eElement.getElementsByTagName("username").item(0).getTextContent();
					String password = eElement.getElementsByTagName("password").item(0).getTextContent();
					String tablename = eElement.getElementsByTagName("tablename").item(0).getTextContent();
					String stateName = eElement.getElementsByTagName("state").item(0).getTextContent();
					int numberOfActions = Integer.parseInt(eElement.getElementsByTagName("numberOfActions").item(0).getTextContent());					   
					dbConfig = new DBConfig(dbhost, dbname, user, password, tablename, stateName, numberOfActions);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbConfig;
	}
}
