package models.attendance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttendanceRequest {

    @SerializedName("student_id")
    @Expose
    private String studentId;
    @SerializedName("group_number")
    @Expose
    private String groupNumber;
    @SerializedName("grouping_processor_id")
    @Expose
    private String groupingProcessorId;

    @SerializedName("academic_year_id")
    @Expose
    private String academicYearId;
    @SerializedName("week_number")
    @Expose
    private String weekNumber;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("campus_id")
    @Expose
    private String campusId;


    public AttendanceRequest(String studentId, String groupNumber,
                             String groupingProcessorId, String academicYearId,
                             String weekNumber, String status, String campusId) {

        this.studentId = studentId;
        this.groupNumber = groupNumber;
        this.groupingProcessorId = groupingProcessorId;
        this.academicYearId = academicYearId;
        this.weekNumber = weekNumber;
        this.status = status;
        this.campusId = campusId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getGroupingProcessorId() {
        return groupingProcessorId;
    }

    public void setGroupingProcessorId(String groupingProcessorId) {
        this.groupingProcessorId = groupingProcessorId;
    }

    public String getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(String academicYearId) {
        this.academicYearId = academicYearId;
    }

    public String getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(String weekNumber) {
        this.weekNumber = weekNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCampusId() {
        return campusId;
    }

    public void setCampusId(String campusId) {
        this.campusId = campusId;
    }

}