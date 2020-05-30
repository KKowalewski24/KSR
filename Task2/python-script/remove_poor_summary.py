import sys
from typing import List

"""
First param filename, second param is value that below it the summary will be removed 
"""


# VAR ------------------------------------------------------------------------ #

# DEF ------------------------------------------------------------------------ #
def clear_file(filename: str, limit_value: float) -> None:
    with open(filename, "r") as file:
        data: List[str] = file.readlines()

    result: List[str] = []

    for i in range(len(data)):
        index: int = data[i].find("[")
        if index != -1:
            value: float = float(data[i][index + 1:index + 5])
            if value > limit_value:
                result.extend(data[i])

    with open(filename, "w") as file:
        for it in result:
            file.write(it)


# MAIN ----------------------------------------------------------------------- #
def main() -> None:
    if len(sys.argv) == 3:
        clear_file(sys.argv[1], float(sys.argv[2]))
    display_finish()


# UTIL ----------------------------------------------------------------------- #
def display_finish() -> None:
    print("------------------------------------------------------------------------")
    print("FINISHED")
    print("------------------------------------------------------------------------")


if __name__ == "__main__":
    main()
