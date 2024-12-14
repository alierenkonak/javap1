package Project1;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main Class
 */
public class Main {

    /**
     * A list that stores encrypted text strings.
     * Used in text decryption history
     */
    public static ArrayList<String> encryptedTexts = new ArrayList<>();

    
    /**
     * A list that stores the corresponding shift amounts for the encrypted texts.
     * Used in text decryption history
     */
    public static ArrayList<Integer> shiftAmounts = new ArrayList<>();

    /**
     * The entry point of the program, where the main menu and user interaction occurs.
     *
     * This method initializes the program, displays a welcome message, and continuously presents the main menu
     * to the user until they choose to exit the program or select a valid option. It handles user input and
     * invokes appropriate actions based on the selected option.
     * @param args default argument for main function
     */
    public static void main(String[] args) {
        boolean isProgramRunning = true;
        Scanner scanner = new Scanner(System.in);
        clearConsole();

        // Display welcome message
        displayWelcomeMessage();
        while (isProgramRunning) {
            displayMainMenu();
            String input = scanner.next();

            if (input.length() == 1) {
                char selectedOption = input.toUpperCase().charAt(0);
                isProgramRunning = handleUserSelection(selectedOption, scanner);
            } else {
                clearConsole();
                System.out.println("Invalid selection. Please enter a single character option. \n");
            }
        }

        System.out.println("See you later!\n");
        scanner.close();
    }

    /**
     * Displays the welcome ASCII art and team members.
     */
    public static void displayWelcomeMessage() {
        String[] teamMemberList = {"Volkan Erdoğan", "Zeynep Temur", "Zeynep Kurtuluş", "Merve Yıldız", "Ali Eren Konak"};
        System.out.println(
"       _      _      _      _      _      _      _      _ \n" +
"     _( )_  _( )_  _( )_  _( )_  _( )_  _( )_  _( )_  _( )_ \n" +
"    (_ o _)(_ o _)(_ o _)(_ o _)(_ o _)(_ o _)(_ o _)(_ o _) \n" +
"     (_,_)  (_,_)  (_,_)  (_,_)  (_,_)  (_,_)  (_,_)  (_,_) \n" +
"     _                                                     _ \n" +
"   _( )_   __        __   _                              _( )_ \n" +
"  (_ o _)  \\ \\      / /__| | ___ ___  _ __ ___   ___    (_ o _) \n" +
"   (_,_)    \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\    (_,_) \n" +
"     _       \\ V  V /  __/ | (_| (_) | | | | | |  __/      _  \n" +
"   _( )_      \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|    _( )_ \n" +
"  (_ o _)                                               (_ o _) \n" +
"   (_,_)                                                 (_,_) \n" +
"       _      _      _      _      _      _      _      _ \n" +
"     _( )_  _( )_  _( )_  _( )_  _( )_  _( )_  _( )_  _( )_ \n" +
"    (_ o _)(_ o _)(_ o _)(_ o _)(_ o _)(_ o _)(_ o _)(_ o _) \n" +
"     (_,_)  (_,_)  (_,_)  (_,_)  (_,_)  (_,_)  (_,_)  (_,_) \n"
       );

       System.out.println("┌────────────────────────────────┐");
       System.out.println("│  Our Team Members              │");
       System.out.println("│────────────────────────────────│");
       for (int i = 0; i < teamMemberList.length; i++) {
           System.out.printf("│ - %-29s│\n", teamMemberList[i]);
       }
       System.out.println("└────────────────────────────────┘");
       System.out.println();
    }

    /**
     * Displays all the operations in a styled manner that are inside main menu.
     * @author Volkan Erdogan
     */
    public static void displayMainMenu() {

        String[] menuOptions = {
            "Select an option:",
            "[A] Statistical Information about an array",
            "[B] Matrix Operations",
            "[C] Text Encryption/Decryption",
            "[D] Tic-tac-toe HotSeat",
            "[E] Terminate",
        };

        int maxLengthOfMenu = 0;
        for (int i = 0; i < menuOptions.length; i++) {
            maxLengthOfMenu = Math.max(maxLengthOfMenu, menuOptions[i].length());
        }

        String header = "Main Menu";
        int headerPadding = (maxLengthOfMenu - header.length()) / 2;
        System.out.println(" ".repeat(headerPadding) + header + " ".repeat(headerPadding));

        // int[] lengths = new int[1];     these 2 lines are equal to new intt[]{maxLengthOfMenu}
        // lengths[0] - maxLengthOfMenu    you would pass printBorder(lengths, "top") without this syntax
        printBorder(new int[]{maxLengthOfMenu}, "top");
        for (int i = 0; i < menuOptions.length; i++) {
            System.out.println("│ " + menuOptions[i] + " ".repeat(maxLengthOfMenu - menuOptions[i].length()) + " │");
        }

        printBorder(new int[]{maxLengthOfMenu}, "bottom");
        System.out.print("Your selection: ");
    }

    /**
     * Calls the appropiate function based on user selection
     * @param selectedOption Option selected by the user in main menu.
     * @param scanner The scanner object to read user input
     * @return true if the user does not want to terminate the program, false otherwise. 
     * @author Volkan Erdogan
     */
    public static boolean handleUserSelection(char selectedOption, Scanner scanner) {
        switch (selectedOption) {
            case 'A':
                computeStatisticalInformation(scanner);
                break;
            case 'B':
                performMatrixOperation(scanner);
                break;
            case 'C':
                startTextCryption(scanner);
                break;
            case 'D':
                playTicTacToe(scanner);
                break;
            case 'E':
                return false;
            default:
                clearConsole();
                System.out.println("Invalid choice. Please choose a valid option.\n");
        }
        return true;
    }

    //  ---------- START OF STATISTICAL INFORMATION ----------
     /**
     * Computes statistical information based on user input for an array of numbers.
     * This method prompts the user to enter the size of the array, 
     * reads the array elements, calculates statistical measures (such as 
     * median, arithmetic mean, geometric mean, and harmonic mean), 
     * and displays the results. It also provides options for the user to 
     * either analyze another array or return to the main menu.
     * 
     * @param scanner The scanner object to read user input.
     * @author Zeynep Temur
     */
    public static void computeStatisticalInformation(Scanner scanner) {
        while (true) {
            clearConsole();
            int size = getArraySize(scanner);
            double[] arr = getArrayInput(scanner, size);
            double[] sortedArr = getSortedArray(arr);
            displayResults(arr, calculateStatistics(arr, sortedArr));

            while (true) {

                // menu transition handling
                System.out.println("Would you like to: ");
                System.out.println("[A] Analyze another array");
                System.out.println("[B] Return to main menu");
                System.out.print("Your selection: ");
                
                String input = scanner.next();
                clearConsole();
                
                if (input.length() == 1) {
                    char option = input.toUpperCase().charAt(0);
                    
                    if (option == 'A') {
                        break; // break out of the menu loop and start over
                    } else if (option == 'B') {
                        return; // exit the method
                    } else {
                        System.out.println("Invalid selection. Please select a valid option. \n");
                    }
                } else {
                    System.out.println("Invalid selection. Please enter a single character option. \n");
                }
            }
        }
    }

    /**
     * Gets the integer array size as input from the user while validating it. No strings or negative values can be inserted.
     * @param scanner The scanner object to read user input.
     * @return A valid integer that represents array size.
     * @author Zeynep Temur
     */
    public static int getArraySize(Scanner scanner) {
        int size = -1;
        while (size <= 0) {
            System.out.print("Please enter size of the array: ");
            if (scanner.hasNextInt()) {
                size = scanner.nextInt();
                if (size <= 0) {
                    clearConsole();
                    System.out.println("Invalid input. Please enter a positive integer!");
                }
            } else {
                clearConsole();
                System.out.println("Invalid input. Please enter a positive integer!");
                scanner.next();
            }
        }
        return size;
    }

    /**
     * Populates the array with doubles input by the user while validating each one. No strings or negative values can be inserted into the array.
     * @param scanner The scanner object to read user input.
     * @param size The size of the array.
     * @return A valid double array with elements.
     * @author Zeynep Temur
     */
    public static double[] getArrayInput(Scanner scanner, int size) {
        double[] arr = new double[size];
        System.out.println("Please enter numbers for the array:");
        
        for (int i = 0; i < size; i++) {
            arr[i] = getValidNumber(scanner, i + 1);
        }
        return arr;
    }
    
    /**
     * Checks the double value input by the user and validates it. No strings or negative numbers can be inserted 
     * (since geometric and harmonic mean does not work with negative values). 
     * 
     * @param scanner The scanner object to read user input.
     * @param index The index of the current element.
     * @return A valid integer.
     * @author Zeynep Temur
     */
    public static double getValidNumber(Scanner scanner, int index) {
        double number = -1;
        while (number <= 0) {
            System.out.print("[" + index + "]: ");
            if (scanner.hasNextDouble()) {
                number = scanner.nextDouble();
                break;
            } else {
                clearConsole();
                System.out.println("Invalid input. Please enter a valid number!");
                scanner.next();
            }
        }
        return number;
    }

    /**
     * Sorts the double array input.
     * @param arr Double array to be sorted.
     * @return the sorted double array.
     * @author Zeynep Temur
     */
    public static double[] getSortedArray(double[] arr) {
        double[] sortedArray = arr.clone();
        for (int i = 0; i < sortedArray.length - 1; i++) { //sort an array
            for (int j = i + 1; j < sortedArray.length; j++) {
                if (sortedArray[i] > sortedArray[j]) {
                    double temp = sortedArray[i];
                    sortedArray[i] = sortedArray[j];
                    sortedArray[j] = temp;
                }
            }
        }
        return sortedArray;
    }

    /**
     * Calculates median, arithmetic, geometric, and harmonic mean.
     * @param arr The array on which the calculations will be performed.
     * @param sortedArr The sorted version of the array which will be used to calculate median.
     * @return an array of double values that stores the result of each operation.
     * @author Zeynep Temur
     */
    public static double[] calculateStatistics(double[] arr, double[] sortedArr) {
        int size = arr.length;
        double total = 0;
        double product = 1;
        boolean containsInvalidNumber = false;

        for (int i = 0; i < size; i++) {
            total += arr[i];
            if (arr[i] <= 0) {
                containsInvalidNumber = true;
            } else {
                product *= arr[i];
            }
        }

        return new double[] {
            calculateMedian(sortedArr),
            total / size,                           // arithmetic mean
            containsInvalidNumber ? -1 : calculateRoot(product, size),          // geometric mean
            containsInvalidNumber ? -1 : size / calculateHarmonicMean(arr, size) // harmonic mean
        };
    }

    /**
     * Calculates the power for perform Calculate Root Operation 
     * @param base is the number that will calculating repeatedly multiplied for the final product.
     * @param expo is the number that indicates how many times the base number is multiplied by itself. 
     * @return The product returned is the result of the exponent calculation.
     * @author Zeynep Temur
     */
    public static double calculatePower(double base, double expo) {
        double product = 1;
        if (expo == 0) 
            return 1;
        
        if (expo > 0) {
            for (int i = 1; i <= expo; i++) 
                product *= base;
            return product;

        } else {
            for (int i = 1; i <= -expo; i++) 
                product *= base;
            return 1 / product;
        }
    }



     /**
     * Calculates the root for perform Geometric Mean Operation.
     * @param base is the number which root is to be calculated
     * @param rootDegree is the number that shows which degree of the root will be taken for the result.
     * @return firstGuess is a return type which is the result of the root calculations process, the value calculated with minimum deviation.
     * @author Zeynep Temur
     */
    public static double calculateRoot(double base, double rootDegree){//calculates root
        if (base < 0 && rootDegree % 2 == 0) 
            System.out.println("Base cannot be a negative number!");
        
        double firstGuess = base / rootDegree; //an approximate guess is made
        double tolerance = 0.000000001; //deviation value is determined
        double diff = 1;

        while (diff > tolerance) { //to minimize the difference
            double currentGuess = (((rootDegree - 1) * firstGuess) + (base / calculatePower(firstGuess, rootDegree - 1))) / rootDegree; //formula for calculation
            double result = (currentGuess-firstGuess);

            if (result < 0) //to get absolute value
                result = result * -1;
            diff = result;
            firstGuess = currentGuess;
        }
        return firstGuess;
    }

    /**
     * Calculates the median of a sorted array of double values.
     * @param sortedArr Sorted array of double values.
     * @return The median of the array. If the array length is odd, returns the middle value. If it is even, returns the average of the two middle values. 
     * @author Zeynep Temur
     */
    public static double calculateMedian(double[] sortedArr) {
        int size =  sortedArr.length;
        if (size % 2 == 1) 
            return sortedArr[(size + 1) / 2 - 1];
        else 
            return (sortedArr[size / 2 - 1] + sortedArr[size / 2]) / 2;
    }

    /**
     * Recursively calculates the Harmonic Mean of an array of double values.
     * The Harmonic Mean is calculated as the reciprocal of the arithmetic mean of the reciprocals.
     * 
     * @param arr The array of double values for which the Harmonic Mean is calculated.
     * @param size The size of the array or the remaining size in the recursive call.
     * @return The Harmonic Mean of the array.
     * @author Zeynep Temur
     */
    public static double calculateHarmonicMean(double[] arr, int size){//calculates Harmonic Mean
        if (size == 0)
            return 0;
        else if (size == 1) 
            return 1 / arr[size-1];
        else 
          return (1 / arr[size-1]) + (calculateHarmonicMean(arr, size-1));
    }

    /**
     * Displays the statistical results (such as Median, Arithmetic Mean, Geometric Mean, 
     * and Harmonic Mean) in a tabular format with aligned headers and values.
     * 
     * @param arr The original array of data (not directly used in display but passed for context).
     * @param stats The array containing the calculated statistics where:
     *              stats[0] = Median,
     *              stats[1] = Arithmetic Mean,
     *              stats[2] = Geometric Mean,
     *              stats[3] = Harmonic Mean.
     */
    public static void displayResults(double[] arr, double[] stats) {
        String[] headers = {"Median", "Arithmetic Mean", "Geometric Mean", "Harmonic Mean"};
        String[] values = new String[4];
        
        values[0] = String.format("%.3f", stats[0]);
        values[1] = String.format("%.3f", stats[1]);
        values[2] = stats[2] == -1 ? "---" : String.format("%.3f", stats[2]);
        values[3] = stats[3] == -1 ? "---" : String.format("%.3f", stats[3]);
    
        // determine column widths for headers and values by considering the biggest ones,
        int[] colWidths = new int[2];
        colWidths[0] = Math.max("Operation".length(), findMaxLength(headers)); // first col is the widest header
        colWidths[1] = Math.max("Result".length(), findMaxLength(values));   // second col is the widest value
        System.out.println();
        
        // top border
        printBorder(colWidths, "top");
    
        // print headers
        System.out.print("│ ");
        System.out.print(centerText("Measure", colWidths[0]));
        System.out.print(" │ ");
        System.out.print(centerText("Value", colWidths[1]));
        System.out.println(" │");
    
        // middle border
        printBorder(colWidths, "middle");
    
        // print statistics
        for (int i = 0; i < headers.length; i++) {
            System.out.print("│ ");
            System.out.print(centerText(headers[i], colWidths[0]));
            System.out.print(" │ ");
            System.out.print(centerText(values[i], colWidths[1]));
            System.out.println(" │");
    
            if (i < headers.length - 1) {
                printBorder(colWidths, "middle");
            }
        }
    
        // bottom border
        printBorder(colWidths, "bottom");
        System.out.println();
    }
    //  ---------- END OF STATISTICAL INFORMATION ----------


    //  ---------- START OF MATRIX OPERATIONS ----------
    /**
     * Displays the formatted matrix operation menu and handles user input for various matrix operations.
     * The user can choose between operations such as Transpose, Inverse, Matrix Multiplication, 
     * Element-wise Multiplication, or return to the Main Menu.
     * 
     * @param scanner The Scanner object used for reading user input.
     * @author Zeynep Temur
     */
    public static void performMatrixOperation(Scanner scanner) {
        clearConsole();
        char selectedOption;
    
        String[] menuOptions = {
            "Select the operation you want to perform:",
            "[A] Transpose Matrix",
            "[B] Inverse Matrix",
            "[C] Matrix Multiplication",
            "[D] Element Wise Multiplication",
            "[E] Return to the Main Menu"
        };
    
        // Find the maximum length of menu options for formatting
        int maxLengthOfMenu = 0;
        for (int i = 0; i < menuOptions.length; i++) {
            maxLengthOfMenu = Math.max(maxLengthOfMenu, menuOptions[i].length());
        }
    
        String header = "Matrix Operations";
        int headerPadding = (maxLengthOfMenu - header.length()) / 2;
        
        // Initial menu display
        displayMatrixMenu(menuOptions, maxLengthOfMenu, header, headerPadding);
        System.out.print("Your selection: ");
        
        while (true) {
            String input = scanner.next();
            clearConsole();
            
            if (input.length() == 1) {
                selectedOption = input.toUpperCase().charAt(0);
                boolean shouldReturnToMainMenu = handleMatrixOperation(selectedOption, scanner);
                if (shouldReturnToMainMenu) {
                    return;
                }
            } else {
                System.out.println("Invalid selection. Please enter a single character option. \n");
            }
    
            displayMatrixMenu(menuOptions, maxLengthOfMenu, header, headerPadding);
            System.out.print("Your selection: ");
        }
    }

    /**
     * Routes the selected matrix operation based on user input.
     * Executes the corresponding operation or returns to the Main Menu if the user selects 'E'.
     * 
     * @param option The character representing the selected operation (e.g., 'A', 'B', etc.).
     * @param scanner The Scanner object used for reading user input.
     * @return true if the user selects the option to return to the Main Menu, false otherwise.
     * @author Zeynep Temur
     */
    public static boolean handleMatrixOperation(char option, Scanner scanner) {
        switch (option) {
            case 'A':
                return handleTranspose(scanner);
            case 'B':
                return handleInverse(scanner);
            case 'C':
                return handleMultiplication(scanner);
            case 'D':
                return handleElementWise(scanner);
            case 'E':
                clearConsole();
                return true;
            default:
                System.out.println("Invalid selection. Please select a valid option.\n");
                return false;
        }
    }

    /**
     * Handles the process of transposing a matrix. 
     * The user is prompted to input the matrix, which is then transposed and displayed.
     * 
     * @param scanner The Scanner object used for reading user input.
     * @return true if the user wants to return to the Main Menu after the operation, false otherwise.
     * @author Zeynep Kurtulus
     */
    public static boolean handleTranspose(Scanner scanner) {
        double[][] mat = createMatrix(scanner, 1);
        
        if (mat == null) {
            System.out.println();
            return shouldReturnToMainMenu(scanner, "Matrix Operation");
        }

        clearConsole();
        System.out.println("\n---Your Matrix---\n");
        printMatrix(mat);

        System.out.println("\n---Transposed Matrix---\n");
        double[][] matTranspose = transposeMatrix(mat);
        printMatrix(matTranspose);
        System.out.println();

        // handle menu navigation
        return shouldReturnToMainMenu(scanner, "Matrix Operation");
    }

    /**
     * Handles the process of calculating the inverse of a square matrix. 
     * The user is prompted to enter a square matrix, and its inverse is displayed if it exists.
     * If the matrix is singular and cannot be inverted, the user is asked whether to retry.
     * 
     * @param scanner The Scanner object used for reading user input.
     * @return true if the user wants to return to the Main Menu after the operation or when 
     *         the user decides not to retry after an invalid matrix, false otherwise.
     * @author Merve Yildiz
     */
    public static boolean handleInverse(Scanner scanner) {
        while (true) {
            System.out.println("Please enter the dimensions of the matrix (it must be square): ");
            System.out.println();
            
            int rows = -1;
            int cols = -1;

            rows = getValidDimension(scanner, "rows", 1);

            while (true) {
                cols = getValidDimension(scanner, "columns", 1);

                if (rows !=  cols) {
                    clearConsole();
                    System.out.println("Please enter the dimensions of the matrix (it must be square): ");
                    System.out.println();
                    System.out.println("Invalid input. Matrix must be square to be invertible. Your entered row is: [" + rows + "]");
                    System.out.println();
                } else {
                    break;
                }
            }

            double[][] mat = createMatrix(scanner, 1, rows, cols);

            if (mat == null) {
                System.out.println();
                return shouldReturnToMainMenu(scanner, "Matrix Operation");
            }

            clearConsole();
            System.out.println("\n---Your Matrix---\n");
            printMatrix(mat);
            
            //Inverse Matrix
            double[][] matInverse = inverseMatrix(mat);
            
            if (matInverse != null) {
                System.out.println("\n---Inversed Matrix---\n");
                printMatrix(matInverse);
                System.out.println();
                return shouldReturnToMainMenu(scanner, "Matrix Operation");
            } else {
                System.out.println("\nMatrix is singular and cannot be inverted.");
                if (!shouldRetry(scanner))
                    return true;
            }
        }
    }

    /**
     * Handles the process of multiplying two matrices. 
     * The user is prompted to input two matrices, and their product is displayed.
     * Validates that the number of columns in the first matrix equals the number of rows in the second matrix.
     * 
     * @param scanner The Scanner object used for reading user input.
     * @return true if the user wants to return to the Main Menu after the operation, false otherwise.
     * @author Zeynep Temur
     */
    public static boolean handleMultiplication(Scanner scanner) {
        while (true) {
            System.out.println("Please enter the dimensions of the first matrix");
            
            // First Matrix
            System.out.print("\n---First Matrix---\n");
            System.out.println();

            double[][] mat1 = createMatrix(scanner, 1);
            
            if (mat1 == null) {
                System.out.println();
                return shouldReturnToMainMenu(scanner, "Matrix Operation");
            }

            clearConsole();
            
            System.out.println("Please enter the dimensions of the second matrix");
            // Second Matrix dimensions
            System.out.print("\n---Second Matrix---\n");
            System.out.println();
            int secondMatrixRows = -1;
            int secondMatrixCols = -1;
    
            while (true) {
                secondMatrixRows = getValidDimension(scanner, "rows", 2);
                if (secondMatrixRows != mat1[0].length) {
                    clearConsole();
                    System.out.println("Please enter the dimensions of the second matrix");
                    System.out.print("\n---Second Matrix---\n");
                    System.out.println();
                    System.out.println("Invalid input: Rows of the second matrix must equal the columns of the first matrix which is: [" + mat1[0].length + "].\n");
                } else {
                    secondMatrixCols = getValidDimension(scanner, "columns", 2);
                    break;
                }
            }
    
            clearConsole();
    
            System.out.println("\n---First Matrix---\n");
            printMatrix(mat1);
            
            System.out.print("\n---Second Matrix---\n");
            double[][] mat2 = createMatrix(scanner, 2, secondMatrixRows, secondMatrixCols); // Overloaded version

            if (mat2 == null) {
                System.out.println();
                return shouldReturnToMainMenu(scanner, "Matrix Operation");
            }
            clearConsole();
    
            System.out.println("\n---First Matrix---\n");
            printMatrix(mat1);
            System.out.println("\n---Second Matrix---\n");
            printMatrix(mat2);
    
            // Multiply Matrix
            System.out.println("\n---Multiplied Matrix---\n");
            double[][] matProduct = multiplyMatrix(mat1, mat2);
            printMatrix(matProduct);
            System.out.println();

            return shouldReturnToMainMenu(scanner, "Matrix Operation");
        }
    }
    
    /**
     * Handles the process of performing element-wise multiplication on two matrices.
     * The user is prompted to input two matrices with the same dimensions, and their element-wise product is displayed.
     * Validates that both matrices have the same dimensions.
     * 
     * @param scanner The Scanner object used for reading user input.
     * @return true if the user wants to return to the Main Menu after the operation, false otherwise.
     * @author Zeynep Temur
     */
    public static boolean handleElementWise(Scanner scanner) {
        while (true) {
            System.out.println("Please enter the dimensions of the first matrix");
            
            System.out.println("\n---First Matrix---\n");
            double[][] mat1 = createMatrix(scanner, 1);
            
            if (mat1 == null) {
                System.out.println();
                return shouldReturnToMainMenu(scanner, "Matrix Operation");
            }

            clearConsole();
    
            System.out.println("Please enter the dimensions of the second matrix");
            System.out.println("\n---Second Matrix---\n");
            
            // Second Matrix dimensions validation
            int secondMatrixRows = -1;
            int secondMatrixCols = -1;
    
            while (true) {
                secondMatrixRows = getValidDimension(scanner, "rows", 2);
                if (secondMatrixRows != mat1.length) {
                    clearConsole();
                    System.out.println("Please enter the dimensions of the second matrix");
                    System.out.println("\n---Second Matrix---\n");
                    System.out.println("Invalid input: Rows of the second matrix must equal the rows of the first matrix which is: [" + mat1.length + "].\n");
                } else {
                    break;
                }
            }

            while (true) {
                secondMatrixCols = getValidDimension(scanner, "columns", 2);
                if (secondMatrixCols != mat1[0].length) {
                    clearConsole();
                    System.out.println("Please enter the dimensions of the second matrix");
                    System.out.println("\n---Second Matrix---\n");
                    System.out.println("Invalid input: Columns of the second matrix must equal the columns of the first matrix which is: [" + mat1[0].length + "].\n");
                } else {
                    break;
                }
            }
    
            clearConsole();
    
            System.out.println("\n---First Matrix---\n");
            printMatrix(mat1);
            
            System.out.println("\n---Second Matrix---\n");
            double[][] mat2 = createMatrix(scanner, 2, secondMatrixRows, secondMatrixCols); 
            
            if (mat2 == null) {
                System.out.println();
                return shouldReturnToMainMenu(scanner, "Matrix Operation");
            }

            clearConsole();
    
            System.out.println("\n---First Matrix---\n");
            printMatrix(mat1);
            System.out.println("\n---Second Matrix---\n");
            printMatrix(mat2);
    
            System.out.println("\n---Element Wise Multiplied Matrix---\n");
            double[][] matResult = elementWiseMultiMat(mat1, mat2);
            printMatrix(matResult);
            System.out.println();
    
            return shouldReturnToMainMenu(scanner, "Matrix Operation");
        }
    }
    
    /**
     * Creates a matrix by prompting the user for the number of rows and columns.
     * The user can enter values for each element of the matrix. 
     * It allows quitting the input process and handles invalid inputs.
     *
     * @param scanner The Scanner object used for user input.
     * @param matrixNumber The number assigned to the matrix being created (for display purposes).
     * @return The filled matrix as a two-dimensional array of doubles, or null if the user chooses to quit.
     * @author Zeynep Temur
     */
    public static double[][] createMatrix(Scanner scanner, int matrixNumber) {
        // Get rows and columns using the helper function
        int rows = getValidDimension(scanner, "rows", matrixNumber);
        int cols = getValidDimension(scanner, "columns", matrixNumber);
    
        double[][] matrix = new double[rows][cols];
        boolean[][] isFilled = new boolean[rows][cols];
        
        clearConsole();
        System.out.println("Please enter the elements of Matrix " + matrixNumber + ": ");
    
        printMatrix(matrix, isFilled); // Prints the empty matrix with placeholders
        
        // Fill the matrix with user input
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double number = -1;
                while (true) {
                    System.out.println("\nEnter [Q] to quit.\n");
                    System.out.print("[" + i + "][" + j + "]: ");

                    if (scanner.hasNext()) {
                        String input = scanner.next();
                        if ((input.toUpperCase()).equals("Q")) {
                            if (confirmQuit(scanner)) {
                                return null;
                            } else {
                                clearConsole();
                                System.out.println("Please enter the elements of Matrix " + matrixNumber + ": ");
                                printMatrix(matrix, isFilled); // Prints the empty matrix with placeholders
                                continue;
                            }
                        }
                        
                        try {
                            number = Double.parseDouble(input);
                            matrix[i][j] = number;
                            isFilled[i][j] = true;
                            
                            clearConsole(); // Clears the console after each entry
                            System.out.println("Please enter the elements of Matrix " + matrixNumber + ": ");
                            printMatrix(matrix, isFilled); // Prints the matrix as it's being filled
                            break;
                        } catch (NumberFormatException exception) {
                            clearConsole();
                            System.out.println("Please enter the elements of Matrix " + matrixNumber + ": ");
                            printMatrix(matrix, isFilled);
                            System.out.println();
                            System.out.println("Invalid input. Please enter a valid number!");
                        }
                    }
                }
            }
        }
    
        return matrix; // Return the filled matrix
    }
    
    /**
     * Creates a matrix with specified row and column sizes and prompts the user for input.
     * The user can enter values for each element of the matrix. 
     * It allows quitting the input process and handles invalid inputs gracefully.
     *
     * @param scanner The Scanner object used for user input.
     * @param matrixNumber The number assigned to the matrix being created (for display purposes).
     * @param rows The number of rows in the matrix.
     * @param cols The number of columns in the matrix.
     * @return The filled matrix as a two-dimensional array of doubles, or null if the user chooses to quit.
     * @author Zeynep Temur
     */
    public static double[][] createMatrix(Scanner scanner, int matrixNumber, int rows, int cols) {
        double[][] matrix = new double[rows][cols];
        boolean[][] isFilled = new boolean[rows][cols];

        clearConsole();
        System.out.println("Please enter the elements of Matrix " + matrixNumber + ": ");
        
        printMatrix(matrix, isFilled); // Prints the empty matrix with placeholders

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double number = -1;
                while (true) {
                    System.out.println("\nEnter [Q] to quit.\n");
                    System.out.print("[" + (i) + "][" + (j) + "]: ");
                    
                    if (scanner.hasNext()) {
                        String input = scanner.next();
                        if ((input.toUpperCase()).equals("Q")) {
                            if (confirmQuit(scanner)) {
                                return null; 
                            } else {
                                clearConsole();
                                System.out.println("Please enter the elements of Matrix " + matrixNumber + ": ");
                                printMatrix(matrix, isFilled); 
                                continue; // Continue to ask for input
                            }
                        }

                        try {
                            number = Double.parseDouble(input);
                            matrix[i][j] = number;
                            isFilled[i][j] = true;
                            
                            clearConsole(); 
                            System.out.println("Please enter the elements of Matrix " + matrixNumber + ": ");
                            printMatrix(matrix, isFilled); 
                            break; 
                        } catch (NumberFormatException exception) {
                            clearConsole();
                            System.out.println("Please enter the elements of Matrix " + matrixNumber + ": ");
                            printMatrix(matrix, isFilled);
                            System.out.println();
                            System.out.println("Invalid input. Please enter a valid number!");
                        }
                    }
                }
            }
        }
        
        return matrix; // Return the filled matrix
    }

    /**
     * Prints the specified matrix to the console in a formatted manner.
     * Each number is formatted to two decimal places, and borders are drawn around the matrix.
     *
     * @param matrix The matrix to be printed. It is a two-dimensional array of doubles.
     * @author Zeynep Temur
     */
    public static void printMatrix(double[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
    
        int rows = matrix.length;
        int cols = matrix[0].length;
    
        // finds the maximum width needed for each column
        int[] colWidths = new int[cols];
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                String num = String.format("%.3f", matrix[i][j]);
                colWidths[j] = Math.max(colWidths[j], num.length());
            }
        }
    
        // top border
        printBorder(colWidths, "top");
    
        // print the matrix contents
        for (int i = 0; i < rows; i++) {
            System.out.print("│");
            for (int j = 0; j < cols; j++) {
                String num = String.format("%.3f", matrix[i][j]);
                int leftPadding = Math.max((colWidths[j] - num.length()) / 2, 0);  // they need to be non-negative for some reason
                int rightPadding = Math.max(colWidths[j] - num.length() - leftPadding, 0); 
    
                System.out.print(" " + " ".repeat(leftPadding) + num + " ".repeat(rightPadding) + " ");
    
                if (j < cols - 1) {
                    System.out.print("│");
                }
            }
            System.out.println("│");
    
            // middle border between rows if not last row
            if (i < rows - 1) {
                printBorder(colWidths, "middle");
            }
        }
    
        // Bottom border
        printBorder(colWidths, "bottom");
    }
    
    /**
     * Prints the specified matrix to the console, showing either the actual values 
     * or placeholders for unfilled elements.
     * Each number is formatted to two decimal places if filled, or displayed as "---" if not.
     *
     * @param matrix The matrix to be printed. It is a two-dimensional array of doubles.
     * @param isFilled A boolean matrix representing which elements have been filled.
     * @author Zeynep Temur
     */
    public static void printMatrix(double[][] matrix, boolean[][] isFilled) {
        if (matrix == null || matrix.length == 0) return;
        System.out.println();

        int rows = matrix.length;
        int cols = matrix[0].length;
    
        // finds the maximum width needed for each column
        int[] colWidths = new int[cols];
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                String num = isFilled[i][j] ? String.format("%.3f", matrix[i][j]) : "---";
                colWidths[j] = Math.max(colWidths[j], num.length());
            }
        }
    
        // top border
        printBorder(colWidths, "top");
    
        // print the matrix contents
        for (int i = 0; i < rows; i++) {
            System.out.print("│");
            for (int j = 0; j < cols; j++) {
                String num = isFilled[i][j] ? String.format("%.3f", matrix[i][j]) : "---";
                int leftPadding = Math.max((colWidths[j] - num.length()) / 2, 0); 
                int rightPadding = Math.max(colWidths[j] - num.length() - leftPadding, 0);  
    
                System.out.print(" " + " ".repeat(leftPadding) + num + " ".repeat(rightPadding) + " ");
    
                if (j < cols - 1) {
                    System.out.print("│");
                }
            }
            System.out.println("│");
    
            // middl border between rows if not last row
            if (i < rows - 1) {
                printBorder(colWidths, "middle");
            }
        }
    
        // bottom border
        printBorder(colWidths, "bottom");
    }
    
    /**
     * Transposes the given matrix.
     *
     * @param mat matrix to be transposed.
     * @return A new matrix that is the transpose of the input matrix.
     * @author Zeynep Kurtulus
     */
    public static double[][] transposeMatrix(double[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        double[][] matTranspose = new double[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matTranspose[j][i] = mat[i][j];
            }
        }
        return matTranspose;
    }

    /**
     * Multiplies two matrices.
     *
     * @param mat1 The first matrix.
     * @param mat2 The second matrix.
     * @return A new matrix that is the product of the two input matrices.
     * @author Zeynep Temur
     */
    public static double[][] multiplyMatrix(double[][] mat1, double[][] mat2) {
        int rows1 = mat1.length;
        int cols1 = mat1[0].length;
        int cols2 = mat2[0].length;

        double[][] matProduct = new double[rows1][cols2];

        for(int i = 0; i < rows1; i++)  //Iterate as many times as the number of rows in mat1
            for (int j = 0; j < cols2; j++)  //Iterate as many times as the number of cols in mat2
                for (int k = 0; k < cols1; k++)
                    matProduct[i][j] += mat1[i][k] * mat2[k][j];

        return matProduct;
    }

    /**
     * Performs element-wise multiplication of two matrices of the same size.
     *
     * @param mat1 The first matrix.
     * @param mat2 The second matrix.
     * @return A new matrix containing the results of the element-wise multiplication.
     * @author Zeynep Temur
     * 
     */
    public static double[][] elementWiseMultiMat(double[][] mat1, double[][] mat2){
        double[][] matResult = new double[mat1.length][mat2[0].length];

        for (int i = 0; i < matResult.length; i++)
            for (int j = 0; j < matResult[0].length; j++) 
                matResult[i][j] = mat1[i][j] * mat2[i][j];

        return matResult;
    }

    /**
     * Computes the inverse of a square matrix using Gaussian elimination.
     *
     * @param matrix The matrix to be inverted.
     * @return The inverse of the input matrix, or null if the matrix is singular or not square.
     * @author Zeynep Kurtulus
     * @author Merve Yildiz
     */
    public static double[][] inverseMatrix(double[][] matrix) {
        int size = matrix.length;

        if (matrix[0].length != size) {
            System.out.println("Matrix must be square!");
            return null;
        }

        //checking the determinant value of matrix
        double matrixDeterminant = calculateDeterminant(matrix);
        if (matrixDeterminant == 0) {
            return null;
        }

        double[][] augmentedMatrix = createAugmentedMatrix(matrix, size);
        return gaussianElimination(augmentedMatrix, size);
    }

    /**
     * Creates an augmented matrix by appending the identity matrix to a given square matrix. This is needed to find the matrix inverse.
     *
     * @param matrix The square matrix to be augmented, which must have dimensions defined by the specified size.
     * @param size The size of the square matrix.
     * @return The augmented matrix formed by combining the input matrix and its identity matrix, with dimensions [size][2 * size].
     * @author Merve Yildiz
     */
    public static double[][] createAugmentedMatrix(double[][] matrix, int size) {
        double[][] identityMatrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            identityMatrix[i][i] = 1;
        }

        double[][] augmentedMatrix = new double[size][2 * size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                augmentedMatrix[i][j] = matrix[i][j];
            }
            for (int j = 0; j < size; j++) {
                augmentedMatrix[i][j + size] = identityMatrix[i][j];
            }
        }
        return augmentedMatrix;
    }

    /**
     * Performs Gaussian elimination on an augmented matrix to achieve reduced 
     * row echelon form (RREF). This method is used to solve systems of linear 
     * equations and compute the inverse of a matrix.
     *
     * Each row is normalized using its pivot, and entries below the pivots 
     * are eliminated. If a zero pivot is encountered, the matrix is declared 
     * singular, indicating it cannot be inverted.
     *
     * @param augmentedMatrix The augmented matrix with the original matrix on the left and the identity matrix on the right.
     *                      
     * @param size The size of the square matrix, representing the number of rows and columns.
     *            
     * @return The matrix in RREF from which the inverse can be extracted, or 
     *         null if the matrix is singular.
     * @author Merve Yildiz
     */
    public static double[][] gaussianElimination(double[][] augmentedMatrix, int size) {
        for (int i = 0; i < size; i++) {

            int maxRow = i;
            double maxElement = Math.abs(augmentedMatrix[i][i]);

            for (int j = i + 1; j < size; j++) {
                if (Math.abs(augmentedMatrix[j][i]) > maxElement) {
                    maxElement = Math.abs(augmentedMatrix[j][i]);
                    maxRow = j; // finding the row with the largest value
                }
            }

            if (maxElement == 0) {
                return null;
            }

            if (maxRow != i) {
                for (int j = 0; j < 2 * size; j++) {
                    double temp = augmentedMatrix[i][j];
                    augmentedMatrix[i][j] = augmentedMatrix[maxRow][j];
                    augmentedMatrix[maxRow][j] = temp;
                }
            }

            double pivotElement = augmentedMatrix[i][i];
            // normalize the current row by dividing by pivot value
            normalizeCurrentRow(augmentedMatrix[i], pivotElement, 2 * size);

            // eliminate current column from all other rows as in gaussian elimination
            eliminateColumn(augmentedMatrix, i, size);
        }

        // get the inverse of the matrix from the right side.
        return getInverseMatrix(augmentedMatrix, size);
    }

    /**
     * Divide a row of the matrix by the pivot element and
     * normalize it so that the pivot element of that row is 1.
     * 
     * @param currentRow The row to be normalized
     * @param pivotElement The pivot element used for normalization
     * @param size The total number of columns in the augmented matrix
     * @author Merve Yildiz
     */
    public static void normalizeCurrentRow(double[] currentRow, double pivotElement, int size) {
        for (int i = 0; i < size; i++) {
            currentRow[i] /= pivotElement;
        }
    }

    /**
     * Eliminates entries in the specified column of the pivot row from all other rows 
     * in the augmented matrix. This process is part of Gaussian elimination,
     * it is needed to create zeros below the pivot in the current column.
     *
     * The method iterates through each row of the matrix. For rows that are not the 
     * pivot row, it calculates a factor based on the pivot element and updates the entries 
     * in that row to eliminate the pivot column's value.
     *
     * @param augmentedMatrix The augmented matrix containing both the original matrix and the identity matrix for inverse calculations.
     * @param pivotRow The index of the row currently serving as the pivot, which determines which column to eliminate.
     * @param size The size of the square matrix, indicating the number of rows and columns in the augmented matrix.
     * @author Merve Yıldız
     */
    public static void eliminateColumn(double[][] augmentedMatrix, int pivotRow, int size) {
        for (int currentRow = 0; currentRow < size; currentRow++) {
            if (currentRow != pivotRow) {
                double factor = augmentedMatrix[currentRow][pivotRow];
                for (int i = 0; i < size * 2; i++) {
                    augmentedMatrix[currentRow][i] -= factor * augmentedMatrix[pivotRow][i];
                }
            }
        }
    }

    /**
     * Extracts the inverse matrix from the augmented matrix after Gaussian elimination.
     *
     * @param augmentedMatrix The augmented matrix containing the inverse
     * @param size The size of the square matrix
     * @return The extracted inverse matrix
     * @author Merve Yildiz
     */
    public static double[][] getInverseMatrix(double[][] augmentedMatrix, int size) {
        double[][] inverseMatrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                inverseMatrix[i][j] = augmentedMatrix[i][j + size];
            }
        }
        return inverseMatrix;
    }

    /**
     * Calculates the determinant of a square matrix using recursion.
     * This method involves calculating the determinant directly for n=1 and n=2,
     * and for larger matrices calculating the minors first and then recursively finding the determinant.
     *
     * @param matrix The square matrix for which the determinant is to be calculated. 
     *
     * @return The determinant of the matrix. Returns 0 if the matrix is not square 
     *         or the determinant calculation fails for any reason.
     *         For a 1x1 matrix, returns the single element.
     *         For a 2x2 matrix, returns the calculated determinant directly.
     * @author Zeynep Kurtulus
     * @author Merve Yildiz
     */
    public static double calculateDeterminant(double[][] matrix) {
        int n = matrix.length;
        double determinant = 0;
        
        if (matrix[0].length != n){
            System.out.println();
            System.out.println("Matrix must be square!");
            return 0;
        }
        
        if (n == 1) {
            return matrix[0][0];
        }
        
        if (n == 2) {
            return (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
        }

        for (int i = 0; i < n; i++) {
            double[][] minorMat = getMinorMatrix(matrix, 0, i);
            determinant += calculatePower(-1, i) * matrix[0][i] * calculateDeterminant(minorMat);
        }
        return determinant;
    }

    /**
     * Retrieves the minor matrix by eliminating the specified row and column from the original matrix.
     * The minor matrix is formed by taking a square matrix and removing one row and one column from
     * original matrix, which results in a smaller matrix.
     *
     * The process involves iterating through each element of the original matrix and copying 
     * it to the minor matrix unless it belongs to the specified row or column. The minor matrix 
     * will have dimensions that are one less than the original matrix.
     * 
     * @param mat The original matrix
     * @param row The index of the row to eliminate
     * @param col The index of the column to eliminate
     * @return The minor matrix obtained by removing the specified row and column. The returned matrix will have dimensions (rows - 1) (cols - 1).
     * @author Zeynep Kurtulus
     * @author Merve Yildiz
     */
    public static double[][] getMinorMatrix(double[][] mat, int row, int col) {
        int size = mat.length;
        double[][] minorMat = new double[size - 1][size - 1];
        int minorRow = 0;

        for (int i = 0; i < size; i++) {
            if (i == row) continue; // skips the row we are eliminating

            int minorCol = 0;
            for (int j = 0; j < size; j++) {
                if (j == col) continue; // skips the column we are eliminating

                minorMat[minorRow][minorCol] = mat[i][j];
                minorCol++;
            }
            minorRow++;
        }
        return minorMat;
    }
    //  ---------- END OF MATRIX OPERATIONS ----------
    
    //  ---------- START OF TEXT CRYPTION ----------
    /**
     * Starts the Text Cryption menu, allowing the user to select between encryption, decryption, or returning to the main menu.
     * @param scanner The scanner object to read user input.
     * @author Volkan Erdogan
     */
    public static void startTextCryption(Scanner scanner) {
        clearConsole();
        char selectedOption;

        while (true) {
            String[] textCryptionMenu = {
                "[A] Encryption",
                "[B] Decryption",
                "[C] Return to main menu"
            };

            int[] colWidths = {findMaxLength(textCryptionMenu)};

            String header = "Text Cryption";
            printHeaderMenu(header, colWidths);

            printBorder(colWidths, "top");
            for (int i = 0; i < textCryptionMenu.length; i++) {
                System.out.print("│ ");
                System.out.print(String.format("%-" + colWidths[0] + "s", textCryptionMenu[i]));
                System.out.println(" │");
            }
            printBorder(colWidths, "bottom");
            System.out.print("Your selection: ");

            String input = scanner.next();
            clearConsole();
            
            if (input.length() == 1) {
                selectedOption = input.toUpperCase().charAt(0);
                boolean shouldReturnToMainMenu = handleTextCryption(selectedOption, scanner);
                if (shouldReturnToMainMenu) {
                    return; // return to main menu by breaking out of the loop.
                }
            } else {
                System.out.println("Invalid selection. Please enter a single character option. \n");
            }
        }
    }

    /**
     * Handles user selection in the Text Cryption menu.
     * 
     * @param option The selected option by the user ('A', 'B', or 'C').
     * @param scanner The scanner object to read user input
     * @return true if the user wants to return to the main menu, false otherwise.
     * @author Volkan Erdogan
     */
    public static boolean handleTextCryption(char option, Scanner scanner) {

        switch (option) {
            case 'A':
                encryptText(scanner);
                return shouldReturnToMainMenu(scanner, "Text Cryption");
            case 'B':
                boolean returnToCryptionMenu = handleDecryptionMenu(scanner);
                if (!returnToCryptionMenu)
                    return shouldReturnToMainMenu(scanner, "Text Cryption");
                return false;
            case 'C':
                return true; // indicates returning to the main menu
            default:
                System.out.println("Invalid selection. Please select a valid option. \n");
                return false; // indicates staying in the current menu.
        }
    }

    /**
     * Handles the Decryption menu, a llowing the user to choose between two decryption options.
     * from history or by manual input.
     * @param scanner The scanner object to read user input.
     * @return true if the user wants to return to the Text Cryption menu, false otherwise.
     * @author Volkan Erdogan
     */
    public static boolean handleDecryptionMenu(Scanner scanner) {
        while (true) {
            String[] textDecryptionMenu = {
                "[A] Select from user history",
                "[B] Enter crypted text manually",
                "[C] Return to cryption menu"
            };

            int[] colWidths = {findMaxLength(textDecryptionMenu)};

            String header = "Decryption Menu";
            printHeaderMenu(header, colWidths);

            printBorder(colWidths, "top");
            for (int i = 0; i < textDecryptionMenu.length; i++) {
                System.out.print("│ ");
                System.out.print(String.format("%-" + colWidths[0] + "s", textDecryptionMenu[i]));
                System.out.println(" │");
            }

            printBorder(colWidths, "bottom");
            System.out.print("Your selection: ");

            String input = scanner.next();
            clearConsole();

            if (input.length() == 1) {
                char option = input.toUpperCase().charAt(0);

                switch (option) {
                    case 'A': 
                        handleHistoryDecryption(scanner);
                        break;
                    case 'B':
                        decryptText(scanner);
                        return false;
                    case 'C':
                        return true;
                    default:
                        System.out.println("Invalid option selected. Please enter a valid option.");
                }
            } else {
                System.out.println("Invalid selection. Please enter a character option. \n");
            }
        }
    }
    
    /**
     * Handles decryption from the encryption history.
     * 
     * @param scanner The scanner object to read user input
     * @author Volkan Erdogan
     */
    public static void handleHistoryDecryption(Scanner scanner) {
        while (true) {
            if (encryptedTexts.isEmpty())  {
                String[] textHistoryMenu = {
                    "[A] Encrypt a text",
                    "[B] Return to cryption menu"
                };
    
                int[] colWidths = {findMaxLength(textHistoryMenu)};

                System.out.println("No decryption history available. Would you like to: ");
                System.out.println();

                printBorder(colWidths, "top");
                for (int i = 0; i < textHistoryMenu.length; i++) {
                    System.out.print("│ ");
                    System.out.print(String.format("%-" + colWidths[0] + "s", textHistoryMenu[i]));
                    System.out.println(" │");
                }

                printBorder(colWidths, "bottom");
                System.out.println();
                System.out.print("Your selection: ");
    
                String input = scanner.next().toUpperCase();
                clearConsole();
    
                switch (input) {
                    case "A":
                        encryptText(scanner);
                        return;
                    case "B":
                        return;
                    default:
                        System.out.println("Invalid option. Please enter a valid option. ");
                        continue;
                }
            }
    
            int[] colWidths = new int[2]; // two columns: number, encrypted text
            for (int i = 0; i < encryptedTexts.size(); i++) {
                String encryptNum = String.format("[%d]", (i + 1));
                String encryptedTextWithLabel = "Encrypted text: " + encryptedTexts.get(i);
                
                colWidths[0] = Math.max(colWidths[0], encryptNum.length());
                colWidths[1] = Math.max(colWidths[1], encryptedTextWithLabel.length());
            }
            
            String header = "Encryption history";
            printHeaderWithTotalPadding(header, colWidths);
            printEncryptionHistory(encryptedTexts, colWidths);
            System.out.println();

            System.out.println("Enter the number of the text you want to decrpyt, or 0 to return.");
            System.out.print("Your selection: ");
            
            while (!scanner.hasNextInt()) {
                clearConsole();
                System.out.println("Invalid selection. Please enter a valid number.");
                printEncryptionHistory(encryptedTexts, colWidths);
            
                scanner.next();
                System.out.print("Your selection: ");
            }

            int choice = scanner.nextInt();
            clearConsole();
            
            if (choice > 0 && choice <= encryptedTexts.size()) {
                String encryptedText = encryptedTexts.get(choice - 1);
                int shiftAmount = shiftAmounts.get(choice - 1);
                String decryptedText = caesarCipher(encryptedText, shiftAmount, true);
    
                int[] decryptedColWidths = new int[2];
                String decryptedTextWithLabel = "Decrypted text: " + decryptedText;
                String shiftWithLabel = "Shift amount: " + shiftAmount;
    
                decryptedColWidths[0] = decryptedTextWithLabel.length();
                decryptedColWidths[1] = shiftWithLabel.length();
                
                printHeaderWithTotalPadding("Decrypted Version", colWidths);

                printBorder(decryptedColWidths, "top");
                System.out.print("│");
                System.out.print(" " + String.format("%-" + decryptedColWidths[0] + "s", decryptedTextWithLabel) + " ");
                System.out.print("│");
                System.out.print(" " + String.format("%-" + decryptedColWidths[1] + "s", shiftWithLabel) + " ");
                System.out.println("│");
                printBorder(decryptedColWidths, "bottom");

            } else if (choice == 0) {
                return;
            } else {
                System.out.println("Invalid selection. Please enter a valid option.");
            }
    
            System.out.println("");
        }
    }

    /**
     * Encrypts the input text using the Caesar cipher with the user-provided shift amount.
     * @param scanner The scanner object to read user input
     * @author Volkan Erdogan
     */
    public static void encryptText(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter string to be encrypted: ");
        String inputString = scanner.nextLine();

        int shiftAmount = validateShiftAmount(scanner);
        String encryptedText = caesarCipher(inputString, shiftAmount, false);

        encryptedTexts.add(encryptedText);
        shiftAmounts.add(shiftAmount);
        
        System.out.println();
        System.out.println("Encrypted string: " + encryptedText);
        System.out.println("The shift amount: " + shiftAmount);
        System.out.println();
    }

    /**
     * Decrypts the input text using the Caesar cipher with the user-provided shift amount.
     * @param scanner The scanner object to read user input
     * @author Volkan Erdogan
     */
    public static void decryptText(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter string to be decrypted: ");
        String inputString = scanner.nextLine();

        int shiftAmount = validateShiftAmount(scanner);
        String decryptedText = caesarCipher(inputString, shiftAmount, true);

        System.out.println("Decrypted string: " + decryptedText);
        System.out.println("The shift amount: " + shiftAmount);
        System.out.println();
    }

    /**
     * Validates the shift amount input by the user, making sure that it is an integer within the range [-26, 26].
     * @param scanner The scanner object to read user input
     * @return A valid integer shift amount.
     * @author Volkan Erdogan
     */
    public static int validateShiftAmount(Scanner scanner) {
        int shiftAmount;
        while (true) {
            System.out.print("Provide an integer shift value within the range [26, -26]: ");
            if (scanner.hasNextInt()) {
                shiftAmount = scanner.nextInt();

                if (shiftAmount <= 26 && shiftAmount >= -26) {
                    break;
                } else {
                    clearConsole();
                    System.out.println("Invalid shift amount. Enter a valid shift amount within the range [26, -26].");
                    scanner.nextLine();
                }
            } else {
                clearConsole();
                System.out.println("Not an integer. Please enter a valid integer.");
                scanner.nextLine();
            }
        }

        return shiftAmount;
    }

    /**
     * Encrypts or decrypts the given input string using the Caesar cipher method.
     * The cipher shifts each letter by a specified amount. Non-letter characters remain unchanged.
     *
     * @param input The string to be encrypted or decrypted, consisting of letters and/or non-letter characters.
     * @param shiftAmount The number of positions to shift each letter. A positive value will encrypt the input, while a negative value will decrypt it.
     * @param isDecrypting A boolean flag indicating whether to decrypt (true) or encrypt (false) the input string.
     * @return The resulting string after applying the Caesar cipher transformation.
     * @author Volkan Erdogan
     */
    public static String caesarCipher(String input, int shiftAmount, boolean isDecrypting) {

        if (isDecrypting) 
            shiftAmount = -shiftAmount;
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {

            char currentCharacter = input.charAt(i);

            if (Character.isLetter(input.charAt(i))) {
                // determine how much we should decrement as ASCII values. (Ex: uppercase A is larger than 'a')
                char decrementValue = Character.isUpperCase(currentCharacter) ? 'A' : 'a';
                // starting from our decrementValue, add the value of the current char + shiftAmount to get the correct shifted character, % 26 to prevent flowing. 
                char shiftedChar = (char) (decrementValue + (currentCharacter - decrementValue + shiftAmount + 26) % 26); 
                result.append(shiftedChar);
            } else {
                result.append(input.charAt(i));
            }
        }

        return result.toString();
    }
    //  ---------- END OF TEXT CRYPTION ----------

    //  ---------- START OF TIC TAC TOE ----------
    /**
    * Initiates Tic-Tac-Toe game and allowing two players to make moves on a 3x3 board until the game ends or a player chose to quit.
    * handles board initialization, user input, validation of moves, 
    * game state updates, and game ending options.
    * @param scanner scanner object to capture user inputs for moves and menu options.
    * @author Ali Eren Konak
    */
    public static void playTicTacToe(Scanner scanner) {
        // Variables for Tic-Tac-Toe
        char[][] board = new char[3][3];
        char currentPlayer = 'X';
        int turns = 0;
        while (true) {
            initBoard(board); // Initialize the board at the start of the game.
            // Resets the game state and sets the starting player and turn.
            Object[] gameStatus = resetGame(board);
            currentPlayer = (char) gameStatus[0];
            turns = (int) gameStatus[1];

            boolean gameRunning = true;
            String errorMessage = ""; // To hold any error message
        
            while (gameRunning) {
                displayBoard(board, turns, errorMessage); // Pass the error message to display
                errorMessage =  ""; // Resets the error message.

                // Get row input from the user.
                int row = getPlayerInput(scanner, "\nPlayer " + currentPlayer + "'s turn. Enter a row between (1-3): ");
                // Check if player wants to quit.
                if (row == -2) {
                    gameRunning = false;
                    break;
                }
                // Get column input from the user.
                int col = getPlayerInput(scanner, "Player " + currentPlayer + "'s turn. Enter a column between (1-3): ");
                // Check if player wants to quit.
                if (col == -2) {
                    gameRunning = false;
                    break;
                }
                // Check move and update the board. Give error message if move is invalid.
                if (isValidMove(row, col) && isAvailableMove(board, row, col)) {
                    board[row][col] = currentPlayer;
                    turns++;
                    // Update the game state and check for win, draw and swith player.
                    Object[] gameState = updateGameState(board, currentPlayer, turns);
                    gameRunning = (boolean) gameState[0];
                    currentPlayer = (char) gameState[1];
                } else {
                    errorMessage = (isAvailableMove(board, row, col)) 
                    ? "---Your move is outside the board boundaries. Please make another move!---" 
                    : "---This index has been taken. Please make another move!---";
                }
            }

             // Menu transition handling
            char selectedOption;
            while (true) {
                System.out.println("\nWould you like to: ");
                System.out.println("[A] Play again");
                System.out.println("[B] Return to main menu");
                System.out.print("Your selection: ");
                
                String input = scanner.next();
                clearConsole();
                
                if (input.length() == 1) {
                    selectedOption = input.toUpperCase().charAt(0);
                    if (selectedOption == 'A')
                        break; // Starts new game.
                    else if (selectedOption == 'B')
                        return; // Returns main menu.
                    else 
                        System.out.println("Invalid selection. Please enter a valid option.");
                } else {
                    System.out.println("Invalid selection. Please enter a single character option.");
                }
            }
        }
    }

    /**
     * Initializes the game board by filling it with empty spaces.
     *
     * @param board The game board to be initialized.
     */
    public static void initBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    /**
     * Displays the current state of the board, number of turns/rounds, and any error messages.
     *
     * @param board The current state of the game board.
     * @param turns The number of turns that have occurred.
     * @param errorMessage Any error messages to display.
     * @author Zeynep Kurtulus
     */
    public static void displayBoard(char[][] board, int turns, String errorMessage) {
        clearConsole();
        if (turns < 10) {
            System.out.println("Move: " + turns);
            System.out.println("Round: " + ((turns + 1) / 2));
        } else {
            System.out.println("End of the game:");
        }
        
        System.out.println("Current board:\n");
        System.out.println("   1   2   3");
    
        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + "  ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 3 - 1) System.out.print(" | ");
            }
            System.out.println();
            if (i < 3 - 1) {
                System.out.println("  ---+---+---");
            }
        }

        if (turns < 10) {
            System.out.println("\nEnter [Q] to quit.");
        }
        
        // Display any error message
        if (!errorMessage.isEmpty()) {
            System.out.println();
            System.out.println(errorMessage);
        }
    }

    /**
     * Checks if the given move is valid based on board boundaries.
     *
     * @param row The row index of the move.
     * @param col The column index of the move.
     * @return true if the move is valid, false otherwise.
     * @author Zeynep Kurtulus
     */
    public static boolean isValidMove(int row, int col) {
        return (row >= 0 && row < 3 && col >= 0 && col < 3 );
    }

    /**
     * Checks if the specified position on the board is available for a move.
     *
     * @param board The current game board.
     * @param row The row index of the move.
     * @param col The column index of the move.
     * @return true if the position is available; false otherwise.
     * @author Zeynep Kurtulus
     */
    public static boolean isAvailableMove(char[][] board, int row, int col) {
        return (board[row][col] == ' ');
    }

    /**
     * Checks if the current player has won the game. It considers all win conditions including rows, colums, and diagonals.
     *
     * @param board The current game board.
     * @param currentPlayer The character representing the current player ('X' or 'O').
     * @return true if the current player has won, false otherwise.
     */
    public static boolean checkWinner(char[][] board, char currentPlayer) {
        // Check rows, columns, and diagonals
        return (checkRows(board, currentPlayer) || checkColumns(board, currentPlayer) || checkDiagonals(board, currentPlayer));
    }

    /**
     * Checks if the current player has any complete rows.
     * This is one of the win conditions.
     * 
     * @param board The current game board.
     * @param currentPlayer The character representing the current player ('X' or 'O').
     * @return true if there is a complete row for the current player, false otherwise.
     */
    public static boolean checkRows(char[][] board, char currentPlayer) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the current player has any complete columns.
     * This is one of the win conditions.
     * 
     * @param board The current game board.
     * @param currentPlayer The character representing the current player ('X' or 'O').
     * @return true if there is a complete column for the current player, false otherwise.
     */
    public static boolean checkColumns(char[][] board, char currentPlayer) {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the current player has any complete diagonals.
     * This is one of the win conditions.
     * 
     * @param board The current game board.
     * @param currentPlayer The character representing the current player ('X' or 'O').
     * @return true if there is a complete diagonal for the current player, false otherwise.
     */
    public static boolean checkDiagonals(char[][] board, char currentPlayer) {
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }

    /**
     * Prompts the player for input and retrieves their selection. It also includes the check for quitting, whether the player wants to exit the game or not.
     *
     * @param scanner The Scanner object for input.
     * @param prompt The prompt message to display to the player. (Ex: Select row: )
     * @return The player's input as an integer (row or col), or -2 if they choose to quit.
     * @author Volkan Erdogan
     */
    public static int getPlayerInput(Scanner scanner, String prompt) {
        int value = -1;
        while (true) {
            System.out.print(prompt);

            if (scanner.hasNext()) {  // first check if there's any input
                String input = scanner.next();
                
                // check for Q first
                if (input.length() == 1 && input.toUpperCase().charAt(0) == 'Q') {
                    if (confirmQuit(scanner)) {
                        return -2;
                    }
                    continue;  // either they said no or invalid input, continue game
                }
                
                // parse the input as integer for player input (row or col)
                try {
                    value = Integer.parseInt(input) - 1;
                    if (value >= 0 && value < 3) {
                        break; // valid input
                    } else {
                        System.out.println("Invalid input. Please enter a number between 1 and " + 3 + ".");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number or Q to quit.");
                }
            }
        }
        return value;
    }

    /**
     * Updates the game state after a player makes a move.
     * Updates both the current player symbol and whether the game has concluded or not.
     * @param board The current game board.
     * @param currentPlayer The character representing the current player ('X' or 'O').
     * @param turns The number of turns that have happened.
     * @return An array containing the game running status (boolean) and the next player character. (Ex: [isGameRunning, currentPlayerSymbol])
     * @author Zeynep Kurtulus
     */
    public static Object[] updateGameState(char[][] board, char currentPlayer, int turns) {
        if (checkWinner(board, currentPlayer)) {
            displayBoard(board, turns, ""); // clear error message before displaying the board
            System.out.println("\n----Player " + currentPlayer + " wins after " + (turns - 1) + " turns!----");
            return new Object[]{false, currentPlayer}; // game over

        } else if (turns == 10) {
            displayBoard(board, turns, ""); 
            System.out.println("\n----It's a tie after " + (turns - 1) + " turns!----");
            return new Object[]{false, currentPlayer};
        }
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        return new Object[]{true, currentPlayer};
    }

    /**
     * Resets the game state for a new game.
     * Sets turns 1, current player to 'X', re-initializes board.
     * 
     * @param board The game board to reset.
     * @return An array containing the initial current player character and the turn count.
     */
    public static Object[] resetGame(char[][] board) {
        char currentPlayer = 'X';
        int turns = 1;
        initBoard(board);
        return new Object[]{currentPlayer, turns};
    }
    //  ---------- END OF TIC TAC TOE ----------
    

    //  ---------- START OF HELPER FUNCTIONS ----------

    /**
     * Clears the console by executing the appropriate command based on the operating system.
     * It uses "cmd /c cls" for Windows and ANSI escape codes for others (Mac, Linux).
     */
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); //clear terminal for windows
            } else {
                System.out.print("\033[H\033[2J"); //clear terminal for others (Mac, Linux etc.)
                System.out.flush();
            }
        }
        catch (Exception error) {
            System.out.println("Error while clearing console: " + error);
        }
    }

    /**
     * Prompts the user to enter a valid dimension for a matrix.
     * The input is repeatedly requested until a positive integer is provided.
     *
     * @param scanner The Scanner object to read user input.
     * @param dimensionType A description of the dimension type (e.g., "rows" or "columns").
     * @param matrixNumber The index of the matrix for display purposes.
     * @return The valid dimension entered by the user.
     * @author Volkan Erdogan
     */
    public static int getValidDimension(Scanner scanner, String dimensionType, int matrixNumber) {
        int dimension = -1;
        while (dimension <= 0) {
            System.out.print("Please enter number of " + dimensionType + " for Matrix " + matrixNumber + ": ");
            if (scanner.hasNextInt()) {
                dimension = scanner.nextInt();
                if (dimension <= 0) {
                    clearConsole();
                    System.out.println("Invalid input. Please enter a positive integer!");
                }
            } else {
                clearConsole();
                System.out.println("Invalid input. Please enter a valid number!");
                scanner.next();
            }
        }
        return dimension;
    }

    /**
     * Asks the user if they want to return to the specified menu or the main menu.
     * Keeps prompting until a valid selection is made.
     *
     * @param scanner The Scanner object to read user input.
     * @param menuName The name of the menu to return to.
     * @return true if the user chooses to return to the main menu, false if returning to the specified menu.
     * @author Volkan Erdogan
     */
    public static boolean shouldReturnToMainMenu(Scanner scanner, String menuName) {
        while (true) {
            System.out.println("Would you like to: ");
            System.out.println("[A] Return to the " + menuName + " menu");
            System.out.println("[B] Return to main menu");
            System.out.print("Your selection: ");

            char retryOption;
            String input = scanner.next();
            if (input.length() == 1) {
                retryOption = input.toUpperCase().charAt(0);
                clearConsole();

                if (retryOption == 'A')
                    return false;
                else if (retryOption == 'B')
                    return true;
                else
                    System.out.println("Invalid selection. Please select a valid option. \n");
            } else {
                clearConsole();
                System.out.println("Invalid selection. Please enter a single character option. \n");
            }
        }
    }

    /**
     * Prompts the user to decide whether to retry an action or not.
     * Keeps prompting until a valid selection is made.
     *
     * @param scanner The Scanner object to read user input.
     * @return true if the user chooses to retry, false if they do not.
     * @author Volkan Erdogan
     */
    public static boolean shouldRetry(Scanner scanner) {
        while (true) {
            System.out.print("\nWould you like to try again? (Y/N): ");
            char retryOption = scanner.next().toUpperCase().charAt(0);
            
            if (retryOption == 'Y') {
                clearConsole();
                return true;
            } else if (retryOption == 'N') {
                clearConsole();
                return false;
            } else {
                System.out.println("Invalid selection. Please enter Y or N.");
            }
        }
    }
    
    /**
     * Prints a border based on the specified border type (top, middle, bottom).
     *
     * @param colWidths An array containing the widths of each column.
     * @param borderType The type of border to print ("top", "middle", "bottom").
     * @author Volkan Erdogan
     */
    // this is a border map, returns the appropiate border style, specified by the borderType
    public static void printBorder(int[] colWidths, String borderType) {

        // object containing characters that are left-aligned
        char leftCharacter = switch (borderType) {
            case "top" -> '┌';
            case "middle" -> '├';
            case "bottom" -> '└';
            default -> '│';
        };
        
        // object containing characters that are right-aligned
        char rightCharacter = switch (borderType) {
            case "top" -> '┐';
            case "middle" -> '┤';
            case "bottom" -> '┘';
            default -> '│';
        };
        
        // object containing characters that are middle-aligned
        char middleCharacter = switch (borderType) {
            case "top" -> '┬';
            case "middle" -> '┼';
            case "bottom" -> '┴';
            default -> '│';
        };
        
        // first print the left char,
        // print middle char as many as the width of the col + 2
        // print the right char.
        System.out.print(leftCharacter); 
        for (int i = 0; i < colWidths.length; i++) {
            System.out.print("─".repeat(colWidths[i] + 2));
            if (i < colWidths.length - 1) {
                System.out.print(middleCharacter);
            } else {
                System.out.print(rightCharacter);
            }
        }
        System.out.println();
    }


    /**
     * Finds the maximum length of strings in an array.
     *
     * @param arr The array of strings to be checked.
     * @return The length of the longest string in the array.
     * @author Volkan Erdogan
     */
    public static int findMaxLength(String[] arr) {
        int maxLength = 0;
        for (int i = 0; i < arr.length; i++) 
            if (arr[i].length() > maxLength) 
                maxLength = arr[i].length();
        return maxLength;
    }

    /**
     * Centers the given text within a specified width by adding padding on both sides.
     *
     * @param text The text to be centered.
     * @param width The total width within which to center the text.
     * @return The centered text with padding.
     * @author Volkan Erdogan
     */
    public static String centerText(String text, int width) {
        int leftPadding = Math.max((width - text.length()) / 2, 0);
        int rightPadding = Math.max(width - text.length() - leftPadding, 0);
        return " ".repeat(leftPadding) + text + " ".repeat(rightPadding);
    }    
        
    /**
     * Displays a menu with options for matrix operations.
     *
     * @param menuOptions An array of menu option strings.
     * @param maxLengthOfMenu The maximum length of the menu options for formatting purposes.
     * @param header The title of the menu to display.
     * @param headerPadding The padding to apply to the header.
     * @author Volkan Erdogan
     */
    public static void displayMatrixMenu(String[] menuOptions, int maxLengthOfMenu, String header, int headerPadding) {
        System.out.println(" ".repeat(headerPadding) + header + " ".repeat(headerPadding));
        // System.out.println();
    
        printBorder(new int[]{maxLengthOfMenu}, "top");
        for (int i = 0; i < menuOptions.length; i++) {
            System.out.println("│ " + menuOptions[i] + " ".repeat(maxLengthOfMenu - menuOptions[i].length()) + " │");
        }
        printBorder(new int[]{maxLengthOfMenu}, "bottom");
    }

    /**
     * Prints the encryption history from a list of encrypted texts.
     *
     * @param encryptedTexts An ArrayList containing the encrypted text entries.
     * @param colWidths An array of column widths for formatting the output.
     * @author Volkan Erdogan
     */
    public static void printEncryptionHistory(ArrayList<String> encryptedTexts, int[] colWidths) {
        printBorder(colWidths, "top");
        for (int i = 0; i < encryptedTexts.size(); i++) {
            String encryptNum = String.format("[%d]", (i + 1));
            String encryptedTextWithLabel = "Encrypted text: " + encryptedTexts.get(i);

            System.out.print("│");
            System.out.print(" " + String.format("%-" + colWidths[0] + "s", encryptNum) + " ");
            System.out.print("│");
            System.out.print(" " + String.format("%-" + colWidths[1] + "s", encryptedTextWithLabel) + " ");
            System.out.println("│");

            if (i < encryptedTexts.size() - 1) 
                printBorder(colWidths, "middle");
            else 
                printBorder(colWidths, "bottom");
        }
    }

    /**
     * Prints a header with appropriate padding based on column widths.
     *
     * @param header The header text to be printed.
     * @param colWidths An array of column widths for calculating total width.
     * @author Volkan Erdogan
     */
    public static void printHeaderWithTotalPadding(String header, int[] colWidths) {
        int totalTableWidth = colWidths[0] + colWidths[1] + 6; 

        int totalPadding = totalTableWidth - header.length();
        int leftPadding = totalPadding / 2;
        int rightPadding = totalPadding - leftPadding;
            
        if (leftPadding > 0)
            System.out.print(" ".repeat(leftPadding));
        System.out.print(header);
            
        if (rightPadding > 0) 
            System.out.print(" ".repeat(rightPadding));
        System.out.println();
    }
    

    /**
     * Prints a centered header menu based on the specified column widths.
     *
     * @param header The header text to be displayed.
     * @param colWidths An array of column widths for formatting the header.
     * @author Volkan Erdogan
     */
    public static void printHeaderMenu(String header, int[] colWidths) {
        int headerPadding = (colWidths[0] - header.length()) / 2;
        System.out.println(" ".repeat(headerPadding) + header + " ".repeat(headerPadding));
    }

    /**
     * Prompts the user for confirmation before quitting the process.
     * Keeps asking until a valid response is received.
     *
     * @param scanner The Scanner object to read user input.
     * @return true if the user confirms quitting, false otherwise.
     * @author Volkan Erdogan
     */
    public static boolean confirmQuit(Scanner scanner) {
        System.out.println();
        System.out.println("Are you sure you want to quit? (Y/N): ");
        while (true) {
            System.out.print("Your selection: ");

            String input = scanner.next();
            
            if (input.length() == 1) {
                char option = input.toUpperCase().charAt(0);
                if (option == 'Y') {
                    return true; // user confirmed quit
                } else if (option == 'N') {
                    return false; // user chose not to quit
                } else {
                    System.out.println("\nInvalid option. Please enter Y or N.");
                }
            } else {
                System.out.println("\nInvalid input. Please only enter Y or N.");
            }
        }
    }
}
