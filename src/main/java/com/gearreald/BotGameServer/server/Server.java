package com.gearreald.BotGameServer.server;

import static spark.Spark.*;

import com.gearreald.BotGameServer.server.controllers.MatchController;
import com.gearreald.BotGameServer.server.controllers.MatchRequestController;

import spark.servlet.SparkApplication;

public class Server implements SparkApplication {
	public static void staticLaunch(){
		configureInstance();
 	}
	public void init(){
		configureInstance();
	}
	public static void configureInstance(){
		initialConfiguration();
    	getRouting();
    	postRouting();
    	System.out.println("Server Fully Configured");
	}
	public static void initialConfiguration(){
		SQLConnection.runMigrations();
		staticFiles.location("/public");
		port(12346);
	}
	public static void getRouting(){
		get(Path.MATCHMAKING_STATUS, MatchRequestController.matchRequestGet);
		get(Path.NEW_MATCHMAKING, MatchRequestController.newMatchRequest);
		get(Path.GAME_STATUS, MatchController.matchGet);
	}
	public static void postRouting(){
		post(Path.TAKE_ACTION, MatchController.takeAction);
	}
	public static void exit(){
		stop();
	}
}
