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

    public String getStudentName() {
        return studentName;
    }

    public String getNic() {
        return nic;
    }

    public String getGender() {
        return gender;
    }

    public String getPassword() {
        return password;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
