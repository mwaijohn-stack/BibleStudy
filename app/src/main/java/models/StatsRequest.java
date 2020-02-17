package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatsRequest {

    @SerializedName("student_id")
    @Expose
    private String studentId;
    @SerializedName("campus_id")
    @Expose
    private String campusId;

    public StatsRequest(String studentId, String campusId) {
        this.studentId = studentId;
        this.campusId = campusId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCampusId() {
        return campusId;
    }

    public void setCampusId(String campusId) {
        this.campusId = campusId;
    }

}
