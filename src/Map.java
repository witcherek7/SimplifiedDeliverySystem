import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Map {

    String path;

    // Zmiena bierze linijki z pliku z mapą
    String[] lines;

    public Map(String path) {
        this.path = path;
    }

    void read() {
        try {
            String line;
            String content = new String(Files.readAllBytes(Paths.get(path)));
            //System.out.println(content);

            line = content;



            lines = content.split(("\n"));


        } catch (IOException e) {
            java.lang.System.out.print(e);
        }

    }


}
