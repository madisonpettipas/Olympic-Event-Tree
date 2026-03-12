
/**
 * Author: Madison Pettipas
 * Email: mpettipas2024@my.fit.edu
 * Course: CSE2010
 * Section: E2
 * Description: Uses a Tree ADT to keep track of simulated Olympic sports, their
 *              events, and their gold, silver, and bronze medalists by name and
 *              country. Takes an input data file to create the olympic tree, and
 *              then a query file in order to output certain data depending on
 *              what is asked.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
public class HW3 {
    /** Boolean method with a String parameter. Determines whether the
     * String is a valid method name that can be called by chooseMethod.*/
    public static boolean isMethod(String command) {
        switch (command) { //switch statement to determine if command is valid
            case "GetEventsBySport": return true;
            case "GetWinnersAndCountriesBySportAndEvent": return true;
            case "GetGoldMedalistAndCountryBySportAndEvent":
            case "GetAthleteWithMostMedals": return true;
            case "GetAthleteWithMostGoldMedals": return true;
            case "GetCountryWithMostMedals": return true;
            case "GetCountryWithMostGoldMedals": return true;
            case "GetSportAndEventByAthlete": return true;
            default: return false;
        }
    }
    
    /** Void method with the parameters a String, Scanner object, and Tree
     * object. Calls the correct method depending on the String variable */
    public static void chooseMethod(String method, Scanner scnr, Tree tree) {
        switch (method) { //switch statement to determine which method to call
            case "GetEventsBySport":
                GetEventsBySport(scnr, tree);
                break;
            case "GetWinnersAndCountriesBySportAndEvent": 
                GetWinnersAndCountriesBySportAndEvent(scnr, tree);
                break;
            case "GetGoldMedalistAndCountryBySportAndEvent":
                GetGoldMedalistAndCountryBySportAndEvent(scnr, tree);
                break;
            case "GetAthleteWithMostMedals":
                GetAthleteWithMostMedals(tree);
                break;
            case "GetAthleteWithMostGoldMedals":
                GetAthleteWithMostGoldMedals(tree);
                break;
            case "GetCountryWithMostMedals":
                GetCountryWithMostMedals(tree);
                break;
            case "GetCountryWithMostGoldMedals":
                GetCountryWithMostGoldMedals(tree);
                break;
            case "GetSportAndEventByAthlete":
                GetSportAndEventByAthlete(scnr, tree);
                break;
        } 
    }
    
    /** Void method with the parameters a Scanner and Tree object. Reads the
     * input sport to search for a Node with the same name, and if found
     * outputs all of that Node's children, which are the sport's events */
    public static void GetEventsBySport(Scanner scnr, Tree t) {
        String sport = scnr.next();
        //search for a Node in the Tree whose data equals the sport
        Tree.Node sportNode = t.findNode(t.root, sport);
        if (sportNode != null) {
            System.out.print("GetEventsBySport " + sport);
            t.printChildren(sportNode);
            System.out.println();
        }
    }
    
    /** Void method with the parameters a Scanner and Tree object. Reads the
     * input sport and event to search for Nodes in the Tree with the same 
     * names, and if both are found, outputs all children of the Node sharing
     * a name with the event, which are the winners of the event. */
    public static void GetWinnersAndCountriesBySportAndEvent (Scanner scnr, Tree t) {
        String sport = scnr.next();
        String event = scnr.next();
        //search for a Node in the Tree whose data equals the sport
        Tree.Node sportNode = t.findNode(t.root, sport);
        if (sportNode != null) {
            //search for a Node in the Tree whose data equals the event
            Tree.Node eventNode = t.findNode(sportNode, event);
            if (eventNode != null) {
                System.out.print("GetWinnersAndCountriesBySportAndEvent ");
                System.out.print(sport + " " + event);
                t.printChildren(eventNode);
                System.out.println();
            }
        }
    }
    
    /** Void method with the parameters a Scanner and Tree object. Reads the
     * input sport and event to search for Nodes in the Tree with the same 
     * names, and if both are found, outputs the first child of the event 
     * Node, which is the gold medalist. */
    public static void GetGoldMedalistAndCountryBySportAndEvent(Scanner scnr, Tree t) {
        String sport = scnr.next();
        String event = scnr.next();
        //search for a Node in the Tree whose data equals the sport
        Tree.Node sportNode = t.findNode(t.root, sport);
        if (sportNode != null) {
            //search for a Node in the Tree whose data equals the event
            Tree.Node eventNode = t.findNode(sportNode, event);
            if (eventNode != null) {
                String gMedalist = eventNode.children.get(0).data;
                if (gMedalist != null) {
                    System.out.print("GetGoldMedalistAndCountryBySportAndEvent ");
                    System.out.println(sport +" "+ event +" "+ gMedalist);
                }
            }
        }
    }
    
    /** Void method with a Tree parameter. Saves the max amount of medals
     * that any athlete may have, then searches for all athletes who have
     * that same amount of medals, and prints each out */
    public static void GetAthleteWithMostMedals(Tree t) {
        //find the max amount of medals that any athlete may have
        int maxMedals = t.maxMedals(t.root, 0, "all", "athlete");
        ArrayList<String> maxAthletes = new ArrayList<>();
        //search for all athletes that have the same amount of medals as maxMedals
        maxAthletes = t.elementMostMedals(t.root, maxAthletes, maxMedals, "all", "athlete");
        
        System.out.print("GetAthleteWithMostMedals " + maxMedals);
        for (int i=0; i<maxAthletes.size(); i++) {
            System.out.print(" " + maxAthletes.get(i));
        }
        System.out.println();
    }
    
    /** Void method with a Tree parameter. Saves the max amount of gold medals
     * that any athlete may have, then searches for all athletes who have
     * that same amount of gold medals, and prints each out  */
    public static void GetAthleteWithMostGoldMedals(Tree t) {
        //find the max amount of gold medals that any athlete may have
        int maxMedals = t.maxMedals(t.root, 0, "gold", "athlete");
        ArrayList<String> maxGoldAthletes = new ArrayList<>();
        //search for all athletes that have the same amount of gold medals as maxMedals
        maxGoldAthletes = t.elementMostMedals(t.root, maxGoldAthletes, maxMedals, "gold", "athlete");
        
        System.out.print("GetAthleteWithMostGoldMedals " + maxMedals);
        for (int i=0; i<maxGoldAthletes.size(); i++) {
            System.out.print(" " + maxGoldAthletes.get(i));
        }
        System.out.println();
    }
    
    /** Void method with a Tree parameter. Saves the max amount of medals
     * that any country may have, then searches for all countries that have
     * that same amount of medals, and prints each out */
    public static void GetCountryWithMostMedals(Tree t) {
        //find the max amount of medals that any country may have
        int maxMedals = t.maxMedals(t.root, 0, "all", "country");
        ArrayList<String> maxMedalCountries = new ArrayList<>();
        //search for all countries that have the same amount of medals as maxMedals
        maxMedalCountries = t.elementMostMedals(t.root, maxMedalCountries, maxMedals, "all", "country");
        
        System.out.print("GetCountryWithMostMedals " + maxMedals);
        for (int i=0; i<maxMedalCountries.size(); i++) {
            System.out.print(" " + maxMedalCountries.get(i));
        }
        System.out.println();
    }
    
    /** Void method with a Tree parameter. Saves the max amount of gold medals
     * that any country may have, then searches for all countries that have
     * that same amount of gold medals, and prints each out */
    public static void GetCountryWithMostGoldMedals(Tree t) {
        //find the max amount of gold medals that any country may have
        int maxMedals = t.maxMedals(t.root, 0, "gold", "country");
        ArrayList<String> maxMedalCountries = new ArrayList<>();
        //search for all countries that have the same amount of gold medals as maxMedals
        maxMedalCountries = t.elementMostMedals(t.root, maxMedalCountries, maxMedals, "gold", "country");
        
        System.out.print("GetCountryWithMostGoldMedals " + maxMedals);
        for (int i=0; i<maxMedalCountries.size(); i++) {
            System.out.print(" " + maxMedalCountries.get(i));
        }
        System.out.println();
    }
    
    /** Void method with the parameters a Scanner and a Tree object. Searches
     * for all Nodes that share an athlete name with the input athlete, then 
     * saves the data of each Node's parent and grandparent as the event and 
     * sport, adding them to a sorted ArrayList to ensure alphabetical order. */
    public static void GetSportAndEventByAthlete(Scanner scnr, Tree t) {
        String athlete = scnr.next();
        ArrayList<Tree.Node> allOccurences = new ArrayList<>();
        //find every Node who's data matches the input athlete name
        allOccurences = t.getAllAthleteNodes(t.root, athlete, allOccurences);
        
        String event, sport, both;
        ArrayList<String> sorted = new ArrayList<>();
        //collect the parent & grandparent data for each Node that was found
        for (int i=0; i<allOccurences.size(); i++) {
            event = allOccurences.get(i).parent.data;
            sport = allOccurences.get(i).parent.parent.data;
            both = sport + ":" + event;
            //add each sport:event pair to a list in alphabetical order
            if (!sorted.contains(both)) {
                int j=0;
                while (j<sorted.size() && sorted.get(j).compareTo(both) < 0) {
                    j++;
                }  
                sorted.add(j, both);
            }
        }
        
        System.out.print("GetSportAndEventByAthlete " + athlete);
        for (int i=0; i<sorted.size(); i++) {
            System.out.print(" " + sorted.get(i));
        }
        System.out.println();
    }
    
    /** Main void method that may throw a FileNotFoundException. Ensures 2
     * files ending in .txt are entered. Creates a Tree object by reading the
     * first file and inputting its data into a tree. Reads the second file to
     * determine which methods should be called to output certain information
     * regarding the data input from the first file. */
    public static void main(String[]args) throws FileNotFoundException {
        if (args.length != 2 || !args[0].endsWith(".txt")
            || !args[1].endsWith(".txt")) { //makes sure exactly one .txt file was entered
            System.out.println("Input Error");
            return;
        }

        Tree olympicTree = new Tree(); //initialize the tree
        
        File dataFile = new File(args[0]); //open the first file
        Scanner scnr = new Scanner(dataFile); //read the file with Scanner
        Tree.Node currentParent = null;
        while(scnr.hasNextLine()) { //while the file has another line
            String line = scnr.nextLine(); //read each line individually
            if (!line.isEmpty()) {
                Scanner lineScnr = new Scanner(line);
                String parent = lineScnr.next(); //identify first String as parent
                if (olympicTree.isEmpty()) { //set root if Tree is empty
                    olympicTree.root = olympicTree.new Node(parent, null);
                    currentParent = olympicTree.root;
                } else {
                    //if the first String is not already a Node in the tree
                    if (olympicTree.findNode(olympicTree.root, parent) != null) {
                        currentParent = olympicTree.findNode(olympicTree.root, parent);
                    } else {
                        olympicTree.appendChild(currentParent, parent);
                    }
                }   
                while (lineScnr.hasNext()) {
                    String child = lineScnr.next();
                    int depth = olympicTree.depth(currentParent);
                    if (depth <= 1) { //alphabetical order if depth is <=1
                        olympicTree.insertChild(currentParent, child);
                    } else { //keep insertion order for medalists at depth 2
                        olympicTree.appendChild(currentParent, child);
                    }
                }
                lineScnr.close();
            }
        }
        scnr.close();
        
        File queryFile = new File(args[1]); //open second file
        scnr = new Scanner(queryFile); //read file with scanner
        while (scnr.hasNext()) { //while the file has another line
            String method = scnr.next();
            //if the first String is a valid method, call chooseMethod
            if (isMethod(method)) {
                chooseMethod(method, scnr, olympicTree);
            }
        }
        scnr.close();
    }
}