import sys
import matplotlib.pyplot as plt


# VAR ------------------------------------------------------------------------ #

# DEF ------------------------------------------------------------------------ #

# def generate_chart_three(first_x: [], first_y: [],
#                          second_x: [], second_y: [],
#                          third_x: [], third_y: [],
#                          filename: str):
#     plt.plot(first_x, first_y)
#     plt.plot(second_x, second_y)
#     plt.plot(third_x, third_y)
#     plt.savefig(filename)
#
#
# def generate_chart_four(first_x: [], first_y: [],
#                         second_x: [], second_y: [],
#                         third_x: [], third_y: [],
#                         fourth_x: [], fourth_y: [],
#                         filename: str):
#     plt.plot(first_x, first_y)
#     plt.plot(second_x, second_y)
#     plt.plot(third_x, third_y)
#     plt.plot(fourth_x, fourth_y)
#     plt.savefig(filename)

class PlotUnit:

    def __init__(self, x: [], y: []):
        self.x = x
        self.y = y


def generate(array: [], filename: str):
    for it in array:
        plt.plot(it.x, it.y)
    plt.savefig(filename)


# MAIN ----------------------------------------------------------------------- #
def main() -> None:
    generate(
        [PlotUnit([2, 4],
                  [1, 0]),
         PlotUnit([2, 4, 5, 7],
                  [0, 1, 1, 0]),
         PlotUnit([5, 7, 8, 10],
                  [0, 1, 1, 0]),
         PlotUnit([8, 10, 11, 12],
                  [0, 1, 1, 0]),
         PlotUnit([10, 12],
                  [0, 1])],
        "measurementSeason"
    )

    generate(
        [PlotUnit([0, 0, 75, 300],
                  [1, 1, 1, 0]),
         PlotUnit([150, 450, 600, 800],
                  [0, 1, 1, 0]),
         PlotUnit([600, 900, 1000, 1000],
                  [0, 1, 1, 1])],
        "cde"
    )

    print_finish()


# UTIL ----------------------------------------------------------------------- #
def print_finish() -> None:
    print("------------------------------------------------------------------------")
    print("FINISHED")
    print("------------------------------------------------------------------------")


if __name__ == "__main__":
    main()
