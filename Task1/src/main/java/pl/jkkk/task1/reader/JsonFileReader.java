package pl.jkkk.task1.reader;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.jkkk.task1.exception.ReaderException;

public class JsonFileReader {

    /*------------------------ FIELDS REGION ------------------------*/
    private Gson gson = new Gson();

    /*------------------------ METHODS REGION ------------------------*/
    public List<String> readFromFile(String filename) throws URISyntaxException, ReaderException {
        try (Reader reader = new InputStreamReader(getClass().getResourceAsStream(filename))) {
            return new ArrayList<>(Arrays.asList(gson.fromJson(reader, String[].class)));
        } catch (IOException e) {
            throw new ReaderException(e);
        }
    }
}
    
