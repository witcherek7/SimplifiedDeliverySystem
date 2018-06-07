import java.util.LinkedList;
import static java.lang.System.out;

public class Algorithms {

    // Number of Nodes in Graph
    int N = 20;

    int Dijkstra(int source, int destination, Nodes.Node[][] graph)
    {
        int distance[] = new int[N*N];

        // Nieodwiedzone Node'y
        LinkedList<Nodes.Node> Q = new LinkedList<Nodes.Node>();

        // Wypełniamy tablice czasem inf
        for(int i=0; i<distance.length; i++)
        {
            distance[i] = 200;
        }

        distance[source-1] = 0;


        // --------- Add all nodes to Q -------
        for(int i=0; i<N; i++)
        {
            for(int j=0; j<N; j++)
            {
                Q.add(graph[i][j]);
            }
        }
        // -------------------------------------

        while (!Q.isEmpty())
        {
            //out.println(Q.size());

            int nodeNumberImportant = 0;
            Nodes.Node u = new Nodes.Node(500);
            // ------------ Searching smallest dist ---
            int min = 9999;
            for(int i=0; i<Q.size(); i++)
            {
                int nodeNumber = Q.get(i).nodeNumber;
                if(distance[nodeNumber-1]<min)
                {
                    min = distance[nodeNumber-1];
                    nodeNumberImportant = nodeNumber;
                }
            }
            //out.println(nodeNumberImportant);
            // ------------------------------------------

            // -------- Remove u from Q -------------
            for(int i=0; i<Q.size(); i++)
            {
                if(Q.get(i).nodeNumber==nodeNumberImportant)
                {
                    u = Q.get(i);
                    Q.remove(i);
                    break;
                }
            }
            // -------------------------------------

            int alt = 0;
            for(int i=0; i<u.edges.size(); i++)
            {
                // --------- Check if destination is in Q
                boolean isNodeInQ = false;

                for(int j = 0; j<Q.size(); j++)
                {
                    if(u.edges.get(i).destination==Q.get(j).nodeNumber)
                    {
                        isNodeInQ = true;
                    }
                }


                if(isNodeInQ) {
                    alt = distance[nodeNumberImportant - 1] + u.edges.get(i).weight;
                    if (alt < distance[u.edges.get(i).destination - 1]) {
                        distance[u.edges.get(i).destination - 1] = alt;
                    }
                }
            }






        }



        return distance[destination-1];
    }

    int Dijstra_starter(int[][] destinations, Nodes.Node[][] graph)
    {
        int cost = 0;
        int source = 1;
        for(int i=0; i<destinations.length; i++)
        {
            int destinationNodeNumber = graph[destinations[i][0]-1][destinations[i][1]-1].nodeNumber;
            cost = cost + Dijkstra(source, destinationNodeNumber, graph);
        }
        return cost;
    }

}
