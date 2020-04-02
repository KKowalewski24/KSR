import subprocess

'''
How to use
Copy generated jar with dependencies to directory 'Automatization', set jar file name,
optionally add parameters and run in cmd by typing python main.py

Sample call of the function 
subprocess.call(
        ["java", "-jar", JAR_NAME, "features", "60", "5", "5", "eucl"], shell=True
    )
    
'''

JAR_NAME = "task1-0.0.1-jar-with-dependencies.jar"


# For ngram
def call_jar_with_four_args(classification, training_set, knn, number_n):
    subprocess.call(
        ["java", "-jar", JAR_NAME, classification, training_set, knn, number_n], shell=True
    )


# For features and tfm
def call_jar_with_five_args(classification, training_set, knn, keywords, metric):
    subprocess.call(
        ["java", "-jar", JAR_NAME, classification, training_set, knn, keywords, metric], shell=True
    )


def main():
    for i in range(3):
        print("Iteration number(counted from 1): " + str(i + 1))
        call_jar_with_five_args("features", "60", "5", "5", "eucl")

    print("==============")
    print("END OF PROCESS")
    print("==============")


if __name__ == "__main__":
    main()
