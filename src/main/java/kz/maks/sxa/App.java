package kz.maks.sxa;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Insufficient arguments supplied");
        }
        String inputOriginFilePath = args[0];
        String inputOtherFilePath = args[1];
        String targetElementId = args.length > 2 ? args[2] : null;

        try {
            SXAnalyzer analyzer = new SXAnalyzer(inputOriginFilePath, inputOtherFilePath, targetElementId);
            ElementMatchResult result = analyzer.analyze();
            if (result != null) {
                System.out.println(result.getPath());

                for (String reason : result.getReasons()) {
                    System.out.println(reason);
                }
                System.out.println("Total match score: " + result.getScore());
            } else {
                System.out.println("NO MATCH");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
