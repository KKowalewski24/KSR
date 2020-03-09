package pl.jkkk.task1.reader;

import com.google.gson.Gson;
import pl.jkkk.task1.exception.ReaderException;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pl.jkkk.task1.reader.ReaderUtil.preparePath;

public class JsonFileReader {

    /*------------------------ FIELDS REGION ------------------------*/
    private Gson gson = new Gson();

    /*------------------------ METHODS REGION ------------------------*/
    public List<String> readFromFile(String filename) throws URISyntaxException, ReaderException {
        try (FileReader fileReader = new FileReader(String.valueOf(preparePath(filename)))) {
            return new ArrayList<>(Arrays.asList(gson.fromJson(fileReader, String[].class)));
        } catch (IOException e) {
            throw new ReaderException(e);
        }
    }
}
    