package c14.NoCountry.exception;

public class UserException extends Exception{
    public static final String REGISTERED_EMAIL= "THE EMAIL HAS ALREADY BEEN REGISTERED  P - 421";
    public static final String USER_NOT_FOUND = "THE USER WAS NOT FOUND  p - 441";
    public UserException(String message){
        super(message);
    }
}
