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
    String string = str.substring (74); //Takes anything away that is not necessary for the program to run so the string starts at the the 74 character
    String txt [];
    txt = string.split ("\t"); //spliting the string into an array where ever there is a tab
    studentNumber = (txt [7]).substring (0,6); //sets the studentNumber
    studentName = (txt [14]); //sets the studentName
    //System.out.println (txt[11]);
    int num [] = {0,10,11,12,1,3,4,5,6,8}; //The order in which the subjects are in from semester 1 period 1 to semester 2 period 5
    int x = 0; 
    
    if (semester != 1) //Determing if the semester is 1 or 2
    {
      x = 5; 
    }
    
    for (int i = 0; i < NUMOFPERIODS; i++) // i is less than the number of periods there are in a semester
    {   
      String temp = txt [num[x]]; //Getting the length of the temp to see if its a tab or set of words
      int number = temp.length();
      
      if (number == 0) //if its a tab then its either a lunch or a spare
      {
        course [i] = "Lunch or Spare"; 
        teacher [i] = "N/A";
      }
      else //If not is a subject
      {
        course [i] =  (txt[num[x]]).substring (0,7);
        teacher [i] = (txt[num[x]]).substring (7);
      }
      x++;
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
    
    int size = ArrayLibrary.countLines("TimeTables-Oct2015.txt"); //Determine how big the array should be depending on how many lines there are in 
    
    student = new StudentRecord[size]; // Declaring the array
    
    for (int i = 0; i < student.length; i++){ //Initializing the array
      student[i] = new StudentRecord();
    }
    String array []; //Creating an array
    String teacherArray [];
    student[0].setSemester (2); //Setting the semester to the 2nd semester
    ArrayLibrary.uploadFile("TimeTables-Oct2015.txt",student);  //Reading and and storing the info into studentRecord
    array = student[0].getCourses(); //Getting the all the courses for the 2nd semestter
    teacherArray = student[0].getTeachers();

    for (int i = 0; i < array.length; i++)
    {
      System.out.println(array [i]); //Showing the courses in 2nd semester
      System.out.println(teacherArray [i]); //Showing the courses in 2nd semester
    }
    
    
  }
  /* ADD YOUR CODE HERE */
  
  
}