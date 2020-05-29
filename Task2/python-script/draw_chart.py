import math
import sys
from typing import List

import matplotlib.pyplot as plt
import numpy

"""
Run `python draw_chart.py first_param second_param etc `
first_param - center
second_param - width

"""


# VAR ------------------------------------------------------------------------ #
class ChartData:

    def __init__(self, center: float, width: float) -> None:
        self.center = center
        self.width = width


# DEF ------------------------------------------------------------------------ #
def calculate_gaussian(value: float, center: float, width: float) -> float:
    return math.exp(-(center - value) * (center - value) / (2 * width * width))


def draw_multiple() -> None:
    if len(sys.argv) >= 3 and len(sys.argv) % 2 == 1:
        axis_x = numpy.linspace(0, 1, 200)
        points: List[ChartData] = []

        for i in range(2, len(sys.argv)):
            points.append(ChartData(float(sys.argv[i - 1]), float(sys.argv[i])))

        for it in points:
            plt.plot(axis_x, [calculate_gaussian(jt, it.center, it.width) for jt in axis_x])
        plt.show()
        pass


# MAIN ----------------------------------------------------------------------- #
def main() -> None:
    draw_multiple()
    display_finish()


# UTIL ----------------------------------------------------------------------- #
def display_finish() -> None:
    print("------------------------------------------------------------------------")
    print("FINISHED")
    print("------------------------------------------------------------------------")


if __name__ == "__main__":
    main()
