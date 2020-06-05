package com.taylor.ilhc.core;

import com.cavariux.twitchirc.Core.TwitchBot;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.eclipse.swt.widgets.Display;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.cavariux.twitchirc.Chat.Channel;
import com.cavariux.twitchirc.Chat.User;


public class ILHC extends TwitchBot {
	public static String request; 
	static JSONArray jsonArray;
	static JSONObject jsonObject;
	Boolean exists = false;
	static String runRequest;
	public static String jsonPath;
	public static String oauthKey;
	static String runConsole;
	
    
	public ILHC() {
		this.setUsername("ILHC");
		this.setOauth_Key(oauthKey);
		this.setClientID("3wcyrykem7jc46nm3exm7noj4di89q");
		
		JSONParser parser = new JSONParser();
		try {
		FileReader reader = new FileReader(jsonPath);
		jsonObject = (JSONObject) parser.parse(reader);
			if (jsonObject.containsKey("dgvSongsMaster")) {
				jsonArray = (JSONArray)jsonObject.get("dgvSongsMaster");
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (ParseException e3) {
	        e3.printStackTrace();
		}
	}
	
	@Override
	public void onCommand(User user, Channel channel, String command)
	{
		if (command.startsWith("sr")) {
			request = command.replace("sr ", "");
			if (request != "" && Window.takingReqs) {
				queryJson(request, channel);
				exists = false;
			}
		}
	}
	
	public void queryJson(String request, Channel channel){
		for (int i = 0; i < jsonArray.size(); i++) {
			String qRequest = request.replaceAll("[^a-zA-Z0-9_]", "");
	    	JSONObject obj = (JSONObject) jsonArray.get(i);
	    	String artist = obj.get("colArtist").toString();
	    	String qArtist = artist.replaceAll("[^a-zA-Z0-9_]", "");
	    	String song = obj.get("colTitle").toString();
	    	String qSong = song.replaceAll("[^a-zA-Z0-9_]", "");
	    	if (qRequest.equalsIgnoreCase(qArtist)) {
	    		runRequest = "Anything by " + artist;
	    		Display.getDefault().asyncExec(Window.runAddText);
	    		this.sendMessage("You have requested anything by '" + artist + "'", channel);
	    		exists = true;
	    		break;
	    	} else if (qRequest.equalsIgnoreCase(qSong)){
	    		runRequest = song + " - " + artist;
	    		Display.getDefault().asyncExec(Window.runAddText);
	    		this.sendMessage("You have requested " + song + " by " + artist, channel);
	    		exists = true;
	    		break;
	    	}
	    }
		if (exists != true) {
			this.sendMessage("The song or artist you have request does not appear to be in my database.", channel);
		}
	}

}




	


	



