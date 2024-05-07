package academic.model;

/**
 * @author 12S22007 Tamara Yunika Sianipar
 * @author 12S22042 Ruth Septiana Simanullang
 */
public class Course {

    String code;
    String name;
    int credit;
    String pGrade = "None";
    // List<Lecturer> lecturers = new ArrayList<>();

    public Course(String code, String name, int credit, String pGrade) {
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.pGrade = pGrade;
        // this.lecturers = new ArrayList<>();
    }

    public String getCode() { 
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCredit() {
        return credit;
    }

    public String getPGrade() {
        return pGrade;
    }

    // public List<Lecturer> getLecturers() {
    //     return lecturers;
    // }

    // public void addLecturer(Lecturer lecturer) {
    //     lecturers.add(lecturer);
    // }

    public String toString() {

        return this.code + "|" + this.name + "|" + this.credit + "|" + this.pGrade;
    }

}