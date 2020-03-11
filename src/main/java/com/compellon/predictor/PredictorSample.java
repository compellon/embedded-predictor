package com.compellon.predictor;

import com.compellon.predictor.BinaryPrediction;
import com.compellon.predictor.NumericPrediction;
import com.compellon.predictor.PredictionRunner;
import com.compellon.predictor.api.PredictionApi;

import scala.reflect.ClassTag$;

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
   *             args[2] is the name of the uniqueColumn specified by the user which is to be used by the predictor as the unique-id column
   */
  public static void main(String[] args) throws java.io.IOException {
    String predictorPath = args[0];
    String fileName = args[1];
    String uniqueColumn = args.length > 2 ? args[2] : "";

    // Read in the prediction dataset and convert the data rows to a 2d list of strings
    // The header (1st) row will be saved separately
    Scanner scanner = new Scanner(new File(fileName));
    scanner.useDelimiter("\n");

    String[] headerSplits = scanner.next().split(",");
    List<String> header = Arrays.asList(headerSplits);

    List<List<String>> dataRows = new ArrayList();
    String row;
    List<String> rowSplits;
    while (scanner.hasNext()) {
      row = scanner.next();
      rowSplits = Arrays.asList(row.split(","));
      dataRows.add(rowSplits);
    }
    scanner.close();

    /** Change this from .Binary to .Numeric when using a numeric predictor */
    PredictionRunner runner = new PredictionRunner(predictorPath, PredictionRunner.PredictionType.Binary);

    /** An instance of PredictionVisitor, used to pattern match on prediction type and print the responses on StdOut */
    PredictionRunner.PredictionVisitor<Void> visitor = new PredictionRunner.PredictionVisitor<Void>() {
      @Override
      public Void caseBinary(PredictionApi.Out<BinaryPrediction> prediction) {
        String[] originalRow = ((Identifier.RowID) prediction.id().get()).value().get();
        Contribution[] contributions = (Contribution[]) prediction.prediction().contributors()
          .toArray(ClassTag$.MODULE$.apply(Contribution.class));
        int response           = prediction.prediction().response();
        double confidence      = (double) prediction.prediction().confidence().get();
        boolean outOfRange     = prediction.prediction().outOfRange();
        boolean newCombination = prediction.prediction().newCombination();

        System.out.println("row = " + Arrays.toString(originalRow));
        System.out.println("response = " + response);
        System.out.println("confidence = " + confidence);
        System.out.println("contributors = " + Arrays.toString(contributions));
        System.out.println("outOfRange  = " + outOfRange);
        System.out.println("newCombination = " + newCombination);

        return null;
      }
      @Override
      public Void caseNumeric(PredictionApi.Out<NumericPrediction> prediction) {
        System.out.println(prediction);
        return null;
      }
    };

    if (uniqueColumn != "") {
      runner.predict(dataRows, header, uniqueColumn, visitor);
    } else {
      runner.predict(dataRows, header, PredictionRunner.IdFormat.RowIsId, visitor);
    }
  }
}
