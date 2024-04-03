import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class WordLadders{
    private int nQueries, nWords;
    private String word,start,end;
    private ArrayList<String> words;
    private Map<String,ArrayList<String>> queries;
    private Set<String> keySet;

    private void readFile(boolean output){
        Scanner scanner = new Scanner(System.in);
        nWords = scanner.nextInt();
        nQueries = scanner.nextInt();
        scanner.nextLine();
        words = new ArrayList<>();
        queries = new HashMap<>();

        for(int i=0; i < nWords; i++){
            String word = scanner.next();
            words.add(word);
            }
        scanner.nextLine();
        for(int j=0; j < nQueries; j++){
            String in = scanner.nextLine();
            String[] input = in.split(" ");
            start = input[0];
            end = input[1];
            if(queries.containsKey(start)){
                queries.get(start).add(end);
            }else{
                ArrayList<String> ends = new ArrayList<>();
                ends.add(end);
                queries.put(start, ends);
            }
            
        }
        keySet = queries.keySet();

        if(output){
            System.out.println("Number of words: " + nWords);
            System.out.println("Number of queries: " + nQueries +"\n");
            System.out.println("Words:");
            for (int w = 0; w < words.size(); w++){
                System.out.println(words.get(w) + " ");
            }
            System.out.println("\nQueries:");
            for(String w2 : keySet){
                System.out.println(w2 + " to " + queries.get(w2));
            }
        }
    }


    public int wordAlgorithm(){
        int pathLength;
        //for(String start : keySet){
            pathLength = shortestPath("hello", "putin", words, 0);
            System.out.println("Shortest path: " + pathLength);
        //}

        return 0;
    }

    private int shortestPath(String currentWord, String finalWord, ArrayList<String> paths, int pathLength){
        paths.remove(currentWord);
        if(currentWord.equals(finalWord)){
            return pathLength;
        }else{
            String[] letters = currentWord.substring(currentWord.length()-4, currentWord.length()).split("");
            for(String path : paths){
                System.out.print(path);
                Boolean containsLetters = true;
                for(String letter : letters){
                    if(!path.contains(letter)){
                        containsLetters = false;
                    }
                }
                if(containsLetters){
                    return shortestPath(path, finalWord, paths ,pathLength+1);
                }
            }
            return 10;
        }
    }

    public static void main(String[] args){
        WordLadders program = new WordLadders();
        boolean output = false;
        for(String arg : args){
            if (arg.equals("output")){
                output = true;
            }
        }
        program.readFile(output);
        int hej = program.wordAlgorithm();
    }
}