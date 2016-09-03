
## Prerequisites

Download and Install Maven

Add rddlike_2.11-4.39.14-SNAPSHOT.jar jar and predictor-v4-project_2.11-4.39.14-SNAPSHOT.jar 
to the *local* maven repository by running the following commands in the *predictorJarSample* directory:

```
mvn install:install-file -Dfile=rddlike_2.11-4.39.14-SNAPSHOT.jar \
 -DgroupId=com.compellon.predictor \
 -DartifactId=rddlike_2.11 \
 -Dversion=4.39.14-SNAPSHOT \
 -Dpackaging=jar
```

```
mvn install:install-file -Dfile=predictor-v4-project_2.11-4.39.14-SNAPSHOT.jar \
 -DgroupId=com.compellon.predictor \
 -DartifactId=predictor-v4-project_2.11 \
 -Dversion=4.39.14-SNAPSHOT \
 -Dpackaging=jar
```


The following dependencies have been added to the example pom file (pom.xml).
For your projects, add these dependencies to the pom.xml file.
```
        <dependency>
            <groupId>com.compellon.predictor</groupId>
            <artifactId>predictor-v4-project_2.11</artifactId>
            <version>4.39.14-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>com.compellon.predictor</groupId>
            <artifactId>rddlike_2.11</artifactId>
            <version>4.39.14-SNAPSHOT</version>
        </dependency>
```

## Running the provided example predictor jar and input data set.

From the command line
```
mvn compile
mvn exec:java -Dexec.mainClass="com.compellon.predictor.PredictorSample" -Dexec.args="arg1 arg2 arg3"

```
where arg1 is a path to the predictor-resources jar, arg2 is a path to the dataset to predict, and arg3 is the list of column names