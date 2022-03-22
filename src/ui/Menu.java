package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.MedalOrder;

public class Menu {

	private MedalOrder mo;

	public void startMenu() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int option = -1;
		do{
			mo = new MedalOrder();
			System.out.println("Welcome to the Medal Order\n");
			System.out.println("1: Use console");
			System.out.println("2: Use file");
			System.out.println("0: Leave\n");
			try {
				System.out.print("Select an option: ");
				option = Integer.parseInt(br.readLine());
				if(option == 0) {
					return;
				} else if(option == 1) {
					System.out.println("\nEnter the input\n");
					useConsole();
				} else if(option == 2) {
					System.out.print("\nEnter the file path: ");
					String path = br.readLine();
					useFile(path);
				} else {
					System.out.print("\nInvalid option");
				}
			} catch(NumberFormatException e) {
				option = -1;
				System.out.print("\nInvalid option");
			} catch(FileNotFoundException e) {
				option = -1;
				System.out.print("\nFile not found");
			}
			System.out.println("\n\nPress enter to continue");
			br.readLine();
		} while(option != 0);
		br.close();
	}

	private void useConsole() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		process(br);
	}

	private void useFile(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		process(br);
	}

	private void process(BufferedReader br) throws IOException {
		int n = 0;
		try {
			String temp = br.readLine();
			if(temp==null || temp.equals("")) {
				System.out.println("\n\nThe file is empty");
				return;
			}
			n = Integer.parseInt(temp);
		} catch(NumberFormatException e) {
			System.out.println("\n\nThe first line must be an integer");
			return;
		}
		try {
			for(int i=0; i<n; i++) {
				String[] line = br.readLine().split(";");
				String name = line[0];
				int mg = Integer.parseInt(line[1]);
				int ms = Integer.parseInt(line[2]);
				int mb = Integer.parseInt(line[3]);
				int fg = Integer.parseInt(line[4]);
				int fs = Integer.parseInt(line[5]);
				int fb = Integer.parseInt(line[6]);
				mo.addCountry(name, mg, ms, mb, fg, fs, fb);
			}
			System.out.print("\n"+mo.printOrders());

		} catch(IndexOutOfBoundsException | NumberFormatException e) {
			System.out.println("\n\nVerify that each line is made up of String;int;int;int;int;int;int");
		} catch(NullPointerException e) {
			System.out.println("\n\nThe number of countries announced is greater than the number of countries in the file");
		}
	}	
}
