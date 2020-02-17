package models;

public class AttendanceRequest {

    private String studentId;
    private String groupNumber;
    private String groupingProcessorId;
    private String academicYearId;
    private String weekNumber;
    private String status;
    private String campusId;

    public AttendanceRequest(String studentId, String groupNumber, String groupingProcessorId,
                             String academicYearId, String weekNumber, String status, String campusId) {
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
}
