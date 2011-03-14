package bosk.ovelser.ovelse6.ovelse23;

public class IntegerCell extends Cell{

    private int value;

    public IntegerCell(int value) {
        this.value = value;
    }

    public double getNumberValue() {
        return value;
    }

    public String toString() {
        return "" + value;
    }
}
