# KSR

## Task 1
`mvn clean package` \
`java -jar <jar-with-dependencies> param1 param2 param3 param4 param5`

##### Call parameters 
* <percentage of training set (integer 1-99)> 
* <k for kNN (integer >0)> 
* <number of keywords (integer >0)>
* <numerical metric [eucl|manh|cheb]>
* <text metric [trigram|tfm]>

##### Extra params to remove selected feature:
* "1",
* "2,0-50", "2,50-100",
* "3,0-50,tfidf", 
* "3,50-100,tfidf", 
* "3,0-50,tfcitfoc", 
* "3,50-100,tfcitfoc",
* "4,0-50,tfidf", 
* "4,50-100,tfidf", 
* "4,0-50,tfcitfoc", 
* "4,50-100,tfcitfoc",
* "5,0-50,tfidf", 
* "5,50-100,tfidf", 
* "5,0-50,tfcitfoc", 
* "5,50-100,tfcitfoc",
* "6,0-50,tfidf", 
* "6,50-100,tfidf", 
* "6,0-50,tfcitfoc", 
* "6,50-100,tfcitfoc",

#### Script
Uncomment selected function in main function in `main_1.py` and run by `python main_1.py`\
The rest of information is stored in script

## Task 2

###### [Dataset](https://www.kaggle.com/sogun3/uspollution) 
