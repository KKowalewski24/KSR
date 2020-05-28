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

IN_ALMOST_NONE = "Almost none"
IN_SOME = "In some"
IN_ABOUT_HALF_OF_ALL = "In about half of all"
IN_MANY = "In many"
IN_ALL = "In all"

SEASON_IS_SPRING = "season is spring."
SEASON_IS_SUMMER = "season is summer."
SEASON_IS_AUTUMN = "season is autumn."
SEASON_IS_WINTER = "season is winter."

MAXIMUM_CO_CONCENTRATION_IS_IN_THE_MORNING = "maximum CO concentration is in the morning."
MAXIMUM_CO_CONCENTRATION_IS_IN_THE_AFTERNOON = "maximum CO concentration is in the afternoon."
MAXIMUM_CO_CONCENTRATION_IS_IN_THE_EVENING = "maximum CO concentration is in the evening."
MAXIMUM_CO_CONCENTRATION_IS_IN_THE_NIGHT = "maximum CO concentration is in the night."
MAXIMUM_NO_2_CONCENTRATION_IS_IN_THE_MORNING = "maximum NO2 concentration is in the morning."
MAXIMUM_NO_2_CONCENTRATION_IS_IN_THE_AFTERNOON = "maximum NO2 concentration is in the afternoon."
MAXIMUM_NO_2_CONCENTRATION_IS_IN_THE_EVENING = "maximum NO2 concentration is in the evening."
MAXIMUM_NO_2_CONCENTRATION_IS_IN_THE_NIGHT = "maximum NO2 concentration is in the night."
MAXIMUM_O_3_CONCENTRATION_IS_IN_THE_MORNING = "maximum O3 concentration is in the morning."
MAXIMUM_O_3_CONCENTRATION_IS_IN_THE_AFTERNOON = "maximum O3 concentration is in the afternoon."
MAXIMUM_O_3_CONCENTRATION_IS_IN_THE_EVENING = "maximum O3 concentration is in the evening."
MAXIMUM_O_3_CONCENTRATION_IS_IN_THE_NIGHT = "maximum O3 concentration is in the night."
MAXIMUM_SO_2_CONCENTRATION_IS_IN_THE_MORNING = "maximum SO2 concentration is in the morning."
MAXIMUM_SO_2_CONCENTRATION_IS_IN_THE_AFTERNOON = "maximum SO2 concentration is in the afternoon."
MAXIMUM_SO_2_CONCENTRATION_IS_IN_THE_EVENING = "maximum SO2 concentration is in the evening."
MAXIMUM_SO_2_CONCENTRATION_IS_IN_THE_NIGHT = "maximum SO2 concentration is in the night."

MEAN_CO_CONCENTRATION_IS_LOW = "mean CO concentration is low."
MEAN_CO_CONCENTRATION_IS_MIDDLE = "mean CO concentration is middle."
MEAN_CO_CONCENTRATION_IS_HIGH = "mean CO concentration is high."
MEAN_NO_2_CONCENTRATION_IS_LOW = "mean NO2 concentration is low."
MEAN_NO_2_CONCENTRATION_IS_MIDDLE = "mean NO2 concentration is middle."
MEAN_NO_2_CONCENTRATION_IS_HIGH = "mean NO2 concentration is high."
MEAN_O_3_CONCENTRATION_IS_LOW = "mean O3 concentration is low."
MEAN_O_3_CONCENTRATION_IS_MIDDLE = "mean O3 concentration is middle."
MEAN_O_3_CONCENTRATION_IS_HIGH = "mean O3 concentration is high."
MEAN_SO_2_CONCENTRATION_IS_LOW = "mean SO2 concentration is low."
MEAN_SO_2_CONCENTRATION_IS_MIDDLE = "mean SO2 concentration is middle."
MEAN_SO_2_CONCENTRATION_IS_HIGH = "mean SO2 concentration is high."

MAX_CO_CONCENTRATION_IS_LOW = "max CO concentration is low."
MAX_CO_CONCENTRATION_IS_MIDDLE = "max CO concentration is middle."
MAX_CO_CONCENTRATION_IS_HIGH = "max CO concentration is high."
MAX_NO_2_CONCENTRATION_IS_LOW = "max NO2 concentration is low."
MAX_NO_2_CONCENTRATION_IS_MIDDLE = "max NO2 concentration is middle."
MAX_NO_2_CONCENTRATION_IS_HIGH = "max NO2 concentration is high."
MAX_O_3_CONCENTRATION_IS_LOW = "max O3 concentration is low."
MAX_O_3_CONCENTRATION_IS_MIDDLE = "max O3 concentration is middle."
MAX_O_3_CONCENTRATION_IS_HIGH = "max O3 concentration is high."
MAX_SO_2_CONCENTRATION_IS_LOW = "max SO2 concentration is low."
MAX_SO_2_CONCENTRATION_IS_MIDDLE = "max SO2 concentration is middle."
MAX_SO_2_CONCENTRATION_IS_HIGH = "max SO2 concentration is high."

CO_AQI_VALUE_IS_CORRECT = "CO AQI value is correct."
CO_AQI_VALUE_IS_UNHEALTHY = "CO AQI value is unhealthy."
CO_AQI_VALUE_IS_HAZARDOUS = "CO AQI value is hazardous."
NO_2_AQI_VALUE_IS_CORRECT = "NO2 AQI value is correct."
NO_2_AQI_VALUE_IS_UNHEALTHY = "NO2 AQI value is unhealthy."
NO_2_AQI_VALUE_IS_HAZARDOUS = "NO2 AQI value is hazardous."
O_3_AQI_VALUE_IS_CORRECT = "O3 AQI value is correct."
O_3_AQI_VALUE_IS_UNHEALTHY = "O3 AQI value is unhealthy."
O_3_AQI_VALUE_IS_HAZARDOUS = "O3 AQI value is hazardous."
SO_2_AQI_VALUE_IS_CORRECT = "SO2 AQI value is correct."
SO_2_AQI_VALUE_IS_UNHEALTHY = "SO2 AQI value is unhealthy."
SO_2_AQI_VALUE_IS_HAZARDOUS = "SO2 AQI value is hazardous."


# DEF ------------------------------------------------------------------------ #
def seed_pollution_database() -> None:
    run_jar(["-sp"])


def seed_linguistic_database() -> None:
    run_jar(["-sl"])


def run_experiments() -> None:
    run_jar([IN_ALMOST_NONE, MAX_CO_CONCENTRATION_IS_HIGH])
    run_jar([IN_ALMOST_NONE, MAX_CO_CONCENTRATION_IS_HIGH, CO_AQI_VALUE_IS_CORRECT])
    run_jar([IN_ALMOST_NONE, MAX_CO_CONCENTRATION_IS_HIGH,
             CO_AQI_VALUE_IS_CORRECT, MAX_CO_CONCENTRATION_IS_LOW])


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
