package program18;

import java.io.FileWriter;
import java.io.IOException;

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
	 * returns true if point target is within the triangle made by triangle points
	 * @param trianglePoints
	 * @param target
	 * @return
	 */
	private static boolean function3(double[][] trianglePoints, double[] target) {
		double[][] polygonPoints = new double[4][2];
		for(int i=0; i<3; i++) {
			polygonPoints[i][0] = trianglePoints[i][0];
			polygonPoints[i][1] = trianglePoints[i][1];
		}
		polygonPoints[3][0] = target[0];
		polygonPoints[3][1] = target[1];
		
		/**
		 * if the triangle has a larger area the the polygon made by the triangle points AND
		 * the target point, then the point is in the triangle, if not, the point is outside the
		 * triangle (polygon is bigger than triangle)
		 */
		if(calculateTriangleArea(trianglePoints) > calculateQuadrilateralArea(polygonPoints)) {
			return true;
		} else return false;
	}
	
	private static double distanceFormula(double[] point1, double[] point2) {
		return Math.sqrt(Math.pow(point2[0] - point1[0], 2) + Math.pow(point2[1] - point2[1], 2));
	}
	
	private static void sortPointsByX(double[][] points) {
		
	}
	
	private static double calculateTriangleArea(double[][] points) {
		double a = distanceFormula(points[0], points[1]);
		double b = distanceFormula(points[1], points[2]);
		double c = distanceFormula(points[2], points[3]);
		
		double semiP = (a + b + c)/2;
		return Math.sqrt(semiP*(a-semiP)*(b-semiP)*(c-semiP));
	}
	
	/**
	 * returns the area of the polgyon made by the points passed through the parameter
	 * @param points
	 * @return
	 */
	private static double calculateQuadrilateralArea(double[][] points) {
		if(points.length < 3) {
			throw new IllegalArgumentException("A polygon must have at least 3 vertices");
		}
		
		double total = 0;
		for(int i=0; i< points.length; i++) {
			int j = (i+1) % points.length;
			total+= points[i][0] * points[j][1] - points[j][0] * points[i][1];
		}
		return Math.abs(total / 2.0);
	}
	
	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FileWriter myWriter = new FileWriter("recursiveBinaryTriangle.txt");
			myWriter.write("Function 1 Output: \n");
			myWriter.write("f(1): " + function1(1) + "\n");
			myWriter.write("f(15): " + function1(15) + "\n");
			myWriter.write("f(27): " + function1(27) + "\n");
			myWriter.write("f(53): " + function1(53) + "\n");
			myWriter.write("\n");
			myWriter.write("Function 2: \n");
			myWriter.write("Using the following points: x=1, y=1000, z=1023 + \n");
			myWriter.write("Output: " + function2(1, 1000, 1023) + "\n");
			myWriter.write("\n");
			myWriter.write("Function 3: \n");
			double[][] tPoints = new double[3][2];
			tPoints[0][0]=2.2;
			tPoints[0][1]=5.6;
			tPoints[1][0]=1.1;
			tPoints[1][1]=2.2;
			tPoints[2][0]=2.4;
			tPoints[2][1]=4.7;
			double[] target = new double[2];
			target[0] = 2.5;
			target[1] = 3.0;
			if(function3(tPoints, target)) {
				myWriter.write("The point (" + target[0] + ", " + target[1] + ") is "
						+ "inside the triangle with coordinates (" + tPoints[0][0] + ", " 
						+ tPoints[0][1] + "), " + "(" + tPoints[1][0] + ", " 
						+ tPoints[1][1] + "), " + "(" + tPoints[2][0] + ", " 
						+ tPoints[2][1] + ").");
			} else {
				myWriter.write("The point (" + target[0] + ", " + target[1] + ") is "
						+ "not inside the triangle with coordinates (" + tPoints[0][0] + ", " 
						+ tPoints[0][1] + "), " + "(" + tPoints[1][0] + ", " 
						+ tPoints[1][1] + "), " + "(" + tPoints[2][0] + ", " 
						+ tPoints[2][1] + ").");
			}
			myWriter.write('\n');
			
			double[][] t2Points = new double[3][2];
			t2Points[0][0]=-4.2;
			t2Points[0][1]=3.0;
			t2Points[1][0]=-2.7;
			t2Points[1][1]=3.0;
			t2Points[2][0]=-9.1;
			t2Points[2][1]=8.18;
			double[] target2 = new double[2];
			target2[0] = -4.4;
			target2[1] = 3.8;
			if(function3(t2Points, target2)) {
				myWriter.write("The point (" + target2[0] + ", " + target2[1] + ") is "
						+ "inside the triangle with coordinates (" + tPoints[0][0] + ", " 
						+ t2Points[0][1] + "), " + "(" + t2Points[1][0] + ", " 
						+ t2Points[1][1] + "), " + "(" + t2Points[2][0] + ", " 
						+ t2Points[2][1] + ").");
			} else {
				myWriter.write("The point (" + target2[0] + ", " + target2[1] + ") is "
						+ "not inside the triangle with coordinates (" + tPoints[0][0] + ", " 
						+ t2Points[0][1] + "), " + "(" + t2Points[1][0] + ", " 
						+ t2Points[1][1] + "), " + "(" + t2Points[2][0] + ", " 
						+ t2Points[2][1] + ").");
			}
			myWriter.close();
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}	
	}
}