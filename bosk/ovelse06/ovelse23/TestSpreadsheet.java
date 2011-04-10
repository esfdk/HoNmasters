package bosk.ovelse06.ovelse23;

public class TestSpreadsheet {

    public static void main(String[] args) {
        System.out.println("==<< Laver tomt regneark >>==");
        Spreadsheet s = new Spreadsheet(5, 6);
        System.out.println(s);

        System.out.println();
        System.out.println("==<< Indsætter tekstceller >>==");
        s.setCell(1, 1, new TextCell("Bananer"));
        s.setCell(1, 2, new TextCell("Appelsiner"));
        s.setCell(1, 4, new TextCell("Total"));
        s.setCell(2, 2, new TextCell("+"));
        s.setCell(2, 4, new TextCell("="));
        System.out.println(s);

        System.out.println();
        System.out.println("==<< Indsætter heltalscelle >>==");
        s.setCell(3, 1, new IntegerCell(12));
        System.out.println(s);

        System.out.println();
        System.out.println("==<< Indsætter kommatalscelle >>==");
        s.setCell(3, 2, new FloatingPointCell(4.5));
        System.out.println(s);

        System.out.println();
        System.out.println("==<< Indsætter sumcelle >>==");
        s.setCell(3, 4, new SumCell(s.getCell(3, 1), s.getCell(3, 2)));
        System.out.println(s);
        System.out.println(s.getCell(3, 4));
    }
}
