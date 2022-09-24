package Exceptii;

public class ExceptiiCitire extends RuntimeException {
    public ExceptiiCitire() {
        super("Nu este corect citit si preluat");
    }
}
