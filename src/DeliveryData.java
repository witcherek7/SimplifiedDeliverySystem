import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

import static java.lang.System.out;
import org.apache.commons.lang3.StringUtils;

public class DeliveryData{

    String path;
    String lines[];
    String separatedData[];
    boolean isThereNewLine = false;

    // -------------------- Lista z przesyłkami (wpisami z pliku) -----
    LinkedList<DeliveryEntry> deliveries = new LinkedList<DeliveryEntry>();
    // ----------------------------------------------------------------

    public DeliveryData(String path) {
        this.path = path;
    }


    // Funkcja wczytuje wszystkie linie do tablicy lines[]
    void readFile() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            String line;
            line = content;
            lines = content.split(("\n"));


            PrintWriter writer = new PrintWriter(path);

            // ---------- tutaj zerujemy plik ----------
            writer.print("");
            writer.close();



        } catch (IOException e) {
            java.lang.System.out.print(e);
        }

    }


    // Funkcja sprawdza czy pojawił się jakiś wpis w pliku
    // jeżeli tak to wywołuje funkcje readFile() a po niej Separate()
    // na wszystkich wczytanych wpisach i dodaje je do deliveries
    void checkIfNewEntryAndLoadAndSeparate()
    {
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            String line;
            line = content;

            // ---------- tutaj zerujemy plik ----------
            if(line.length()==0) {
                isThereNewLine = false;
            }
            else
            {
                isThereNewLine = true;
                readFile();
                for(int i = 0; i<lines.length; i++)
                {
                    separateData(lines[i]);
                }

            }



        } catch (IOException e) {
            java.lang.System.out.print(e);
        }
    }

    // Funkcja oddzielająca dane i dodająca do stosu Deliveries
    // TUTAJ DODA SPRAWDZANIE BŁĘDnych danych
    void separateData(String line)
    {
        int packagesNumber = StringUtils.countMatches(line, "(");
        int[][] packages = new int[packagesNumber][2];

        String[] lineParts = line.split(",");

        String timestamp = lineParts[0];
        String driverName = lineParts[1];

        String numberOnly;
        int partsCounter = 2;

        try {
            for (int i = 0; i < packagesNumber; i++) {

                numberOnly = lineParts[partsCounter];
                numberOnly = numberOnly.replaceAll("[^0-9]", "");
                packages[i][0] = Integer.parseInt(numberOnly);
                //System.out.print(packages[i][0]);
                partsCounter++;
                numberOnly = lineParts[partsCounter];
                numberOnly = numberOnly.replaceAll("[^0-9]", "");
                packages[i][1] = Integer.parseInt(numberOnly);
                //System.out.print(packages[i][1]);
                partsCounter++;


            }

            // Tworzymy obiekt delivery i sprawdzamy czy dane sie zgadzaja, jak tak dodajem do kolejki
            DeliveryEntry deliveryEntry = new DeliveryEntry(timestamp, driverName, packages, packagesNumber);
            Boolean canIAdd = deliveryEntry.checkIfEntryIsCorrect();
            if(canIAdd) {
                deliveries.add(deliveryEntry);
            }
            else
            {
                out.println("Error in line: " + line);
            }
        }
            catch(Exception error)
            {
                // Tutaj wypisujemy linię która sprawiła kłopoty
                // nie robimy z niej wpisu
                out.println("Error in line = " + line);
            }




    }


}


