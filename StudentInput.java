//Name: Sachkiran Kaur
//Date: Jan 26 2016
//Description: User enters what info he wants about specific students also able to show where the student picture directory is
//Method List: static void main(String[] args)
//           : StudentInput extends JFrame implemts ActionListener 
//           : void actionPerformed(ActionEvent e)
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.io.*;
import javax.imageio.*;
import java.text.NumberFormat;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class StudentInput extends JFrame implements ActionListener {
  
  private JTextArea studentNumberInput; //Declaring variables as private
  private JList dayList,semesterList,periodList;
  private Picture pic1,pic2,picLogo,help1,optionPic;
  private NumberFormat nf;
  private double num,num1,num2,num3,num4,num5;
  private JLabel lblSlogan, lblStudentNumberInput,lblDayList,lblSemesterList,lblDateDay,lblPeriodList,lblDateList,lblPicture,lblExcel,lblSheetName,lblTxtFile,lblTitle;
  private JTextField txtPicture, txtExcel,txtSheetName,txtTxtFile,txtTitle;
  private JScrollPane scroller,scroller1;
  private ButtonGroup group;
  private Color color;
  private JButton btnHelp, btnEnter, btnOption,btnEntr, btnClear;
  private StudentRecord student [];
  private JFrame option,help;
  private StudentOutput output;
  private String array [], studentNum [];
  
  
  public StudentInput() { 
    super ("StudentFinder");
    
    ArrayLibrary.uploadFileInfo ("FileInfo.txt");
    
    try
    {
      pic1 =  new Picture(new ImageIcon(ImageIO.read(getClass().getResource("pic1.png"))),0,0);
      pic1.setBounds (0,0,1366,200); //Initailizing the pictures/buttons and putting it into a certain location
      pic2 =  new Picture(new ImageIcon(ImageIO.read(getClass().getResource("pic2.png"))),0,0);
      pic2.setBounds (0,200,1366,568);
      picLogo = new Picture(new ImageIcon(ImageIO.read(getClass().getResource("logo.png"))),0,0);
      picLogo.setBounds (137,0,200,200);
      btnHelp = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("help.png"))));
      help1 = new Picture(new ImageIcon(ImageIO.read(getClass().getResource("Help1.png"))),0,0);
      help1.setBounds (0,0,1348,725);
      btnOption = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("option.png"))));
      optionPic =  new Picture(new ImageIcon(ImageIO.read(getClass().getResource("options.png"))),0,0);
    }
    catch (Exception d)
    {
      JOptionPane.showMessageDialog (null, d.toString());
      JOptionPane.showMessageDialog (null,"Error: 11");
    }
    
    int [] size = (ArrayLibrary.countLines(ArrayLibrary.getTextFileName()));
    student = new StudentRecord [size[1]]; //Setting the size of the array
    
    for (int i = 0; i < student.length; i++){
      student[i] = new StudentRecord(); //Creating an object
    }
    
    ArrayLibrary.uploadFile(ArrayLibrary.getTextFileName(),student,size[0]); //Putting all the student infomation into each element
    for (int i = 0; i < student.length; i++){
      //System.out.println(student[i].getStudentNumber());
    }

    // System.out.println(ArrayLibrary.getTextFileName());
    btnHelp.setBounds (1300,0,50,50); //Setting where the buttons are located
    btnHelp.setBorder(BorderFactory.createEmptyBorder());
    btnHelp.setContentAreaFilled(false);
    
    btnOption.setBounds (0,0,50,50);
    btnOption.setBorder(BorderFactory.createEmptyBorder());
    btnOption.setContentAreaFilled(false);
    
    btnEnter = new JButton ("Enter"); 
    btnEnter.setBounds (1000,600,200,50);
    btnEntr = new JButton ("Enter");
    
    btnClear = new JButton ("Clear");
    btnClear.setBounds (700,600,200,50);
    
    try
    {
      Font reisenberg = Font.createFont (Font.TRUETYPE_FONT,this.getClass().getClassLoader().getResourceAsStream("Reisenberg.otf")); //Allowing the jar file to contain this font
      Font reisenberg70Pt =reisenberg.deriveFont(70f);
      
      lblSlogan = new JLabel ("Be the Change");
      lblSlogan.setFont(reisenberg70Pt);
      lblSlogan.setForeground(Color.WHITE);
      
      FontMetrics fm = lblSlogan.getFontMetrics(lblSlogan.getFont());
      int width = fm.stringWidth(lblSlogan.getText());
      
      lblSlogan.setHorizontalAlignment(SwingConstants.RIGHT);
      lblSlogan.setBounds (550,38,width,100);
    }
    catch (Exception e)
    {      
      JOptionPane.showMessageDialog (null, e.toString());
      JOptionPane.showMessageDialog (null,"Error: 12");
    }
    
    color = new Color(0,102,0); 
    
    lblStudentNumberInput = new JLabel ("Enter The Student Numbers :"); //Initializing and position the labels
    lblStudentNumberInput.setBounds (100,225,500,50);
    lblStudentNumberInput.setForeground(Color.WHITE); //Changing the label color to white
    lblStudentNumberInput.setFont (lblStudentNumberInput.getFont ().deriveFont (30.0f));
    lblDayList = new JLabel ("Day :"); //Initializing and position the labels
    lblDayList.setBounds (725,215,100,65);
    lblDayList.setForeground(Color.WHITE); //Changing the label color to white
    lblDayList.setFont (lblDayList.getFont ().deriveFont (30.0f));
    lblSemesterList = new JLabel ("Semester :"); //Initializing and position the labels
    lblSemesterList.setBounds (100,370,180,65);
    lblSemesterList.setForeground(Color.WHITE); //Changing the label color to white
    lblSemesterList.setFont (lblSemesterList.getFont ().deriveFont (30.0f));
    lblPeriodList = new JLabel ("Period :"); //Initializing and position the labels
    lblPeriodList.setBounds (100,490,180,65);
    lblPeriodList.setFont (lblPeriodList.getFont ().deriveFont (30.0f));
    lblPeriodList.setForeground(Color.WHITE); //Changing the label color to white
    
    studentNumberInput = new JTextArea (1,1); //Initializing and positioning the txtFields
    studentNumberInput.setBounds (100,295,500,65);
    studentNumberInput.setFont(new Font("Verdana", Font.PLAIN, 20));
    
    scroller = new JScrollPane(studentNumberInput); //Adding a scroller to the JTextArea and setting its location
    scroller.setBounds (100,275,500,65);
    
    
    String semesterData [] = {" 1 "," 2 "}; //Making lists and putting info into the lists
    semesterList = new JList(semesterData); //data has type Object[]
    semesterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    semesterList.setLayoutOrientation(JList.HORIZONTAL_WRAP); //Making the list horizontal
    semesterList.setVisibleRowCount(1);
    semesterList.setFont(semesterList.getFont().deriveFont(20.0f));
    semesterList.setBounds(100,430,54,27); //Setting the location for each list
    semesterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    DefaultListCellRenderer renderer = (DefaultListCellRenderer)semesterList.getCellRenderer();
    renderer.setHorizontalAlignment(JLabel.CENTER);
    
    String dayData [] = {" 1 "," 2 "};
    dayList = new JList(dayData); //data has type Object[]
    dayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    dayList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
    dayList.setVisibleRowCount(1);
    dayList.setFont(dayList.getFont().deriveFont(20.0f));
    dayList.setBounds(725,275,54,27);
    dayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    DefaultListCellRenderer renderer1 = (DefaultListCellRenderer)dayList.getCellRenderer();
    renderer1.setHorizontalAlignment(JLabel.CENTER);
    
    String periodData [] = {" 1 "," 2 "," 3 "," 4 "," 5 "," All "};
    periodList = new JList(periodData); //data has type Object[]
    periodList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    periodList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
    periodList.setVisibleRowCount(1);
    periodList.setFont(periodList.getFont().deriveFont(20.0f));
    periodList.setBounds(100,555,240,27);
    periodList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    DefaultListCellRenderer renderer2 = (DefaultListCellRenderer)periodList.getCellRenderer();
    renderer2.setHorizontalAlignment(JLabel.CENTER);
    
    add (btnClear);
    add (btnOption);
    add (btnEnter); //Adding buttons, Jlabel, jtextarea, picture and jlist into the jframe
    add (btnHelp);
    add (lblPeriodList);
    add (lblSemesterList);
    add (semesterList);    
    add (lblDayList);
    add (dayList);
    add (periodList);
    add (lblStudentNumberInput);
    add (scroller);
    add (lblSlogan);
    add (picLogo);
    add (pic1);
    add (pic2);
    
    btnEnter.addActionListener (this); //Allowing something to happen once a button is clicked
    btnHelp.addActionListener (this);
    btnOption.addActionListener (this);
    btnClear.addActionListener (this);
    btnEntr.addActionListener (this);
    setResizable (false);
    setSize(1366,768);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

  }
  
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == btnHelp) //Help button shows a picture where the person sees what each thing is for
    {
      help = new JFrame("Help");
      help.add(help1);
      help.setSize(1366, 768);
      help.setVisible(true);
      help.setResizable (false);
      
    }
    else if (e.getSource() == btnClear)
    {
      studentNumberInput.setText(""); 
    }
    else if (e.getSource () == btnEnter) //Takes the user to StudentOutput
    {
      array = studentNumberInput.getText().split("\n");
      int number = 0;
      for (int i = 0; i < array.length;i++)
      {
        
        int num = ArrayLibrary.linearSearch (student, array [i]);
        
        if (num == -1)
        {
          JOptionPane.showMessageDialog (null, array[i] + " is not valid student number!");
          number++;
        }
        
        
      }
      ArrayLibrary.downFile ("StudentNumbers.txt",array); //Puts all the student numbers from the JtextArea into a txt file
      if (number == 0)
      {
        output = new StudentOutput ((int)(semesterList.getSelectedIndex() + 1),(int)(dayList.getSelectedIndex() + 1),(int)(periodList.getSelectedIndex() + 1));
        setVisible (false);
      }
    }
    else if (e.getSource () == btnEntr)
    {
      
      ArrayLibrary.downFile ("FileInfo.txt",txtPicture.getText(),txtExcel.getText(),txtSheetName.getText(),txtTxtFile.getText(), txtTitle.getText()); //Puts the settings information into a txt file which is later accesed by the studentoutput
      ArrayLibrary.uploadFileInfo ("FileInfo.txt");
      option.setVisible (false);
      option.dispose();
    }  
    else 
    {
      option = new JFrame("Settings");
      lblPicture = new JLabel ("Enter the Picture Directory "); //Initializing JLabels and JTextFields into specific spots
      lblPicture.setBounds (40,25,400,20);
      lblPicture.setForeground(Color.WHITE);
      txtPicture = new JTextField ("T:\\\\Student Photos\\\\");
      txtPicture.setBounds (40,50,400,20);
      lblExcel = new JLabel ("Enter a name for an excel file (Ex: 'StudentInfo.xls')");
      lblExcel.setBounds (40,85,400,20);
      lblExcel.setForeground(Color.WHITE);
      txtExcel = new JTextField ("StudentInfo.xls");
      txtExcel.setBounds (40,110,400,20);
      lblSheetName = new JLabel ("Enter a name for a sheet in an excel file (Ex: FirstSheet)");
      lblSheetName.setBounds (40,145,400,20);
      lblSheetName.setForeground(Color.WHITE);
      txtSheetName = new JTextField ("First Sheet");
      txtSheetName.setBounds (40,170,400,20);
      lblTxtFile = new JLabel ("Enter the txt file name which contain the student information"); //Initializing JLabels and JTextFields into specific spots
      lblTxtFile.setBounds (40,205,400,20);
      lblTxtFile.setForeground(Color.WHITE);
      txtTxtFile = new JTextField ("TimeTables-Oct2015.txt");
      txtTxtFile.setBounds (40,230,400,20);
      lblTitle = new JLabel ("Enter the title that should be shown on the Excel file"); //Initializing JLabels and JTextFields into specific spots
      lblTitle.setBounds (40,265,400,20);
      lblTitle.setForeground(Color.WHITE);
      txtTitle = new JTextField ("");
      txtTitle.setBounds (40,290,400,20);
      btnEntr.setBounds (200,350,100,50);
      
      option.add (btnEntr); //Adding the Jlabels, pictures and Jtextfield into the option Jframe
      option.add (lblSheetName);
      option.add (txtSheetName);
      option.add (lblPicture);
      option.add (txtPicture);
      option.add (lblExcel);
      option.add (txtExcel);
      option.add (lblTxtFile);
      option.add (txtTxtFile);
      option.add (lblTitle);
      option.add (txtTitle);
      option.add (optionPic);
      
      option.setSize(500, 500); //Setting the size of the option jframe
      option.setVisible(true);
      option.setResizable (false);
      
    }
  }
  public void showInput (int se,int da, int pe)
  {
    setVisible (true);
    studentNum = ArrayLibrary.uploadFile ("StudentNumbers.txt");
    String txt = studentNum [0];
    for (int i = 1; i < studentNum.length; i++)
    {
      txt = txt + "\n" + studentNum[i]; 
    }
    studentNumberInput.setText (txt);
    
    semesterList.setSelectedIndex(se);
    dayList.setSelectedIndex(da);
    periodList.setSelectedIndex(pe);
    
  }
  public static void main(String[] args) { 
    StudentInput myGui = new StudentInput();
  }
  
  /* ADD YOUR CODE HERE */
  
}
