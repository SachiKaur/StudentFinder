/**
 * @(#)CheckPeriod.java
 * 
 *
 * @author Jason Lam
 * Description: Checks which day it is and returns the period based on what day it is and what period is needed
 * Date January 4, 2016
 * Method List: CheckPeriod(), void checkDate(), String checkDay(StudentRecord student), void main(String[] args)
 * @version 1.00 2016/1/4
 */
import javax.swing.*; //imports javax.swing package to use for self testing
public class CheckPeriod
{
  
  private int day, period; // declares date, day and period as integers variables and are private data
  private final int NUMOFPERIOD = 5; // declares NUMOFPERIOD as a integer and a private final (cant be changed anywhere in the class)
  private String course [], teacher []; // declares course as a string array
  
  public CheckPeriod()//CheckPeriod constructor
  {
  }
  
  public String checkDay (StudentRecord student)//method that switchs period 1 abd 5 depending on what is 
  {
    day = student.getDay();//day is equal to student record getDay method
    period = student.getPeriod();//period is equal to student record getPeriod method
    course = student.getCourses ();//course is equal to student record getCourse method
    teacher = student.getTeachers();
    if ((period == 1) || (period == 5) || (period == 6))//if period is equal to 1 OR 5
    {
      if (day == 2)//if day is equal to 2 then...
      {
        String tempPeriod;//declare tempPeriod as a string variable
        String tempTeacher;
        //sorting: switches the first course in the array with the last course in the array
        tempPeriod = course [0]; 
        course[0] = course [course.length - 1];
        course [course.length - 1] = tempPeriod;
        
        tempTeacher = teacher [0]; 
        teacher[0] = teacher [course.length - 1];
        teacher [course.length - 1] = tempTeacher;
        
        if (period == 1)//if period is equal to 1 then...
        {
          return course[0];
        }
        if (period == 6)
        {
         student.setCourse (course, teacher); 
        }
        else //if the period isnt equal to period 1
        {
          return course [course.length - 1];
        }
        
        
      }
      else //if day is equal to day 1 
      {
        if (period == 1)//if period is equal to 1 then...
        {
          return course [0];
        }
        else//if the period is not 1
        {
          return course [course.length - 1];
        }
      }
    }
    else if ((period == 2) || (period == 3) || (period == 4))//if the period is equal to 2 OR 3 OR 4
    {
      if  (period == 2)//if period is equal to 2 then...
      {
        return course [1];
      }
      else if (period == 3)//if period is equal to 3 then...
      {
        return course [2];
      }
      else//if period is 4 then...
      {
        return course [3];
      }
    }
    if (period == 6)
    {
      
    }
    return null;//if not any period then return null
  }
  
  public static void main(String[] args) //self testing main
  {
    CheckPeriod check[] = new CheckPeriod[1]; //creates an array for checkPeriod
    
    StudentRecord student [] = new StudentRecord[1];//creates an array for student
    
    student [0] = new StudentRecord ();//calling the constructor
    
    check[0] = new CheckPeriod();//calling the constructor
    
    int day = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter a day (1 or 2)"));//declares date as a integer and allows the user to set its value
    int period =  Integer.parseInt(JOptionPane.showInputDialog(null,"Enter a period"));//declares period as a integer and allows the user to set its value
    int semester = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter a semester"));//declares semsester as a integer and allows the user to set its value
    student[0].setPeriod (period);//method to set period as the period the user enters
    student[0].setDay (day);//method to set date as the date the user enters
    student[0].setSemester (semester);//method to set semester as the semester the user enters
    ArrayLibrary.uploadFile("TimeTables-Oct2015.txt",student,1);//the users finds the course from the txt file using the information above
    
    System.out.println("Period: " + period);//displays the period
    System.out.println("Semester: " + semester);//displays the semeseter
    System.out.println("Day: "+ student[0].getDay());//displays the day 
    System.out.println(check[0].checkDay(student[0]));//displays the course based on period,semester and day
    
  }
}


