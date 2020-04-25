import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    private static final String ROOT_LIST = "-readDrives";
    private static final String SEARCH = "-search";
    private static final String SEARCH_AND_CHANGE = "-searchupdate";

    public static void main(String[] args) {
        String command = "";

        if (args.length > 0) {
            command = args[0];
        }

        switch (command) {
            case ROOT_LIST:
                File[] roots = File.listRoots();

                for (File root : roots) {
                    System.out.println(root.getAbsolutePath());
                }
                break;
            case SEARCH:
                searchFile(args);
                break;
            case SEARCH_AND_CHANGE:
                Search search = searchFile(args);

                if (search != null) {
                    try {
                        search.changeFiles();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                System.out.println("unknown command");
                break;
        }
    }

    private static Search searchFile(String[] args) {
        Search search = null;
        if (args.length == 3) {
            String path = args[1].replace("'", "");
            String name = args[2].replace("'", "");

            File searchPath = new File(path);
            search = new Search();
            ArrayList<File> result = search.getSearchResult(searchPath, name);

            for (File finds : result) {
                System.out.println(finds.getAbsolutePath());
            }
        } else {
            System.out.println("wrong parameters. rtm pls");
        }
        return search;
    }
}