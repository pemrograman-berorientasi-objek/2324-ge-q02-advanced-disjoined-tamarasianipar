package academic.model;

import java.util.ArrayList;


/**
 * @author 12S22007 Tamara Yunika Sianipar
 * @author 12S22042 Ruth Septiana Simanullang
 */
public class Enrollment {
    String code;
    String id;
    String grade = "None";
    String semester;
    String year;
    String prevGrade;
    boolean remedial;
   ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();


   public Enrollment(String code, String id, String year, String semester, String grade, String gradeRemedial, String remedialGrade) {
       this.code = code;
       this.id = id;   
       this.grade = grade ;
       this.semester = semester;
       this.year = year;
       this.prevGrade = prevGrade; 
       this.remedial = false;
       
       
   }

   public String getCode() {
       return code;
   }    

   public String getId() {
       return id;
   }

   public String getGrade() {
       return grade;
   }

   public void setGrade(String grade) {
       this.grade = grade;
   }


   public String getSemester() {
       return semester;
   }   

   public String getYear() {
       return year;
   }

   public String getPrevGrade() {
       return prevGrade;
   }

   public void setPrevGrade(String prevGrade) {
       this.prevGrade = prevGrade;
   }

   public boolean Remedial() {
       return remedial;
   }

   public void setRemedial(boolean remedial) {
       this.remedial = remedial;
   }

   public void addEnrollment(Enrollment enrollment){
       enrollments.add(enrollment);
   }

   @Override
   public String toString(){
       if (remedial){
           return code+ "|" + id + "|" + year+ "|" + semester + "|" + grade + "(" + prevGrade + ")";
       }
       return code+ "|" +id + "|" + year+ "|" + semester + "|" + grade ;

       }
   //constructor
   public Enrollment(String code, String id, String year, String semester) {
       this.code = code;
       this.id = id;
       this.semester = semester;
       this.year = year;
     

   }
}
