from datetime import datetime
import glob
import os
import pathlib

"""
Script merge all .txt files in current directory 

"""

# VAR ------------------------------------------------------------------------ #
TXT = "*.txt"
TYPE_BASIC = "basic"
TYPE_ADVANCED = "advanced"
TYPE_MULTI_FIRST = "multiFirst"
TYPE_MULTI_SECOND = "multiSecond"
TYPE_MULTI_THIRD = "multiThird"

LOCATION_FILE_NAMES = glob.glob(os.path.join(pathlib.Path(os.getcwd()), TYPE_MULTI_THIRD + TXT))


# DEF ------------------------------------------------------------------------ #
def merge() -> None:
    begin = "\\begin{enumerate}"
    end = "\n\end{enumerate}"
    result = begin
    for it in LOCATION_FILE_NAMES:
        with open(it, "r") as file:
            result += "\n"
            result += "\item "
            result += file.read()
    result += end

    current_time = datetime.now().strftime("%H%M%S")

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
