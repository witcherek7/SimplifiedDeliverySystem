import java.util.ArrayList;

public class Nodes {

    int N = 20;

    public Nodes(String[] lines) {
        createNodes();        createGraph(lines);
    }

    Node[][] nodes = new Node[N][N];

    public Node[][] getNodes() {
        return nodes;
    }

    static class Edge {
        int destination;
        int weight;

        public Edge(int destination, int weight)
        {
            this.destination=destination;
            this.weight=weight;
        }
    }


    static class Node{
        public Node(int nodeNumber) {
            this.nodeNumber = nodeNumber;
        }

        int nodeNumber;

        ArrayList<Edge> edges = new ArrayList<Edge>();

        void addEdge(int destination, int value)
        {
            Edge edge = new Edge(destination, value);
            edges.add(edge);
        }

        void printEdges()
        {
            for(Edge e : edges)
            {
                java.lang.System.out.println("Do: " + e.destination + " z wagą: " + e.weight);
            }
        }


    }

    void createNodes()
    {
        int number = 1;
        for(int i=0; i<N; i++)
        {
            for(int j=0; j<N; j++)
            {
                Node node = new Node(number);
                number++;
                nodes[i][j]=node;
            }
        }
    }

    void createGraph(String[] allLines)
    {
        int whichhor = 0;
        int whichvert = 0;
        for(int i = 0; i < N+N; i++)
        {
            if(i%2==0)
            {
                for(int j = 0; j < N; j++)
                {
                    int value = Character.getNumericValue(allLines[i].charAt(j));
                    //System.out.println("Value: " + value);
                    if(value != 0 ) {
                        int destination = N * whichhor + j + 2;
                        int source = N*whichhor+j + 1;
                        nodes[whichhor][j].addEdge(destination,value);
                        if(j!=N-1) {
                            nodes[whichhor][j + 1].addEdge(source, value);
                        }

                    }


                }
                whichhor++;
            }
            if(i%2==1)
            {
                for(int j = 0; j<N; j++)
                {
                    int value = Character.getNumericValue(allLines[i].charAt(j));
                    int destination = (N*(whichvert+1))+j+1;
                    int source = N*(whichvert)+j+1;
                    //System.out.println(source + " " + destination);
                    nodes[whichvert][j].addEdge(destination,value);
                    if(i!= 2*N-1)
                    {
                    nodes[whichvert+1][j].addEdge(source,value);
                    }
                }
                whichvert++;

            }
        }
    }

    void printNode(int nodeNumber)
    {
        for(int i=0; i<N; i++)
        {
            for(int j=0; j<N; j++)
            {
                if(nodes[i][j].nodeNumber == nodeNumber)
                {
                    nodes[i][j].printEdges();
                }
            }
        }


    }





}
