package academic.model;
/**
 * @author 12S22042 RUTH SEPTIANA SIMANULLANG
 * @author NIM NAMA
 */
public class Lecturer extends InHeritance{
  

     String initial;
     String email;
     String studyProgram;

    public Lecturer(String id, String name, String initial, String email, String studyProgram) {
        this.id = id;
        this.name = name;
        this.initial = initial;
        this.email = email;
        this.studyProgram = studyProgram;
    }

    public String getInitial() {   
        return initial;
    }

    public String getEmail() {
        return email;
    }
 

    public String getStudyProgram() {
        return studyProgram;
    }

    @Override
    public String toString() {
    return super.toString()+ "|" + initial + "|" + email + "|" + studyProgram;
    }

}
