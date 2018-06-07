import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;


public class Generator {



    public static void creator (){


        String[] Names = {"Oliver" ,
                "George" ,
                "Harry" ,
                "Jack" ,
                "Jacob" ,
                "Noah" ,
                "Charlie" ,
                "Muhammad" ,
                "Thomas" ,
                "Oscar" ,
                "William" ,
                "James" ,
                "Henry" ,
                "Leo" ,
                "Alfie" ,
                "Joshua" ,
                "Freddie" ,
                "Archie" ,
                "Ethan" ,
                "Isaac" ,
                "Alexander" ,
                "Joseph" ,
                "Edward" ,
                "Samuel" ,
                "Max" ,
                "Daniel" ,
                "Arthur" ,
                "Lucas" ,
                "Mohammed" ,
                "Logan" ,
                "Theo" ,
                "Harrison" ,
                "Benjamin" ,
                "Mason" ,
                "Sebastian" ,
                "Finley" ,
                "Adam" ,
                "Dylan" ,
                "Zachary" ,
                "Riley" ,
                "Teddy" ,
                "Theodore" ,
                "David" ,
                "Toby" ,
                "Jake" ,
                "Louie" ,
                "Elijah" ,
                "Reuben" ,
                "Arlo" ,
                "Hugo"};

        int numberOfCouriers;
        int numberofPackages;

        java.lang.System.out.print("Podaj ilosc kurierow: ");

        Scanner reader = new Scanner(java.lang.System.in);

        numberOfCouriers = reader.nextInt();
        
        String packages = "";
        String container= "" ;



        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        String timestamp = formatter.format(today);

        //System.out.println("Timestamp: = " + timestamp2);
        Random random = new Random();


        for (int i=0; i<numberOfCouriers; i++) {
            numberofPackages= random.nextInt(3)+2;

            for (int j=0; j<=numberofPackages;j++) {

                container = packages + container;
                packages = "(" + Integer.toString(random.nextInt(10)+1) + "," + Integer.toString(random.nextInt(10)+1) + ")" +",";

                if(j==numberofPackages){
                    packages = "(" + Integer.toString(random.nextInt(10)+1) + "," + Integer.toString(random.nextInt(10)+1) + ")";

                }

            }

            try {
                FileWriter writer = new FileWriter("Generator.txt", true);

                writer.write(timestamp + "," + Names[random.nextInt(9)+1]+","+container);
                writer.write("\r\n");
                writer.close();
                //java.lang.System.out.print(timestamp + "," + Names[random.nextInt(9)+1]+","+container);
                //java.lang.System.out.println("");



            } catch (IOException e) {
                e.printStackTrace();
            }



            container ="";

        }

    }


}
