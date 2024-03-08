package program18;

import java.util.ArrayList;

public class Program18 {

	/**
	 * function1
	 * returns different output based on the parameter
	 * @param x
	 * @return
	 */
	private static double function1(int x) {
		if(x<=1) {
			return 1.0;
		} else if(isPrime(x)) {
			return x*x +x;
		} else {
			return 2*function1(x-3) -4;
		}
	}
	
	/**
	 * helper method to function1
	 * returns true if the integer passed as a parameter is a prime number
	 * only works for positive integers
	 * @param x
	 * @return
	 */
	private static boolean isPrime(int x) {
		int i=2;
		while(i<x) {
			if(x % i == 0) {
				return false;
			}
			i++;
		}
		return true;
	}
	
	/**
	 * counts how many integers between x and y, given that x<y<z
	 * have a binary representation consisting of 8 1s
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	private static int function2(int x, int y, int z) {
		int count = 0;
		String current;
		for(int i=x+1; i<y; i++) {
			current = Integer.toBinaryString(i);
			for(int a=0; a<current.length(); a++) {
				int countOnes = 0;
				if(current.charAt(a) == '0') {
					countOnes++;
				}
				if(countOnes == 8) {
					count++;
				}
			}
		}
		return count;
	}
	
	/**
	 * the parameters are 4 x and y coordinates of the or x, y, x, y ...
	 * the first 3 xy pairs are the vertices of a triangle
	 * the method returns true if the 4th xy pair is in the triangle(or on the border)
	 * @param p1x
	 * @param p1y
	 * @param p2x
	 * @param p2y
	 * @param p3x
	 * @param p3y
	 * @param p4x
	 * @param p4y
	 * @return
	 */
	private static boolean function3(double p1x, double p1y, double p2x, double p2y, double p3x, double p3y, double p4x, double p4y) {
		ArrayList<Double> orderedByX = sort(p1x, p1y, p2x, p2y, p3x, p3y);
		ArrayList<Double> orderedByY = sort(p1y, p1x, p2y, p2x, p3y, p3x);
		double right, left;
		double up, down;
		double m, b;
		//case 1 - no vertices of the triangle have the same x or y values
		if(((orderedByX.get(0) < orderedByX.get(2)) && (orderedByX.get(2) < orderedByX.get(4))) &&
			((orderedByY.get(0) < orderedByY.get(2)) && (orderedByY.get(2)) < orderedByY.get(4))) {
			m = slopeOf(orderedByX.get(2), orderedByX.get(3), orderedByX.get(4), orderedByX.get(5));
			b = orderedByX.get(5) - m*orderedByX.get(4);
			right = (p4y-b)/m;
			m = slopeOf(orderedByX.get(2), orderedByX.get(3), orderedByX.get(0), orderedByX.get(1));
			b = orderedByX.get(1) - m*orderedByX.get(0);
			left = (p4y-b)/m;
			// now find what up and down would be
			
			//then check if left<=p4x<=right && down<=p4x<=up
		}
		
		return false;
	}
	
	private static double slopeOf(double x1, double y1, double x2, double y2) {
		return (y2-y1)/(x2-x1);
	}
	
	private static ArrayList<Double> sort(double a, double a2, double b, double b2, double c, double c2) {
		ArrayList<Double> array = new ArrayList<Double>();
		if(a>b) {
			array.add(b);
			array.add(b2);
			array.add(a);
			array.add(a2);
		} else {
			array.add(a);
			array.add(a2);
			array.add(b);
			array.add(b2);
		}
		if(c<array.get(0)) {
			array.add(1, c);
			array.add(2, c2);
		} else if(c<array.get(2)) {
			array.add(2, c);
			array.add(3, c2);
		} else {
			array.add(c);
			array.add(c2);
		}
		return array;
	}
	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

}
