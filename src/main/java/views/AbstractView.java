package views;

// Built-in modules
import java.util.Scanner;


/**
 * This is the base class for views.
 */
public abstract class AbstractView {
    protected Scanner scanner;

    public AbstractView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

}