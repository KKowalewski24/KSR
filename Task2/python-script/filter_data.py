import pandas

# Change path for initial file to correct one
INITIAL_FILE_PATH = "initial_pollution_data.csv"
FILTERED_FILE_PATH = "../src/main/resources/filtered_pollution_data.csv"


def main():
    data = pandas.read_csv(INITIAL_FILE_PATH)
    data.dropna(how="any", inplace=True)
    data.drop([data.columns[0], data.columns[1], data.columns[2], data.columns[3], 
        data.columns[9], data.columns[14], data.columns[19], data.columns[24]], axis=1, inplace=True)
    data.to_csv(FILTERED_FILE_PATH, index=False, header=False)
    print(data)


if __name__ == '__main__':
    main()
