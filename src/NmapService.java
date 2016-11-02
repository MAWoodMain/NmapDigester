/**
 * NmapSorter - PACKAGE_NAME
 * Created by MAWood on 02/11/2016.
 */
class NmapService
{

    private final int port;
    private final String type;
    private final String state;
    private final String name;
    private final String version;

    NmapService(String data) throws IndexOutOfBoundsException
    {
        String[] parts = data.split("\\s+",4);
        String[] portAndType = parts[0].split("/");
        port = Integer.parseInt(portAndType[0]);
        type = portAndType[1];
        state = parts[1];
        name = parts[2];
        version = parts.length > 3?parts[3]:null;
    }

    public String toString()
    {
        if (version != null) return "'" + name + "' version '" + version + "' is running '" + state + "' on port '" + port + "' using '" + type + "'";
        return "'" + name + "' is running '" + state + "' on port '" + port + "' using '" + type + "'";
    }

    String toCSV()
    {
        return port + "," + type + "," + state + ",\"" + name + "\",\"" + version + "\"";
    }
}
