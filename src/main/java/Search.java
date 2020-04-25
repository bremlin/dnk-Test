import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

public class Search {

    private ArrayList<File> gotcha = new ArrayList<>();

    private void search(File directory, String name) {
        for (File child : directory.listFiles()) {
            if (child.isFile() && child.getName().contains(name)) {
                gotcha.add(child);
            } else if (child.isDirectory()) {
                if (child.list() != null) {
                    search(child, name);
                }
            }
        }
    }

    public ArrayList<File> getSearchResult(File directory, String name) {
        search(directory, name);
        return gotcha;
    }

    public void changeFiles() throws IOException {
        for (File file : gotcha) {
            if (file.getName().contains(".txt")) {
                String into = new String(Files.readAllBytes(file.toPath()));
                FileWriter fooWriter = new FileWriter(file, false);
                fooWriter.write("bomjur\n");
                fooWriter.write(into);
                fooWriter.close();
            }
        }
    }
}