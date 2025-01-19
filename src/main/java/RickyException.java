public class RickyException extends Exception {
    public RickyException() {
        super("Please enter a valid command.");
    }
    public RickyException(String message) {
        super(message);
    }
}
