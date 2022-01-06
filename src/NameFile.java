import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NameFile {

  public static List<List<String>> firstLinesBlack;
  public static List<List<String>> firstLinesWhite;
  public static List<List<String>> lastLines;


  public static void getFirstNames(){
    getFirstNamesBlack();
    getFirstNamesWhite();
  }

  private static void getFirstNamesBlack(){
    String fileName = "BlackNames.csv";
    File file= new File(fileName);

    // this gives you a 2-dimensional array of strings
    Scanner inputStream;

    try{
      inputStream = new Scanner(file);
      firstLinesBlack = new ArrayList<>();
      while(inputStream.hasNext()){
        String line= inputStream.next();
        String[] values = line.split(",");
        // this adds the currently parsed line to the 2-dimensional string array
        firstLinesBlack.add(Arrays.asList(values));
      }

      inputStream.close();
    }catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }

  private static void getFirstNamesWhite(){
    String fileName = "WhiteNames.csv";
    File file= new File(fileName);

    // this gives you a 2-dimensional array of strings
    Scanner inputStream;

    try{
      inputStream = new Scanner(file);
      firstLinesWhite = new ArrayList<>();
      while(inputStream.hasNext()){
        String line= inputStream.next();
        String[] values = line.split(",");
        // this adds the currently parsed line to the 2-dimensional string array
        firstLinesWhite.add(Arrays.asList(values));
      }

      inputStream.close();
    }catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }

  public static void getLastNames(){
    String fileName= "lastNames.csv";
    File file= new File(fileName);

    // this gives you a 2-dimensional array of strings
    Scanner inputStream;

    try{
      inputStream = new Scanner(file);
      lastLines = new ArrayList<>();
      while(inputStream.hasNext()){
        String line= inputStream.next();
        String[] values = line.split(",");
        // this adds the currently parsed line to the 2-dimensional string array
        lastLines.add(Arrays.asList(values));
      }

      inputStream.close();
    }catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }

}

