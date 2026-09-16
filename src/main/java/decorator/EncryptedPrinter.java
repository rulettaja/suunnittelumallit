package decorator;

public class EncryptedPrinter implements Printer {

    private static final int SHIFT = 1;

    private final Printer printer;

    public EncryptedPrinter(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void print(String message) {
        printer.print(transform(message, SHIFT));
    }

    public static String decrypt(String message) {
        return transform(message, -SHIFT);
    }

    private static String transform(String message, int shift) {
        return message.chars()
                .mapToObj(character -> String.valueOf((char) (character + shift)))
                .reduce(String::concat)
                .orElse("");
    }
}