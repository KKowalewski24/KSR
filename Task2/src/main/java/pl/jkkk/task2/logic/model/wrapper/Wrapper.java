package pl.jkkk.task2.logic.model.wrapper;

public interface Wrapper<T> {

    void serialize(T object);

    T deserialize();
}
