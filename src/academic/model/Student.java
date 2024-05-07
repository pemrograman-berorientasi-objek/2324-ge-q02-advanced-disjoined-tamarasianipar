package academic.model;
import java.util.*;

/**
 * @author 12S22007 Tamara Yunika Sianipar
 * @author 12S22042 Ruth Septiana Simanullang
 */
public class Student extends InHeritance{

    String year;
    String studyProgram;
   List<Performance> performances = new ArrayList<>();

   public Student(String id, String name, String year, String studyProgram) {
       this.id = id;
       this.name = name;
       this.year = year;
       this.studyProgram = studyProgram;
       this.performances = new ArrayList<>();
      
   }

   
   public String getYear() {
       return year;
   }

   public String getStudyProgram() {
       return studyProgram;
   }


   public List<Performance> getPerformance(){
       return performances;
   }

   public void addPerformance(Performance performance){
       performances.add(performance);
   }


   @Override
   public String toString(){
       StringBuilder sb = new StringBuilder();
       for (int i = 0; i < performances.size(); i++) {
           Performance performance = performances.get(i);
           sb.append("|").append(performance.getGpa()).append("|").append(performance.getCredit()); // Fix: Call getInitial() method on student object
           
       }
      return super.toString() + "|" + this.year + "|" + this.studyProgram + sb.toString();
      
   }

   

}