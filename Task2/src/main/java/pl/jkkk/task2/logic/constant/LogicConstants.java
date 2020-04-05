package pl.jkkk.task2.logic.constant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicConstants {

    /*------------------------ FIELDS REGION ------------------------*/
    //    TODO FILL SETS
    public static final List<String> QUALIFIER_SUMMARIZER_OPERATIONS = Stream.of(
            ""
    ).collect(Collectors.toCollection(ArrayList::new));

    /*------------------------ METHODS REGION ------------------------*/
    private LogicConstants() {
    }
}
    