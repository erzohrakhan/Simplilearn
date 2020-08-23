package com.locker;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class SubMenuImpl implements SubMenu {
	private Scanner in;
	private String fileName;
	private Path path;
	private String fileSeparator;

	public SubMenuImpl() {
		subMenuScreen();
	}

	private void subMenuScreen() {
		String[] subMenu = { "\n******** File operations ********", "\t1. Add a file", "\t2. Delete a file",
				"\t3. Search a file", "\t4. Main Menu\n", " Choose any option: " };

		boolean isNotExit = true;
		in = new Scanner(System.in);
		// While creating the file path, we should use System property file.
		// separator to make our program platform independent.
		fileSeparator = System.getProperty("file.separator");
		while (isNotExit) {
			Arrays.stream(subMenu).forEach(System.out::println);
			String option = in.next();
			if (option.equals(ADD) || option.equals(DELETE) || option.equals(SEARCH)) {
				System.out.print("\n\n File name: ");
				fileName = in.next();
				path = Paths.get(MainApp.DIRNAME + fileSeparator + fileName);
			}
			switch (option) {
			case ADD:
				addFile();
				break;

			case DELETE:
				deleteFile();
				break;

			case SEARCH:
				searchFile();
				break;

			case MAINMENU:
				mainMenu();
				break;

			default:
				System.out.println("Invalid option");

			}
		}
	}

	@Override
	public void addFile() {
		try {
			Files.createFile(path);
			System.out.println(fileName + " created");
		} catch (FileAlreadyExistsException x) {
			System.out.println("File " + fileName + " already exists. Try different name");
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	@Override
	public void deleteFile() {

		try {
			Files.delete(path);
			System.out.println(fileName + " deleted");
		} catch (NoSuchFileException x) {
			System.err.format("%s: no such" + " file %n", path);
		} catch (IOException e) {
			// File permissions problem are caught here
			System.err.println(e);
		}

	}

	@Override
	public void searchFile() {
		if (Files.exists(path)) {
			System.out.println("File found " + path);
		} else
			System.err.format("No such file exist. Try different name");

	}

	// Go back to Main menu
	@Override
	public void mainMenu() {
		new MainMenuImpl();
	}
}
