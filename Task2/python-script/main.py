import glob
import os
import pathlib
import platform
import subprocess
import sys
from typing import List

'''
How to use
To build run `python main.py build` or `python main.py -b` in order to build jar file, 

To run experiments `python main.py -r`
args for run_jar()
* no args - GUI mode
* -sp or -sl - seeding db - more info in README.md
* two args - quantifier and summarizer - basic summarization
* more than two args - if first param true then advanced summarization with single qualifier
                       if first param false then basic summarization with multiple summarizer
                       in this mode number of summarizers is not specified - feel free XD

'''

# VAR ------------------------------------------------------------------------ #
JAR_NAME = "task2-0.0.1.jar"
TXT = "*.txt"
JAR = "*.jar"

ALMOST_NONE = "Almost none"
SOME = "Some"
ABOUT_HALF_OF_ALL = "About half of all"
MANY = "Many"
ALL = "All"

BEEN_DONE_IN_SPRING = "been done in spring"
BEEN_DONE_IN_SUMMER = "been done in summer"
BEEN_DONE_IN_AUTUMN = "been done in autumn"
BEEN_DONE_IN_WINTER = "been done in winter"

MAXIMUM_CO_CONCENTRATION_IN_THE_MORNING = "maximum CO concentration in the morning"
MAXIMUM_CO_CONCENTRATION_IN_THE_AFTERNOON = "maximum CO concentration in the afternoon"
MAXIMUM_CO_CONCENTRATION_IN_THE_EVENING = "maximum CO concentration in the evening"
MAXIMUM_CO_CONCENTRATION_IN_THE_NIGHT = "maximum CO concentration in the night"
MAXIMUM_NO_2_CONCENTRATION_IN_THE_MORNING = "maximum NO2 concentration in the morning"
MAXIMUM_NO_2_CONCENTRATION_IN_THE_AFTERNOON = "maximum NO2 concentration in the afternoon"
MAXIMUM_NO_2_CONCENTRATION_IN_THE_EVENING = "maximum NO2 concentration in the evening"
MAXIMUM_NO_2_CONCENTRATION_IN_THE_NIGHT = "maximum NO2 concentration in the night"
MAXIMUM_O_3_CONCENTRATION_IN_THE_MORNING = "maximum O3 concentration in the morning"
MAXIMUM_O_3_CONCENTRATION_IN_THE_AFTERNOON = "maximum O3 concentration in the afternoon"
MAXIMUM_O_3_CONCENTRATION_IN_THE_EVENING = "maximum O3 concentration in the evening"
MAXIMUM_O_3_CONCENTRATION_IN_THE_NIGHT = "maximum O3 concentration in the night"
MAXIMUM_SO_2_CONCENTRATION_IN_THE_MORNING = "maximum SO2 concentration in the morning"
MAXIMUM_SO_2_CONCENTRATION_IN_THE_AFTERNOON = "maximum SO2 concentration in the afternoon"
MAXIMUM_SO_2_CONCENTRATION_IN_THE_EVENING = "maximum SO2 concentration in the evening"
MAXIMUM_SO_2_CONCENTRATION_IN_THE_NIGHT = "maximum SO2 concentration in the night"

LOW_MEAN_CO_CONCENTRATION = "low mean CO concentration"
MIDDLE_MEAN_CO_CONCENTRATION = "middle mean CO concentration"
HIGH_MEAN_CO_CONCENTRATION = "high mean CO concentration"
LOW_MEAN_NO_2_CONCENTRATION = "low mean NO2 concentration"
MIDDLE_MEAN_NO_2_CONCENTRATION = "middle mean NO2 concentration"
HIGH_MEAN_NO_2_CONCENTRATION = "high mean NO2 concentration"
LOW_MEAN_O_3_CONCENTRATION = "low mean O3 concentration"
MIDDLE_MEAN_O_3_CONCENTRATION = "middle mean O3 concentration"
HIGH_MEAN_O_3_CONCENTRATION = "high mean O3 concentration"
LOW_MEAN_SO_2_CONCENTRATION = "low mean SO2 concentration"
MIDDLE_MEAN_SO_2_CONCENTRATION = "middle mean SO2 concentration"
HIGH_MEAN_SO_2_CONCENTRATION = "high mean SO2 concentration"

LOW_MAXIMUM_CO_CONCENTRATION = "low maximum CO concentration"
MIDDLE_MAXIMUM_CO_CONCENTRATION = "middle maximum CO concentration"
HIGH_MAXIMUM_CO_CONCENTRATION = "high maximum CO concentration"
LOW_MAXIMUM_NO_2_CONCENTRATION = "low maximum NO2 concentration"
MIDDLE_MAXIMUM_NO_2_CONCENTRATION = "middle maximum NO2 concentration"
HIGH_MAXIMUM_NO_2_CONCENTRATION = "high maximum NO2 concentration"
LOW_MAXIMUM_O_3_CONCENTRATION = "low maximum O3 concentration"
MIDDLE_MAXIMUM_O_3_CONCENTRATION = "middle maximum O3 concentration"
HIGH_MAXIMUM_O_3_CONCENTRATION = "high maximum O3 concentration"
LOW_MAXIMUM_SO_2_CONCENTRATION = "low maximum SO2 concentration"
MIDDLE_MAXIMUM_SO_2_CONCENTRATION = "middle maximum SO2 concentration"
HIGH_MAXIMUM_SO_2_CONCENTRATION = "high maximum SO2 concentration"

CORRECT_CO_AQI_VALUE = "correct CO AQI value"
UNHEALTHY_CO_AQI_VALUE = "unhealthy CO AQI value"
HAZARDOUS_CO_AQI_VALUE = "hazardous CO AQI value"
CORRECT_NO_2_AQI_VALUE = "correct NO2 AQI value"
UNHEALTHY_NO_2_AQI_VALUE = "unhealthy NO2 AQI value"
HAZARDOUS_NO_2_AQI_VALUE = "hazardous NO2 AQI value"
CORRECT_O_3_AQI_VALUE = "correct O3 AQI value"
UNHEALTHY_O_3_AQI_VALUE = "unhealthy O3 AQI value"
HAZARDOUS_O_3_AQI_VALUE = "hazardous O3 AQI value"
CORRECT_SO_2_AQI_VALUE = "correct SO2 AQI value"
UNHEALTHY_SO_2_AQI_VALUE = "unhealthy SO2 AQI value"
HAZARDOUS_SO_2_AQI_VALUE = "hazardous SO2 AQI value"

SUMMARY_SEPARATOR_CMD = ","

TRUE = "true"
FALSE = "false"

args_to_call: List[str] = []


# DEF ------------------------------------------------------------------------ #
def add_args_to_run(args: List[str]) -> None:
    args_to_call.extend(args + [SUMMARY_SEPARATOR_CMD])


def run_selected_args() -> None:
    run_jar(args_to_call)


def seed_pollution_database() -> None:
    run_jar(["-sp"])


def seed_linguistic_database() -> None:
    run_jar(["-sl"])


# BASIC - FIRST STEP FOR EVERY EXPERIMENT
def quantifier_series(args: List[str], is_advanced: str) -> None:
    add_args_to_run([is_advanced, ALMOST_NONE] + args)
    add_args_to_run([is_advanced, SOME] + args)
    add_args_to_run([is_advanced, ABOUT_HALF_OF_ALL] + args)
    add_args_to_run([is_advanced, MANY] + args)
    add_args_to_run([is_advanced, ALL] + args)


def quantifier_series_basic(args: List[str]) -> None:
    quantifier_series(args, FALSE)


def quantifier_series_advanced(args: List[str]) -> None:
    quantifier_series(args, TRUE)


def season_series(args: List[str], is_advanced=TRUE) -> None:
    if is_advanced == TRUE:
        quantifier_series_advanced([BEEN_DONE_IN_SPRING] + args)
        quantifier_series_advanced([BEEN_DONE_IN_SUMMER] + args)
        quantifier_series_advanced([BEEN_DONE_IN_AUTUMN] + args)
        quantifier_series_advanced([BEEN_DONE_IN_WINTER] + args)
        pass
    elif is_advanced == FALSE:
        quantifier_series_basic([BEEN_DONE_IN_SPRING] + args)
        quantifier_series_basic([BEEN_DONE_IN_SUMMER] + args)
        quantifier_series_basic([BEEN_DONE_IN_AUTUMN] + args)
        quantifier_series_basic([BEEN_DONE_IN_WINTER] + args)


def datetime_series(args: List[str], co=FALSE, no2=FALSE,
                    o3=FALSE, so2=FALSE, is_advanced=TRUE) -> None:
    if is_advanced == TRUE:
        if co == TRUE:
            quantifier_series_advanced([MAXIMUM_CO_CONCENTRATION_IN_THE_MORNING] + args)
            quantifier_series_advanced([MAXIMUM_CO_CONCENTRATION_IN_THE_AFTERNOON] + args)
            quantifier_series_advanced([MAXIMUM_CO_CONCENTRATION_IN_THE_EVENING] + args)
            quantifier_series_advanced([MAXIMUM_CO_CONCENTRATION_IN_THE_NIGHT] + args)
        elif no2 == TRUE:
            quantifier_series_advanced([MAXIMUM_NO_2_CONCENTRATION_IN_THE_MORNING] + args)
            quantifier_series_advanced([MAXIMUM_NO_2_CONCENTRATION_IN_THE_AFTERNOON] + args)
            quantifier_series_advanced([MAXIMUM_NO_2_CONCENTRATION_IN_THE_EVENING] + args)
            quantifier_series_advanced([MAXIMUM_NO_2_CONCENTRATION_IN_THE_NIGHT] + args)
        elif o3 == TRUE:
            quantifier_series_advanced([MAXIMUM_O_3_CONCENTRATION_IN_THE_MORNING] + args)
            quantifier_series_advanced([MAXIMUM_O_3_CONCENTRATION_IN_THE_AFTERNOON] + args)
            quantifier_series_advanced([MAXIMUM_O_3_CONCENTRATION_IN_THE_EVENING] + args)
            quantifier_series_advanced([MAXIMUM_O_3_CONCENTRATION_IN_THE_NIGHT] + args)
        elif so2 == TRUE:
            quantifier_series_advanced([MAXIMUM_SO_2_CONCENTRATION_IN_THE_MORNING] + args)
            quantifier_series_advanced([MAXIMUM_SO_2_CONCENTRATION_IN_THE_AFTERNOON] + args)
            quantifier_series_advanced([MAXIMUM_SO_2_CONCENTRATION_IN_THE_EVENING] + args)
            quantifier_series_advanced([MAXIMUM_SO_2_CONCENTRATION_IN_THE_NIGHT] + args)
    elif is_advanced == FALSE:
        if co == TRUE:
            quantifier_series_basic([MAXIMUM_CO_CONCENTRATION_IN_THE_MORNING] + args)
            quantifier_series_basic([MAXIMUM_CO_CONCENTRATION_IN_THE_AFTERNOON] + args)
            quantifier_series_basic([MAXIMUM_CO_CONCENTRATION_IN_THE_EVENING] + args)
            quantifier_series_basic([MAXIMUM_CO_CONCENTRATION_IN_THE_NIGHT] + args)
        elif no2 == TRUE:
            quantifier_series_basic([MAXIMUM_NO_2_CONCENTRATION_IN_THE_MORNING] + args)
            quantifier_series_basic([MAXIMUM_NO_2_CONCENTRATION_IN_THE_AFTERNOON] + args)
            quantifier_series_basic([MAXIMUM_NO_2_CONCENTRATION_IN_THE_EVENING] + args)
            quantifier_series_basic([MAXIMUM_NO_2_CONCENTRATION_IN_THE_NIGHT] + args)
        elif o3 == TRUE:
            quantifier_series_basic([MAXIMUM_O_3_CONCENTRATION_IN_THE_MORNING] + args)
            quantifier_series_basic([MAXIMUM_O_3_CONCENTRATION_IN_THE_AFTERNOON] + args)
            quantifier_series_basic([MAXIMUM_O_3_CONCENTRATION_IN_THE_EVENING] + args)
            quantifier_series_basic([MAXIMUM_O_3_CONCENTRATION_IN_THE_NIGHT] + args)
        elif so2 == TRUE:
            quantifier_series_basic([MAXIMUM_SO_2_CONCENTRATION_IN_THE_MORNING] + args)
            quantifier_series_basic([MAXIMUM_SO_2_CONCENTRATION_IN_THE_AFTERNOON] + args)
            quantifier_series_basic([MAXIMUM_SO_2_CONCENTRATION_IN_THE_EVENING] + args)
            quantifier_series_basic([MAXIMUM_SO_2_CONCENTRATION_IN_THE_NIGHT] + args)


def season_datetime_series(args: List[str], co=FALSE, no2=FALSE, o3=FALSE, so2=FALSE) -> None:
    datetime_series([BEEN_DONE_IN_SPRING] + args, co, no2, o3, so2)
    datetime_series([BEEN_DONE_IN_SUMMER] + args, co, no2, o3, so2)
    datetime_series([BEEN_DONE_IN_AUTUMN] + args, co, no2, o3, so2)
    datetime_series([BEEN_DONE_IN_WINTER] + args, co, no2, o3, so2)


def run_experiments() -> None:
    # season_series([], is_advanced=FALSE)
    # datetime_series([], co=TRUE, is_advanced=FALSE)
    # datetime_series([], no2=TRUE, is_advanced=FALSE)
    # datetime_series([], o3=TRUE, is_advanced=FALSE)
    # datetime_series([], so2=TRUE, is_advanced=FALSE)
    # 
    # quantifier_series_basic([CORRECT_CO_AQI_VALUE])
    # quantifier_series_basic([UNHEALTHY_CO_AQI_VALUE])
    # quantifier_series_basic([HAZARDOUS_CO_AQI_VALUE])
    # quantifier_series_basic([CORRECT_NO_2_AQI_VALUE])
    # quantifier_series_basic([UNHEALTHY_NO_2_AQI_VALUE])
    # quantifier_series_basic([HAZARDOUS_NO_2_AQI_VALUE])
    # quantifier_series_basic([CORRECT_O_3_AQI_VALUE])
    # quantifier_series_basic([UNHEALTHY_O_3_AQI_VALUE])
    # quantifier_series_basic([HAZARDOUS_O_3_AQI_VALUE])
    # quantifier_series_basic([CORRECT_SO_2_AQI_VALUE])
    # quantifier_series_basic([UNHEALTHY_SO_2_AQI_VALUE])
    # quantifier_series_basic([HAZARDOUS_SO_2_AQI_VALUE])

    # TODO  DO NOT DELETE - I LEFT THIS JUST IN CASE - MEAN, MAX AND AQI
    # TODO ARE FUNCTIONALLY RELATED SO RESULTS SHOULD BE SIMILAR
    # season_series([LOW_MEAN_CO_CONCENTRATION])
    # season_series([MIDDLE_MEAN_CO_CONCENTRATION])
    # season_series([HIGH_MEAN_CO_CONCENTRATION])
    # season_series([LOW_MEAN_NO_2_CONCENTRATION])
    # season_series([MIDDLE_MEAN_NO_2_CONCENTRATION])
    # season_series([HIGH_MEAN_NO_2_CONCENTRATION])
    # season_series([LOW_MEAN_O_3_CONCENTRATION])
    # season_series([MIDDLE_MEAN_O_3_CONCENTRATION])
    # season_series([HIGH_MEAN_O_3_CONCENTRATION])
    # season_series([LOW_MEAN_SO_2_CONCENTRATION])
    # season_series([MIDDLE_MEAN_SO_2_CONCENTRATION])
    # season_series([HIGH_MEAN_SO_2_CONCENTRATION])
    #
    # season_series([LOW_MAXIMUM_CO_CONCENTRATION])
    # season_series([MIDDLE_MAXIMUM_CO_CONCENTRATION])
    # season_series([HIGH_MAXIMUM_CO_CONCENTRATION])
    # season_series([LOW_MAXIMUM_NO_2_CONCENTRATION])
    # season_series([MIDDLE_MAXIMUM_NO_2_CONCENTRATION])
    # season_series([HIGH_MAXIMUM_NO_2_CONCENTRATION])
    # season_series([LOW_MAXIMUM_O_3_CONCENTRATION])
    # season_series([MIDDLE_MAXIMUM_O_3_CONCENTRATION])
    # season_series([HIGH_MAXIMUM_O_3_CONCENTRATION])
    # season_series([LOW_MAXIMUM_SO_2_CONCENTRATION])
    # season_series([MIDDLE_MAXIMUM_SO_2_CONCENTRATION])
    # season_series([HIGH_MAXIMUM_SO_2_CONCENTRATION])
    #
    # season_series([CORRECT_CO_AQI_VALUE])
    # season_series([UNHEALTHY_CO_AQI_VALUE])
    # season_series([HAZARDOUS_CO_AQI_VALUE])
    # season_series([CORRECT_NO_2_AQI_VALUE])
    # season_series([UNHEALTHY_NO_2_AQI_VALUE])
    # season_series([HAZARDOUS_NO_2_AQI_VALUE])
    # season_series([CORRECT_O_3_AQI_VALUE])
    # season_series([UNHEALTHY_O_3_AQI_VALUE])
    # season_series([HAZARDOUS_O_3_AQI_VALUE])
    # season_series([CORRECT_SO_2_AQI_VALUE])
    # season_series([UNHEALTHY_SO_2_AQI_VALUE])
    # season_series([HAZARDOUS_SO_2_AQI_VALUE])
    # 
    # datetime_series([CORRECT_CO_AQI_VALUE], co=TRUE)
    # datetime_series([UNHEALTHY_CO_AQI_VALUE], co=TRUE)
    # datetime_series([HAZARDOUS_CO_AQI_VALUE], co=TRUE)
    # datetime_series([CORRECT_NO_2_AQI_VALUE], no2=TRUE)
    # datetime_series([UNHEALTHY_NO_2_AQI_VALUE], no2=TRUE)
    # datetime_series([HAZARDOUS_NO_2_AQI_VALUE], no2=TRUE)
    # datetime_series([CORRECT_O_3_AQI_VALUE], o3=TRUE)
    # datetime_series([UNHEALTHY_O_3_AQI_VALUE], o3=TRUE)
    # datetime_series([HAZARDOUS_O_3_AQI_VALUE], o3=TRUE)
    # datetime_series([CORRECT_SO_2_AQI_VALUE], so2=TRUE)
    # datetime_series([UNHEALTHY_SO_2_AQI_VALUE], so2=TRUE)
    # datetime_series([HAZARDOUS_SO_2_AQI_VALUE], so2=TRUE)
    #
    # season_datetime_series([CORRECT_CO_AQI_VALUE], co=TRUE)
    # season_datetime_series([UNHEALTHY_CO_AQI_VALUE], co=TRUE)
    # season_datetime_series([HAZARDOUS_CO_AQI_VALUE], co=TRUE)
    #
    # season_datetime_series([CORRECT_NO_2_AQI_VALUE], no2=TRUE)
    # season_datetime_series([UNHEALTHY_NO_2_AQI_VALUE], no2=TRUE)
    # season_datetime_series([HAZARDOUS_NO_2_AQI_VALUE], no2=TRUE)
    #
    # season_datetime_series([CORRECT_O_3_AQI_VALUE], o3=TRUE)
    # season_datetime_series([UNHEALTHY_O_3_AQI_VALUE], o3=TRUE)
    # season_datetime_series([HAZARDOUS_O_3_AQI_VALUE], o3=TRUE)
    #
    # season_datetime_series([CORRECT_SO_2_AQI_VALUE], so2=TRUE)
    # season_datetime_series([UNHEALTHY_SO_2_AQI_VALUE], so2=TRUE)
    # season_datetime_series([HAZARDOUS_SO_2_AQI_VALUE], so2=TRUE)

    # run_selected_args()
    pass


# MAIN ----------------------------------------------------------------------- #
def main() -> None:
    # subprocess.call(["mypy", "main.py"])

    if len(sys.argv) == 2:
        if sys.argv[1] == "build" or sys.argv[1] == "-b":
            build_jar()
        elif sys.argv[1] == "seed_pollution" or sys.argv[1] == "-sp":
            seed_pollution_database()
        elif sys.argv[1] == "seed_linguistic" or sys.argv[1] == "-sl":
            seed_linguistic_database()
        elif sys.argv[1] == "run" or sys.argv[1] == "-r":
            run_experiments()

    if len(sys.argv) >= 2 and (sys.argv[1] == "clean" or sys.argv[1] == "-c"):
        if len(sys.argv) == 3 and (sys.argv[2] == "jar" or sys.argv[2] == "-j"):
            clean_project_directories(True)
        else:
            clean_project_directories(False)

    display_finish()


# UTIL ----------------------------------------------------------------------- #
def run_jar(args: List[str]) -> None:
    subprocess.call(["java", "-jar", JAR_NAME] + args)


def build_jar() -> None:
    script_directory = pathlib.Path(os.getcwd())
    os.chdir(script_directory.parent)
    if platform.system().lower() == "windows":
        subprocess.call("mvnw.cmd clean package", shell=True)
        subprocess.call("copy target\\" + JAR_NAME + " " + str(script_directory), shell=True)
    elif platform.system().lower() == "linux":
        subprocess.call("./mvnw clean package", shell=True)
        subprocess.call("cp target/" + JAR_NAME + " " + str(script_directory), shell=True)


def clean_project_directories(remove_jar: bool) -> None:
    script_directory = pathlib.Path(os.getcwd())
    if remove_jar:
        remove_files(glob.glob(JAR))

    remove_files(glob.glob(TXT))

    os.chdir(script_directory.parent)
    remove_files(glob.glob(TXT))

    os.chdir(script_directory.parent.parent)
    remove_files(glob.glob(TXT))


def remove_files(filenames: List[str]) -> None:
    for it in filenames:
        os.remove(it)


def display_finish() -> None:
    print("------------------------------------------------------------------------")
    print("FINISHED")
    print("------------------------------------------------------------------------")


if __name__ == "__main__":
    main()
