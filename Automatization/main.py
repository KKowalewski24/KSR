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


def call_jar_with_five_args(training_set, knn, keywords, metric, text_metric):
    subprocess.call(
        ["java", "-jar", JAR_NAME, training_set, knn, keywords, metric, text_metric]
    )


def main():
    for i in range(3):
        print("Iteration number(counted from 1): " + str(i + 1))
        call_jar_with_five_args("", "", "", "", "")

    print("==========================================================================")
    print("END OF PROCESS")
    print("==========================================================================")


if __name__ == "__main__":
    main()
