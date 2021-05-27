
public class PasswordException extends Exception {
	PasswordException(String s){
			super(s);
	}
}


 class FirstLengthException extends Exception {
	FirstLengthException(String s){
			super(s);
	}
}


 class LastLengthException  extends Exception {
	LastLengthException(String s){
			super(s);
	}
}

 class MobileLengthException extends Exception {
	MobileLengthException(String s){
			super(s);
	}
}
 
 
 class ErrorException extends Exception {
	ErrorException(String s){
			super(s);
	}
}
 
 class TableSizeException extends Exception {
	TableSizeException(String s){
			super(s);
	}
}
 class LoginFailedException extends Exception{
	 LoginFailedException(String s){
		 super(s);
	 }
 }
//it will accept all exception from main.java file 
//This is important for all Exception handle in registation form page
