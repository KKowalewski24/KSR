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

JAR_NAME = "task2-0.0.1.jar"
TXT = "*.txt"
JAR = "*.jar"

ALMOST_NONE = "Almost None"
SOME = "Some"
ABOUT_HALF = "About Half"
MANY = "Many"
ALMOST_ALL = "Almost All"
SPRING = "Spring"
SUMMER = "Summer"
AUTUMN = "Autumn"
WINTER = "Winter"
MORNING_HOUR = "Morning Hour"
AFTERNOON_HOUR = "Afternoon Hour"
EVENING_HOUR = "Evening Hour"
NIGHT_HOUR = "Night Hour"
LOW_CONCENTRATION = "Low Concentration"
MIDDLE_CONCENTRATION = "Middle Concentration"
HIGH_CONCENTRATION = "High Concentration"


# UTIL ------------------------------------------------------------------------ #
def build_jar() -> None:
    script_directory = pathlib.Path(os.getcwd())
    os.chdir(script_directory.parent)
    if platform.system().lower() == "windows":
        subprocess.call("mvnw.cmd clean package", shell=True)
        subprocess.call("copy target\\" + JAR_NAME + " " + str(script_directory), shell=True)
    elif platform.system().lower() == "linux":
        subprocess.call("./mvnw clean package", shell=True)
        subprocess.call("cp target/" + JAR_NAME + " " + str(script_directory), shell=True)


def remove_files(filenames: []) -> None:
    for it in filenames:
        os.remove(it)


def clean_project_directories(remove_jar: bool) -> None:
    script_directory = pathlib.Path(os.getcwd())
    if remove_jar:
        remove_files(glob.glob(JAR))

    os.chdir(script_directory.parent)
    remove_files(glob.glob(TXT))

    os.chdir(script_directory.parent.parent)
    remove_files(glob.glob(TXT))
    pass


def print_finish() -> None:
    print("------------------------------------------------------------------------")
    print("FINISHED")
    print("------------------------------------------------------------------------")
    pass


def run_jar(args: []) -> None:
    subprocess.call(["java", "-jar", JAR_NAME] + args)


def seed_pollution_database() -> None:
    run_jar(["-sp"])


def seed_linguistic_database() -> None:
    run_jar(["-sl"])


# TASK ------------------------------------------------------------------------ #
def run_experiments() -> None:
    run_jar([])
    pass


# ----------------------------------------------------------------------------- #
def main() -> None:
    if len(sys.argv) == 2 and (sys.argv[1] == "build" or sys.argv[1] == "-b"):
        build_jar()
    elif len(sys.argv) >= 2 and (sys.argv[1] == "clean" or sys.argv[1] == "-c"):
        if len(sys.argv) == 3 and (sys.argv[2] == "jar" or sys.argv[2] == "-j"):
            clean_project_directories(True)
        else:
            clean_project_directories(False)
    elif len(sys.argv) == 2 and (sys.argv[1] == "seed_pollution" or sys.argv[1] == "-sp"):
        seed_pollution_database()
    elif len(sys.argv) == 2 and (sys.argv[1] == "seed_linguistic" or sys.argv[1] == "-sl"):
        seed_linguistic_database()
    elif len(sys.argv) == 2 and (sys.argv[1] == "run" or sys.argv[1] == "-r"):
        run_experiments()

    print_finish()
    pass


if __name__ == "__main__":
    main()
