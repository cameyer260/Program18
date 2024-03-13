/**
 * Name: Christopher Meyer
 * Group: Hayden Cook, Paul Manning, Sophie Byron
 * Class: AP Computer Science A
 * Instructor: Mr. Meinzen
 * Project: Program 18
 * Date: 13 March 2024
 */

package program18;

import java.util.ArrayList;
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
	 * counts how many integers between x and y (given that x<y<z)
	 * have a binary representation consisting of 8 1s
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	private static int function2(int x, int y, int z) {
		int countZeros = 0;
		int count = 0;
		ArrayList<Integer> myArrayList = new ArrayList<>();
		Integer temp;
		for(int i=x+1; i<y; i++) {
			temp = i;
			//convert i to binary
			if(temp==0) {
				myArrayList.add(0, 0);
			}
			while(temp>0) {
				myArrayList.add(0, temp % 2);
				temp/=2;
			}
			//count how many 1s are in the binary representation of i
			for(Integer a: myArrayList) {
				if(a.equals(1)) {
					countZeros ++;
				}
			}
			//if there are 8 increase the count of #s with 8 1s in their binary representation
			if(countZeros == 8) {
				count++;
				System.out.println(i);
			}
			myArrayList.clear();
			countZeros = 0;
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
		double[][] quadrilateralPoints = new double[4][2];
		for(int i=0; i<3; i++) {
			quadrilateralPoints[i][0] = trianglePoints[i][0];
			quadrilateralPoints[i][1] = trianglePoints[i][1];
		}
		quadrilateralPoints[3][0] = target[0];
		quadrilateralPoints[3][1] = target[1];
		
		/**
		 * if the triangle has a larger area the the polygon made by the triangle points AND
		 * the target point, then the point is in the triangle, if not, the point is outside the
		 * triangle (polygon is bigger than triangle)
		 */
		if(calculateTriangleArea(trianglePoints) > calculateQuadrilateralArea(quadrilateralPoints)) {
			return true;
		} else return false;
	}
	
	/**
	 * returns the distance between two points, assuming that the parameters consist of x at index 0
	 * and y at index 1
	 * @param point1
	 * @param point2
	 * @return
	 */
	private static double distanceFormula(double[] point1, double[] point2) {
		return Math.sqrt(Math.pow(point2[0] - point1[0], 2) + Math.pow(point2[1] - point1[1], 2));
	}
	
	/**
	 * sorts the matrix parameter by the first index of each array
	 * in this case that would be the x value of the coordinates
	 * @param points
	 */
	private static void sortPointsByX(double[][] points) {
		for(int i=0; i<points.length; i++) {
			for(int j=0; j<points.length-1; j++) {
				if(points[j][0] > points[j+1][0]) {
					double temp1 = points[j][0];
					points[j][0] = points[j+1][0];
					points[j+1][0] = temp1;
					double temp2 = points[j][1];
					points[j][1] = points[j+1][1];
					points[j+1][1] = temp2;
				}
			}
		}
	}
	
	/**
	 * calculates the area of the triangle created by the points passed
	 * through the parameter
	 * @param points
	 * @return
	 */
	private static double calculateTriangleArea(double[][] points) {
		double a = distanceFormula(points[0], points[1]);
		double b = distanceFormula(points[1], points[2]);
		double c = distanceFormula(points[2], points[0]);
		
		double semiP = (a + b + c)/2;
		double answer = semiP*(semiP-a)*(semiP-b)*(semiP-c);
		
		return Math.sqrt(answer);
	}
	
	/**
	 * returns the area of the quadrilateral made by the points passed through the parameter
	 * @param points
	 * @return
	 */
	private static double calculateQuadrilateralArea(double[][] points) {
		sortPointsByX(points);
		double[][] tri1 = new double[3][2];
		double[][] tri2 = new double[3][2];
		for(int i=0; i<3; i++) {
			tri1[i][0] = points[i][0];
			tri1[i][1] = points[i][1];
			tri2[i][0] = points[i+1][0];
			tri2[i][1] = points[i+1][1];
		}
		return calculateTriangleArea(tri1) + calculateTriangleArea(tri2);
	}
	
	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) {
		//surround with try catch block due to the possibility of an IOException occurring because
		//of the use of FileWriter
		try {
			FileWriter myWriter = new FileWriter("recursiveBinaryTriangle.txt");
			
			//function1
			myWriter.write("Function 1 Output: \n");
			myWriter.write("f(1): " + function1(1) + "\n");
			myWriter.write("f(15): " + function1(15) + "\n");
			myWriter.write("f(27): " + function1(27) + "\n");
			myWriter.write("f(53): " + function1(53) + "\n");
			myWriter.write("\n");
			
			//function2
			myWriter.write("Function 2: \n");
			myWriter.write("Using the following points: x=1, y=1015, z=1023\n");
			myWriter.write("Output: " + function2(1, 1015, 1023) + "\n");
			myWriter.write("\n");
			
			//function 3
			myWriter.write("Function 3: \n");
			
			//triangle one
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
			
			//triangle two
			double[][] t2Points = new double[3][2];
			t2Points[0][0]=-2.2;
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
