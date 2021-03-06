import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import static java.lang.System.out;

public class Logger2 {

    String path;
    String lines[];
    String separatedData[];
    String save_path;

    static class Driver
    {
        public Driver(String name) {
            this.name = name;
        }

        String name;
        int delivered = 0;


    }

    static class Date
    {
        public Date(String data) {
            this.data = data;
        }

        String data;
        int paczki = 0;
    }



    public Logger2(String path, String save_path) {
        this.path = path;
        this.save_path = save_path;
    }


    // Funkcja wczytuje wszystkie linie do tablicy lines[] z pliku Log.txt
    void readFile() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            String line;
            line = content;
            lines = content.split(("\n"));



        } catch (IOException e) {
            //java.lang.System.out.print(e);
        }

    }

    // Separuje wczytane dane i zapisuje raport do pliku
    void setSeparatedDataAndSave(int medium_cost, long time)
    {
        LinkedList<Driver> listOfDrivers = new LinkedList<Driver>();
        LinkedList<Date> listOfDates = new LinkedList<Date>();


        //Dla każdeej linii podziel ze wzgledu na ":"
        for(int i=0; i<lines.length; i++) {
            separatedData = lines[i].split(":");
            String data = separatedData[0];
            int packages_number = Integer.parseInt(separatedData[2]);

            //Sprawdzanie czy data już jest w liście, jeżeli nie utwórz nowa,
            //jeżeli tak to dodaj paczki do daty
            Boolean dateInList = false;
            for(int j=0; j<listOfDates.size(); j++)
            {
                if(listOfDates.get(j).data.equals(data))
                {
                    dateInList = true;
                    listOfDates.get(j).paczki+=packages_number;
                }

            }
            if(dateInList==false)
            {
                Date new_date = new Date(data);
                new_date.paczki+=packages_number;
                listOfDates.add(new_date);

            }

            //Sprawdzanie czy dany kierowca już jest w liście, jeżeli nie utwórz nowa,
            //jeżeli tak to dodaj paczki do kierowcy
            Boolean driverInList = false;
            String name = separatedData[1];

            for(int j=0; j<listOfDrivers.size(); j++)
            {
                if(listOfDrivers.get(j).name .equals(name))
                {
                    driverInList = true;
                    listOfDrivers.get(j).delivered+=packages_number;
                }

            }
            if (driverInList==false)
            {
                Driver new_Driver= new Driver(name);
                new_Driver.delivered = packages_number;
                listOfDrivers.add(new_Driver);
            }

            // Zapisanie raportu do pliku
            try {
                PrintWriter writer = new PrintWriter(save_path);

                writer.println("------------Paczki dostarczone danego dnia---------------");
                for(Date x : listOfDates)
                {
                    writer.println(x.data + " zostało dostarczone " + x.paczki + " paczek.");
                }



                writer.println("---------------Suma dostarczonych przesyłek----------------");
                // ---------- tutaj zerujemy plik ----------
                for(Driver x : listOfDrivers)
                {
                    writer.println(x.name + " dostarczył " + x.delivered + " paczek.");
                }

                writer.println("-------------Srednia długość trasy-----------");
                writer.println("Wynosi: " + medium_cost);
                writer.println("Sredni czas wykonania algorytmu: " + time + "ms.");
                writer.close();
            }
            catch(IOException e)
            {

            }



        }
    }


}
