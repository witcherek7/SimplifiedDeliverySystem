import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Logger {


    void createLog(String timestamp, String name, int packagesNumber, int pathTime) throws IOException {
        try {


            // Getting timestamp date

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

            LocalDate date = LocalDate.parse(timestamp, formatter);

            String newtimestamp = formatter.format(date);

            // Changing date format

            char D1letter = newtimestamp.charAt(0);
            char D2letter = newtimestamp.charAt(1);
            char M1letter = newtimestamp.charAt(2);
            char M2letter = newtimestamp.charAt(3);
            char Y1letter = newtimestamp.charAt(4);
            char Y2letter = newtimestamp.charAt(5);
            char Y3letter = newtimestamp.charAt(6);
            char Y4letter = newtimestamp.charAt(7);

            java.util.Map<String, Integer> nameAndCount = new HashMap<>();


            // Writing file

            FileWriter writer = new FileWriter("Log.txt", true);

            writer.write(Character.toString(D1letter)+D2letter+"-"+M1letter+M2letter+"-"+Y1letter+Y2letter+Y3letter+Y4letter + ":"
                    +name+":"+packagesNumber+":"+pathTime);

            writer.write("\r\n");

            writer.close();

            //System.out.print(D1letter+D2letter+"-"+M1letter+M2letter+"-"+Y1letter+Y2letter+Y3letter+Y4letter + ":"
            //       +name+":"+packagesNumber+":"+pathTime);

        } catch (IOException e) {
            java.lang.System.out.print("Wrong Date Format!!");

            e.printStackTrace();
        }


    }
        void CreateLog2(String timestamp, String[] NamesContainer, int howManyLines){
            try {

                int NumberOfPaths = 1;
                int counter = 0;
                String name;

                String[] pathsForNames = new String[howManyLines];


                //Sprawdzanie ilości tras każdego kuriera poprzez szukanie duplikatów w tablicy utworzonej z generatora

                for (int j=0;j<NamesContainer.length ;j++)
                    for (int k=j+1;k<NamesContainer.length;k++)
                        if (k!=j && NamesContainer[k] == NamesContainer[j]) {
                            NumberOfPaths++;

                            pathsForNames[j] = NamesContainer[j] +":"+ Integer.toString(NumberOfPaths);
                            counter++;
                            java.lang.System.out.println(pathsForNames[j]);
                            NumberOfPaths--;
                            //List<String> list = new ArrayList<String>(Arrays.asList(NamesContainer));
                            //list.remove(NamesContainer[j]);
                           // NamesContainer = list.toArray(new String[0]);


                        }
                        else if (k!=j && NamesContainer[k] != NamesContainer[j]){
                            pathsForNames[k] = NamesContainer[k] +":"+ Integer.toString(1);
                            //pathsForNames[j] = NamesContainer[j] +":"+ Integer.toString(1);
                            java.lang.System.out.println(pathsForNames[k]);
                           // System.out.println(pathsForNames[j]);
                        }
                       // else{
                       //     pathsForNames[counter] = NamesContainer[k] +":"+ Integer.toString(1);
                       //     System.out.println(pathsForNames[counter]);


                       // }



                //Wczytywanie timestampa i zmiana formatu na date

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

                LocalDate date = LocalDate.parse(timestamp, formatter);

                String newtimestamp = formatter.format(date);

                //Zmienianie formatu daty

                char D1letter = newtimestamp.charAt(0);
                char D2letter = newtimestamp.charAt(1);
                char M1letter = newtimestamp.charAt(2);
                char M2letter = newtimestamp.charAt(3);
                char Y1letter = newtimestamp.charAt(4);
                char Y2letter = newtimestamp.charAt(5);
                char Y3letter = newtimestamp.charAt(6);
                char Y4letter = newtimestamp.charAt(7);

                //Wpisywanie w plik

                FileWriter writer = new FileWriter("Log2.txt", true);

             //   writer.write(D1letter+D2letter+"-"+M1letter+M2letter+"-"+Y1letter+Y2letter+Y3letter+Y4letter
             //           +":"+" tras" );

                writer.write("\r\n");

                writer.close();

                //System.out.print(D1letter+D2letter+"-"+M1letter+M2letter+"-"+Y1letter+Y2letter+Y3letter+Y4letter + ":"
                //       +name+":"+packagesNumber+":"+pathTime);

            } catch (IOException e) {
                java.lang.System.out.print("Wrong Date Format!!");

                e.printStackTrace();
            }



        }



        }
