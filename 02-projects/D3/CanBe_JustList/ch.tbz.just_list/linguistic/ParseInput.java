package linguistic;

import linguistic.CommandVocabulary;
import org.apache.commons.text.similarity.FuzzyScore;

import java.util.Locale;
import java.util.Scanner;

public class ParseInput {
    public String ParseInput(String demo_input) {
        FuzzyScore fuzzy = new FuzzyScore(Locale.ENGLISH);
        CommandVocabulary vocabulary = new CommandVocabulary();

        StringBuilder corrected_task = new StringBuilder();
        for (String word : demo_input.split("\\s+")) {
            String best_match = word;
            int best_score = -1;
            for (String vocab : vocabulary.getVocabulary()) {
                int score = fuzzy.fuzzyScore(word, vocab);
                if (score > best_score) {
                    best_score = score;
                    best_match = vocab;
                }
            }
            corrected_task.append(best_match).append(" ");
        }

        System.out.println("Did you mean? " + corrected_task.toString().trim());
        System.out.print("Do you want this version? (Y/n): ");
        Scanner sc = new Scanner(System.in);
        String user_input = sc.nextLine();
        return user_input.equalsIgnoreCase("Y") ? corrected_task.toString().trim() : demo_input;
    }

}

