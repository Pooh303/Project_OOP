
//Import section
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class NumGen {

    private File logs;

    public NumGen() {
        logs = new File("Serial.dat");
        if (logs.exists()) {
            try (FileInputStream stream = new FileInputStream(logs); ObjectInputStream ips = new ObjectInputStream(stream);) {
                ips.read();
            } catch (Exception ex) {
//                System.out.println(ex);
            }
        } else {
            try {
                logs.createNewFile();
            } catch (IOException ev) {
//                ev.printStackTrace();
            }
        }
    }

    public String genNumber() {
        int MAX = 9; // Maximum number (0-9)
        int[] drawNum = new int[MAX + 1]; // Adding 1 to the size to match the array indexing

        for (int i = 0; i < drawNum.length; i++) {
            if (i == 0) {
                drawNum[i] = (int) (Math.random() * (MAX - 1)) + 1; // Generate a random number (1-9) for the first index
            } else {
                drawNum[i] = (int) (Math.random() * (MAX + 1)); // Generate a random number (0-9) for the rest of the indexes
            }
        }

        // Format the numbers as a string
        StringBuilder formattedNum = new StringBuilder();
        for (int num : drawNum) {
            formattedNum.append(num);
        }

        String result = formattedNum.toString();
        return result;
//        System.out.println("Formatted number: " + result);
    }

    public static void main(String[] args) {
        System.out.println(new NumGen().genNumber());
    }
}
