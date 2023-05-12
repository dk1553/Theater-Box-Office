package db;

public class MessagePrinter {
    private static String message;
    private MessagePrinter() {
        throw new IllegalStateException("Utility class");
    }

    public static void dbOpened() {
        message = "Opened database successfully";
        print();
    }

    public static void dbClosed() {
        message = "Closed database successfully";
        print();
    }

    public static void recordsCreated() {
        message = "Records created successfully";
        print();
    }

    public static void print() {
        System.out.println(message);
    }
}



