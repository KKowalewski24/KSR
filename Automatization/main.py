import subprocess

'''
How to use
Copy generated jar with dependencies to directory 'Automatization', set jar file name,
optionally add parameters and run in cmd by typing python main.py

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
def call_jar_with_five_args(training_set, knn, keywords, metric, text_metric):
    subprocess.call(
        ["java", "-jar", JAR_NAME, training_set, knn, keywords, metric, text_metric]
    )

def series(training_set, k, number_of_keywords):
    call_jar_with_five_args(training_set, k, number_of_keywords, "eucl", "tfm")
    call_jar_with_five_args(training_set, k, number_of_keywords, "manh", "tfm")
    call_jar_with_five_args(training_set, k, number_of_keywords, "cheb", "tfm")
    call_jar_with_five_args(training_set, k, number_of_keywords, "eucl", "trigram")
    call_jar_with_five_args(training_set, k, number_of_keywords, "manh", "trigram")
    call_jar_with_five_args(training_set, k, number_of_keywords, "cheb", "trigram")
    

# ----------------------------------------------------------------------------- #
def main():

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

    print("==========================================================================")
    print("END OF PROCESS")
    print("==========================================================================")


if __name__ == "__main__":
    main()
