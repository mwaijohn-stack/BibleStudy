package models;

public class IsRegisteredRequest {
    String student_id;
    String campus_id;

    public IsRegisteredRequest(String student_id, String campus_id) {
        this.student_id = student_id;
        this.campus_id = campus_id;
    }
}
