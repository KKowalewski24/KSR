package pl.jkkk.task1.reader;

import com.google.gson.Gson;
import pl.jkkk.task1.exception.ReaderException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonFileReader {

    /*------------------------ FIELDS REGION ------------------------*/
    private Gson gson = new Gson();

    /*------------------------ METHODS REGION ------------------------*/
    public List<String> readFromFile(String filename) throws ReaderException {
        try (Reader reader = new InputStreamReader(getClass().getResourceAsStream(filename))) {
            return new ArrayList<>(Arrays.asList(gson.fromJson(reader, String[].class)));
        } catch (IOException e) {
            throw new ReaderException(e);
        }
    }
}
    
