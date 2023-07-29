import javafx.scene.shape.Line;

import java.io.*;
import java.util.ArrayList;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Scanner;

public class FileListMaker {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // Create Array list
        java.util.ArrayList<String> myArrList = new java.util.ArrayList<>();
        // show initial list as empty
        myArrList.add("Empty");
        // Declare Variables
        String menuChoice = "";
        String newItem = "";
    String menu = "A-Add | C-Clear | D-Delete | L=Sort | O=Open | S-Save | V-View | Q-Quit";
    int removeItem = 0;
    int maxIndex = 0;
    boolean closeProgram = false;
    boolean needsToBeSaved = false;
    String fileName = "";
    // Loop To close program
        do {
            // Display Array list
            System.out.println("\n    List");
            displayList(myArrList);
            //Display Menu options
            System.out.println("\n" + menu);
            menuChoice = SafeInput.getregExString(in, "Please select from the menu", "[AaDdSsCcLlOoQqVv]").toUpperCase();
            switch (menuChoice) {
                case "A": // add an item
                    newItem = SafeInput.getNonZeroLenString(in, "Enter the Item");
                    addtoList(myArrList, newItem);
                    needsToBeSaved = true;
                    break;
                case "C": // clear current list
                    if (myArrList.get(0) == "Empty") {
                        System.out.println("The list is already Empty");
                    } else {
                        boolean clearListYN = SafeInput.getYNConfirm(in, "This will erase your current list. Are you sure?");
                        if (clearListYN) {
                            clearList(myArrList);
                            fileName = "";
                            needsToBeSaved = false;
                        }
                    }
                    break;
                case "D": // remove item from list
                    maxIndex = myArrList.size();
                    removeItem = SafeInput.getRangedInt(in, "Enter the index number", 1, maxIndex);
                    deletefromList(myArrList, removeItem);
                    needsToBeSaved = true;
                    break;
                case "L": // sort list alphabetically
                    sortList(myArrList);
                    needsToBeSaved = true;
                    break;
                case "O": // open and load a list from storage
                    if (needsToBeSaved) {
                        boolean deleteListYN = SafeInput.getYNConfirm(in, "Opening a new list will erase the current one. Are you sure?");
                        if (deleteListYN) {
                            fileName = openListFile(in, myArrList);
                        }
                    } else {
                        fileName = openListFile(in, myArrList);
                    }
                    break;
                case "S": // save current listing
                    if (myArrList.get(0) == "Empty") {
                        System.out.println("The list is still Empty");
                    } else {
                        if (fileName == "") {
                            fileName = getFileName(fileName);
                        }
                        saveCurrentFile(myArrList, fileName);
                        needsToBeSaved = false;
                    }
                    break;
                case "V": // view current listing
                    displayList(myArrList);
                    Scanner input = new Scanner(System.in);
                    System.out.print("Press Enter to Continue....");
                    input.nextLine();
                    break;
                case "Q": // quit the program
                    closeProgram = SafeInput.getYNConfirm(in, "Are you sure you want to quit?");
                    if (closeProgram == true) {
                        if (needsToBeSaved == false) {
                            System.out.println("Good Bye!");
                        } else if (myArrList.get(0) == "Empty") {
                            System.out.println("Good Bye");
                        } else {
                            boolean exitSave = SafeInput.getYNConfirm(in, "Do you want to save the current list?");
                            if (exitSave == false) {
                                System.out.println("Good Bye");
                            } else {
                                if (fileName == "") {
                                    fileName = getFileName(fileName);
                                }
                                saveCurrentFile(myArrList, fileName);
                                System.out.println("Good Bye");
                            }
                        }
                    }
                    break;
            }
        } while (!closeProgram);
    }
    public static void deletefromList(java.util.ArrayList<String> myArrList, int removeItem) {
        //Delete an item from the list
        if (myArrList.get(0) == "Empty") {
            System.out.println("The list is still Empty");
        } else {
            myArrList.remove(removeItem -1);
        }
        if (myArrList.isEmpty()) {
            myArrList.add("Empty");
        }
    }
    public static void addtoList(java.util.ArrayList<String> myArrList, String newItem) {
        // Add an item to the end of the list
        if (myArrList.contains(newItem)) {
            System.out.println("Item is already on the list");
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
        // clear the entire list and start over
        myArrList.clear();
        myArrList.add("Empty");
    }
    public static void displayList(java.util.ArrayList<String> myArrList) {
        // Display list with index number
        int index = 0;
        for (String i : myArrList) {
            index++;
            System.out.println("[" + index + "] " + i);
        }
    }
    public static String getFileName(String fName) {
        // Get filename if necessary
        Scanner in = new Scanner(System.in);
        fName = SafeInput.getNonZeroLenString(in, "Enter the file name for the list");

        fName = fName + ".txt";

        return fName;
    }
    public static void saveCurrentFile(java.util.ArrayList<String> myArrList, String fileName) {
        PrintWriter outFile;
        Path target = new File(System.getProperty("user.dir")).toPath();

        target = target.resolve(fileName);

        try
        {
            outFile = new PrintWriter(target.toString());
            for (int i = 0; i < myArrList.size(); i++) {
                outFile.println(myArrList.get(i));
            }
            outFile.close();
            System.out.printf("File \"%s\" saved!\n", target.getFileName());
        } catch (IOException e) {
            System.out.println("IOException Erro");
        }
    }
    private static String openListFile(Scanner in, ArrayList arrList) {
        arrList.clear();
        Scanner inFile;
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt","text");
        chooser.setFileFilter(filter);
        String line;

        Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve("src");
        chooser.setCurrentDirectory(target.toFile());

        try {
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                target = chooser.getSelectedFile().toPath();
                inFile = new Scanner(target);
                System.out.println("Opening File: " + target.getFileName());
                while (inFile.hasNextLine()) {
                    line = inFile.nextLine();
                    arrList.add(line);
                }
                inFile.close();
            } else { // user did not select a file
                System.out.println("You must select a file! Returning to menu...");
            }
        } catch (IOException e)
        {
            System.out.println("IOException Error");
        }
        return target.toFile().toString();
    }
}
