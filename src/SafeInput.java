import java.util.Scanner;

public class SafeInput {
    /**
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = "";
        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
        } while (retString.length() == 0);

        return retString;
    }
    /**
     * Get an int value with no constraints
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return - unconstrained int value
     */
    public static int getInt(Scanner pipe, String prompt) {
        int retVal = 0;
        String trash = "";
        boolean done = false;
        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter an int, not: " + trash);
            }
        } while (!done);

        return retVal;
    }
    /**
     * Get an unconstrained double value
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return - an unconstrained double value
     */
    public static double getDouble(Scanner pipe, String prompt) {
        double retVal = 0;
        String trash = "";
        boolean done = false;
        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter an double, not: " + trash);
            }
        } while (!done);

        return retVal;
    }
    /**
     * Get an int value within a specific numeric range
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param low - low end of inclusive range
     * @param high - high end of inclusive range
     * @return - int value within the inclusive range
     */
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int retVal = 0;
        String trash = "";
        boolean done = false;
        do {
            System.out.print(prompt + "[" + low + "_" + high + "]: ");
            if (pipe.hasNextLine()) {
                retVal = pipe.nextInt();
                pipe.nextLine();
                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.print("\nNumber is out of range [" + low + "_" + high + "]: " + retVal);
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter an int: " + trash);
            }
        } while (!done);

        return retVal;
    }
    /**
     * Get a double value within an inclusive range
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param low -Low end of inclusive range
     * @param high -High end of inclusive range
     * @return - double value within the inclusive range
     */
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double retVal = 0;
        String trash = "";
        boolean done = false;
        do {
            System.out.print(prompt + "[" + String.format("%.2f", low) + "_" + String.format("%.2f", high) + "]: ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.print("\nNumber is out of rang $[" + String.format("%.2f", low) + "_" + String.format("%.2f", high) + "]: " + retVal + "\n");
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a double, not: " + trash);
            }
        } while (!done);

        return retVal;
    }
    /**
     * Get a [Y/N] confirmation from the user
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return - true for yes and false for no
     */
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        boolean retVal = true;
        String response = "";
        boolean gotAVal = false;
        do {
            System.out.print("\n" + prompt + " [Y/N] ");
            response = pipe.nextLine();
            if (response.equalsIgnoreCase("Y")) {
                gotAVal = true;
                retVal = true;
            } else if (response.equalsIgnoreCase("N")) {
                gotAVal = true;
                retVal = false;
            } else {
                System.out.println("You must answer [Y/N]! " + response);
            }
        } while (!gotAVal);

        return retVal;
    }
    /**
     * Get a string that matches a RegEx pattern!
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param regExPattern - java style RegEx pattern to constrain the input
     * @return - a string that matches the RegEx pattern supplied
     */
    public static String getregExString(Scanner pipe, String prompt, String regExPattern) {
        String response = "";
        boolean gotAVal = false;
        do {
            System.out.print("\n" + prompt + ": ");
            response = pipe.nextLine();
            if (response.matches(regExPattern)) {
                gotAVal = true;
            } else {
                System.out.println("\n" + response + " does not match the pattern");
                System.out.println("Try Again!");
            }
        } while (!gotAVal);

        return response;
    }
    /**
     * Get a string and produce a Pretty Header
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return - a three line sting with a centered message
     */
    public static String getPrettyHeader(Scanner pipe, String prompt) {
        int space = 0;
        String msg = "";
        String retString = "";
        System.out.print("\n" + prompt + ": ");
        msg = pipe.nextLine();
        for (int x = 1; x <= 60; x++) {
            System.out.print("*");
        }
        System.out.print("\n***");
        space = (54 - msg.length())/2;
        for (int x = 1; x <= space; x++) {
            System.out.print(" ");
        }
        System.out.print(msg);
        if(msg.length() % 2 == 0) {
            for (int x = 1; x <= space; x++) {
                System.out.print(" ");
            }
        } else {
            for (int x = 0; x <= space; x++) {
                System.out.print(" ");
            }
        }
        System.out.print("***\n");
        for (int x = 1; x <= 60; x++) {
            System.out.print("*");
        }


        return retString;
    }
}
