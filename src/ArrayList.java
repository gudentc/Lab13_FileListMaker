import java.util.Collections;
import java.util.Scanner;

public class ArrayList {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // Create Array List
        java.util.ArrayList<String> myArrList = new java.util.ArrayList<>();
        //Show initial list as empty
        myArrList.add("Empty");
        //declare variables
        String menuChoice = "";
        String newItem = "";
        int removeItem = 0;
        int maxIndex = 0;
        boolean closeProgram = false;
        // Loop to close program
        do {
            // Display Array List
            System.out.println("\n     List");
            displayList(myArrList);
            // Display Menu options
            System.out.println("\n        Menu");
            System.out.println("[A] - Add an item to your list");
            System.out.println("[D] - Delete an item from the list");
            System.out.println("[S] - Sort the list alphabetically");
            System.out.println("[C] - Clear the contents of the list");
            System.out.println("[Q] - Quit");
            menuChoice = SafeInput.getregExString(in, "Please select from the menu","[AaDdSsCcQq]");
            // call functions base on menu choice
            if (menuChoice.equalsIgnoreCase("Q")) {
                closeProgram = SafeInput.getYNConfirm(in, "Are you sure you want to Quit? ");
            } else if (menuChoice.equalsIgnoreCase("A")) {
                newItem  = SafeInput.getNonZeroLenString(in, "Enter your item");
                addtoList(myArrList, newItem);
            } else if (menuChoice.equalsIgnoreCase("D")) {
                maxIndex = myArrList.size();
                removeItem = SafeInput.getRangedInt(in,"Enter the index number", 1,maxIndex);
                deletefromList(myArrList, removeItem);
            } else if (menuChoice.equalsIgnoreCase("S")) {
                sortList(myArrList);
            } else if (menuChoice.equalsIgnoreCase("C")) {
                clearList(myArrList);
            }
        } while (!closeProgram);
    }
    public static void deletefromList(java.util.ArrayList<String> myArrList, int removeItem) {
        //Delete an item from the list
        if (myArrList.get(0) == "Empty") {
            System.out.println("The list is Empty");
        } else {
            myArrList.remove(removeItem -1);
        }
        if (myArrList.isEmpty()) {
            myArrList.add("Empty");
        }
    }
    public static void  addtoList(java.util.ArrayList<String> myArrList, String newItem) {
        // Add an item to the end of the list

        if (myArrList.contains(newItem)) {
            System.out.println("Item already exits");
        } else {
            if (myArrList.get(0) == "Empty") {
                myArrList.set(0,newItem);
            } else {
                myArrList.add(newItem);
            }
        }
    }
    public static void sortList(java.util.ArrayList<String> myArrList) {
        // Sorts the list alphabetically
        Collections.sort(myArrList);
    }
    public static void clearList(java.util.ArrayList<String> myArrList) {
        // Clear the entire list and start over
        myArrList.clear();
        myArrList.add("Empty");
    }
    public static void displayList(java.util.ArrayList<String> myArrList) {
        //Display list with index number
        int index = 0;
        for (String i : myArrList) {
            index++;
            System.out.println("[" + index + "]" + i);
        }
    }
}
