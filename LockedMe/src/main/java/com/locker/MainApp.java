package com.locker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;


public class MainApp {
	private static String separator = "*************************************************************************************************";

	public static final String DIRNAME = "locker"; // name of the root directory folder

	public static void main(String[] args) {
		String[] welcomeMessage = { separator, "\n\t\tWelcome to Locker\n", "\t\tDeveloper: Zohra Khan\n",
				separator };

		// print welcome message
		Arrays.stream(welcomeMessage).forEach(System.out::println);

		// If folder does not exist then create it
		Path path = Paths.get(DIRNAME);
		if (!Files.exists(path)) {
			try {
				Files.createDirectory(path);
			} catch (IOException e) {
				System.out.println("Writing permissions are not granted to create a directory in the current folder.");
				// fail to create directory
				return;
			}
		}

		// Display Main menu
		new MainMenuImpl();

	}
}
