import java.util.Random;
import java.io.*;

public class GenerateFile {

    static int countSentence = 0;

    public void getFiles(String path, int n, int size, int probability, String[] words) {
        for (int i = 0; i < n; i++) {
            try (FileWriter writer = new FileWriter(path + "text" + (i + 1) + ".txt")) {
                String text = makeText(size, probability, words);
                writer.write(text);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            countSentence = 0;
        }
    }

    public String makeWord() {
        int maxLettersInWord = 15;
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String word = "";
        Random random = new Random();
        int randomLen = 1 + random.nextInt(maxLettersInWord - 1);
        for (int i = 0; i < randomLen; i++) {
            char c = alphabet.charAt(random.nextInt(26));
            word += c;
        }
        return word;
    }

    public String makeSentence(int probability, String[] words) {
        int maxWordsInSentence = 15;
        Random random = new Random();
        int randomLen = 1 + random.nextInt(maxWordsInSentence - 1);
        String sentence = "";
        for (int i = 0; i < randomLen; i++) {
            String[] spaceOrComma = new String[]{" ", ","};
            int n = (int) Math.floor(Math.random() * spaceOrComma.length);
            sentence += makeWord() + spaceOrComma[n];
        }

        String[] punctuationMark = new String[]{".", "!", "?"};
        int n = (int) Math.floor(Math.random() * punctuationMark.length);


        if (approveInsert(probability)) {
            sentence = replaceTheWord(sentence, words);
        }
        return sentence.substring(0, 1).toUpperCase() + sentence.substring(1, sentence.length() - 1) + punctuationMark[n];
    }

    public String makeParagraph(int probability, String[] words) {
        int maxSentenceInParagraph = 20;
        Random random = new Random();
        int randomLen = 1 + random.nextInt(maxSentenceInParagraph - 1);
        String paragraphBefore = "";
        int numOfSentence = 0;
        for (numOfSentence = 0; numOfSentence < randomLen; numOfSentence++) {
            paragraphBefore += makeSentence(probability, words) + " ";
            countSentence += 1;
        }
        return paragraphBefore + "\n";
    }

    public String makeText(int size, int probability, String[] words) {
        String text = "";
        while (countSentence < size) {
            text += makeParagraph(probability, words);
        }
        return text;
    }

    public Boolean approveInsert(int probability) {
        Random random = new Random();
        return random.nextInt() < 1 / probability ? true : false;
    }

    public String getWordFromArray(String[] words) {
        int n = (int) Math.floor(Math.random() * words.length);
        return words[n];
    }

    public String replaceTheWord(String sentence, String[] words) {
        String[] wordGenerate = sentence.split(" ");
        int n = (int) Math.floor(Math.random() * wordGenerate.length);
        wordGenerate[n] = getWordFromArray(words);
        StringBuilder builder = new StringBuilder();
        for (String s : wordGenerate) {
            builder.append(s).append(" ");
        }
        return builder.toString();
    }
}
