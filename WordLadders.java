import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

public class WordLadders {
    private int nQueries, nWords;
    private Set<String> words;
    private Map<String, ArrayList<String>> possiblePaths;
    private Map<Integer, ArrayList<String>> queries;

    /**
     * Method readInput reads an .in file and put the data into multiple collections
     * later used in algorithm.
     * 
     * The first row of the input consists two integers N,Q, the number of words we
     * consider and the number of queries.
     * Then follows N lines containing one five-letter word each. After this Q lines
     * follow containing two space-separated five-letter words each,
     * which are the queries.
     * 
     * @param output Decideds if extra information should be printed out or not in
     *               the terminal.
     */
    private void readInput(boolean output) {
        Scanner scanner = new Scanner(System.in);
        nWords = scanner.nextInt();
        nQueries = scanner.nextInt();
        scanner.nextLine();
        words = new HashSet<>();
        possiblePaths = new HashMap<>();
        queries = new HashMap<>();

        if (output) {
            System.out.println("Number of words: " + nWords);
            System.out.println("Number of queries: " + nQueries + "\n");
            System.out.println("Words and queries on following lines:");
        }

        for (int i = 0; i < nWords; i++) {
            String word = scanner.next();
            words.add(word);
        }

        for (String word : words) {
            ArrayList<String> possible = possiblePaths(word);
            possiblePaths.put(word, possiblePaths(word));
            if (output) {
                System.out.println(word);
            }
        }

        scanner.nextLine();
        for (int j = 1; j < nQueries + 1; j++) {
            String in = scanner.nextLine();
            String[] input = in.split(" ");
            ArrayList<String> query = new ArrayList<>();
            query.add(input[0]);
            query.add(input[1]);
            queries.put(j, query);
            if (output) {
                System.out.println(queries.get(j).get(0) + " to " + queries.get(j).get(1));
            }
        }
        scanner.close();
    }

    /**
     * This is a simple method calling the actual BTS algorithm method for every
     * query received from the
     * read method. After getting the results the method decides what to print.
     * 
     * @param output boolean to decide whether to print extra output information or
     *               not.
     */
    private void queries(boolean output) {
        for (int i = 1; i < queries.size() + 1; i++) {
            ArrayList<String> query = queries.get(i);
            int pathlength = shortestPath(query.get(0), query.get(1));
            String s = query.get(0) + " --> " + query.get(1) + ": ";
            if (output) {
                if (pathlength == -1) {
                    s += "Impossible";
                } else {
                    s += pathlength;
                }
            } else {
                if (pathlength == -1) {
                    s = "Impossible";
                } else {
                    s = String.valueOf(pathlength);
                }
            }
            System.out.println(s);
        }
    }

    /**
     * shortestPath method is the method describing Breadth-first search algorithm,
     * going through
     * each word connected to current word and then increasing the path length until
     * it finds the endword
     * or not.
     * 
     * @param startWord a String representing the start of the query.
     * @param endWord   a String representing the end of the query.
     * @return return a Integer which is the shortest path from start to end.
     */
    private int shortestPath(String startWord, String endWord) {
        LinkedList<String> queue = new LinkedList<>();
        Set<String> visitedWords = new HashSet<String>();
        queue.add(startWord);
        visitedWords.add(startWord);
        int pathLength = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                String currentWord = queue.remove();
                if (currentWord.equals(endWord)) {
                    return pathLength;
                } else {
                    for (String path : possiblePaths.get(currentWord)) {
                        if (visitedWords.add(path)) {
                            queue.addLast(path);
                        }
                    }
                }
            }
            pathLength += 1;
        }
        return -1;
    }

    /**
     * The possiblePaths method is a method to fill a data collection, more
     * specifically a map, with all the possible paths
     * you can go from a certain word.
     * 
     * @param currentWord a String representing the word to consider when finding
     *                    paths.
     * @return a List containing all the Strings meeting the condition.
     */
    private ArrayList<String> possiblePaths(String currentWord) {
        ArrayList<String> paths = new ArrayList<>();
        String[] letters = currentWord.substring(currentWord.length() - 4, currentWord.length()).split("");
        Map<String, Integer> letterCount = new HashMap<>();

        for (String letter : letters) {
            letterCount.put(letter, letterCount.getOrDefault(letter, 0) + 1);
        }

        for (String path : words) {
            Map<String, Integer> pathLetters = new HashMap<>();
            for (String letter : path.split("")) {
                pathLetters.put(letter, pathLetters.getOrDefault(letter, 0) + 1);
            }

            boolean containsLetters = true;
            for (String letter : letters) {
                if (pathLetters.getOrDefault(letter, 0) < letterCount.get(letter)) {
                    containsLetters = false;
                    break;
                }
            }
            if (containsLetters) {
                paths.add(path);
            }
        }
        paths.remove(currentWord);
        return paths;
    }

    public static void main(String[] args) {
        WordLadders program = new WordLadders();
        boolean output = false;
        for (String arg : args) {
            if (arg.equals("output")) {
                output = true;
            }
        }
        program.readInput(output);
        program.queries(output);
    }
}