import subprocess

JAR_NAME = "task2-0.0.1.jar"


# ----------------------------------------------------------------------------- #
def run_jar():
    subprocess.call(
        ["java", "-jar", JAR_NAME]
    )


# ----------------------------------------------------------------------------- #
def main():
    # run_jar()

    print("------------------------------------------------------------------------")
    print("FINISHED SUCCESSFULLY")
    print("------------------------------------------------------------------------")


if __name__ == "__main__":
    main()
