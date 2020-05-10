import os
import pathlib
import platform
import subprocess
import sys
import glob

'''
How to use
To build run `python main.py build` or `python main.py -b` in order to build jar file, 
then run program without args to run experiments

'''

JAR_NAME = "task2-0.0.1.jar"
TXT = "*.txt"
JAR = "*.jar"


# UTIL ------------------------------------------------------------------------ #
def build_jar() -> None:
    script_directory = pathlib.Path(os.getcwd())
    os.chdir(script_directory.parent)
    if platform.system().lower() == "windows":
        subprocess.call("mvnw.cmd clean package", shell=True)
        subprocess.call("copy target\\" + JAR_NAME + " " + str(script_directory), shell=True)
    elif platform.system().lower() == "linux":
        subprocess.call("./mvnw clean package", shell=True)
        subprocess.call("copy target/" + JAR_NAME + " " + str(script_directory), shell=True)


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


def run_jar(args) -> None:
    subprocess.call(["java", "-jar", JAR_NAME] + args)


# TASK ------------------------------------------------------------------------ #
def run_experiments():
    run_jar([])
    pass


# ----------------------------------------------------------------------------- #
def main():
    if len(sys.argv) == 2 and (sys.argv[1] == "build" or sys.argv[1] == "-b"):
        build_jar()
    elif len(sys.argv) >= 2 and (sys.argv[1] == "clean" or sys.argv[1] == "-c"):
        if len(sys.argv) == 3 and (sys.argv[2] == "jar" or sys.argv[2] == "-j"):
            clean_project_directories(True)
        else:
            clean_project_directories(False)
    elif len(sys.argv) == 2 and (sys.argv[1] == "run" or sys.argv[1] == "-r"):
        run_experiments()
        pass

    print("------------------------------------------------------------------------")
    print("FINISHED SUCCESSFULLY")
    print("------------------------------------------------------------------------")


if __name__ == "__main__":
    main()
