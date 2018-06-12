public class DeliveryEntry {
    String timestamp;
    String name;
    int[][] packages;
    int packagesNumber;


    //N - wielkość mapy NxN
    int N = 20;


    public DeliveryEntry(String timestamp, String name, int[][] packages, int packagesNumber) {
        this.timestamp = timestamp;
        this.name = name;
        this.packages = packages;
        this.packagesNumber = packagesNumber;
        //System.out.println(packagesNumber+ " tyyyyyle paczek");
    }

    Boolean checkIfEntryIsCorrect()
    {
        Boolean isCorrect = true;

        //1. Koordynaty czy pasują do zakresu
        for(int i=0; i<packages.length; i++)
        {
            if(packages[i][0]==0 || packages[i][1]==0 || packages[i][0]>N || packages[i][1]>N)
            {
                isCorrect = false;
            }

        }

        //2.Timestamp czy jest liczbą
        try {
            Integer.parseInt(timestamp);
        } catch (NumberFormatException e) {
            isCorrect = false;
        }


        //3. Czy liczba paczek jest z odpowiedniego zakresu
        if(packages.length>5)
        {
            isCorrect = false;
        }




        return isCorrect;
    }



}
