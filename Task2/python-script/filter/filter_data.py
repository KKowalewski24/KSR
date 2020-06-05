import sys
import matplotlib.pyplot as plt
import pandas

# VAR ------------------------------------------------------------------------ #
FILTERED_FILE_PATH = "../src/main/resources/filtered_pollution_data.csv"


# DEF ------------------------------------------------------------------------ #
def print_statistics():
    data = pandas.read_csv(FILTERED_FILE_PATH)
    data.info()
    data.hist()
    plt.show()
    print(data.min())
    print("\n\n")
    print(data.max())


def filter_and_remove(initial_file_path):
    data = pandas.read_csv(initial_file_path)
    data.dropna(how="any", inplace=True)
    data.drop([data.columns[0], data.columns[1], data.columns[2], data.columns[3],
               data.columns[9], data.columns[14], data.columns[19], data.columns[24]],
              axis=1, inplace=True)
    data.to_csv(FILTERED_FILE_PATH, index=False, header=False)
    print(data)


# MAIN ----------------------------------------------------------------------- #
def main() -> None:
    if len(sys.argv) == 2:
        if sys.argv[1] == "stat":
            print_statistics()
        else:
            filter_and_remove(sys.argv[1])
    print_finish()


# UTIL ----------------------------------------------------------------------- #
def print_finish() -> None:
    print("------------------------------------------------------------------------")
    print("FINISHED")
    print("------------------------------------------------------------------------")


if __name__ == "__main__":
    print_statistics()
