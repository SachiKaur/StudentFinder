//Name: Jason and Sachi
//Author: January 26 2016
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import javax.imageio.ImageIO;
import jxl.write.WritableImage;
import jxl.format.Alignment;
import javax.swing.*;
public class ArrayLibrary  {
  
  private static final double CELL_DEFAULT_HEIGHT = 17; //Declaring and initializing variables as private final
  private static final double CELL_DEFAULT_WIDTH = 64;
  private static StudentRecord student; //Declaring variables as private
  private static String sheetname,filename,txtFileName;
  private static WritableWorkbook wworkbook;
  private static String title;
  public static int linearSearch (StudentRecord array [], String searchKey) //Asks for array from LoginRecord and what the user wants to search
  { 
    for (int i = 0; i< array.length; i++)
    {
      if ((array[i].getStudentNumber()).compareToIgnoreCase(searchKey) == 0) //If what the user want to search is the same as that element than it returns where it is in the array
      {
        return i; 
      }
    }
    return - 1; //If it can not find what the user wants to search then it returns -1
  }
  public static int nthOccurence(String str, String substr, int n) {
    int pos = str.indexOf(substr);
    while (--n > 0 && pos != -1)
        pos = str.indexOf(substr, pos + 1);
    return pos;
}
  public static void uploadFile (String inFile, StudentRecord array [],int lines) //method to break the line in the student txt file
  {
    int j = 0;
    try
    { 
      BufferedReader br = new BufferedReader(new FileReader(inFile));//opens file
      String line = br.readLine();
      String studentInfo = "";
      for (int i = 0; i < lines; i++)
      { 
        //System.out.println(line);
        line = br.readLine();
        studentInfo = studentInfo + line + " ";
        if (nthOccurence(line,"JOSH_CROZIER,F",1) != -1){
          //System.out.println("Comes here");
          if (j <= array.length) {
            //System.out.println(studentInfo);
            //System.out.println("Comes here!");
            array[j].breaker(studentInfo); //Breaks the lines into the corresponding info
            //System.out.println("Comes here!!");
            studentInfo = "";
            //System.out.println("Comes here!!!");
            j++;
            //System.out.println("Comes here!!!!");
          }
        }
      }
      br.close (); //Closes the file
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog (null, e.toString());
      JOptionPane.showMessageDialog (null,"Error: 1");
    }
  }
  public static String [] uploadFile (String inFile) //method to set the size of the array
  {
    System.out.println ("Upload Call");
    String [] array = null;
    try
    { 
      BufferedReader fr = new BufferedReader(new FileReader(inFile)); //Opens the file
      int size = Integer.parseInt(fr.readLine()); 
      array = new String [size];//Setting the size of the array 
      
      for (int i = 0; i < array.length; i++)
      {
        array[i] = fr.readLine(); //Reads each line from the txt file
        System.out.println (array[i]);
      }
      fr.close (); //Closes the file
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog (null, e.toString());
      JOptionPane.showMessageDialog (null,"Error: 2");
    }
    return array;
  }
  public static int [] countLines (String file){ //Count the number of lines in a txt file
  
    int size = 0, student = 0;

    try
    { 
      BufferedReader br = new BufferedReader(new FileReader(file));//opens file
      String line = br.readLine();
      while ((line = br.readLine()) != null && !line.isEmpty())
      { 
        size ++;
        if (line.indexOf("JOSH_CROZIER,F") != -1){
          student++; //Adding 1 to size if the string is not null
        }
      }
      br.close();
      int[] A = {size,student};
      return A; //Returning the size of the array
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog (null, e.toString());
      JOptionPane.showMessageDialog (null,"Error: 3");
    }
    
    int[] A = {size,student};
    return A;
  }
  public static void downFile (String outFile,String array []) 
  {
    try 
    {
      PrintWriter fw = new PrintWriter (new FileWriter(outFile)); 
      
      fw.println(array.length); //Putting the size of array
      for (int i = 0 ; i < array.length; i++)
      {
        fw.println(array[i]); //Printing each line of the array into a txt file
      }
      fw.close(); //Closing the file
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog (null, e.toString());
      JOptionPane.showMessageDialog (null,"Error: 4");
    }
  }
  public static void downFile (String outFile,String directory, String excelName, String sheetName, String txtFile, String stitle)  
  {
    try 
    {
      PrintWriter fw = new PrintWriter (new FileWriter(outFile));
      fw.println(directory + "," + excelName + "," + sheetName + "," + txtFile + "," + stitle);
      fw.close();
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog (null, e.toString());
      JOptionPane.showMessageDialog (null,"Error: 5");
    }
  }
  public static String uploadFileInfo (String inFile)// method to open file and set information from the file
  {
    String txt, text [] = null;
    try
    {
      BufferedReader fr = new BufferedReader(new FileReader(inFile)); //Opens the file
      txt = fr.readLine();
      text = txt.split (",");
      filename = text[1];
      sheetname = text[2];
      txtFileName = text[3];
      title = text[4];
      System.out.println (filename + "," + sheetname);
      createFile (text[1],text[2],0);
      fr.close (); //Closes the file
    }
    catch (Exception f)
    {      
      JOptionPane.showMessageDialog (null, f.toString());
      JOptionPane.showMessageDialog (null,"Error: 6");
    }
    return text [0];
  }
  public static String getFileName ()//method to get the name of the file
  {
    System.out.println (filename);
    return filename;
  }
  public static String getSheetName () //method to get the name of the sheet
  {
    System.out.println (sheetname);
    return sheetname;
  }
  public static String getTextFileName ()
  {
    return txtFileName;
  }
  public static void setInfo (int semester, int day, int period, StudentRecord array[])//method to set semester, day and period
  {
    try
    { 
      for (int i = 0; i < array.length; i++)
      {
        array[i].setSemester(semester);
        array[i].setDay(day);
        array[i].setPeriod(period);
      }
    }
    catch (Exception f)
    {
      JOptionPane.showMessageDialog (null, f.toString());
      JOptionPane.showMessageDialog (null,"Error: 7");
    }
  }
  
  public static void createFile (String filename, String sheetname,int num)//method to a new excel file
  {
    try
    {
      WritableWorkbook wworkbook = Workbook.createWorkbook(new File(filename));
      WritableSheet wsheet = wworkbook.createSheet(sheetname,num);
      
      wworkbook.write();
      wworkbook.close();
    }
    catch (Exception f)
    {
      JOptionPane.showMessageDialog (null, f.toString());
      JOptionPane.showMessageDialog (null,"Error: 8");
    }
  }
  
  public static void excelhah(String filename,String sheetname,String studentname,String period,String period1,String period2,String period3,String period4, String picture,int x,int y, int periodNum, String directory, String teacher0, String teacher1, String teacher2, String teacher3, String teacher4) //name is all jason
  {                                                                                                                                                                                                                                                                             //method that creates what is inside the excel file
    StudentRecord student = new StudentRecord ();
    try
    {
      WritableFont titleFont = new WritableFont(WritableFont.TIMES, 20);
      WritableCellFormat ftitle = new WritableCellFormat(titleFont);
      ftitle.setBorder(Border.ALL, BorderLineStyle.THIN);
      ftitle.setAlignment (Alignment.CENTRE);
      
      Workbook workbook = Workbook.getWorkbook(new File(filename)); //file of the excel file is equal to filename
      WritableWorkbook wworkbook = Workbook.createWorkbook(new File(filename), workbook);
      WritableSheet wsheet = wworkbook.getSheet(sheetname);
      
      WritableCellFormat lborder = new WritableCellFormat();
      lborder.setBorder(Border.LEFT, BorderLineStyle.THIN);
      
      WritableCellFormat rborder = new WritableCellFormat();
      rborder.setBorder(Border.RIGHT, BorderLineStyle.THIN);
      
      WritableCellFormat uborder = new WritableCellFormat();
      uborder.setBorder(Border.TOP, BorderLineStyle.THIN);
      
      WritableCellFormat dborder = new WritableCellFormat();
      dborder.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
      
      WritableCellFormat luborder = new WritableCellFormat();
      luborder.setBorder(Border.TOP, BorderLineStyle.THIN);
      luborder.setBorder(Border.LEFT, BorderLineStyle.THIN);
      
      WritableCellFormat urborder = new WritableCellFormat();
      urborder.setBorder(Border.TOP, BorderLineStyle.THIN);
      urborder.setBorder(Border.RIGHT, BorderLineStyle.THIN);
      
      WritableCellFormat ldborder = new WritableCellFormat();
      ldborder.setBorder(Border.LEFT, BorderLineStyle.THIN);
      ldborder.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
      
      WritableCellFormat drborder = new WritableCellFormat();
      drborder.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
      drborder.setBorder(Border.RIGHT, BorderLineStyle.THIN);
      
      wsheet.addCell(new Label(x + 2,3,"",uborder));
      wsheet.addCell(new Label(x + 3,3,"",urborder));
      wsheet.addCell(new Label(x + 2,y,"",uborder));
      wsheet.addCell(new Label(x + 3,y,"",urborder));
      wsheet.addCell(new Label(x,y + 1,"",lborder));
      wsheet.addCell(new Label(x,y + 2,"",lborder));
      wsheet.addCell(new Label(x,y + 3,"",lborder));
      wsheet.addCell(new Label(x,y + 4,"",lborder));
      wsheet.addCell(new Label(x,y + 5,"",lborder));
      wsheet.addCell(new Label(x + 3,y + 1,"",rborder));
      wsheet.addCell(new Label(x + 3,y + 2,"",rborder));
      wsheet.addCell(new Label(x + 3,y + 3,"",rborder));
      wsheet.addCell(new Label(x + 3,y + 4,"",rborder));
      wsheet.addCell(new Label(x + 3,y + 5,"",rborder));
      wsheet.addCell(new Label(x,y + 6,"",ldborder));
      wsheet.addCell(new Label(x + 1,y + 6,"",dborder));
      wsheet.addCell(new Label(x + 2,y + 6,"",dborder));
      wsheet.addCell(new Label(x + 3,y + 6,"",drborder));
      
      wsheet.mergeCells(0, 0, 7, 2);
      Label titleLabel = new Label (0,0, title,ftitle);
      wsheet.addCell (titleLabel);
      Label tlabel = new Label(x, 3,"Name: ",luborder); //creates label
      wsheet.addCell(tlabel); //adds the label to the excel file
      Label tlabel1 = new Label(x+1, 3,"Periods ; Teacher's Name / Room :",uborder);//displays period, and what peroid it is 
      wsheet.addCell(tlabel1);//adds the lavel for period
      Label slabel = new Label(x, y,studentname,luborder);//creates a label for student name
      wsheet.addCell(slabel);//adds the label fo student number
      Label plabel = new Label(x + 1, y ,period + ":" + teacher0,uborder);//creates label for period
      wsheet.addCell(plabel);//adds the label for period
      Label plabel1 = new Label(x + 1, y + 1 ,period1 + ":" + teacher1);//creates label for period
      wsheet.addCell(plabel1);//adds the label for period
      Label plabel2 = new Label(x + 1, y + 2,period2 + ":" + teacher2);//creates label for period
      wsheet.addCell(plabel2);//adds the label for period
      Label plabel3 = new Label(x + 1, y + 3,period3 + ":" + teacher3);//creates label for period
      wsheet.addCell(plabel3);//adds the label for period
      Label plabel4 = new Label(x + 1, y + 4,period4 + ":" + teacher4);//creates label for period
      wsheet.addCell(plabel4);//adds the label for period
      System.out.println ("IT COMES HERE"); 
      System.out.println (filename + "," + sheetname + "," + studentname + "," + period + "," + picture +  "," + x + "," + y + "," + periodNum);//displays information
      
      File imageFile = new File(directory + picture + ".JPG"); //opens picture and reads the picture file
      BufferedImage input = ImageIO.read(imageFile);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ImageIO.write(input, "JPG", baos);
      wsheet.addImage(new WritableImage(x+2,y,128/ CELL_DEFAULT_WIDTH,
                                        120/ CELL_DEFAULT_HEIGHT,baos.toByteArray())); //set the size and location of the picture WritableCellFormat lborder = new WritableCellFormat();
      
      wworkbook.write();
      wworkbook.close();
      workbook.close();
      
    }
    catch (Exception f)
    {
      JOptionPane.showMessageDialog (null, f.toString());
      JOptionPane.showMessageDialog (null,"Error: 10");
    }
  }
  public static void excelhah(String filename,String sheetname,String studentname,String period,String picture,int x,int y, int periodNum, String directory, String teacher) //name is all jason
  {                                                                                                                                                                                                                                                                             //method that creates what is inside the excel file
    StudentRecord student = new StudentRecord ();
    try
    {
      WritableFont titleFont = new WritableFont(WritableFont.TIMES, 20);
      WritableCellFormat ftitle = new WritableCellFormat(titleFont);
      ftitle.setBorder(Border.ALL, BorderLineStyle.THIN);
      ftitle.setAlignment (Alignment.CENTRE);
      
      Workbook workbook = Workbook.getWorkbook(new File(filename)); //file of the excel file is equal to filename
      WritableWorkbook wworkbook = Workbook.createWorkbook(new File(filename), workbook);
      WritableSheet wsheet = wworkbook.getSheet(sheetname);
      
      WritableCellFormat lborder = new WritableCellFormat();
      lborder.setBorder(Border.LEFT, BorderLineStyle.THIN);
      
      WritableCellFormat rborder = new WritableCellFormat();
      rborder.setBorder(Border.RIGHT, BorderLineStyle.THIN);
      
      WritableCellFormat uborder = new WritableCellFormat();
      uborder.setBorder(Border.TOP, BorderLineStyle.THIN);
      
      WritableCellFormat dborder = new WritableCellFormat();
      dborder.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
      
      WritableCellFormat luborder = new WritableCellFormat();
      luborder.setBorder(Border.TOP, BorderLineStyle.THIN);
      luborder.setBorder(Border.LEFT, BorderLineStyle.THIN);
      
      WritableCellFormat urborder = new WritableCellFormat();
      urborder.setBorder(Border.TOP, BorderLineStyle.THIN);
      urborder.setBorder(Border.RIGHT, BorderLineStyle.THIN);
      
      WritableCellFormat ldborder = new WritableCellFormat();
      ldborder.setBorder(Border.LEFT, BorderLineStyle.THIN);
      ldborder.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
      
      WritableCellFormat drborder = new WritableCellFormat();
      drborder.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
      drborder.setBorder(Border.RIGHT, BorderLineStyle.THIN);
      
      wsheet.addCell(new Label(x + 2,3,"",uborder));
      wsheet.addCell(new Label(x + 3,3,"",urborder));
      wsheet.addCell(new Label(x + 2,y,"",uborder));
      wsheet.addCell(new Label(x + 3,y,"",urborder));
      wsheet.addCell(new Label(x,y + 1,"",lborder));
      wsheet.addCell(new Label(x,y + 2,"",lborder));
      wsheet.addCell(new Label(x,y + 3,"",lborder));
      wsheet.addCell(new Label(x,y + 4,"",lborder));
      wsheet.addCell(new Label(x,y + 5,"",lborder));
      wsheet.addCell(new Label(x + 3,y + 1,"",rborder));
      wsheet.addCell(new Label(x + 3,y + 2,"",rborder));
      wsheet.addCell(new Label(x + 3,y + 3,"",rborder));
      wsheet.addCell(new Label(x + 3,y + 4,"",rborder));
      wsheet.addCell(new Label(x + 3,y + 5,"",rborder));
      wsheet.addCell(new Label(x,y + 6,"",ldborder));
      wsheet.addCell(new Label(x + 1,y + 6,"",dborder));
      wsheet.addCell(new Label(x + 2,y + 6,"",dborder));
      wsheet.addCell(new Label(x + 3,y + 6,"",drborder));
      
      wsheet.mergeCells(0, 0, 7, 2);
      Label titleLabel = new Label (0,0, title,ftitle);
      wsheet.addCell (titleLabel);
      Label tlabel = new Label(x, 3,"Name: ",luborder); //creates label
      wsheet.addCell(tlabel); //adds the label to the excel file
      Label tlabel1 = new Label(x+1, 3,"Periods " + periodNum + " ; Teacher's Name / Room :",uborder);//displays period, and what peroid it is 
      wsheet.addCell(tlabel1);//adds the lavel for period
      Label slabel = new Label(x, y,studentname,luborder);//creates a label for student name
      wsheet.addCell(slabel);//adds the label fo student number
      Label plabel = new Label(x + 1, y ,period + ":" + teacher,uborder);//creates label for period
      wsheet.addCell(plabel);//adds the label for period
      System.out.println ("IT COMES HERE"); 
      System.out.println (filename + "," + sheetname + "," + studentname + "," + period + "," + picture +  "," + x + "," + y + "," + periodNum);//displays information
      
      File imageFile = new File(directory + picture + ".JPG"); //opens picture and reads the picture file
      BufferedImage input = ImageIO.read(imageFile);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ImageIO.write(input, "JPG", baos);
      wsheet.addImage(new WritableImage(x+2,y,128/ CELL_DEFAULT_WIDTH,
                                        120/ CELL_DEFAULT_HEIGHT,baos.toByteArray())); //set the size and location of the picture WritableCellFormat lborder = new WritableCellFormat();
      
      wworkbook.write();
      wworkbook.close();
      workbook.close();
      
    }
    catch (Exception f)
    {
      JOptionPane.showMessageDialog (null, f.toString());
      JOptionPane.showMessageDialog (null,"Error: 10");
    }
  }
  public static void closeExcel (String filename,String sheetname)
  {
    try
    {
      Workbook workbook = Workbook.getWorkbook(new File(filename)); //file of the excel file is equal to filename
      WritableWorkbook wworkbook = Workbook.createWorkbook(new File(filename), workbook);
      WritableSheet wsheet = wworkbook.getSheet(sheetname);
      
      wworkbook.close();
      workbook.close();
    }
    catch (Exception d)
    {
      
    }
  }
  public static void main(String[] args) {  //Self Testing
    StudentRecord student [];
    CheckPeriod check = new CheckPeriod ();
    
    int [] size = ArrayLibrary.countLines("Harsh.csv");//size of array is equal to the # of lines in the txt document
    
    student = new StudentRecord[size[1]];
    
    for (int i = 0; i < student.length; i++){
      student[i] = new StudentRecord();
    }
    
    ArrayLibrary.uploadFile("Harsh.csv",student,size[0]);//opens the txt file
    
    String studentNum = JOptionPane.showInputDialog (null, "Enter a student number ('533987')");//declares studentNum as a string and allows the user to enter what student number they want
    System.out.println("Comes here");
    int num = ArrayLibrary.linearSearch (student, studentNum);//searches for the student info using the student number
    System.out.println(num + " " + student + " " + studentNum + " " + student[num]);
    if (num == -1)//if num is equal to -1
    {
      JOptionPane.showMessageDialog (null, "Student Could Not Be Found"); //displays student could not be found
    }
    else//if num isnt equal to -1
    {
      int period = Integer.parseInt(JOptionPane.showInputDialog (null,"Enter what period ('1','2','3','4','5')"));//declares period as a integer and allows the user to enter what period they wnat
      int semester = Integer.parseInt(JOptionPane.showInputDialog (null,"Enter a semester('1','2')"));//declares semester as a integer and allows the user to enter what semester they want
      String choice = JOptionPane.showInputDialog (null, "Would you like to enter the date or day. To enter the date enter 'date' and to enter the day enter 'day'");//declares choice as a string 
      if (choice.equalsIgnoreCase("date"))                                                                                                           //allows the user to choose date or day                  
      { //if date was chosen
        int date = Integer.parseInt(JOptionPane.showInputDialog (null,"Enter a date ('1' - '31')"));//declares date as a integer and allows the user to enter a date
        student[num].setDay (date);//set the day based on the date
      }
      else//if day was chosen
      {
        int day = Integer.parseInt(JOptionPane.showInputDialog (null,"Enter a day ('1','2')"));//delcares int as a integer and allows the user to enter a day
        student[num].setDay (day);//set the day based on the day
      }
      student[num].setPeriod (period);//sets the period based on what the user entered for period
      student[num].setSemester (semester);//sets the semester based on what the user entered for semester
      ArrayLibrary.uploadFile("Harsh.csv",student,size[0]);//opens the txt file containing the student info
      
      String array []; //Creating an array
      array = student[num].getCourses(); //Getting the all the courses for the 2nd semestter
      
      // for (int i = 0; i < array.length; i++)
      // {
      //   System.out.println(array [i]); //Showing the courses in 2nd semester
      // }
      
      String name = student[num].getStudentName ();//declares name as a string and the name is getStudentName method
      String course = check.checkDay(student[num]);//declares course as a string and make it equal to check.checkday(with student[num]) as input 
      System.out.println (course);//displays the course
      ArrayLibrary.createFile ("Student.xls","Kappa",0);//creastes the file 
      //ArrayLibrary.excelhah("Student.xls","Kappa",name,course,studentNum,0,1,period,"null");//calls the excel hah 
    }
    
  }
}

