import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * NmapSorter - PACKAGE_NAME
 * Created by MAWood on 02/11/2016.
 */
public class Main
{
    public static void main(String[] args) throws IOException
    {
        for(NmapSystem system: processFile("NmapNew.txt"))
        {
            System.out.print(system.toCSV());
        }
    }

    private static List<NmapSystem> processFile(String fileName)
    {
        List<NmapSystem> systems = new ArrayList<>();
        try
        {
            // Create matcher on file
            Pattern pattern = Pattern.compile("(Nmap\\sscan\\sreport\\sfor)((.|\\n)*?)(?=No\\s)",Pattern.MULTILINE);
            Matcher matcher = pattern.matcher(fromFile(fileName));

            while (matcher.find())
            {
                String match = matcher.group();
                systems.add(new NmapSystem(match));
            }
        } catch (IOException ignored)
        {
        }
        return systems;
    }

    private static CharSequence fromFile(String filename) throws IOException
    {
        FileInputStream input = new FileInputStream(filename);
        FileChannel channel = input.getChannel();

        ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, (int) channel.size());
        return Charset.forName("8859_1").newDecoder().decode(buf);
    }
}
