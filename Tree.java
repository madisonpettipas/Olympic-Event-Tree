/**
 * Author: Madison Pettipas
 * Description: A Tree ADT with an inner Node class and general methods
 *              such as insertChild, appendChild, getChildren, and getParent.
 *              Includes multiple specialized methods to assist the methods
 *              found in HW3.java.
 */
import java.util.ArrayList;
public class Tree {
    Node root; 
    
    /** Inner Node class that keeps track of data, children, and parent */
    public class Node {
        String data;
        ArrayList<Node> children;
        Node parent;
        public Node(String d, Node p) { //constructor
            this.data = d;
            this.parent = p;
            this.children = new ArrayList<Node>();
        }
    }
    
    /** Recursive method that returns a Node and has parameters a Node and
     * a String. Searches for a Node whose data matches the input String,
     * and returns that Node if found */
    public Node findNode(Node current, String target) { 
        if (current == null) return null; //base case
        if (current.data.equals(target)) return current; //base case if found
        if (current.data.contains(":")) { //to search for an athlete name
            int colon = current.data.indexOf(":");
            String athlete = current.data.substring(0, colon);
            if (athlete.equals(target)) return current;
        }
        //recursion, for every node
        for (int i=0; i<current.children.size(); i++) {
            Node n = findNode(current.children.get(i), target);
            if (n != null) {
                return n;
            }
        }
        return null;
    }
    
    /** Void method with the parameters a Node and a String. Creates a 
     * new Node with the input String as data and inserts the Node into 
     * the Tree as a child of the insert parent Node in alphabetical order */
    public void insertChild(Node parent, String childData) {
        if (parent != null) {
            int i=0;
            while(i<parent.children.size() &&
                parent.children.get(i).data.compareTo(childData) < 0) {
                i++;
            }
            parent.children.add(i, new Node(childData, parent));
        }
    }
    
    /** Void method with the parameters a Node and a String. Creates a 
     * new Node with the input String as data and appends the Node into 
     * the Tree as a child of the insert parent Node in added order */
    public void appendChild(Node parent, String childData) {
        if (parent != null) {
            parent.children.add(new Node(childData, parent));
        }
    }
    
    /** Returns an ArrayList that holds Nodes and has a String parameter.
     * Searches the tree for a Node whose data matches the input String, 
     * then returns the ArrayList of children of the found node. */
    public ArrayList<Node> getChildren(String target) {
        Node parent = findNode(root, target);
        if (parent != null) {
            return parent.children;
        }
        return null;
    }
    
    /** Returns a String and has a String parameter. Searches the tree for
     * a Node whose data matches the input String, then returns the data
     * of the found Node's parent. */
    public String getParent(String target) {
        Node child = findNode(root, target);
        if (child != null && child.parent != null) {
            return child.parent.data;
        }
        return null;
    }
    
    /** Boolean method with no parameters that determines if the tree is empty */
    public boolean isEmpty() {
        return (root==null);
    }
    
    /** Int method with Node parameter. Returns the depth of a Node, used to
     * determine whether a node is the root, a sport, event, or athlete */
    public int depth(Node n) {
        int depth = 0;
        while(n.parent != null) {
            depth++;
            n = n.parent;
        }
        return depth;
    }
    
    /** Void method with a Node parameter. Prints the data of each child of
     * the input Node. */
    public void printChildren(Node n) {
        for (int i=0; i<n.children.size(); i++) {
            System.out.print(" " + n.children.get(i).data);
        }
    }
    
    /** Recursive int method with parameters a Node and 2 Strings. Counts
     * all times a given athlete or country appears in the tree. If the
     * String target is specified as "gold" it returns how many times the
     * specific athlete or country appears as a gold medalist. */
    public int countElement(Node current, String target, String type) {
        if (current == null || current.children == null || 
        current.children.size() == 0) return 0;
        int count = 0;
        
        if (type.equals("gold")) { //if only searching for gold medalists
            Node goldElement = current.children.get(0); //get the gold medalist
            if (goldElement.data.contains(":")) {
                int colon = goldElement.data.indexOf(":");
                //check if the athlete name matches
                if (goldElement.data.substring(0, colon).equals(target)) {
                    count++;
                //check if the country name matches
                } else if (goldElement.data.substring(colon+1).equals(target)) {
                    count++;
                }
            }
            //recurse through all children of each node
            for (int i=0; i<current.children.size(); i++) {
                count += countElement(current.children.get(i), target, type);
            }
        } else { //same thing but for all medal types, not just gold
            for (int i=0; i<current.children.size(); i++) {
                Node currChild = current.children.get(i);
                if (currChild.data.contains(":")) {
                    int colon = currChild.data.indexOf(":");
                    if (currChild.data.substring(0, colon).equals(target)) {
                        count++;
                    } else if (currChild.data.substring(colon+1).equals(target)) {
                        count++;
                    }
                }
                count += countElement(currChild, target, type);
            }
        }
        return count;
    }
    
    /** Recursive int method with parameters a Node, int, and 2 Strings.
     * Returns the max amount of medals or gold medals that any specific 
     * athlete or country may have by calling countElement on the data of 
     * each Node of the tree. */
    public int maxMedals(Node current, int max, String type, String element) {
        if (current == null || current.children == null || 
        current.children.size() == 0) return max; //base case
        if (type.equals("gold")) { //if only searching for gold medalists
            Node goldMedal = current.children.get(0);
            if (goldMedal.data.contains(":")) {
                int colon = goldMedal.data.indexOf(":");
                String name;
                if (element.equals("athlete")) { //check if athlete name matches
                    name = goldMedal.data.substring(0, colon);
                } else { //check if country name matches
                    name = goldMedal.data.substring(colon+1);
                } 
                //count how many times the name appears in the tree
                int count = countElement(root, name, type);
                if(count > max) max = count; //keep track of largest
            }
        } else { //same thing but for all medal types, not just gold
            for (int i=0; i<current.children.size(); i++) {
                Node medal = current.children.get(i);
                if (medal.data.contains(":")) {
                    int colon = medal.data.indexOf(":");
                    String name;
                    if (element.equals("athlete")) {
                        name = medal.data.substring(0, colon);
                    } else {
                        name = medal.data.substring(colon+1);
                    }
                    int count = countElement(root, name, type);
                    if(count > max) max = count;
                }
            }
        }
        //recurse through all children of every Node
        for (int i=0; i<current.children.size(); i++) {
            max = maxMedals(current.children.get(i), max, type, element);
        }
        return max;
    }
    
    /** Recursive method that returns an ArrayList of Strings and has parameters,
     * a Node, a String ArrayList, an int, and 2 Strings. Uses the other recursive
     * methods to help find all the athletes or countries that equal the max
     * amount of medals or gold medals. */
    public ArrayList<String> elementMostMedals(Node current, 
        ArrayList<String> aList, int max, String type, String element) { 
        if (current == null || current.children == null || 
        current.children.size() == 0) return aList; //base case
        if (type.equals("gold")) { //if only searching for gold medalists
            Node goldMedalist = current.children.get(0);
            if (goldMedalist.data.contains(":")) {
                int colon = goldMedalist.data.indexOf(":");
                String name;
                if (element.equals("athlete")) { //check if athlete name matches
                    name = goldMedalist.data.substring(0, colon);
                } else { //check if country name matches
                    name = goldMedalist.data.substring(colon+1);
                }
                int count = countElement(root, name, type);
                if (count == max && !aList.contains(name)) {
                    int i=0;
                    while (i<aList.size() && aList.get(i).compareTo(name) < 0) {
                        i++;
                    }
                    aList.add(i, name); //add the medalist to the list
                }   
            }
        } else { //same thing but for all medal types, not just gold
            for (int i=0; i<current.children.size(); i++) {
                Node medalist = current.children.get(i);
                if (medalist.data.contains(":")) {
                    int colon = medalist.data.indexOf(":");
                    String name;
                    if (element.equals("athlete")) {
                        name = medalist.data.substring(0, colon);
                    } else {
                        name = medalist.data.substring(colon+1);
                    }
                    int count = countElement(root, name, type);
                    if (count == max && !aList.contains(name)) {
                        int j=0;
                        while (j<aList.size() && aList.get(j).compareTo(name) < 0) {
                            j++;
                        }
                        aList.add(j, name);
                    }   
                }
            }
        }
        //recurse through every child of every node
        for (int i=0; i<current.children.size(); i++) {
            elementMostMedals(current.children.get(i), aList, max, type, element);
        }
        return aList;
    }
    
    /** Recursive method that returns an ArrayList of Nodes and has parameters
     * a Node, String, and ArrayList of Nodes. Finds every Node that includes
     * the input athlete name, and adds each found Node to an ArrayList */
    public ArrayList<Node> getAllAthleteNodes(Node current, String athlete, ArrayList<Node> aList) {
        if (current == null || current.children == null) return aList; //base case
        if (current.data.contains(":")) {
            int colon = current.data.indexOf(":");
            String name = current.data.substring(0, colon);
            if (name.equals(athlete)) {
                aList.add(current);
            }
        }
        //recurse through every child of every Node
        for (int i=0; i<current.children.size(); i++) {
            getAllAthleteNodes(current.children.get(i), athlete, aList);
        }
        return aList;
    }
}
