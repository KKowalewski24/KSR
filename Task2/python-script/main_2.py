import glob
import os
import pathlib
import platform
import subprocess
import sys

'''
How to use
To build run `python main.py build` or `python main.py -b` in order to build jar file, 
then run program without args to run experiments

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


# DEF ------------------------------------------------------------------------ #
def seed_pollution_database() -> None:
    run_jar(["-sp"])


def seed_linguistic_database() -> None:
    run_jar(["-sl"])


def run_experiments() -> None:
    run_jar([ALMOST_NONE, HIGH_MAXIMUM_CO_CONCENTRATION])
    run_jar([ALMOST_NONE, HIGH_MAXIMUM_CO_CONCENTRATION, CORRECT_CO_AQI_VALUE])
    run_jar([ALMOST_NONE, HIGH_MAXIMUM_CO_CONCENTRATION, CORRECT_CO_AQI_VALUE, BEEN_DONE_IN_AUTUMN])
    pass


# MAIN ----------------------------------------------------------------------- #
def main() -> None:
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

    print_finish()


# UTIL ----------------------------------------------------------------------- #
def run_jar(args: []) -> None:
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
    pass


def remove_files(filenames: []) -> None:
    for it in filenames:
        os.remove(it)


def print_finish() -> None:
    print("------------------------------------------------------------------------")
    print("FINISHED")
    print("------------------------------------------------------------------------")
    pass


if __name__ == "__main__":
    main()
