import java.util.LinkedList;

import static java.lang.System.in;
import static java.lang.System.out;

public class Algorithms {

    // Number of Nodes in Graph
    int N = 20;


    // Metody Algorytm liczą koszt/długość dla pojedynczej pary kooordynatów
    // Source - Destination

    // Metody _starter uruchamiają w pętli metody Algorytm dla tablich koordynatów[][]

    int Dijkstra(int source, int destination, Nodes.Node[][] graph)
    {
        int distance[] = new int[N*N];
        int distance_hops[] = new int[N*N];

        // Wypełanienie distance_hops zerami
        for(int i=0; i< distance_hops.length; i++)
        {
            distance_hops[i]=0;
        }



        int hops = 0;

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
                        distance_hops[u.edges.get(i).destination-1] = distance_hops[u.nodeNumber-1] +1;

                    }
                }
            }






        }



        //return distance[destination-1];
        return distance_hops[destination-1];
    }

    int Dijstra_starter(int[][] destinations, Nodes.Node[][] graph)
    {
        int cost = 0;
        int source = 1;
        for(int i=0; i<destinations.length; i++)
        {
            int destinationNodeNumber = graph[destinations[i][0]-1][destinations[i][1]-1].nodeNumber;
            cost = cost + Dijkstra(source, destinationNodeNumber, graph);
            source = destinationNodeNumber;
        }
        return cost;
    }

    int Floyd_Warshall(int source, int destinations, Nodes.Node[][] graph)
    {
        int[][] distance  = new int[N*N][N*N];
        int[][] next = new int[N*N][N*N];


        //Wypełnienie tablicy dist[u][v] ← w(u,v)  // the weight of the edge (u,v)
        for(int u=0; u<N; u++)
        {
            for(int v=0; v<N; v++)
            {
                distance[u][v] = 999;
                next[u][v] = 0;
            }
        }
        // Sam do siebie
        for(int v=0; v<N; v++)
        {
            distance[v][v]=0;
        }

        //Przeszukanie krawędzi
        LinkedList<Nodes.Node> Q = new LinkedList<Nodes.Node>();
        // --------- Add all nodes to Q -------
        for(int i=0; i<N; i++)
        {
            for(int j=0; j<N; j++)
            {
                Q.add(graph[i][j]);
            }
        }
        // -------------------------------------

        int x = 0;
        int y = 0;

        for(int i=0; i<Q.size(); i++)
        {
            Nodes.Node node = Q.get(i);


            for(int j=0; j<node.edges.size(); j++)
            {
                if(node.edges.get(j).destination-1<N*N) {
                    distance[node.nodeNumber - 1][node.edges.get(j).destination - 1] = node.edges.get(j).weight;
                    next[node.nodeNumber - 1][node.edges.get(j).destination - 1] = node.edges.get(j).destination - 1;

                }
            }
            //out.println("here4");
       }

       for(int k = 0; k<N; k++)
       {
           for(int i = 0; i<N; i++)
           {
               for(int j=1; j<N; j++)
               {
                   if(distance[i][j] > distance[i][k]+distance[k][j])
                   {
                       distance[i][j] = distance[i][k]+distance[k][j];
                       next[i][j] = next[i][k];
                       //out.println("here2");
                   }
               }
           }
       }

      int hops = 0;


      int u = source - 1;
      int v = destinations-1;
  /*    while(u!=v)
      {
        u = next[u][v];
        hops++;
      }*/

    return distance[source-1][destinations-1];
    //return hops;
    }

    int Floyd_Warshall_starter(int[][] destinations, Nodes.Node[][] graph)
    {
        int cost = 0;
        int source = 1;

        for(int i=0; i<destinations.length; i++)
        {
            int destinationNodeNumber = graph[destinations[i][0]-1][destinations[i][1]-1].nodeNumber;
            cost = cost + Floyd_Warshall(source, destinationNodeNumber, graph);
            source = destinationNodeNumber;
        }
        return cost;
    }

    int Bellman_Ford(int source, int destination, Nodes.Node[][] graph) {
        LinkedList<Nodes.Node> nodes = new LinkedList<Nodes.Node>();
        LinkedList<Nodes.Edge> edges = new LinkedList<Nodes.Edge>();

        int[] predecessor = new int[N * N];

        // ----------------- NULL wartosci
        for (int i = 0; i < predecessor.length; i++) {
            predecessor[i] = 0;
        }


        // Dodanie node'ów
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Nodes.Node node = graph[i][j];
                nodes.add(node);
                for (int k = 0; k < node.edges.size(); k++) {
                    Nodes.Edge edge = node.edges.get(k);
                    edge.source = node.nodeNumber;
                    if (edge.destination <= N*N) {
                        edges.add(node.edges.get(k));
                    }
                }
            }
        }
        // ------------------------------------------


        int distance[] = new int[N * N];


        for (int i = 0; i < distance.length; i++) {
            distance[i] = 999;
        }

        distance[source - 1] = 0;

        for (int i = 0; i < N - 1; i++) {
            for (Nodes.Edge edge : edges) {
                if (distance[edge.source - 1] + edge.weight < distance[edge.destination - 1]) {

                    distance[edge.destination - 1] = distance[edge.source - 1] + edge.weight;
                    predecessor[edge.destination-1] = edge.source;

                }

            }
        }

        int start = destination;
        int stop = source;
        int hops = 0;
        while(stop != start)
        {
            start = predecessor[start-1];
            hops++;
        }


        //out.println(distance[destination-1]);
        //out.println(hops);
        //return distance[destination-1];
        return hops;

    }

    int Bellman_Ford_starter(int[][] destinations, Nodes.Node[][] graph)
    {
        int cost = 0;
        int source = 1;

        for(int i=0; i<destinations.length; i++)
        {
            int destinationNodeNumber = graph[destinations[i][0]-1][destinations[i][1]-1].nodeNumber;
            cost = cost + Bellman_Ford(source, destinationNodeNumber, graph);
            source = destinationNodeNumber;
        }
        return cost;
    }
}

