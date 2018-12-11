import java.util.*;

public class FracCalc {

    /**
     * Prompts user for input, passes that input to produceAnswer, then outputs the result.
     * @param args - unused
     */
    public static void main(String[] args) 
    {
    	Scanner console = new Scanner(System.in);
    	System.out.print("Type in an equation (press x to stop): ");
    	String equation = console.nextLine();
    	while (equation!="x") {
    		System.out.println(produceAnswer(equation));
    		System.out.print("Type in an equation (press x to stop): ");
        	equation = console.nextLine();
        	if (equation.equals("x")) {
        		break;
        	}
    	}
    		System.out.print("Thank you for using FracCalc!");
    		
    	}
    	
        // TODO: Read the input from the user and call produceAnswer with an equation
        // Checkpoint 1: Create a Scanner, read one line of input, pass that input to produceAnswer, print the result.
        // Checkpoint 2: Accept user input multiple times.
    
    
    /**
     * produceAnswer - This function takes a String 'input' and produces the result.
     * @param input - A fraction string that needs to be evaluated.  For your program, this will be the user input.
     *      Example: input ==> "1/2 + 3/4"
     * @return the result of the fraction after it has been calculated.
     *      Example: return ==> "1_1/4"
     */
    public static String produceAnswer(String input)
    { 
        // TODO: Implement this function to produce the solution to the input
        // Checkpoint 1: Return the second operand.  Example "4/5 * 1_2/4" returns "1_2/4".
        // Checkpoint 2: Return the second operand as a string representing each part.
        //               Example "4/5 * 1_2/4" returns "whole:1 numerator:2 denominator:4".
        // Checkpoint 3: Evaluate the formula and return the result as a fraction.
        //               Example "4/5 * 1_2/4" returns "6/5".
        //               Note: Answer does not need to be reduced, but it must be correct.
        // Final project: All answers must be reduced.
        //               Example "4/5 * 1_2/4" returns "1_1/5".
    	Scanner console = new Scanner(input);
    	
        String first = console.next();
        String op = console.next();
        String second = console.next();
        
        int firstWhole = whole(first);
        int firstNum = numer(first);
        int firstDenom = denomer(first);
        
        int secondWhole = whole(second);
        int secondNum = numer(second);
        int secondDenom = denomer(second);
        
        int resultWhole =0;
        int resultNum = 0;
        int resultDenom =0;
        
        int holder =0;
        
        int lcm =0;
        int uno =0;
        int dos =0;
        
        String whole = "";
        String num = "";
        String denom ="";
        
        String answer ="";
        
        if (op.equals("+")) {
        	resultWhole = firstWhole + secondWhole;
        	whole = Integer.toString(resultWhole)+ "_";
        	
        	
        	if (firstDenom == secondDenom) {
        		resultNum = firstNum + secondNum;
        	}else {
        		lcm = leastCommonMultiple(firstDenom, secondDenom);        		
        		uno = lcm/firstDenom;
        		dos = lcm/secondDenom;
        		firstNum*=uno;
        		secondNum*=dos;
        		resultNum = firstNum + secondNum;
        		
        	}
        	num = Integer.toString(firstNum+secondNum);
    		denom = Integer.toString(leastCommonMultiple(firstDenom, secondDenom));
        	answer = whole + num + "/" + denom;
        }        
        if (op.equals("-")) {
        	/*resultWhole = firstWhole - secondWhole;
        	whole = Integer.toString(resultWhole)+ "_";*/
        	
        	
        	if (firstDenom == secondDenom) {
        		firstNum = firstNum + (firstWhole*firstDenom);
        		
        		secondNum = secondNum + (secondWhole*secondDenom);
        		
        		resultNum = firstNum - secondNum;
        	}else {
        		firstNum = firstNum + (firstWhole*firstDenom);
        		
        		secondNum = secondNum + (secondWhole*secondDenom);
        		
        		lcm = leastCommonMultiple(firstDenom, secondDenom);        		
        		uno = lcm/firstDenom;
        		dos = lcm/secondDenom;
        		firstNum*=uno;
        		secondNum*=dos;
        		resultNum = firstNum-secondNum;
        		
        	}
        	num = Integer.toString(resultNum);
    		denom = Integer.toString(leastCommonMultiple(firstDenom, secondDenom));
        	answer = whole + num + "/" + denom;
        }
        if (op.equals("*")) {
        	if (firstWhole>0) {
        		firstNum = firstNum + (firstWhole*firstDenom);        	
        	}
        	if (secondWhole>0) {
        		secondNum = secondNum + (secondWhole*secondDenom);        		
        	}
        	resultNum = firstNum*secondNum;
        	resultDenom = firstDenom*secondDenom;
        	num = Integer.toString(resultNum);
        	denom = Integer.toString(resultDenom);
        	answer = num+ "/" + denom;
        }
        if (op.equals("/")) {
        	if (firstWhole>0) {
        		firstNum = firstNum + (firstWhole*firstDenom);        	
        	}
        	if (secondWhole>0) {
        		secondNum = secondNum + (secondWhole*secondDenom);        		
        	}
        	holder = secondNum;
        	secondNum = secondDenom;
        	secondDenom = holder;
        	resultNum = firstNum*secondNum;
        	resultDenom = firstDenom*secondDenom;
        	num = Integer.toString(resultNum);
        	denom = Integer.toString(resultDenom);
        	answer = num+ "/" + denom;
        }
        
        return answer;
    }
    

    // TODO: Fill in the space below with helper methods
    public static int whole(String str) {
    	if (str.contains("_")) {
    		return Integer.parseInt(str.substring(0, str.indexOf('_')));
    	}else if (str.contains("/")) {
    		return 0;
    	}else{
    		return Integer.parseInt(str) ;
    	}
    	
    	}
    	
    	
		
    public static int numer(String str) {
    	if (str.contains("_")) {
    		return Integer.parseInt(str.substring(str.indexOf('_')+1, str.indexOf('/')));
    	}else if (str.contains("/")) {
    		return Integer.parseInt(str.substring(0, str.indexOf('/')));
    	}else {
    		return 0;
    	}
    }
    	
    public static int denomer(String str) {
    	if (str.contains("/")) {
    		return Integer.parseInt(str.substring(str.indexOf("/")+1));
    	}else{
    		return 1;
    	}
    		
    	}
    	
    
   
    /**
     * greatestCommonDivisor - Find the largest integer that evenly divides two integers.
     *      Use this helper method in the Final Checkpoint to reduce fractions.
     *      Note: There is a different (recursive) implementation in BJP Chapter 12.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The GCD.
     */
    public static int greatestCommonDivisor(int a, int b)
    {
        a = Math.abs(a);
        b = Math.abs(b);
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        while (min != 0) {
            int tmp = min;
            min = max % min;
            max = tmp;
        }
        return max;
    }
    
    /**
     * leastCommonMultiple - Find the smallest integer that can be evenly divided by two integers.
     *      Use this helper method in Checkpoint 3 to evaluate expressions.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The LCM.
     */
    public static int leastCommonMultiple(int a, int b)
    {
        int gcd = greatestCommonDivisor(a, b);
        return (a*b)/gcd;
    }
}
