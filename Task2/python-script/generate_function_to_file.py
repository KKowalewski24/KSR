import subprocess
import sys
from typing import List

# VAR ------------------------------------------------------------------------ #
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
    run_script(BEEN_DONE_IN_SPRING,
               [32, 92, 122, 183], [0, 1, 1, 0])
    run_script(BEEN_DONE_IN_SUMMER,
               [122, 183, 214, 275], [0, 1, 1, 0])
    run_script(BEEN_DONE_IN_AUTUMN,
               [214, 275, 306, 336], [0, 1, 1, 0])
    run_script(BEEN_DONE_IN_WINTER,
               [0, 32, 92, 275, 365], [1, 1, 0, 0, 1])

    # Max Hour Concentration
    run_script(MAXIMUM_SO_2_CONCENTRATION_IN_THE_MORNING,
               [3, 6, 10, 13], [0, 1, 1, 0])
    run_script(MAXIMUM_SO_2_CONCENTRATION_IN_THE_AFTERNOON,
               [12, 13, 17, 19], [0, 1, 1, 0])
    run_script(MAXIMUM_SO_2_CONCENTRATION_IN_THE_EVENING,
               [18, 20, 21, 22], [0, 1, 1, 0])
    run_script(MAXIMUM_SO_2_CONCENTRATION_IN_THE_NIGHT,
               [0, 4, 5, 21, 23], [1, 1, 0, 0, 1])

    # Mean Concentration SO2
    run_script(LOW_MEAN_SO_2_CONCENTRATION,
               [0, 75, 300], [1, 1, 0])
    run_script(MIDDLE_MEAN_SO_2_CONCENTRATION,
               [150, 450, 600, 800], [0, 1, 1, 0])
    run_script(HIGH_MEAN_SO_2_CONCENTRATION,
               [600, 1000], [0, 1])

    # # Max Concentration SO2
    run_script(LOW_MAXIMUM_SO_2_CONCENTRATION,
               [0, 75, 300], [1, 1, 0])
    run_script(MIDDLE_MAXIMUM_SO_2_CONCENTRATION,
               [150, 450, 600, 800], [0, 1, 1, 0])
    run_script(HIGH_MAXIMUM_SO_2_CONCENTRATION,
               [600, 950, 1000], [0, 1, 1])

    # # AQI Value SO2
    run_script(CORRECT_SO_2_AQI_VALUE,
               [0, 50, 75], [1, 1, 0])
    run_script(UNHEALTHY_SO_2_AQI_VALUE,
               [50, 75, 150, 250], [0, 1, 1, 0])
    run_script(HAZARDOUS_SO_2_AQI_VALUE,
               [150, 250, 500], [0, 1, 1])

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
