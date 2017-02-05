//Name: Sachkiran Kaur
//Date: Jan 26 2016
//Description: Shows the students picture, name and scheldule
//Method List: BufferedImage resize(BufferedImage image, int width, int height)
//           : StudentOutput (int s, int d, int p)
//           : static void main(String[] args)
//           : void actionPerformed(ActionEvent e)
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.io.*;
import javax.imageio.*;
import java.text.NumberFormat;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;
public class StudentOutput extends JFrame implements ActionListener{ //Implementing ActionListener
  
  private Picture pic1,pic2,picLogo,studentPic; //Declaring variables as private
  private JLabel lblSlogan, lblStudentName, lblPeriod,lblPeriod1,lblPeriod2,lblPeriod3,lblPeriod4;
  private StudentRecord student [];
  private JLabel lblPic;
  private CheckPeriod check;
  private String course [],studentNum [], file, teacher [];
  private int number, x, period, semester, day;
  private BufferedImage ci,bi,resizedimage;
  private Graphics2D g2d;
  private ImageIcon iconimage,resizedicon;
  private Graphics g;
  private JButton next, btnEnter, back, home;
  private boolean first;
  private StudentInput gui;
  private int se,da,pe;
  public StudentOutput(int s, int d, int p) {
    
    super ("StudentFinder"); // Jframe's header
    gui = new StudentInput ();
    gui.setVisible(false);
    int size = ArrayLibrary.countLines(ArrayLibrary.getTextFileName()); //Getting how many students are in the school
    student = new StudentRecord[size]; //Setting the size of the StudentRecord array
    check = new CheckPeriod (); // Creating an object for CheckPeriod
    for (int i = 0; i < student.length; i++)
    {
      student[i] = new StudentRecord(); //Creating an array of object for StudentRecord
    }
    ArrayLibrary.setInfo (s,d,p,student); //Setting the period, semester and day 
    //System.out.println ("Period:" + p);
    ArrayLibrary.uploadFile(ArrayLibrary.getTextFileName(),student); //Putting each student's info into each element of the student record array
    se = s - 1;
    pe = p - 1;
    da = d - 1;
    
    x = 0;
    
    file  = ArrayLibrary.uploadFileInfo ("FileInfo.txt"); //Getting the directory for the pictures
    
    try{
      pic1 = new Picture(new ImageIcon(ImageIO.read(getClass().getResource("pic1.png"))),0,0);
      pic1.setBounds (0,0,1366,200);
      pic2 = new Picture(new ImageIcon(ImageIO.read(getClass().getResource("pic2.png"))),0,0);
      pic2.resetColor (0,102,0);
      picLogo = new Picture(new ImageIcon(ImageIO.read(getClass().getResource("logo.png"))),0,0);
      picLogo.setBounds (137,0,200,200);
      next = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("next.png"))));
      back = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("back.png"))));
      home = new JButton (new ImageIcon (ImageIO.read (getClass().getResource ("Home.png"))));
    }
    catch (Exception e){
      JOptionPane.showMessageDialog (null, e.toString());
      JOptionPane.showMessageDialog (null,"Error: 13");
    }
    
    next.setBounds (1100,370,86,88); //Setting the location of the next button
    next.setBorder (BorderFactory.createEmptyBorder());
    back.setBounds (950,370,86,88); //Setting the location of the next button
    back.setBorder (BorderFactory.createEmptyBorder());
    btnEnter = new JButton ("Save As Excel File"); // Setting the location for the enter button
    btnEnter.setBounds (925,500,300,50);
    home.setBounds (1300,0,50,50); //Setting the location of the next button
    home.setBorder (BorderFactory.createEmptyBorder());
    
    try
    {
      Font reisenberg = Font.createFont (Font.TRUETYPE_FONT,this.getClass().getClassLoader().getResourceAsStream("Reisenberg.otf")); //Creating the font and putting it into the jar file
      Font reisenberg70Pt =reisenberg.deriveFont(70f);// Creating a font with a size of 70
      
      lblSlogan = new JLabel ("Be the Change"); //Using the font writing the slogan
      lblSlogan.setFont(reisenberg70Pt);
      lblSlogan.setForeground(Color.WHITE);
      
      FontMetrics fm = lblSlogan.getFontMetrics(lblSlogan.getFont()); //Determining the length of the slogan so that it could be placed at a specific spot
      int width = fm.stringWidth(lblSlogan.getText());
      
      lblSlogan.setHorizontalAlignment(SwingConstants.RIGHT);
      lblSlogan.setBounds (550,38,width,100); 
    }
    catch (Exception e)
    {      
      JOptionPane.showMessageDialog (null, e.toString());
      JOptionPane.showMessageDialog (null,"Error: 14");
    }
    
    
    // System.out.println(period); 
    studentNum = ArrayLibrary.uploadFile ("StudentNumbers.txt"); //Getting the numbers that the user wants
    
    lblPic = new JLabel (); //Initializing JLabels and setting into a specific spots
    lblPic.setBounds(500,245,320,400);
    
    lblStudentName = new JLabel ();
    lblStudentName.setBounds(100,245,500,20);
    
    lblPeriod = new JLabel ();
    lblPeriod.setBounds(100,270,600,20);
    lblPeriod1 = new JLabel ();
    lblPeriod1.setBounds(100,295,600,20);
    lblPeriod2 = new JLabel ();
    lblPeriod2.setBounds(100,320,600,20);
    lblPeriod3 = new JLabel ();
    lblPeriod3.setBounds(100,345,600,20);
    lblPeriod4 = new JLabel ();
    lblPeriod4.setBounds(100,370,600,20);
    
    lblStudentName.setForeground(Color.WHITE); //Making the JLabels white
    lblPeriod.setForeground(Color.WHITE);
    lblPeriod1.setForeground(Color.WHITE);
    lblPeriod2.setForeground(Color.WHITE);
    lblPeriod3.setForeground(Color.WHITE);
    lblPeriod4.setForeground(Color.WHITE);
    
    iconimage = new ImageIcon(); //initializing image Icon
    
    showStudentInfo (x); //Showing the first students info
    x++;
    add (btnEnter); //Adding all the labels, btns and pictures to the jframe
    add (next);
    add (back);
    add (home);
    add(lblPic);
    add(lblStudentName);
    add(lblPeriod);
    add(lblPeriod1);
    add(lblPeriod2);
    add(lblPeriod3);
    add(lblPeriod4);
    add(lblSlogan);
    add(picLogo);
    add(pic1);
    add(pic2);
    
    btnEnter.addActionListener (this); //Allowing something to happen when a button is clicked
    next.addActionListener (this);
    back.addActionListener (this);
    home.addActionListener (this);
    //System.out.println (screenHeight + " " +  screenWidth);
    setSize(1366, 768);
    setVisible(true);
    setResizable (false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
  public void actionPerformed(ActionEvent e) //Listening to the buttons being clicked
  {
    if (e.getSource() == home) //If the next button is clicked then this will happen
    {      
      setVisible (false);
      gui.showInput(se,da,pe);
      
      JOptionPane.showMessageDialog(null,
                 "Please close the excel file before using the Student Finder again.");
    }
    else if (e.getSource() == next) //If the next button is clicked then this will happen
    {      
      if (x == studentNum.length)
      {
        JOptionPane.showMessageDialog (null, "There is no more students to be shown");  // If the user has gone through all the student's info
      }
      else 
      {
        showStudentInfo(x); //If not then it shows the other students info everytime the button is clicked
        x++;
      }
    }
    else if (e.getSource() == back) //If the next button is clicked then this will happen
    {      
      if (x == -1)
      {
        JOptionPane.showMessageDialog (null, "There is no more students to be shown");  // If the user has gone through all the student's info
      }
      else 
      {
        x--;
        showStudentInfo(x); //If not then it shows the other students info everytime the button is clicked
      }
    }
    else  //Exports this info into an excel file
    {
      int y = 4;
      int x = 0;
      first = true;
      for (int i = 1; i <= studentNum.length; i++)//Until all students info is put into the excel file
      {
        number = ArrayLibrary.linearSearch (student, studentNum[i - 1]); 
        if (first == true)
        {
          x = 0;
          first = false;
        }
        else 
        {
          x = 4;
          first = true;
        }
        
        if (student[number].getPeriod() != 6) //If the user want a certain period 
        { 
          System.out.print(pe);
          ArrayLibrary.excelhah(ArrayLibrary.getFileName(),ArrayLibrary.getSheetName(),student[number].getStudentName(),course[pe],student[number].getStudentNumber(),x,y,pe + 1,file,teacher[pe]);
        }
        else 
        {
          ArrayLibrary.excelhah(ArrayLibrary.getFileName(),ArrayLibrary.getSheetName(),student[number].getStudentName(),course[0],course[1],course[2],course[3],course[4],student[number].getStudentNumber(),x,y,1,file,teacher[0],teacher[1],teacher[2],teacher[3],teacher[4]);
        }
        
        if ((i % 2 == 0))
        {
          y  = y + 7; 
          System.out.println (y + "," + i + "," + i%2);
        }
        
        
      }
      JOptionPane.showMessageDialog (null, "The excel file has been upgraded!");
    }
  }
  public void showStudentInfo (int x)
  {
    number = ArrayLibrary.linearSearch (student, studentNum[x]);  //Looks for the student number in the txt file
    System.out.println (x);
    System.out.println (student[number].getStudentName());
    course = student[number].getCourses(); //Gets all the courses for that student
    teacher = student [number].getTeachers();
    lblStudentName.setText("Student Name: " + student[number].getStudentName()); //Shows this on the JFrame
    lblStudentName.setBounds(100,245,500,20); //Sets the location of the studentName and the color that is displayed
    lblStudentName.setForeground(Color.WHITE);
    
    //System.out.println (student[number].getPeriod());
    if (student[number].getPeriod() != 6) //If the user wants a certain period this happens
    {
      lblPeriod.setText("Period " + student[number].getPeriod() + ": " + check.checkDay(student[number]) + ", Teacher / Room: " + teacher [student[number].getPeriod() - 1]); //Shows the Period number and the course code for that student
      lblPeriod.setBounds(100,250,600,50); //Sets this into a certain location
      lblPeriod.setForeground(Color.WHITE);
      }
    else
    {
      check.checkDay(student[number]); //If the user wants all courses then it sets the JLabels into a certain location and makes the txt white
      lblPeriod.setText("Period 1: " + course [0] + ", Teacher / Room: " + teacher [0]);
      lblPeriod.setForeground(Color.WHITE);
      lblPeriod1.setText("Period 2: " + course [1] + ", Teacher / Room: " + teacher [1]);
      lblPeriod1.setForeground(Color.WHITE);
      lblPeriod2.setText("Period 3: " + course [2] + ", Teacher / Room: " + teacher [2]);
      lblPeriod2.setForeground(Color.WHITE);
      lblPeriod3.setText("Period 4: " + course [3] + ", Teacher / Room: " + teacher [3]);
      lblPeriod3.setForeground(Color.WHITE);
      lblPeriod4.setText("Period 5: " + course [4] + ", Teacher / Room: " + teacher [4]);
      lblPeriod4.setForeground(Color.WHITE);
    }
    
    iconimage = new ImageIcon (file + student[number].getStudentNumber() + ".jpg"); //Getting the picture
    bi = new BufferedImage(640, 800, BufferedImage.TYPE_INT_ARGB);
    g = bi.createGraphics();
    iconimage.paintIcon(null, g, 0,0);
    g.dispose(); //Disposing of graphics
    resizedimage= resize(bi,320, 400);
    resizedicon=new ImageIcon(resizedimage); //Using this method
    lblPic.setIcon(resizedicon); //Setting the Pic JLabel as the picture
    }
  
  public BufferedImage resize(BufferedImage image, int width, int height) {
    ci = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
    g2d = (Graphics2D) ci.createGraphics();
    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
    g2d.drawImage(image, 0, 0, width, height, null);
    g2d.dispose();
    return ci;
  }
  
  public static void main(String[] args) {
    StudentOutput myGui = new StudentOutput(1,1,1); //Starts the StudentOutput if run has a self testing method
  }
  }
