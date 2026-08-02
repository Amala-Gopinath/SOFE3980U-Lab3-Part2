package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'
	/**
	* A constructor that generates a binary object.
	*
	* @param number a String of the binary values. It should conatins only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	*/
    public Binary(String number) {
		for (int i = 0; i < number.length(); i++) {
			// check each character if it's not 0 or 1
			char ch=number.charAt(i);
			if(ch!='0' && ch!='1') {
				number="0"; // if not store "0" and end the function
				return;
			}
		}
		// remove any trailing zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg)!='0')
				break;
		}
		//beg has the index of the first non zero digit in the number
		this.number=number.substring(beg); // exclude the trailing zeros if any
		// uncomment the following code
		
		if(this.number=="") { // replace empty strings with a single zero
			this.number="0";
		}
		
    }
	/**
	* Return the binary value of the variable
	*
	* @return the binary value in a string format.
	*/
	public String getValue()
	{
		return this.number;
	}
	/**
	* Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param num1 The first addend object
	* @param num2 The second addend object
	* @return A binary variable with a value of <i>num1+num2</i>.
	*/
	public static Binary add(Binary num1,Binary num2)
	{
		// the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		//initial variable
		int carry=0;
		String num3="";  // the binary value of the sum
		while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
		{
			int sum=carry; // previous carry
			if(ind1>=0){ // if num1 has a digit to add
				sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if(ind2>=0){ // if num2 has a digit to add
				sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
				ind2--; //update ind2
			}
			carry=sum/2; // the new carry
			sum=sum%2;  // the resultant digit
			num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
		}
		Binary result=new Binary(num3);  // create a binary object with the calculated value.
		return result;
		
	}
	/**
	* Bitwise OR of two binary variables.
	*
	* @param num1 The first operand object
	* @param num2 The second operand object
	* @return A binary variable with a value of <i>num1 OR num2</i>.
	*/
	public static Binary or(Binary num1,Binary num2)
	{
		// the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		String num3="";  // the binary value of the result
		while(ind1>=0 || ind2>=0) // loop until all digits are processed
		{
			int b1=(ind1>=0 && num1.number.charAt(ind1)=='1')? 1:0; // digit of num1, or 0 if none left
			int b2=(ind2>=0 && num2.number.charAt(ind2)=='1')? 1:0; // digit of num2, or 0 if none left
			int res=(b1==1 || b2==1)? 1:0; // OR of the two digits
			num3=((res==0)? "0":"1")+num3; // append the result digit
			ind1--; // update ind1
			ind2--; // update ind2
		}
		Binary result=new Binary(num3);  // create a binary object with the calculated value.
		return result;
		
	}
	/**
	* Bitwise AND of two binary variables.
	*
	* @param num1 The first operand object
	* @param num2 The second operand object
	* @return A binary variable with a value of <i>num1 AND num2</i>.
	*/
	public static Binary and(Binary num1,Binary num2)
	{
		// the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		String num3="";  // the binary value of the result
		while(ind1>=0 || ind2>=0) // loop until all digits are processed
		{
			int b1=(ind1>=0 && num1.number.charAt(ind1)=='1')? 1:0; // digit of num1, or 0 if none left
			int b2=(ind2>=0 && num2.number.charAt(ind2)=='1')? 1:0; // digit of num2, or 0 if none left
			int res=(b1==1 && b2==1)? 1:0; // AND of the two digits
			num3=((res==0)? "0":"1")+num3; // append the result digit
			ind1--; // update ind1
			ind2--; // update ind2
		}
		Binary result=new Binary(num3);  // create a binary object with the calculated value.
		return result;
		
	}
	/**
	* Multiplying two binary variables using repeated shift-and-add.
	*
	* @param num1 The first factor object
	* @param num2 The second factor object
	* @return A binary variable with a value of <i>num1*num2</i>.
	*/
	public static Binary multiply(Binary num1,Binary num2)
	{
		Binary total=new Binary("0"); // running total, starts at zero
		int len=num2.number.length();
		for(int i=0;i<len;i++) // loop over each digit of num2, from least significant
		{
			char bit=num2.number.charAt(len-1-i); // the current digit of num2
			if(bit=='1') // only add a shifted copy of num1 if this digit is 1
			{
				String temp=num1.number;
				for(int j=0;j<i;j++) // shift left by appending i zeros
				{
					temp=temp+"0";
				}
				Binary temp2=new Binary(temp);
				total=Binary.add(total,temp2); // add the shifted value to the running total
			}
		}
		return total;
		
	}
}