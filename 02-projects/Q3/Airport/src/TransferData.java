import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class TransferData {

    public HashMap<String, String> transferData(HashMap<String, String> airports) {
        String filePath = "src/airports.csv";
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(csvSplitBy);
                if (values.length == 2) {
                    airports.put(values[0].trim(), values[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Airports loaded: " + airports);
        return airports;
    }
}
