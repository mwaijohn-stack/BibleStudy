package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatsResponseModel {

    @SerializedName("student_id")
    @Expose
    private String studentId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("weeks")
    @Expose
    private String weeks;
    @SerializedName("attended_weeks")
    @Expose
    private String attendedWeeks;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    public String getAttendedWeeks() {
        return attendedWeeks;
    }

    public void setAttendedWeeks(String attendedWeeks) {
        this.attendedWeeks = attendedWeeks;
    }

}