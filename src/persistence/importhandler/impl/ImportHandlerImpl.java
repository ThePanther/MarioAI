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
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("datenbank-configuration");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					String dbname = eElement.getElementsByTagName("dbname").item(0).getTextContent();
					String dbhost = eElement.getElementsByTagName("host").item(0).getTextContent();
					String user = eElement.getElementsByTagName("username").item(0).getTextContent();
					String password = eElement.getElementsByTagName("password").item(0).getTextContent();
					String driver = eElement.getElementsByTagName("driver").item(0).getTextContent();
					String url = "jdbc:mysql://"+dbhost+"/";
					dbConfig = new DBConfig(dbhost, dbname, user, password, driver, url);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbConfig;
	}
}
