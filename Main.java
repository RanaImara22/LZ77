
import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n");
            System.out.println("Choose an option:");
            System.out.println("1. Compress from console input");
            System.out.println("2. Decompress from console input");
            System.out.println("3. Compress from file to file (same path and name)");
            System.out.println("4. Decompress from file to file (same path and name)");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1/2/3/4/5): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter the text to compress: ");
                String input = scanner.nextLine();
                List<String> compressedData = compression.compress(input);
                System.out.println("Compressed Data: " + compressedData);
            }  else if (choice == 2) {
                System.out.print("Enter the compressed data in the form '<position,length,nextChar>': ");
                String compressedData = scanner.nextLine();
                String decompressedData = Decompression.decompress(compressedData);
                System.out.println("Decompressed Data: " + decompressedData);
            }  else if (choice == 3) {
                compressFromFile();
            } else if (choice == 4) {
                decompressToFile();
            } else if (choice == 5) {
                System.out.println("Exiting the program.");
                break;
            } else {
                System.out.println("Invalid choice. Please choose a valid option.");
            }
        }

        scanner.close();
    }

    private static void compressFromFile() {

        try {
            File file = new File("C:\\testjava\\java.txt");

            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true)); // Append mode

            String line;
            StringBuilder fileContent = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                fileContent.append(line);

            }
            String strcon=fileContent.toString();
            List<String> compressedData = compression.compress(strcon);
            String compressedDataStr = compressedData.toString();

            writer = new BufferedWriter(new FileWriter(file, false));
            writer.write(compressedDataStr);
            writer.newLine();

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

private static void decompressToFile() {
    try {
        File file = new File("C:\\testjava\\java.txt");

        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));

        String line;
        StringBuilder fileContent = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            fileContent.append(line);

        }
        String strcon=fileContent.toString();
        String decompressedData = Decompression.decompress(strcon);
//            String compressedDataStr = compressedData.toString();

        writer = new BufferedWriter(new FileWriter(file, false));
        writer.write(decompressedData);
        writer.newLine();
        reader.close();
        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
