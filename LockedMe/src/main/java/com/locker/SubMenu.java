package com.locker;

public interface SubMenu {
	//Option to add a file
	public final String ADD = "1";
	//Option to delete a file
	public final String DELETE = "2";
	//Option to Search a file
	public final String SEARCH = "3";
	//Option to go back to main menu
	public final String MAINMENU = "4";
	
	public void addFile();
	public void deleteFile();
	public void searchFile();
	public void mainMenu();

}
