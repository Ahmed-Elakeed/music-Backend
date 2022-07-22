package configuration;

public class InvalidAgeException extends Exception{

    public InvalidAgeException(){
        super("-18");
    }
}
