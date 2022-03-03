package Hospital.services;

public class Formatting {
	
	//Capitalize the first letter in a string.
	public static String capitalize(String string){
		 String capitalized = string.substring(0, 1).toUpperCase() + string.substring(1);
		 return capitalized;
	}
	
	//Capitalize every word which is separated by spaces.
	public static String capitalizeWithSpaces(String sentence){
		String arr[] = sentence.split(" ");
		for(int i = 0; i < arr.length; i++){
			arr[i] = capitalize(arr[i]);
		}
		String cap = String.join(" ", arr);
		return cap;
	}
}
