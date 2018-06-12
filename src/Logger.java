import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

public class Logger {


    void createLog(String timestamp, String name, int packagesNumber, int pathTime) {
        try {

            //Zamiana stringa na Integera dalej na Date oraz zmiana formatu timestampa na ustalony

            int epochTimestamp = Integer.parseInt(timestamp);

            Date time = new Date((long) epochTimestamp * 1000);

            DateFormat df = new SimpleDateFormat("dd-MM-yyyy ");

            String reportDate = df.format(time);


            // Stworzenie oraz zapis pliku

            FileWriter writer = new FileWriter("Log.txt", true);

            writer.write(reportDate + ":"
                    + name + ":" + packagesNumber + ":" + pathTime);

            writer.write("\r\n");

            writer.close();


        }
        // Lapanie złego formatu timestampa
        catch (IOException e) {
            java.lang.System.out.print("Wrong Date Format!!");

            e.printStackTrace();
        }


    }


}
