import subprocess
import sys
from typing import List

# VAR ------------------------------------------------------------------------ #
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
def run_script(filename: str, axis_x: List, axis_y: List) -> None:
    args: List = [filename]

    if len(axis_x) == len(axis_y):
        for i in range(len(axis_x)):
            args.append(str(axis_x[i]))
            args.append(str(axis_y[i]))

        print(args)
        subprocess.call(["python", "generate_function.py"] + args)


# MAIN ----------------------------------------------------------------------- #
def main() -> None:
    # Measurement Season
    run_script(SEASON_IS_SPRING, [32, 92, 122, 183], [0, 1, 1, 0])
    run_script(SEASON_IS_SUMMER, [122, 183, 214, 275], [0, 1, 1, 0])
    run_script(SEASON_IS_AUTUMN, [214, 275, 306, 336], [0, 1, 1, 0])
    run_script(SEASON_IS_WINTER, [275, 336, 32, 92], [0, 1, 1, 0])

    # Max Hour Concentration
    run_script(MAXIMUM_SO_2_CONCENTRATION_IS_IN_THE_MORNING, [3, 6, 10, 13], [0, 1, 1, 0])
    run_script(MAXIMUM_SO_2_CONCENTRATION_IS_IN_THE_AFTERNOON, [12, 13, 17, 19], [0, 1, 1, 0])
    run_script(MAXIMUM_SO_2_CONCENTRATION_IS_IN_THE_EVENING, [18, 20, 21, 22], [0, 1, 1, 0])
    run_script(MAXIMUM_SO_2_CONCENTRATION_IS_IN_THE_NIGHT, [21, 23, 4, 5], [0, 1, 1, 0])

    # TODO FIX ERROR WITH DIVIDING BY ZERO
    # Mean Concentration SO2
    # run_script(MEAN_SO_2_CONCENTRATION_IS_LOW, [0, 0, 75, 300], [0, 1, 1, 0])
    run_script(MEAN_SO_2_CONCENTRATION_IS_MIDDLE, [150, 450, 600, 800], [0, 1, 1, 0])
    # run_script(MEAN_SO_2_CONCENTRATION_IS_HIGH, [600, 1000, 1000, 1000], [0, 1, 1, 0])

    # # Max Concentration SO2
    # run_script(MAX_SO_2_CONCENTRATION_IS_LOW, [0, 0, 75, 300], [0, 1, 1, 0])
    run_script(MAX_SO_2_CONCENTRATION_IS_MIDDLE, [150, 450, 600, 800], [0, 1, 1, 0])
    # run_script(MAX_SO_2_CONCENTRATION_IS_HIGH, [600, 1000, 1000, 1000], [0, 1, 1, 0])

    # # AQI Value SO2
    run_script(SO_2_AQI_VALUE_IS_CORRECT, [0, 25, 50, 75], [0, 1, 1, 0])
    run_script(SO_2_AQI_VALUE_IS_UNHEALTHY, [50, 75, 150, 250], [0, 1, 1, 0])
    run_script(SO_2_AQI_VALUE_IS_HAZARDOUS, [150, 250, 400, 500], [0, 1, 1, 0])

    display_finish()


# UTIL ----------------------------------------------------------------------- #
def display_finish() -> None:
    print("------------------------------------------------------------------------")
    print("FINISHED")
    print("------------------------------------------------------------------------")


def checkstyle() -> None:
    subprocess.call(["mypy", "generate_function_to_file.py"])


if __name__ == "__main__":
    if len(sys.argv) == 2 and sys.argv[1] == "-ct":
        checkstyle()
    else:
        checkstyle()
        main()
