

import static java.lang.System.out;

public class System {

    public static void main(String[] args) {

        // -------- Paths to files -------------
        String deliveryDataPath = "C:\\Users\\Witcherek7_Win10_Lap\\IdeaProjects\\SimplifiedDeliverySystem\\Generator.txt";
        String mapPath = "C:\\Users\\Witcherek7_Win10_Lap\\IdeaProjects\\Packages Delivery System\\src\\map.txt";
        // -------------------------------------

        // --------- Implementacja -------------
        DeliveryData fileloader = new DeliveryData(deliveryDataPath);
        Map map = new Map(mapPath);
        Algorithms algorithms = new Algorithms();
        Generator generator = new Generator();
        Logger logger = new Logger();


        // --------- Wywołanie metod -----------
        map.read();
        Nodes nodes = new Nodes(map.lines);
        generator.creator();


        // ---------------------- Operation core -----------------------
        while(true)
        {
            try {
                // Wczytanie danych
                fileloader.checkIfNewEntryAndLoadAndSeparate();
                long time1 = java.lang.System.currentTimeMillis();
                for(int i=0; i<fileloader.deliveries.size(); i++)
                {
                    DeliveryEntry entry = fileloader.deliveries.pollFirst();
                    int cost = algorithms.Dijstra_starter(entry.packages, nodes.nodes);
                    // Tutaj miejsce na raportowanie!
                    out.println(entry.name + " dostarczył " + entry.packagesNumber + " paczek, w czasie " + cost);
                    logger.createLog(entry.timestamp, entry.name, entry.packagesNumber, cost);
                }

                // Mierzenie czasu obliczeń algorytmu, POTRZEBNE DO RAPORTU
                long time2 = java.lang.System.currentTimeMillis();
                long time3 = time2 - time1;
                out.println(time3 + "ms");


                Thread.sleep(1000);
            }
            catch (Exception e)
            {

            }
        }







        // -------- Test classes --------------






















    }
}
