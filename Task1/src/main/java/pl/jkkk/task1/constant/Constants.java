package pl.jkkk.task1.constant;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Constants {

    /*------------------------ FIELDS REGION ------------------------*/

    /*----- METRICS -----*/
    public static final String EUCLIDEAN = "Euclidean";
    public static final String EUCLIDEAN_ABBREVIATION = "eucl";
    public static final String MANHATTAN = "Manhattan";
    public static final String MANHATTAN_ABBREVIATION = "manh";
    public static final String CHEBYSHEV = "Chebyshev";
    public static final String CHEBYSHEV_ABBREVIATION = "cheb";

    /*----- PLACES -----*/
    public static final String PLACES_WEST_GERMANY = "west-germany";
    public static final String PLACES_USA = "usa";
    public static final String PLACES_FRANCE = "france";
    public static final String PLACES_UK = "uk";
    public static final String PLACES_CANADA = "canada";
    public static final String PLACES_JAPAN = "japan";

    public static final ArrayList<String> CHOSEN_PLACES = Stream.of(
            PLACES_WEST_GERMANY,
            PLACES_USA,
            PLACES_FRANCE,
            PLACES_UK,
            PLACES_CANADA,
            PLACES_JAPAN
    ).collect(Collectors.toCollection(ArrayList::new));

    /*----- FILES -----*/
    public static final String STOP_WORDS = "stop-words.json";
    public static final String SUBDIRECTORY = "article/";
    public static final ArrayList<String> FILENAME_LIST = Stream.of(
            SUBDIRECTORY + "reut2-000.sgm",
            SUBDIRECTORY + "reut2-001.sgm",
            SUBDIRECTORY + "reut2-002.sgm",
            SUBDIRECTORY + "reut2-003.sgm",
            SUBDIRECTORY + "reut2-004.sgm",
            SUBDIRECTORY + "reut2-005.sgm",
            SUBDIRECTORY + "reut2-006.sgm",
            SUBDIRECTORY + "reut2-007.sgm",
            SUBDIRECTORY + "reut2-008.sgm",
            SUBDIRECTORY + "reut2-009.sgm",
            SUBDIRECTORY + "reut2-010.sgm",
            SUBDIRECTORY + "reut2-011.sgm",
            SUBDIRECTORY + "reut2-012.sgm",
            SUBDIRECTORY + "reut2-013.sgm",
            SUBDIRECTORY + "reut2-014.sgm",
            SUBDIRECTORY + "reut2-015.sgm",
            SUBDIRECTORY + "reut2-016.sgm",
            SUBDIRECTORY + "reut2-017.sgm",
            SUBDIRECTORY + "reut2-018.sgm",
            SUBDIRECTORY + "reut2-019.sgm",
            SUBDIRECTORY + "reut2-020.sgm",
            SUBDIRECTORY + "reut2-021.sgm"
    ).collect(Collectors.toCollection(ArrayList::new));

    /*------------------------ METHODS REGION ------------------------*/
    private Constants() {
    }
}
    