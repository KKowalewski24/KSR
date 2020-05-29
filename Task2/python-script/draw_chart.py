import sys
import numpy
import matplotlib.pyplot as plt
import math

"""
Run `python draw_chart.py first_param second_param`
first_param - center
second_param - width

"""


# VAR ------------------------------------------------------------------------ #

# DEF ------------------------------------------------------------------------ #
def calculate_gaussian(value: float, center: float, width: float) -> float:
    return math.exp(-(center - value) * (center - value) / (2 * width * width))


# MAIN ----------------------------------------------------------------------- #
def main() -> None:
    if len(sys.argv) == 3:
        axis_x = numpy.linspace(0, 1, 200)
        plt.plot(axis_x, [calculate_gaussian(it, float(sys.argv[1]),
                                             float(sys.argv[2])) for it in axis_x])
        plt.show()
    display_finish()


# UTIL ----------------------------------------------------------------------- #
def display_finish() -> None:
    print("------------------------------------------------------------------------")
    print("FINISHED")
    print("------------------------------------------------------------------------")


if __name__ == "__main__":
    main()
