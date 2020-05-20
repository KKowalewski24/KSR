from datetime import datetime
import glob
import os

"""
Script merge all .txt files in to_merge directory 

"""

# VAR ------------------------------------------------------------------------ #
TXT = "*.txt"
TO_MERGE = "./to_merge"
LOCATION_FILE_NAMES = glob.glob(os.path.join("%s" % TO_MERGE, TXT))


# DEF ------------------------------------------------------------------------ #
def merge() -> None:
    begin = "\\begin{enumerate}"
    end = "\end{enumerate}"
    result = begin
    for it in LOCATION_FILE_NAMES:
        with open(it, "r") as file:
            result += "\n"
            result += "\item "
            result += file.read()
            result += "\n"
    result += end

    current_time = str(datetime.now().hour) + str(
        datetime.now().minute) + str(datetime.now().second)

    with open("merged_" + current_time + ".txt", "w") as file:
        file.write(result)


# MAIN ----------------------------------------------------------------------- #
def main() -> None:
    merge()
    display_finish()


# UTIL ----------------------------------------------------------------------- #
def display_finish() -> None:
    print("------------------------------------------------------------------------")
    print("FINISHED")
    print("------------------------------------------------------------------------")


if __name__ == "__main__":
    main()
