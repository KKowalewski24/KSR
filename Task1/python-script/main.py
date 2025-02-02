import os
import pathlib
import platform
import subprocess
import sys

'''
How to use
To build run `python main.py build` or `python main.py -b` in order to build jar file, 
then run program without args to run experiments

 "Required parameters:  \n" +
                        "\t<percentage of training set (integer 1-99)>\n" +
                        "\t<k for kNN (integer >0)>\n" +
                        "\t<number of keywords (integer >0)>\n" +
                        "\t<numerical metric [eucl|manh|cheb]>\n" +
                        "\t<text metric [trigram|tfm]>"

Sample call of the function 

subprocess.call(
        ["java", "-jar", JAR_NAME, training_set, knn, keywords, metric, text_metric]
    )
    
call_jar_with_five_args("", "", "", "", "")
    
'''

JAR_NAME = "task1-0.0.1-jar-with-dependencies.jar"


# ----------------------------------------------------------------------------- #
def build_jar():
    script_directory = pathlib.Path(os.getcwd())
    os.chdir(script_directory.parent)
    if platform.system().lower() == "windows":
        subprocess.call("mvnw.cmd clean package", shell=True)
        subprocess.call("copy target\\" + JAR_NAME + " " + str(script_directory), shell=True)
    elif platform.system().lower() == "linux":
        subprocess.call("./mvnw clean package", shell=True)
        subprocess.call("copy target/" + JAR_NAME + " " + str(script_directory), shell=True)


def call_jar_with_five_args(training_set, knn, keywords, metric, text_metric):
    subprocess.call(
        ["java", "-jar", JAR_NAME, training_set, knn, keywords, metric, text_metric]
    )


# ----------------------------------------------------------------------------- #
def series(training_set, k, number_of_keywords):
    call_jar_with_five_args(training_set, k, number_of_keywords, "eucl", "tfm")
    call_jar_with_five_args(training_set, k, number_of_keywords, "manh", "tfm")
    call_jar_with_five_args(training_set, k, number_of_keywords, "cheb", "tfm")
    call_jar_with_five_args(training_set, k, number_of_keywords, "eucl", "trigram")
    call_jar_with_five_args(training_set, k, number_of_keywords, "manh", "trigram")
    call_jar_with_five_args(training_set, k, number_of_keywords, "cheb", "trigram")


def first_round():
    series("40", "3", "1000")
    series("40", "5", "1000")
    series("40", "9", "1000")
    series("40", "19", "1000")
    series("40", "51", "1000")

    series("40", "3", "10000")
    series("40", "5", "10000")
    series("40", "9", "10000")
    series("40", "19", "10000")
    series("40", "51", "10000")


# ----------------------------------------------------------------------------- #
def series_1(k_number):
    call_jar_with_five_args("40", k_number, "1000", "eucl", "tfm")


def series_2(k_number):
    call_jar_with_five_args("40", k_number, "1000", "manh", "tfm")


def series_3(k_number):
    call_jar_with_five_args("40", k_number, "10000", "eucl", "tfm")


def second_round():
    series_1("1")
    series_1("3")
    series_1("5")
    series_1("7")
    series_1("9")
    series_1("11")
    series_1("13")
    series_1("15")
    series_1("17")
    series_1("19")

    series_2("1")
    series_2("3")
    series_2("5")
    series_2("7")
    series_2("9")
    series_2("11")
    series_2("13")
    series_2("15")
    series_2("17")
    series_2("19")

    series_3("1")
    series_3("3")
    series_3("5")
    series_3("7")
    series_3("9")
    series_3("11")
    series_3("13")
    series_3("15")
    series_3("17")
    series_3("19")


# ----------------------------------------------------------------------------- #
def series_4(training_set):
    call_jar_with_five_args(training_set, "9", "10000", "eucl", "tfm")


def third_round():
    series_4("30")
    series_4("40")
    series_4("50")
    series_4("60")
    series_4("70")


# ----------------------------------------------------------------------------- #
def main():
    if len(sys.argv) > 1 and (sys.argv[1] == "build" or sys.argv[1] == "-b"):
        build_jar()
    else:
        # Wstepne okreslenie dla jakich wartosci warto przeprowadzac dalsze badania
        # first_round()

        # Wpływ użytej wartość liczby k-najbliższych sąsiadów na skuteczość
        # second_round()

        # Wpływ podziału zbioru na treningowy i testowy na skuteczość
        # third_round()

        # Wpływ użytej metryki i miary na skuteczość
        # series("60", "3", "10000")
        pass

    print("------------------------------------------------------------------------")
    print("FINISHED SUCCESSFULLY")
    print("------------------------------------------------------------------------")


if __name__ == "__main__":
    main()
