
/*=============================================================================
 |   Assignment:  HWK #6
 |       Author:  Logan Sandlin - logansandlin@email.arizona.edu
 |
 |       Course:  CSC245 (Introduction to Discrete Structures), Spring 22
 |   Instructor:  L. McCann
 | Sect. Leader:  N/A
 |     Due Date:  04/08/2022, at the beginning of class
 |
 |     Language:  Java (Eclipse IDE)
 |     Packages:  Default
 |  Compile/Run:  Standard Java compilation and running, > javac Hmwk6.java
 | 														 > java Hmwk6
 |
 +-----------------------------------------------------------------------------
 |
 |  Description:  A recursive program that generates a "proof", or rather, a 
 |  			  demonstration of the Fundamental Theorem of Arithmetic for a 
 |				  given integer value. That is, the program will show, step by 
 |				  step, how the integer is decomposed into factors, and factors 
 |				  of factors, etc., until everything is shown to be prime or 
 |				  products of primes. -- As described by Dr. McCann.
 |                
 |        Input:  The user will give a single integer on the command line.
 |
 |       Output:  Note three features in particular: (a) Indentation is used to 
 |				  make it easier to see what's happening, (b) the larger factor 
 |				  is listed/used before the smaller, and (c) when a factor is 
 |				  shown to be the square of a prime the output reflects this.
 |				  -- As described by Dr.McCann.
 |
 |   Techniques:  This program was solved recursively.
 |
 |   Required Features Not Included:  All features required are included.
 |
 |   Known Bugs:  I know of no logic errors in the code.
 |
 *===========================================================================*/

public class Hmwk6 {

	public static void main(String[] args) {
		int input;

		if (args.length == 1) {
			input = Integer.valueOf(args[0]); // Unchecked exception will be thrown if invalid data is provided.
		} else {
			System.out.println("A single integer should be provided on the command line.");
			return;
		}

		System.out.println("$ java Hmwk6 " + input + " \nThis program will demonstrate that " + input
				+ " is either prime or is the product of two or more prime numbers.\n");
		if (input < 0) {
			Hmwk6.findFactors(-input, "");
		} else {
			Hmwk6.findFactors(input, "");
		}
		System.out.println("\nAs this output shows, the Fundamental Theorem of Arithmetic holds for " + input + ".");
	}

	/**
	 * 
	 * Recursive function which takes an integer input and finds and prints out
	 * information about its factors.
	 * 
	 * @param input,  integer input
	 * @param indent, helpful for indentation increases with recursion
	 * 
	 */
	private static void findFactors(int input, String indent) {
		int maxFactor = (int) Math.pow(input, 0.5);
		boolean found = false;
		int littleFactor = 1;
		int bigFactor = -1;
		while (!found && littleFactor < maxFactor) {
			littleFactor++;
			if (input % littleFactor == 0) {
				bigFactor = input / littleFactor;
				System.out.println(indent + input + " = " + bigFactor + " * " + littleFactor
						+ "; are these factors either prime or products of primes?");
				if (Double.valueOf(Math.sqrt(bigFactor)).equals(Double.valueOf(Math.round(Math.sqrt(bigFactor))))) {
					int sqrt = (int) Math.sqrt(bigFactor);
					System.out.println("\t" + indent + bigFactor + " = " + sqrt
							+ " squared; is this factor either prime or products of primes?");
					Hmwk6.findFactors(sqrt, indent + "\t\t");
					System.out.println("\t" + indent + bigFactor + " is the square of " + sqrt
							+ ", which is prime or the product of primes.");
				} else {
					Hmwk6.findFactors(bigFactor, indent + "\t");
				}
				if (Double.valueOf(Math.sqrt(littleFactor))
						.equals(Double.valueOf(Math.round(Math.sqrt(littleFactor))))) {
					int sqrt = (int) Math.sqrt(littleFactor);
					System.out.println("\t" + indent + littleFactor + " = " + sqrt
							+ " squared; is this factor either prime or products of primes?");
					Hmwk6.findFactors(sqrt, indent + "\t\t");
					System.out.println("\t" + indent + littleFactor + " is the square of " + sqrt
							+ ", which is prime or the product of primes.");
				} else {
					Hmwk6.findFactors(littleFactor, indent + "\t");
				}
				found = true;
			}
		}
		if (found) {
			System.out.println(indent + input + " is the product of primes (" + bigFactor + " and " + littleFactor
					+ " are prime or prime products).");
		} else {
			System.out.println(indent + input + " is prime.");
		}
	}
}
