//Implementation of Linked List Applications on polynomial operations such as addition, subtraction, multiplication and derivation.
import java.util.*;
class PolynomialProject
{
 
	// Node structure containing power and coefficient of polynomial
	static class Node {
		int coeff, power;
		Node next;
	};
	 
	// Function to add a new node at the end of list
	static Node addnode(Node start, int coeff, int power)
	{
		// Create a new node
		Node newnode = new Node();
		newnode.coeff = coeff;
		newnode.power = power;
		newnode.next = null;
	 
		// If linked list is empty
		if (start == null)
			return newnode;
	 
		// If linked list has nodes
		Node ptr = start;
		while (ptr.next != null)
			ptr = ptr.next;
		ptr.next = newnode;
	 
		return start;
	}
	 
	// Function To Display The Linked list
	static void printList( Node ptr)
	{
		while (ptr.next != null) {
			System.out.print( ptr.coeff + "x^" + ptr.power + " + ");
	 
			ptr = ptr.next;
		}
		System.out.print( ptr.coeff  +"\n");
	}
	 
	// Function to add coefficients of two elements having same power
	static void removeDuplicates(Node start)
	{
		Node ptr1, ptr2, dup;
		ptr1 = start;
	 
		// Pick elements one by one 
		while (ptr1 != null && ptr1.next != null) {
			ptr2 = ptr1;
	 
			// Compare the picked element with rest of the elements
			while (ptr2.next != null) {
	 
				// If power of two elements are same
				if (ptr1.power == ptr2.next.power) {
	 
					// Add their coefficients and put it in 1st element
					ptr1.coeff = ptr1.coeff + ptr2.next.coeff;
					dup = ptr2.next;
					ptr2.next = ptr2.next.next;
	 
				}
				else
					ptr2 = ptr2.next;
			}
			ptr1 = ptr1.next;
		}
	}
	 
	//Multiplying polynomials
	static Node multiply(Node poly1, Node poly2,Node poly3)
	{
	 
		// Create two pointers and store the address of 1st and 2nd polynomials
		Node ptr1, ptr2;
		ptr1 = poly1;
		ptr2 = poly2;
		while (ptr1 != null) {
			while (ptr2 != null) {
				int coeff, power;
	 
				// Multiply the coefficient of both polynomials and store it in coeff
				coeff = ptr1.coeff * ptr2.coeff;
	 
				// Add the powers of both polynomials and store it in power
				power = ptr1.power + ptr2.power;
	 
				// Invoke addnode function to create a newnode by passing three parameters
				poly3 = addnode(poly3, coeff, power);
	 
				// move the pointer of 2nd polynomial to get its next term
				ptr2 = ptr2.next;
			}
	 
			// Move the 2nd pointer to the starting point of 2nd polynomial
			ptr2 = poly2;
	 
			// move the pointer of 1st polynomial
			ptr1 = ptr1.next;
		}
	 
		// this function will be invoke to add the coefficient of the elements having same powerer from the resultant linked list
		removeDuplicates(poly3);
		return poly3;
	}
	//Adding Polynomials 
    public static Node addPolynomial(Node poly1, Node poly2,Node poly3)
    {
 
		Node ptr1=poly1, ptr2=poly2;
		while (ptr1 != null || ptr2 != null) 
		{
			// Move the 2nd pointer to the starting point of 2nd polynomial
				int coeff, power;
				if (ptr1 == null) {
					poly3.next = ptr2;
					break;
				}
				else if (ptr2 == null) {
					poly3.next = ptr1;
					break;
				}
				else if (ptr1.power == ptr2.power) {
					coeff = ptr1.coeff + ptr2.coeff;
					power=ptr1.power;
					poly3=addnode(poly3,coeff,power);
					ptr1=ptr1.next;
					ptr2=ptr2.next;
				}
				else if (ptr1.power > ptr2.power) {
					coeff=ptr1.coeff;
					power=ptr1.power;
					poly3=addnode(poly3,coeff,power);
					ptr1=ptr1.next;
				}
				else if (ptr1.power < ptr2.power) {
					coeff=ptr2.coeff;
					power=ptr2.power;
					poly3=addnode(poly3,coeff,power);
					ptr2=ptr2.next;
				}
				//poly3=poly3.next;
		}
		//removeDuplicates(poly3);
		return poly3;
	}
	//Subtracting Polynomials
    public static Node subtractPolynomial(Node poly1, Node poly2,Node poly3)
    {
 
		Node ptr1=poly1, ptr2=poly2;
		while (ptr1 != null || ptr2 != null) 
		{
			    // Move the 2nd pointer to the starting point of 2nd polynomial
				int coeff, power;
				if (ptr1 == null) {
					poly3.next = ptr2;
					break;
				}
				else if (ptr2 == null) {
					poly3.next = ptr1;
					break;
				}
				else if (ptr1.power == ptr2.power) {
					coeff = ptr1.coeff - ptr2.coeff;
					power=ptr1.power;
					poly3=addnode(poly3,coeff,power);
					ptr1=ptr1.next;
					ptr2=ptr2.next;
				}
				else if (ptr1.power > ptr2.power) {
					coeff=ptr1.coeff;
					power=ptr1.power;
					poly3=addnode(poly3,coeff,power);
					ptr1=ptr1.next;
				}
				else if (ptr1.power < ptr2.power) {
					coeff=ptr2.coeff;
					power=ptr2.power;
					poly3=addnode(poly3,coeff,power);
					ptr2=ptr2.next;
				}
				//poly3=poly3.next;
		}
		//removeDuplicates(poly3);
		return poly3;
	}
	//Derivative Logic Starts here
	  static long derivativeTerm(String pTerm, long val)
	  {
	 
		// Get coefficient
		String coeffStr = "";
		int i;
		for (i = 0; pTerm.charAt(i) != 'x' ; i++)
		{
		  if(pTerm.charAt(i)==' ')
			continue;
		  coeffStr += (pTerm.charAt(i));
		}
	 
		long coeff = Long.parseLong(coeffStr);
	 
		// Get Power (Skip 2 characters for x and ^)
		String powStr = ""; 
		for (i = i + 2; i != pTerm.length() && pTerm.charAt(i) != ' '; i++)
		{
		  powStr += pTerm.charAt(i);
		}
	 
		long power=Long.parseLong(powStr);
	 
		// For ax^n, we return a(n-1)x^(n-1)
		return coeff * power * (long)Math.pow(val, power - 1);
	  }
	  static long derivativeVal(String poly, int val)
	  {
		long ans = 0;
	 
		int i = 0;
		String[] stSplit = poly.split("\\+");
		while(i<stSplit.length)
		{
		  ans = (ans +derivativeTerm(stSplit[i], val));
		  i++;
		}
		return ans;
	  }
	//Derivative Logic Ends here
	// Main Code
	public static void main(String args[])
	{
	 
		Node poly1 = null, poly2 = null, poly3 = null;
	 
		// Creation of 1st Polynomial: 3x^2 + 5x^1 + 6
		poly1 = addnode(poly1, 3, 2);
		poly1 = addnode(poly1, 5, 1);
		poly1 = addnode(poly1, 6, 0);
	 
		// Creation of 2nd polynomial: 6x^1 + 8
		poly2 = addnode(poly2, 6, 1);
		poly2 = addnode(poly2, 8, 0);
	 
		// Displaying 1st polynomial
		System.out.print("1st Polynomial:- ");
		printList(poly1);
	 
		// Displaying 2nd polynomial
		System.out.print("2nd Polynomial:- ");
		printList(poly2);
	 
		// calling multiply function
		poly3 = multiply(poly1, poly2, poly3);
		// Displaying Resultant Polynomial
		System.out.print( "Resultant Multiplication Polynomial:- ");
		printList(poly3);
		poly3=null;

		// calling add function
		poly3=addPolynomial(poly1,poly2,poly3);
		// Displaying Resultant Polynomial
		System.out.print( "Resultant Additional Polynomial:- ");
		printList(poly3);
		poly3=null;

		// calling subtract function
		poly3=subtractPolynomial(poly1,poly2,poly3);
		// Displaying Resultant Polynomial
		System.out.print( "Resultant Subtraction Polynomial:- ");
		printList(poly3);

		//derivative function printing
		String str = "3x^2 + 5x^1 + 6x^0";
		int val = 2;
	 
		System.out.println("String is :"+str+" and its derivative is:"+derivativeVal(str, val));
		str = "6x^1 + 8x^0";
 
		System.out.println("String is :"+str+" and its derivative is:"+derivativeVal(str, val));
	 
	}
 
 
}

