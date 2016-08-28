package com.compellon.predictor;


import com.compellon.predictor.BinaryPrediction;
import com.compellon.predictor.NumericPrediction;
import com.compellon.predictor.PredictionRunner;
import com.compellon.predictor.api.PredictionApi;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/** A java interface to instantiate the Simple Predictor and use its methods to load a predictor and run predictions */

class PredictorSample {
  /**
   * @param args An Array of Strings for command line arguments where
   *             args[0] is the path to the prediction-resources jar file
   *             args[1] is the path to the test data set, this example parses a csv file and converts each row to List<String>
   *             args[2] is the list of Strings corresponding to each field in the data set
   *             args[3] is the name of the uniqueColumn specified by the user which is to be used by the predictor as the unique-id column
   */
  public static void main(String[] args) throws java.io.IOException {
    String predictorPath = args[0];
    String fileNameDefined = args[1];
    String schemaFields = args[2];
    //String uniqueColumn = args[3];

    List<List<String>> testDataSet = new ArrayList();

    Scanner scanner = new Scanner(new File(fileNameDefined));
    scanner.useDelimiter("\n");
    String row;
    List<String> rowSplits;
    scanner.next();
    while (scanner.hasNext()) {
      row = scanner.next();
      rowSplits = Arrays.asList(row.split(","));
      testDataSet.add(rowSplits);
    }


    String[] schemaSplits = schemaFields.split(",");
    List<String> schema = Arrays.asList(schemaSplits);
    scanner.close();

    /** An instance of PredictionRunner, used to load and run Binary and Numeric Predictions */
    PredictionRunner runner = new PredictionRunner(predictorPath, PredictionRunner.PredictionType.Numeric);

    /** An instance of PredictionVisitor, used to pattern match on prediction type and print the responses on StdOut */
    PredictionRunner.PredictionVisitor<Void> visitor = new PredictionRunner.PredictionVisitor<Void>() {
      @Override
      public Void caseBinary(PredictionApi.Out<BinaryPrediction> prediction) {
        System.out.println(prediction);
        return null;
      }
      @Override
      public Void caseNumeric(PredictionApi.Out<NumericPrediction> prediction) {
        System.out.println(prediction);
        return null;
      }
    };

    /** An example usage of predict method with a RowIsId Idformat.
     * In this case, the Prediction responses are tied to the input row. */
    //runner.predict(testDataSet, schema, uniqueColumn, visitor);
    //runner.predict(testDataSet, schema, PredictionRunner.IdFormat.NoId, visitor);
    runner.predict(testDataSet, schema, PredictionRunner.IdFormat.RowIsId, visitor);
  }
}
