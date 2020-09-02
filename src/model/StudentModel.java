package model;

public class StudentModel {
    private int studentId;
    private String studentName;
    private String nic;
    private String gender;
    private String password;

    public StudentModel() {
    }

    public StudentModel(int studentId, String studentName, String nic, String gender, String password) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.nic = nic;
        this.gender = gender;
        this.password = password;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
