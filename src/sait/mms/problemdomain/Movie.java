/**
 * 
 */
package sait.mms.problemdomain;

/**
 * Represents a Movie
 * 
 * @author konradrudolf
 * @version Sep 17, 2021
 *
 */

public class Movie {
	private int duration;
	private String title;
	private int year;

	// constructor
	public Movie(int duration, String title, int year) {
		this.duration = duration;
		this.title = title;
		this.year = year;
	}

	public int getDuration() {
		return this.duration;
	}

	public String getTitle() {
		return this.title;
	}

	public int getYear() {
		return this.year;
	}

	public String formatForFile() {
		String movie = this.duration + "," + this.title + "," + this.year + "\n";
		return movie;
	}

	// Override toString()
	public String toString() {

		return duration + "\t" + title + "\t" + year;

	}

}
