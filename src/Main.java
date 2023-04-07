import gnu.getopt.Getopt;
import gnu.getopt.LongOpt;

import java.util.Arrays;
import java.util.Scanner;

// example of using Getopt to process command line arguments and option
public class Main {
    public static void main(String[] args) {
        // store the mode for running this program.
        String mode = "";
        // boolean for verbose output.
        boolean b = false;


        // Specify the options we want ot support in this program.
        LongOpt[] longOptions = {
                new LongOpt("help", LongOpt.NO_ARGUMENT, null, 'h'),
                new LongOpt("mode", LongOpt.REQUIRED_ARGUMENT, null, 'm'),
                new LongOpt("verbose", LongOpt.NO_ARGUMENT, null, 'v')

        };
        // make a GetOPt object to process the args variable
        // constructor :name,the args array,"short option string"

        Getopt g = new Getopt("Lab1", args, "hvm:", longOptions);
        // enable error output
        g.setOpterr(true);

        // getOpt will give us one command line option at a time.

        int choice;
        // g.getopt returns the char/int short flag or -1 if there are no more to process
        // choice contains a single command line option for us to process.

        while ((choice = g.getopt()) != -1) {
            // a switch allows us to specify code to execute for various values of a variable (use here instead of
            // if statements)
            switch (choice) {
                case 'h':

                    printHelp(); // if "-h" present in command line print help message and then exit out.
                    System.exit(0); // quit from program
                    break; //needs a break point after every case.


                case 'm':
                    mode = g.getOptarg(); // get the next argument.
                    // error check to ensure a valid mode.
                    if (!mode.equals("average") && !mode.equals("median")) {
                        System.err.println("Error: invalid mode " + mode);
                        System.exit(1);
                    }
                    break;

                case 'v':
                    // if verbose option present then b = true.
                    b = true;
                    break;

                // default
                default:
                    System.err.println("Error: invalid option");
                    System.exit(1);
                    break;
            } // switch


        } // while

        if (mode.equals("")) { // if command line empty
            System.err.println("Error: Mode not specified "); // error message
            System.exit(1);
        }
        Scanner s = new Scanner(System.in); // scanner to read input

        int count = 0;
        if (s.hasNextInt()) { // scanner has integer on first line
            count = s.nextInt();
        } else {
            System.out.println("No data => no statistics!"); // if no integer then no data.
            System.exit(0);
        }
        // array of all double data points
        double[] data = new double[count];

        for (int i = 0; i < count; i++) {
            data[i] = s.nextDouble();


        }
        // if extra data points present (more than the specified amount)
        // print error message
        if (s.hasNextDouble()) {
            System.err.println("Error: Too many inputs");
            System.exit(1);
        }
        // if no data points but 0 is specified in put as number of data points
        // print error message
        if (data.length == 0) {
            System.out.println("No data => no statistics!");
            System.exit(0);
        }

// if b == true then verbose specified.
        if (b) {
            if (mode.equals("average")) {
                System.out.printf("Reading %d numbers.\n", count);
                System.out.printf("Read %d numbers.\n", count);
                double avg = avg(data);
                System.out.printf("Average: %.2f", avg);


            }
            if (mode.equals("median")) {
                System.out.printf("Reading %d numbers.\n", count);
                System.out.printf("Read %d numbers.\n", count);
                double median = median(data);
                System.out.printf("Median: %.1f\n", median);

            }
            // if verbose not specified
        } else {
            if (mode.equals("average")) {
                double avg = avg(data);
                System.out.printf("Average: %.2f", avg);

            }
            if (mode.equals("median")) {
                double median = median(data);
                System.out.printf("Median: %.1f", median);
            }
        }


    }


    // print help method
    private static void printHelp() {
        System.out.println("Usage: java [options] Main [-v][-m average | median] [-h]");
        System.out.println("This program is an example of processing command line arguments with GetOpt");
    }

    // sum method to help with average
    private static double sum2(double[] arr) {
        double s = 0;
        for (int i = 0; i < arr.length; i++) {
            s += arr[i];
        }
        return s;
    }

    // average method to compute average of array of doubles
    private static double avg(double[] arr) {
        return sum2(arr) / arr.length;
    }

    // median method to compute median of array of doubles
    private static double median(double[] arr) {
        // sort the array
        Arrays.sort(arr);
        // if array length is even
        // median = average of middle numbers
        if (arr.length % 2 == 0) {
            return (arr[arr.length / 2] + arr[arr.length / 2 - 1]) / 2.0;
            // if array is odd
            // median =  middle number
        } else {
            return arr[arr.length / 2];
        }
    }


}
