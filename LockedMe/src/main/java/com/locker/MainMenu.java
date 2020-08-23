package com.locker;

public interface MainMenu {
	// Option for listing all files
	public final String DISPLAY = "1";
	// Sub menu option
	public final String SUBMENU = "2";
	// Exit from the application
	public final String EXIT = "3";

	// List all the files
	public void listFiles();

	// For business level operations
	public void subMenu();

	// To exit
	public void exitApp();

}
