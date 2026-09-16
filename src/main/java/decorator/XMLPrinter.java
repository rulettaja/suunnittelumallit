package decorator;

public class XMLPrinter implements Printer {

    private final Printer printer;

    public XMLPrinter(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void print(String message) {
        printer.print("<message>" + escape(message) + "</message>");
    }

    private static String escape(String message) {
        return message.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }
}