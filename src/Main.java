public class Main {
    public static void main(String[] args) {
        String[] arrayOfWords = {"IN", "THIS", "TEXT", "THERE", "IS", "NO", "SENSE", "IT", "IS", "JUST", "GENERATED", "NONSENSE"};
        GenerateFile newGen = new GenerateFile();
        newGen.getFiles("/Users/maksim/IdeaProjects/generateFile/src/", 5, 500, 2, arrayOfWords);
    }
}
