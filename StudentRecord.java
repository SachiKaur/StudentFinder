/**
 * @(#)CheckPeriod.java
 * 
 *
 * @author Sachi Kaur
 * Description:
 * Date January 5, 2016
 * Method List: StudentRecord(), void setNumberOfStudents(int num),int getNumberOfStudents(), void setStudents (String [] array), String [] getStudents (),void setPeriod(int num), int getPeriod()
 * void setDate(int num), int getDate(), void setDay(int num), int getDay(),void setSemester (int num), int getSemester (), String [] getCourses (), void breaker (String str),String getStudentNumber (), String getStudentName (),void main(String[] args)
 *
 * @version 1.00 2016/1/4
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import javax.swing.JTextArea;
public class StudentRecord {
  
  private int numOfStudents,period,date,day,semester; //Declaring variables as private data
  private String studentNumber, name, studentName;
  private String course [], array [], teacher []; //Creating a array
  private final int NUMOFPERIODS = 5; //Making the number of periods FINAL
  private JTextArea text;
  
  public StudentRecord() { 
    course = new String [NUMOFPERIODS]; //Declaring a array
    teacher = new String [NUMOFPERIODS];
  }
  
  public void setNumberOfStudents (int num) //Setting the number of students that the user would like to see
  { 
    numOfStudents = num;
  }
  public int getNumberOfStudents() //Returning the number of students
  {
    return numOfStudents;
  }
  public void setPeriod (int num) //Setting the period that the user needs
  {
    period = num;
  }
  public int getPeriod () //Returning the period
  {
    return period; 
  }
  public void setDate (int num) //Setting the date of the month
  {
    date = num; 
  }
  public int getDate () //Returing the date of the month
  {
    return date; 
  }
  public void setDay (int num) //Setting the day as either day 1 or day 2
  {
    day = num; 
  }
  public int getDay () //Returning the day
  {
    return day;
  }
  public void setSemester (int num) //Setting the semester as either 1 or 2
  {
    semester = num; 
  }
  public int getSemester ()//Returning semester as either 1 or 2
  {
    return semester;
  }
  public String [] getCourses () //Returning the courses depending on the semester
  {
      return course;
  }
  public void setCourse (String str[], String str1 [])
  {
    course = str;
  }
  public String [] getTeachers ()
  {
   return teacher; 
  }
  public void setTeacher (String str[])
  {
   teacher = str; 
  }
  
  public void breaker (String str) //Breaking the string that the arraylibrary sends
  { 
    studentName  = str.substring(1,ArrayLibrary.nthOccurence(str,",",2) - 1);
    studentNumber = str.substring(ArrayLibrary.nthOccurence(str,",",2) + 1, ArrayLibrary.nthOccurence(str,",",2) + 7);
    //System.out.println(studentName + " " + studentNumber);
    String s1 = (str.substring(ArrayLibrary.nthOccurence(str,",",10) + 1,ArrayLibrary.nthOccurence(str,",",18))).replaceAll("[^A-Za-z0-9- ]", " ");
    String s2 = (str.substring(ArrayLibrary.nthOccurence(str,",",19) + 1,ArrayLibrary.nthOccurence(str,",",28))).replaceAll("[^A-Za-z0-9- ]", " ");
    //System.out.println(s1);
    String s = s1;
    if (semester != 1){
      s = s2;
    }
    
    String [] info = (s).split(" ");
    int j = 0;
    for (int i = 1; i < info.length; i++){
      if (info[i].length() > 0){
        course [j] =  info[i];
        teacher [j] = info[i+1] + ", " + info[i+3];
        i = i + 6;
      }
      else {
        course [j] = "Lunch or Spare"; 
        teacher [j] = "N/A";
      }
      j++;
    } 
  }
  
  public String getStudentNumber () //Returns the student number
  { 
    return studentNumber;
  }
  
  public String getStudentName () //Returns the student name
  {
    return studentName; 
  }
  
  public static void main(String[] args) {  //Self testing
     
    StudentRecord student []; //Creating an array for student record
    int [] size = ArrayLibrary.countLines("Harsh.csv"); //Determine how big the array should be depending on how many lines there are in 
    student = new StudentRecord[size[1]]; // Declaring the array
    
    for (int i = 0; i < student.length; i++){ //Initializing the array
      student[i] = new StudentRecord();
    }
    String array []; //Creating an array
    String teacherArray [];
    student[0].setSemester (2); //Setting the semester to the 2nd semester
    ArrayLibrary.uploadFile("Harsh.csv",student,size[0]);  //Reading and and storing the info into studentRecord
    array = student[0].getCourses(); //Getting the all the courses for the 2nd semestter
    teacherArray = student[0].getTeachers();

    for (int i = 0; i < array.length; i++)
    {
      System.out.println(array [i] + " " + teacherArray [i]); //Showing the courses and teachers in 2nd semester
    }
    
    
  }
  /* ADD YOUR CODE HERE */
  
  
}