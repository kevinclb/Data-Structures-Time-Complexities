import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// write your code here

        //part 1
        //time variables for keeping track of time
        long time1, time2;
        int count = 0;

        //input files of words that start with q and scrabble point values
        //declaring the q word tree and q word maps i'm
        //going to be inserting the giving q words into
        //also declaring and initializing a points file that holds the scrabble points
        File qwordFile = new File("src/QWords.txt");
        File pointsFile = new File("src/Points.txt");
        //maps of words to their total point value
        TreeMap<String, Integer> qWordTotalTree = new TreeMap<>();
        HashMap<String, Integer> qWordTotalMap = new HashMap<>();

        TreeMap<Character, Integer> treePoints = new TreeMap<>();
        HashMap<Character, Integer> mapPoints = new HashMap<>();

        Scanner qWordList = new Scanner(qwordFile);
        Scanner points = new Scanner(pointsFile);


        while (points.hasNextLine()){
            Character c = Character.toLowerCase(points.next().charAt(0));
            Integer i = points.nextInt();
            treePoints.put(c,i);
            mapPoints.put(c,i);
        }

        //counting the time it takes to load these q words into hash maps
        //and tree maps, respectively
        System.out.println("Using a HashMap: ");
        time1 = System.nanoTime();
        while (qWordList.hasNextLine()){
            String s = qWordList.nextLine();
            Integer sum = 0;
            //retrieving the points from the map
            for (Character c : s.toCharArray()){
                sum+= mapPoints.get(c);
            }
            qWordTotalMap.put(s, sum);
        }
        time2 = System.nanoTime();
        System.out.println("Loading into HashMap takes: " + (time2-time1) + " nanoseconds");

        time1 = System.nanoTime();
        for (Map.Entry<String, Integer> entry : qWordTotalMap.entrySet()) {
            System.out.print("Word: " + entry.getKey() + "  Points: " + entry.getValue()+"  ");
            count++;
            if (count % 5 == 0) {
                System.out.println();
            }
        }
        time2 = System.nanoTime();
        count = 0;
        System.out.println("Printing a HashMap takes: " + (time2-time1) + " nanoseconds");



        //making another scanner to measure time again, this time with TreeMap and not HashMap
        System.out.println("\nUsing a TreeMap: ");
        time1 = System.nanoTime();
        Scanner qWordList2 = new Scanner(qwordFile);
        while (qWordList2.hasNextLine()){
            String s = qWordList2.nextLine();
            Integer sum = 0;
            //retrieving the points from the map
            for (Character c : s.toCharArray()){
                sum+= treePoints.get(c);
            }
            qWordTotalTree.put(s, sum);
        }
        time2 = System.nanoTime();
        System.out.println("Loading into TreeMap takes: " + (time2-time1) + " nanoseconds");

        time1 = System.nanoTime();
        for (Map.Entry<String, Integer> entry : qWordTotalTree.entrySet()){
            System.out.print("Word: " + entry.getKey() + "  Points: " + entry.getValue()+"  ");
            count++;
            if (count % 5 == 0){
                System.out.println();
            }
        }
        time2 = System.nanoTime();
        System.out.println("Printing a TreeMap takes: " + (time2-time1) + " nanoseconds");


        //part 2
        //declaring and initializing the words from
        //alice in wonderland into hash
        //and tree sets
        System.out.println("\n\nPart 2:");
        HashSet<String> aliceHashSet = new HashSet<>();
        TreeSet<String> aliceTreeSet = new TreeSet<>();

        File aliceInWonderland = new File("src/Alice.txt");
        Scanner scan = new Scanner(aliceInWonderland);
        Scanner scan2 = new Scanner(aliceInWonderland);

        time1 = System.nanoTime();
        while(scan.hasNext()){
            aliceHashSet.add(scan.next().replaceAll("[^a-zA-Z]", "").toLowerCase());
        }
        time2 = System.nanoTime();
        System.out.println("\nTime to insert Alice in Wonderland into HashSet: " + (time2-time1) + " nanoseconds");

        time1 = System.nanoTime();
        while(scan2.hasNext()){
            aliceTreeSet.add(scan2.next().replaceAll("[^a-zA-z]", "").toLowerCase());
        }
        time2 = System.nanoTime();
        System.out.println("Time to insert Alice in Wonderland into TreeSet: " + (time2 - time1) + " nanoseconds");

        String[] aliceArray = new String[100];
        int i = 0;
        for (String s : aliceHashSet){
            aliceArray[i] = s;
            i++;
            if (i == 100) break;
        }

        time1 = System.nanoTime();
        for (String s : aliceArray){
            aliceHashSet.contains(s);
        }
        time2 = System.nanoTime();
        System.out.println("\nTime to search Hash Set for 100 words: " + (time2-time1) + " nanoseconds");


        time1 = System.nanoTime();
        for (String s : aliceArray){
            aliceTreeSet.contains(s);
        }
        time2 = System.nanoTime();
        System.out.println("Time to search Tree Set for 100 words: " + (time2-time1) + " nanoseconds");



        //part 3
        //making hash and tree maps of the 100 alice words and
        //their corresponding scrabble points
        HashMap<String, Integer> aiwPointsHash = new HashMap<>();
        TreeMap<String, Integer> aiwPointsTree = new TreeMap<>();
        Integer sum = 0;
        for (String s : aliceArray){
            for (Character c : s.toCharArray()){
                sum += mapPoints.get(c);
            }
            aiwPointsHash.put(s,sum);
            aiwPointsTree.put(s,sum);
            sum = 0;
        }
        System.out.println("\nPart 3:");
        int hashSum = 0; //declaring a hash sum so i can take the total point value of all the words
        time1 = System.nanoTime(); //declaring a start time
        for (HashMap.Entry<String, Integer> e : aiwPointsHash.entrySet()){ //iterating the hash map
            hashSum += e.getValue(); //totaling the points
        }
        time2 = System.nanoTime(); //declaring an end time
        System.out.println("\nThe time to iterate the 100-word hash map: " + (time2 - time1) + " nanoseconds   The total sum: " + hashSum);


        int treeSum = 0; //declaring a tree sum to take the total point value (should match hash sum)
        time1 = System.nanoTime(); //declaring a start time
        for (Map.Entry<String,Integer> e : aiwPointsTree.entrySet()){ //iterating the tree map
            treeSum += e.getValue(); //totaling the points
        }
        time2 = System.nanoTime(); //declaring an end time
        System.out.println("The time to iterate the 100-word tree map: " + (time2 - time1) + " nanoseconds   The total sum: " + treeSum);

    }
}

