import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * NmapSorter - PACKAGE_NAME
 * Created by MAWood on 02/11/2016.
 */
class NmapSystem
{
    private String address = "";
    private final List<NmapService> services = new ArrayList<>();

    NmapSystem(String data)
    {
        Scanner scanner = new Scanner(data);
        boolean blockFound = false;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.contains("PORT"))
            {
                blockFound = true;
                continue;
            }
            if (Character.isLetterOrDigit(line.toCharArray()[0]) && !Character.isLetter(line.toCharArray()[0]))
            {
                services.add(new NmapService(line));
                continue;
            }
            if(line.contains("Nmap scan report for ")) address = line.split("for ")[1];

        }
        scanner.close();
    }

    public String toString()
    {
        String output = "Address: " + address + System.lineSeparator();
        for(NmapService service:services) output += service.toString() + System.lineSeparator();
        return output;
    }

    String toCSV()
    {
        String output = "";
        for(NmapService service:services) output += address + "," + service.toCSV() + System.lineSeparator();
        return output;
    }
}
