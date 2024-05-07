package academic.model;

import java.util.ArrayList;
import java.util.List;
/**
 * @author 12S22007 Tamara Yunika Sianipar
 * @author 12S22042 Ruth Septiana Simanullang
 */
public class CourseOpening {

    String code;
    String name;
    int credit;
    String pGrade;
    String year;
    String semester;
    List<Lecturer> lecturers = new ArrayList<>();

    public CourseOpening(String code, String name, int credit, String pGrade, String year, String semester) {
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.pGrade = pGrade;
        this.year = year;
        this.semester = semester;
        this.lecturers = new ArrayList<>();
    }
    public boolean isOdd(String code) {
        int number = Integer.parseInt(code);
        return number % 2 != 0;
    }

    public String getCode() {
        return code;
    }

    public String getYear() {
        return year;
    }

    public String getSemester() {
        return semester;
    }

    public List<Lecturer> getLecturers() {
        return lecturers;
    }

    public void addLecturer(Lecturer lecturer) {
        lecturers.add(lecturer);
    }

    // List<Course> courses = new ArrayList<>();



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lecturers.size(); i++) {
            Lecturer lecturer = lecturers.get(i);
            sb.append(lecturer.getInitial()).append(" (").append(lecturer.getEmail()).append(")"); 
            if (i < lecturers.size() - 1) {
                sb.append(";");
            }
        } 

        return this.code + "|" + this.name + "|" + this.credit + "|" + this.pGrade + "|" + this.year + "|" + this.semester + "|" + sb.toString();
        
    }


   

}