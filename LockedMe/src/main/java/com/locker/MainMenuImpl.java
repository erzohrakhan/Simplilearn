package com.locker;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainMenuImpl implements MainMenu {

	public MainMenuImpl() {
		mainMenuScreen();

	}

	// Displays main menu and get Input From User
	private void mainMenuScreen() {
		String[] mainMenuOptions = { "\n******** Main Menu ********", "\n\t1. List all files ",
				"\n\t2. File menu operations", "\n\t3. Exit", "\n Choose any option: " };

		boolean isNotExit = true;
		Scanner in = new Scanner(System.in);

		// Infinite loop to get multiple inputs from user
		while (isNotExit) {
			Arrays.stream(mainMenuOptions).forEach(System.out::print);
			// Option selected by user
			String option = in.next();
			switch (option) {
			case DISPLAY:
				listFiles();
				break;

			case SUBMENU:
				subMenu();
				break;

			case EXIT:
				in.close();
				exitApp();
				break;

			default:
				System.out.print("Invalid option");
			}
		}
	}

	@Override
	public void listFiles() {
		// List all files in ascending order
		ArrayList<String> fileList = new ArrayList<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(MainApp.DIRNAME))) {
			for (Path path : stream) {
				fileList.add(path.getFileName().toString());
			}
			if (fileList.isEmpty()) {
				System.out.println("Directory is empty");
				return;
			}

			// sort file names in ascending order.
			// Collections.sort(fileList);
			quickSort(fileList, 0, fileList.size() - 1);
			fileList.forEach(fileName -> System.out.println(fileName));
		} catch (IOException e) {
			// e.printStackTrace();
			System.out.println("Directory does not exist");
		}
	}

	@Override
	public void subMenu() {
		new SubMenuImpl();
	}

	@Override
	public void exitApp() {
		System.out.println("Application closed. Thank you!");
		System.exit(0);

	}
	/*
	 * The main function that implements QuickSort() arr[] --> Array to be sorted,
	 * low --> Starting index, high --> Ending index
	 */

	private void quickSort(ArrayList<String> list, int low, int high) {
		if (low < high) {
			int pi = partiton(list, low, high);
			quickSort(list, low, pi - 1);
			quickSort(list, pi + 1, high);
		}

	}

	private int partiton(ArrayList<String> list, int low, int high) {
		String pivot = list.get(high);
		int i = low - 1; // index of smaller element

		for (int j = low; j < high; j++) {
			if (list.get(j).compareTo(pivot) <= 0) {
				i++;
				swap(list, i, j);
			}

		}
		i += 1;
		swap(list, i, high);

		return i;

	}

	private void swap(ArrayList<String> list, int i, int j) {
		String temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}

}
