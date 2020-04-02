# KSR

#### [Google Drive Storage](https://drive.google.com/folderview?id=1SRepZ-OXN6XKP0zGv4DO-OgFMCxQ-F2l) 

#### How To
* Run `./mvnw package exec:java`
* Build `./mvnw clean install`
* Checkstyle `./mvnw checkstyle:checkstyle`

## Task 1
`mvn clean package` \
`java -jar <jar-with-dependencies> param1 param2 param3`

Call parameters
1. Percentage of training to test ratio
args[0] = 60 then trainingSet.size():= documents.size()*60/100
2. Chosen K for kNN
3. Chosen metric - use abbreviation \
Euclidean - eucl \
Manhattan - manh \
Chebyshev - cheb 
      
## Task 2

# Automatization
##### How to use
Copy generated jar with dependencies to directory 'Automatization', set jar file name,
optionally add parameters and run in cmd by typing python main.py

##### Sample call of the function 
subprocess.call(
        ["java", "-jar", JAR_NAME, "features", "60", "5", "5", "eucl"], shell=True
    )
    