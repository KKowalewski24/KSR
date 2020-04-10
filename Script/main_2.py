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


# ----------------------------------------------------------------------------- #
def build_jar():
    script_directory = pathlib.Path(os.getcwd())
    os.chdir(script_directory.parent)
    os.chdir("Task2")
    if platform.system().lower() == "windows":
        subprocess.call("mvnw.cmd clean package", shell=True)
        subprocess.call("copy target\\" + JAR_NAME + " " + str(script_directory), shell=True)
    elif platform.system().lower() == "linux":
        subprocess.call("./mvnw clean package", shell=True)
        subprocess.call("copy target/" + JAR_NAME + " " + str(script_directory), shell=True)


def run_jar(args):
    command = ["java", "-jar", JAR_NAME]
    for it in args:
        command.append(it)

    subprocess.call(command)


# ----------------------------------------------------------------------------- #
def main():
    if len(sys.argv) > 1 and (sys.argv[1] == "build" or sys.argv[1] == "-b"):
        build_jar()
    else:
        run_jar([])
        pass

    print("------------------------------------------------------------------------")
    print("FINISHED SUCCESSFULLY")
    print("------------------------------------------------------------------------")


if __name__ == "__main__":
    main()
