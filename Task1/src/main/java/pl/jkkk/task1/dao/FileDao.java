package pl.jkkk.task1.dao;

import lombok.Getter;
import pl.jkkk.task1.exception.FileDaoException;
import pl.jkkk.task1.model.Document;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Getter
public class FileDao implements Dao<Document> {

    /*------------------------ FIELDS REGION ------------------------*/
    private String filename;

    /*------------------------ METHODS REGION ------------------------*/
    public FileDao(String filename) {
        this.filename = filename;
    }

    @Override
    public Document read() throws FileDaoException {
        return null;
    }

    @Override
    public void write(Document object) throws FileDaoException {
        throw new NotImplementedException();
    }
}
    