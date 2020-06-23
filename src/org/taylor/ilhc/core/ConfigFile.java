package org.taylor.ilhc.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigFile {

	public static boolean wait = true;
	public static String oauthKey;
	public static String jsonPath;
	public static String channelName;
	protected static boolean firstTime = true;

	public static void main(String[] args){
		
	}
	public void open(){
		String path = "C:\\Users\\"+ System.getenv("USERNAME") +"\\AppData\\Roaming\\config.properties";
        try (InputStream input = new FileInputStream(path)) {
        	System.out.println("File Found!");
            Properties prop = new Properties();
            prop.load(input);

            channelName = prop.getProperty("db.channelName");
            System.out.println(channelName);
            channelName = "#" + channelName;
            oauthKey = prop.getProperty("db.oauthKey");
            System.out.println(oauthKey);
            jsonPath = prop.getProperty("db.jsonPath");
            System.out.println(jsonPath);
            
            Window.channelName = channelName;
            ILHC.jsonPath = jsonPath;
            ILHC.oauthKey = oauthKey;
        } catch (FileNotFoundException ex) {
        	System.out.println("File Not Found.");
        	Configure configure = new Configure();
			configure.open();
			while (wait) {}
        	OutputStream output = null;
			try {
				output = new FileOutputStream(path);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
            Properties prop = new Properties();

            prop.setProperty("db.channelName", channelName);
            prop.setProperty("db.oauthKey", oauthKey);
            prop.setProperty("db.jsonPath", jsonPath);
            Window.channelName = channelName;
            ILHC.jsonPath = jsonPath;
            ILHC.oauthKey = oauthKey;
            
            try {
				prop.store(output, null);
			} catch (IOException e) {
				e.printStackTrace();
			}

            System.out.println(prop);
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
	}

}
