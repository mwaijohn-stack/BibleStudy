package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttendanceRequest {

    @SerializedName("student_id")
    @Expose
    private Integer studentId;
    @SerializedName("group_number")
    @Expose
    private Integer groupNumber;
    @SerializedName("grouping_processor_id")
    @Expose
    private Integer groupingProcessorId;

    @SerializedName("academic_year_id")
    @Expose
    private Integer academicYearId;
    @SerializedName("week_number")
    @Expose
    private Integer weekNumber;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("campus_id")
    @Expose
    private Integer campusId;


    public AttendanceRequest(Integer studentId, Integer groupNumber,
                             Integer groupingProcessorId, Integer academicYearId,
                             Integer weekNumber, Integer status, Integer campusId) {

        this.studentId = studentId;
        this.groupNumber = groupNumber;
        this.groupingProcessorId = groupingProcessorId;
        this.academicYearId = academicYearId;
        this.weekNumber = weekNumber;
        this.status = status;
        this.campusId = campusId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }

    public Integer getGroupingProcessorId() {
        return groupingProcessorId;
    }

    public void setGroupingProcessorId(Integer groupingProcessorId) {
        this.groupingProcessorId = groupingProcessorId;
    }

    public Integer getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(Integer academicYearId) {
        this.academicYearId = academicYearId;
    }

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCampusId() {
        return campusId;
    }

    public void setCampusId(Integer campusId) {
        this.campusId = campusId;
    }

}