/**
 * 
 */
package sait.mms.managers;

import sait.mms.problemdomain.Movie;

import java.io.*;
import java.util.*;

/**
 * Manages the movie database
 * 
 * @author konradrudolf
 * @version sep 17, 2021
 *
 */

public class MovieManagementSystem {
	// constants
	public static final String TEXT_FILE = "res/movies.txt";

	// fields
	public ArrayList<Movie> movies;
	Scanner read = new Scanner(System.in);

	// constructors
	public MovieManagementSystem() {

		this.movies = new ArrayList<>();
	}

	// methods
	public void loadMovies() throws FileNotFoundException {

		// open the res/movies.txt file

		File file = new File(TEXT_FILE);

		// create the movies text file parser

		Scanner in = new Scanner(file);

		// loop through lines of file

		while (in.hasNextLine()) {
			String line = in.nextLine();

			int duration;
			String title;
			int year;

			String[] parts = line.split(",");

			String durationString = parts[0];
			duration = Integer.parseInt(durationString);

			title = parts[1];

			String yearString = parts[2];

			year = Integer.parseInt(yearString);

			// create movie object from the parsed line
			Movie movie = new Movie(duration, title, year);

			// add the movie object to the movies field
			this.movies.add(movie);

		}

		// close
		in.close();
	}

	public void addMovie(int duration, String title, int year) {

		if (duration < 0) {
			System.out.println("Error: The movie duration should be any number greater than zero.");
			System.out.println();
			return;
		}

		if (title.equals("")) {
			System.out.println("Error: The movie title should have at least one character.");
			System.out.println();
			return;
		}

		if (year <= 0) {
			System.out.println("Error: The release year should be any number greater than zero.");
			return;

		}

		else if (duration > 0 && title != "" && year > 0) {

			Movie newMovie = new Movie(duration, title, year);
			movies.add(newMovie);

			System.out.println("Added Movie to the file! " + newMovie);
			System.out.println();
		}

	}

	public void generateMoviesInYear(int year) {
		System.out.println();

		int searchListLength = movies.size();
		int totalduration = 0;

		System.out.println("Movie List");
		System.out.printf("%-9s %-9s %-9s \n", "Duration", "Year", "Title");

		for (int i = 0; i < searchListLength; i++) {

			if (movies.get(i).getYear() == year) {

				System.out.printf("%-10s", movies.get(i).getDuration());
				System.out.printf("%-10s", movies.get(i).getYear());
				System.out.printf("%-10s \n", movies.get(i).getTitle());

				totalduration += movies.get(i).getDuration();
			}
		}

		System.out.println();
		System.out.println("Total Duration: " + totalduration + " minutes");
		System.out.println();
	}

	public void generateRandomMovies(int i) {
		System.out.println();
		System.out.println();

		int totalduration = 0;

		System.out.println("Movie List");
		System.out.printf("%-9s %-9s %-9s \n", "Duration", "Year", "Title");

		for (int n = 0; n < i; n++) {

			double randomnum = (Math.random() * movies.size());

			System.out.printf("%-10s", movies.get((int) randomnum).getDuration());
			System.out.printf("%-10s", movies.get((int) randomnum).getYear());
			System.out.printf("%-10s \n", movies.get((int) randomnum).getTitle());

			totalduration += movies.get((int) randomnum).getDuration();
		}

		System.out.println("Total Duration: " + totalduration + " minutes");
		System.out.println();
	}

	public void writeMoviesToFile() throws IOException {
		File file = new File(TEXT_FILE);
		PrintWriter writer = new PrintWriter(file);
		int searchListLength = movies.size();

		for (int i = 0; i < searchListLength; i++) {

			Movie forfile = movies.get(i);
			writer.write(forfile.formatForFile());

		}

		System.out.println("Saving Movies...");
		System.out.println("Movie list saved successfully!");

		writer.close();
	}

	public void displayMenu() throws IOException {

		// Scanner read = new Scanner(System.in);

		String option1 = " 1 \t Add New Movie and Save";
		String option2 = "2 \t Generate List of Movies Released in a Year";
		String option3 = "3 \t Generate List of Random Movies";
		String option4 = "4 \t Save & Exit";

		System.out.println("Movie Management System");
		System.out.printf("%-10s \n %-10s \n %-10s \n %-10s \n", option1, option2, option3, option4);

		System.out.print("Enter an option (1-4): ");
		int userin = read.nextInt();

		if (userin == 1) {

			System.out.print("Enter a movie Duration(mins): ");
			int duration = read.nextInt();

			System.out.print("Enter a movie Title: ");
			String title = read.next();

			System.out.print("Enter a movie Year: ");
			int year = read.nextInt();

			System.out.println("Saving movie...");
			addMovie(duration, title, year);

		}

		if (userin == 2) {
			System.out.print("Enter in year: ");
			int year = read.nextInt();
			generateMoviesInYear(year);
		}

		if (userin == 3) {
			System.out.print("Enter number of movies: ");
			int random = read.nextInt();
			generateRandomMovies(random);
		}

		if (userin == 4) {
			writeMoviesToFile();
			System.out.printf("%s \n", "Goodbye! \n");
			return;
		}

		else if (userin > 4) {
			System.out.println("Invalid option! Please try again.");
			System.out.println();
		}

	}

}
