package itu.bosk.labs.f02.spreadsheet;

public class EmptyCell extends Cell {

    private static Cell singleton = new EmptyCell();

    private EmptyCell() {

    }

    public static Cell getEmptyCell() {
        return singleton;
    }

    public double getNumberValue() {
        String msg = "getNumberValue should not be called on a EmptyCell";
        throw new IllegalStateException(msg);
    }

    public String toString() {
        return "";
    }
}
