package org.biblio.biblio.CustomExceptions;

public class UsernameOrEmailUsedException extends RuntimeException{
    public UsernameOrEmailUsedException(String message) {
        super(message);
    }
}
