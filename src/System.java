

import static java.lang.System.out;

public class System {

    public static void main(String[] args) {

        // -------- Paths to files -------------
        String deliveryDataPath = "Generator.txt";
        String mapPath = "map.txt";
        String logPath = "Log.txt";
        String Dijkstra = "Dijkstra.txt";
        String Bellman = "Bellman.txt";

        // -------------------------------------

        // --------- Implementacja -------------
        DeliveryData fileloader = new DeliveryData(deliveryDataPath);
        Map map = new Map(mapPath);
        Algorithms algorithms = new Algorithms();
        Generator generator = new Generator();
        Logger logger = new Logger();
        Logger2 logger2 = new Logger2(logPath, Dijkstra);
        Logger2 logger21 = new Logger2(logPath, Bellman);

        // --------- Wywołanie metod -----------
        map.read();
        Nodes nodes = new Nodes(map.lines);
        generator.creator();

        // ----- Dla średniej długosci trasy --- Dijsktra ----
        int medium_cost = 0;
        boolean first_medium = true;
        int divider = 1;
        int medium_cost_Ford = 0;

        int divider_time = 0;
        long time;
        long time_Dijkstra = 0;
        long time_Bellman = 0;

        // ---------------------- Operation core -----------------------
        while(true)
        {
            try {
                // Wczytanie danych
                fileloader.checkIfNewEntryAndLoadAndSeparate();

                for(int i=0; i<fileloader.deliveries.size(); i++)
                {


                    DeliveryEntry entry = fileloader.deliveries.pollFirst();

                    time = java.lang.System.currentTimeMillis();
                    int cost = algorithms.Dijstra_starter(entry.packages, nodes.nodes);
                    time_Dijkstra = time_Dijkstra + java.lang.System.currentTimeMillis() - time;
                    time = java.lang.System.currentTimeMillis();
                    int cost2 = algorithms.Bellman_Ford_starter(entry.packages, nodes.nodes);
                    time_Bellman = time_Bellman + java.lang.System.currentTimeMillis() - time;
                    divider_time++;





                    //int cost2 = algorithms.Floyd_Warshall(1,5,nodes.nodes);
                    //out.println("Floyd " + cost2);
                    // Tutaj miejsce na raportowanie!
                    //out.println(entry.name + " dostarczył " + entry.packagesNumber + " paczek, w czasie " + cost);


                    //Logger przesyłek ciągły
                    logger.createLog(entry.timestamp, entry.name, entry.packagesNumber, cost);

                    // ------------------------ średnia długość trasy ---------------------------
                    if(first_medium)
                    {
                        medium_cost = cost;
                        medium_cost_Ford = cost2;
                        first_medium = false;
                    }
                    else
                    {
                        divider++;
                        medium_cost = (medium_cost+cost);
                        medium_cost_Ford = medium_cost_Ford + cost2;
                    }
                    // -----------------------------------------------------------------------------

                }

                // Mierzenie czasu obliczeń algorytmu, POTRZEBNE DO RAPORTU




                //Logowanie raportu głównego
                logger2.readFile();
                logger2.setSeparatedDataAndSave(medium_cost/divider, time_Dijkstra/divider_time);
                logger21.readFile();
                logger21.setSeparatedDataAndSave(medium_cost_Ford/divider, time_Bellman/divider_time);



                Thread.sleep(1000);
            }
            catch (Exception e)
            {
                out.println(e);
            }
        }







        // -------- Test classes --------------






















    }
}
