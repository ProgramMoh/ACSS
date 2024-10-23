/*
 * version: 3.1
 * since: 1.0
 *
 */

package edu.ucalgary.oop;
import java.io.*;
public class PrintText {
    private String message;
    public PrintText(String message){
        this.message = message;
    }
    public static void log(String message) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter("Schedule.txt", false), true);
        out.write(message);
        out.close();

    }
}