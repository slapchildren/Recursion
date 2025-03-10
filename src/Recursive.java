/*  Student information for assignment:
 *
 *  On <MY> honor, <Gideon Mogaji> (and <NAME2),
 *  this programming assignment is <MY> own work
 *  and <I> have not provided this code to any other student.
 *
 *  Number of slip days used: 0
 *
 *  Student 1:
 *  UTEID: gm34284
 *  email address: gideonmogaji@gmail.com
 *
 *  Student 2:
 *  UTEID:
 *  email address:
 *
 *  Grader name: Gracelynn Ray
 *  Section number: 50620
 */

import java.awt.Color;
import java.awt.Graphics;

/**
 * Various recursive methods to be implemented.
 */
public class Recursive {

	/**
	 * Problem 1: Returns the number of elements in data that are followed directly
	 * by value that is double that element. pre: data != null post: return the
	 * number of elements in data that are followed immediately by double the value
	 *
	 * @param data The array to search.
	 * @return The number of elements in data that are followed immediately by a
	 *         value that is double the element.
	 */
	public static int nextIsDouble(int[] data) {
		if (data == null) {
			throw new IllegalArgumentException("Failed precondition: " + "revString. parameter may not be null.");
		}
		return countDoubles(data, 0);
	}

	/**
	 * helper method to kick off the recursion
	 * 
	 * @param data  data from the user, data != null
	 * @param index current index of the array, !(index < 0) !(index >= data.length)
	 * @return the number of nextDoubles in the array
	 */
	private static int countDoubles(int[] data, int index) {
		if (index < 0 || index >= data.length) {
			throw new IllegalArgumentException("Invalid index");
		}
		if (index == data.length - 1) {
			return 0;
		} else {
			int doubleCount = 0;
			if (data[index + 1] == data[index] * 2) {
				doubleCount = 1;
			}
			return doubleCount + countDoubles(data, index + 1);
		}
	}

	/**
	 * Problem 2: Draw a Sierpinski Carpet.
	 *
	 * @param size  the size in pixels of the window
	 * @param limit the smallest size of a square in the carpet.
	 */
	public static void drawCarpet(int size, int limit) {
		DrawingPanel p = new DrawingPanel(size, size);
		Graphics g = p.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, size, size);
		g.setColor(Color.WHITE);
		drawSquares(g, size, limit, 0, 0);
	}

	/*
	 * Helper method for drawCarpet Draw the individual squares of the carpet.
	 *
	 * @param g The Graphics object to use to fill rectangles
	 *
	 * @param size the size of the current square
	 *
	 * @param limit the smallest allowable size of squares
	 *
	 * @param x the x coordinate of the upper left corner of the current square
	 *
	 * @param y the y coordinate of the upper left corner of the current square
	 */
	private static void drawSquares(Graphics g, int size, int limit, double x, double y) {
		if (size > limit) {
			int numSquares = 3;
			int newSquareSize = size / numSquares;
			g.fillRect((int) (x + newSquareSize), (int) (y + newSquareSize), newSquareSize, newSquareSize);
			for (int i = 0; i < numSquares; i++) {
				for (int j = 0; j < numSquares; j++) {
					if (!(i == 1 && j == 1)) {
						drawSquares(g, newSquareSize, limit, x + i * newSquareSize, y + j * newSquareSize);
					}
				}
			}
		}
	}

	/**
	 * Problem 3: Find the minimum difference possible between teams based on
	 * ability scores. The number of teams may be greater than 2. The goal is to
	 * minimize the difference between the team with the maximum total ability and
	 * the team with the minimum total ability. pre: numTeams >= 2, abilities !=
	 * null, abilities.length >= numTeams post: return the minimum possible
	 * difference between the team with the maximum total ability and the team with
	 * the minimum total ability.
	 *
	 * @param numTeams  the number of teams to form
	 * @param abilities the ability scores of the people to distribute
	 * @return return the minimum possible difference between the team with the
	 *         maximum total ability and the team with the minimum total ability.
	 *         The return value will be greater than or equal to 0.
	 */
	public static int minDifference(int numTeams, int[] abilities) {
		return -1;
	}
}