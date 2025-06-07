package generation.italy.org.ravenclaw.exceptions;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(Class<?> entityClass) {
        super(String.format("Couldn't find the Entity %S", entityClass));
    }
}
