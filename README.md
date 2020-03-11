
## Prerequisites

Download and Install Maven

Compellon Dependencies: rddlike, predictor-v4-project (please contact Compellon to get the versions needed for your predictor)

## To setup the environment for the example predictor:

Add the provided rddlike and predictor-v4-project .jar dependencies to the *local* maven repository by running the following commands in the same directory as the .jar files:

```
# assumes a Compellon engine version 5.117.2-SNAPSHOT was used to create the target
mvn install:install-file -Dfile=rddlike_2.11-5.117.2-SNAPSHOT.jar \
 -DgroupId=com.compellon.predictor \
 -DartifactId=rddlike_2.11 \
 -Dversion=5.117.2-SNAPSHOT \
 -Dpackaging=jar
```

```
# assumes a Compellon engine version 5.117.2-SNAPSHOT was used to create the target
mvn install:install-file -Dfile=predictor-v4-project_2.11-5.117.2-SNAPSHOT.jar \
 -DgroupId=com.compellon.predictor \
 -DartifactId=predictor-v4-project_2.11 \
 -Dversion=5.117.2-SNAPSHOT \
 -Dpackaging=jar
```

The following dependencies are already specified the example pom file (pom.xml). When creating your own projects, don't forget to replace these dependencies with the updated version from Compellon in your projects pom.xml file.
```
        <dependency>
            <groupId>com.compellon.predictor</groupId>
            <artifactId>predictor-v4-project_2.11</artifactId>
            <version>5.117.2-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>com.compellon.predictor</groupId>
            <artifactId>rddlike_2.11</artifactId>
            <version>5.117.2-SNAPSHOT</version>
        </dependency>
```

## To run the provided example predictor jar with an input data set:

```
mvn compile
mvn exec:java -Dexec.mainClass="com.compellon.predictor.PredictorSample" -Dexec.args="1473635143909646133175PREDICTOR.jar Demog_DS_TRAIN_15K_id.csv"
```

## To run your own predictor jar with an input data set:

You must get the predictor-v4-project and rddlike_2.11 jar files that match the engine version used to create the target from Compellon

After your pom.xml file has been updated you can install the updated dependancies by changing the version numbers in the example above.

To run your own predictor and input dataset, use this form:
```
mvn compile
mvn exec:java -Dexec.mainClass="com.compellon.predictor.PredictorSample" -Dexec.args="arg1 arg2 [arg3]"
```
where arg1 is the path to the predictor jar, arg2 is the path to the dataset to predict, and arg3 is optional to designate the name of a column containing unique ID's for each row if it is desired to not have the entired input row included in the results.

## Example curl command for downloading a predictor JAR

Downloading a predictor JAR requires knowing the Target ID and having an authentication token for the account where it was created.  This example uses wget with curl (the -L curl option is required since the request will be re-directed).

```
$ wget $(curl -L -w "%{url_effective}\n" --silent -i -X  \
    POST -o /dev/null -H 'content-type: application/json' -H "authorization: bearer [token]" \
    https://2020.compellon.com/api/target/[targetID]/predictor) \
    -O myPredictorJar.jar
```