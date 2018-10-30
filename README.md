
## Prerequisites

Download and Install Maven

Compellon Dependencies: rddlike, predictor-v4-project (please contact Compellon if you do not have these)

Add rddlike and predictor-v4-project .jar dependencies to the *local* maven repository by running the following commands in the same directory as the .jar files:

```
mvn install:install-file -Dfile=rddlike_2.11-4.49.0-SNAPSHOT.jar \
 -DgroupId=com.compellon.predictor \
 -DartifactId=rddlike_2.11 \
 -Dversion=4.49.0-SNAPSHOT \
 -Dpackaging=jar
```

```
mvn install:install-file -Dfile=predictor-v4-project_2.11-4.49.0-SNAPSHOT.jar \
 -DgroupId=com.compellon.predictor \
 -DartifactId=predictor-v4-project_2.11 \
 -Dversion=4.49.0-SNAPSHOT \
 -Dpackaging=jar
```


The following dependencies will have been added to the example pom file (pom.xml). When creating your own projects, don't forget to add these dependencies to the projects pom.xml file.
```
        <dependency>
            <groupId>com.compellon.predictor</groupId>
            <artifactId>predictor-v4-project_2.11</artifactId>
            <version>4.49.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>com.compellon.predictor</groupId>
            <artifactId>rddlike_2.11</artifactId>
            <version>4.49.0-SNAPSHOT</version>
        </dependency>
```

## Running the provided example predictor jar and input data set.

For the example project, use
```
mvn compile
mvn exec:java -Dexec.mainClass="com.compellon.predictor.PredictorSample" -Dexec.args="1473635143909646133175PREDICTOR.jar Demog_DS_TRAIN_15K_id.csv UID,age,workclass,fnlwgt,education,education-num,marital-status,occupation,relationship,race,sex,capital-gain,capital-loss,hours-per-week,native-country"
```

From your own predictor, use this form:
```
mvn compile
mvn exec:java -Dexec.mainClass="com.compellon.predictor.PredictorSample" -Dexec.args="arg1 arg2 arg3"

```
where arg1 is the path to the predictor-resources jar, arg2 is the path to the dataset to predict, and arg3 is the list of column names

