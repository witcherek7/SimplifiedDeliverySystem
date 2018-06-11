

import static java.lang.System.out;

public class System {

    public static void main(String[] args) {

        // -------- Paths to files -------------
        String deliveryDataPath = "Generator.txt";
        String mapPath = "map.txt";
        String logPath = "Log.txt";
        // -------------------------------------

        // --------- Implementacja -------------
        DeliveryData fileloader = new DeliveryData(deliveryDataPath);
        Map map = new Map(mapPath);
        Algorithms algorithms = new Algorithms();
        Generator generator = new Generator();
        Logger logger = new Logger();
        Logger2 logger2 = new Logger2(logPath);


        // --------- Wywołanie metod -----------
        map.read();
        Nodes nodes = new Nodes(map.lines);
        generator.creator();

        int medium_cost = 0;
        boolean first_medium = true;
        int divider = 1;



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


                    int cost2 = algorithms.Floyd_Warshall(1,5,nodes.nodes);
                    out.println("Floyd " + cost2);
                    // Tutaj miejsce na raportowanie!
                    //out.println(entry.name + " dostarczył " + entry.packagesNumber + " paczek, w czasie " + cost);


                    //Logger przesyłek ciągły
                    logger.createLog(entry.timestamp, entry.name, entry.packagesNumber, cost);

                    // ------------------------ średnia długość trasy ---------------------------
                    if(first_medium)
                    {
                        medium_cost = cost;
                        first_medium = false;
                    }
                    else
                    {
                        divider++;
                        medium_cost = (medium_cost+cost);
                    }
                    // -----------------------------------------------------------------------------

                }

                // Mierzenie czasu obliczeń algorytmu, POTRZEBNE DO RAPORTU
                long time2 = java.lang.System.currentTimeMillis();
                long time3 = time2 - time1;
                //out.println(time3 + "ms");


                //Logowanie raportu głównego
                logger2.readFile();
                logger2.setSeparatedDataAndSave(medium_cost/divider);


                Thread.sleep(1000);
            }
            catch (Exception e)
            {

            }
        }







        // -------- Test classes --------------






















    }
}
