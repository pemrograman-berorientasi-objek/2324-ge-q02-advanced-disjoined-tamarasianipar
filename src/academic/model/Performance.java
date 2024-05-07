package academic.model;

public class Performance {
    private float gpa;
    private int credit;

    public Performance(float gpa, int credit) {
        this.gpa = gpa;
        this.credit = credit;
    }

    public float getGpa() {
        return gpa;
    }

    public int getCredit() {
        return credit;
    }

}
