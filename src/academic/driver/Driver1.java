package academic.driver;

import java.util.*;
import academic.model.*;

/**
 * @author 12S22007 Tamara Yunika Sianipar
 * @author 12S22042 Ruth Septiana Simanullang
 */
public class Driver1 {

    public static void main(String[] _args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        List<Lecturer> lecturers = new ArrayList<>();
        List<Course> courses = new ArrayList<>();
        List<Performance> performances = new ArrayList<>();
        ArrayList<CourseOpening> courseOpenings = new ArrayList<>();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            if (input.equals("---")) {
                // Output semua informasi yang tersimpan
                for (Performance performance : performances) {
                    System.out.println(performance);
                }
                for (Lecturer lecturer : lecturers) {
                    System.out.println(lecturer);
                }
                for (Course course : courses) {
                    System.out.println(course);
                }
                for (Student student : students) {
                    System.out.println(student.getId() + "|" + student.getName() + "|" + student.getYear() + "|" +
                            student.getStudyProgram());
                }
                for (Enrollment enrollment : enrollments) {
                    System.out.println(enrollment);
                }

                // for (CourseOpening courseOpening : courseOpenings) {
                // System.out.println(courseOpening);
                // }

                break;
            }

            String[] inputArr = input.split("#");
            String command = inputArr[0];

            if (command.equals("course-add")) {
                String code = inputArr[1];
                String name = inputArr[2];
                int credit = Integer.parseInt(inputArr[3]);
                String pGrade = inputArr[4];
                // String[] lecturerInitials = inputArr[5].split(",");

                Course course = new Course(code, name, credit, pGrade);
                courses.add(course);

                // menambahkan dosen pengampu ke matakuliah
                // for (String initial : lecturerInitials) {
                // for (Lecturer lecturer : lecturers) {
                // if (lecturer.getInitial().equals(initial)) {
                // course.addLecturer(lecturer);
                // }
                // }
                // }
            } else if (command.equals("student-add")) {
                String id = inputArr[1];
                String name = inputArr[2];
                String year = inputArr[3];
                String studyProgram = inputArr[4];

                boolean isExist = false;
                for (Student student : students) {
                    if (student.getId().equals(id)) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    Student student = new Student(id, name, year, studyProgram);
                    students.add(student);
                }

            } else if (command.equals("enrollment-add")) {
                String code = inputArr[1];
                String id = inputArr[2];
                String year = inputArr[3];
                String semester = inputArr[4];
                Enrollment enrollment = new Enrollment(code, id, year, semester);
                enrollments.add(enrollment);

            } else if (command.equals("lecturer-add")) {
                String id = inputArr[1];
                String name = inputArr[2];
                String initial = inputArr[3];
                String email = inputArr[4];
                String studyProgram = inputArr[5];
                boolean isExist = false;
                for (Lecturer lecturer : lecturers) {
                    if (lecturer.getId().equals(id)) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    Lecturer lecturer = new Lecturer(id, name, initial, email, studyProgram);
                    lecturers.add(lecturer);
                }

            } else if (command.equals("enrollment-grade")) {
                String code = inputArr[1];
                String id = inputArr[2];
                String year = inputArr[3];
                String semester = inputArr[4];
                String grade = inputArr[5];
                for (Enrollment enrollment : enrollments) {
                    if (enrollment.getCode().equals(code) && enrollment.getId().equals(id) &&
                            enrollment.getYear().equals(year) && enrollment.getSemester().equals(semester)) {
                        enrollment.setGrade(grade);
                    }
                }
            } else if (command.equals("student-details")) {
                String studentId = inputArr[1];
                for (Student student : students) {
                    if (student.getId().equals(studentId)) {
                        double gpa = calculateGPA(student, enrollments, courses);
                        int totalCredit = calculateTotalCredit(student.getId(), courses, enrollments);
                        System.out.println(student.getId() + "|" + student.getName() + "|" + student.getYear() + "|" +
                                student.getStudyProgram() + "|" + String.format("%.2f", gpa) + "|" + totalCredit);
                        break;
                    }
                }
            } else if (command.equals("enrollment-remedial")) {
                String code = inputArr[1];
                String id = inputArr[2];
                String year = inputArr[3];
                String semester = inputArr[4];
                String grade = inputArr[5];
                boolean found = false;
                int foundIndex = -1;

                // Mencari enrollment yang sesuai
                for (int i = 0; i < enrollments.size(); i++) {
                    Enrollment enrollment = enrollments.get(i);
                    if (enrollment.getCode().equals(code) && enrollment.getId().equals(id) &&
                            enrollment.getYear().equals(year) && enrollment.getSemester().equals(semester)) {
                        found = true;
                        foundIndex = i;
                        break;
                    }
                }

                // Jika enrollment ditemukan, update grade dan set sebagai remedial
                if (found) {

                    Enrollment enrollment = enrollments.get(foundIndex);
                    if (enrollment.getCode().equals(code) && enrollment.getId().equals(id) &&
                            enrollment.getYear().equals(year) && enrollment.getSemester().equals(semester)
                            && !enrollment.getGrade().equals("None")) {
                        if (!enrollment.Remedial()) {
                            enrollment.setPrevGrade(enrollment.getGrade());
                            enrollment.setGrade(grade);
                            enrollment.setRemedial(true);
                        }
                    }

                } else {
                    // Jika tidak ada enrollment yang sesuai, tambahkan enrollment baru sebagai
                    // remedial
                    Enrollment remedialEnrollment = new Enrollment(code, id, year, semester);
                    remedialEnrollment.setGrade(grade);
                    remedialEnrollment.setRemedial(true);
                    enrollments.add(remedialEnrollment);
                }
            } else if (command.equals("course-open")) {
                String code = inputArr[1];
                String year = inputArr[2];
                String semester = inputArr[3];
                int credit = 0;
                String name = "";
                String pGrade = "";
                String[] lecturerInitialsArr = inputArr[4].split(",");
                List<String> lecturerInitials = Arrays.asList(lecturerInitialsArr);

                for (Course c : courses) {
                    if (c.getCode().equals(code)) {
                        credit = c.getCredit();
                    }
                }
                for (Course c : courses) {
                    if (c.getCode().equals(code)) {
                        name = c.getName();
                    }
                }
                for (Course c : courses) {
                    if (c.getCode().equals(code)) {
                        pGrade = c.getPGrade();
                    }
                }

                CourseOpening courseOpening = new CourseOpening(code, name, credit, pGrade, year, semester);
                courseOpenings.add(courseOpening);

                // menambahkan dosen pengampu ke matakuliah
                for (String initial : lecturerInitials) {
                    for (Lecturer lecturer : lecturers) {
                        if (lecturer.getInitial().equals(initial)) {
                            courseOpening.addLecturer(lecturer);
                        }
                    }
                }
            } else if (command.equals("course-history")) {
                String code = inputArr[1];

                Collections.sort(courseOpenings, new Comparator<CourseOpening>() {
                    @Override
                    public int compare(CourseOpening s1, CourseOpening s2) {
                        return (s2.getSemester().compareTo(s1.getSemester()));
                    }
                });

                for (CourseOpening courseOpening : courseOpenings) {
                    if (courseOpening.getCode().equals(code)) {
                        System.out.println(courseOpening);
                        for (Enrollment enrollment : enrollments) {
                            if (enrollment.getCode().equals(courseOpening.getCode())
                                    && enrollment.getYear().equals(courseOpening.getYear()) &&
                                    enrollment.getSemester().equals(courseOpening.getSemester())) {
                                System.out.println(enrollment);
                            }
                        }
                    }
                }

            } else if (command.equals("find-the-best-student")) {
                String year = inputArr[1];
                String semester = inputArr[2];

                for (Enrollment enrollment : enrollments){
                    if (enrollment.getYear().equals(year) && enrollment.getSemester().equals(semester)){
                        System.out.println(enrollment.getId() + "|" + enrollment.getGrade() + "/" + enrollment.getGrade());
                    }

                }

            } else if (command.equals("add-best-student")) {
                String best = inputArr[1];
            }

        }
        sc.close();
    }

    // Metode untuk menghitung IPK (GPA)
    public static float convertGradeToPoint(String grade) {
        switch (grade) {
            case "A":
                return 4.00f;
            case "AB":
                return 3.50f;
            case "B":
                return 3.00f;
            case "BC":
                return 2.50f;
            case "C":
                return 2.00f;
            case "D":
                return 1.00f;
            case "E":
                return 0.00f;
            default:
                return 0.00f;
        }
    }

    public static float calculateGPA(Student student, List<Enrollment> enrollments, List<Course> courses) {
        List<String> uniqueCourses = new ArrayList<>();
        List<Enrollment> latestEnrollments = new ArrayList<>();

        // Menyimpan nilai terbaru untuk setiap mata kuliah
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId().equals(student.getId())) {
                String code = enrollment.getCode();
                if (!uniqueCourses.contains(code)) {
                    uniqueCourses.add(code);
                }
                latestEnrollments.add(enrollment);
            }
        }

        float totalGradePoint = 0;
        int totalCredit = 0;

        // Menghitung total nilai berdasarkan nilai terbaru
        for (String code : uniqueCourses) {
            String latestGrade = "None";
            for (Enrollment enrollment : latestEnrollments) {
                if (enrollment.getCode().equals(code)) {
                    latestGrade = enrollment.getGrade();
                }
            }

            for (Course course : courses) {
                if (course.getCode().equals(code)) {
                    float gradePoint = convertGradeToPoint(latestGrade);
                    totalGradePoint += gradePoint * course.getCredit();
                    totalCredit += course.getCredit();
                    break;
                }
            }
        }

        if (totalCredit == 0) {
            return 0; // Menghindari pembagian dengan nol
        }

        return totalGradePoint / totalCredit; // Mengembalikan IPK
    }

    public static int calculateTotalCredit(String studentId, List<Course> courses, List<Enrollment> enrollments) {
        List<String> uniqueCourseCodes = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId().equals(studentId)) {
                String code = enrollment.getCode();
                if (!uniqueCourseCodes.contains(code)) {
                    uniqueCourseCodes.add(code);
                }
            }
        }

        int totalCredit = 0;

        for (String code : uniqueCourseCodes) {
            for (Course course : courses) {
                if (course.getCode().equals(code)) {
                    totalCredit += course.getCredit();
                    break;
                }
            }
        }

        return totalCredit;
    }

}
