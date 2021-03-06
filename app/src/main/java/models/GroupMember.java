package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GroupMember{
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("middle_name")
    @Expose
    private String middleName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("registration_number")
    @Expose
    private String registrationNumber;
    @SerializedName("msisdn")
    @Expose
    private String msisdn;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("county_id")
    @Expose
    private String countyId;
    @SerializedName("campus_id")
    @Expose
    private String campusId;
    @SerializedName("course_id")
    @Expose
    private String courseId;
    @SerializedName("county")
    @Expose
    private String county;
    @SerializedName("groupname")
    @Expose
    private String groupName;
    @SerializedName("grouping_processor_id")
    @Expose
    private  String groupingProcessorId;
    @SerializedName("role")
    @Expose
    private String role;

    private boolean isSelected = false;


    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    //student id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public String getCampusId() {
        return campusId;
    }

    public void setCampusId(String campusId) {
        this.campusId = campusId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getGroupingProcessorId() {
        return groupingProcessorId;
    }

    public void setGroupingProcessorId(String groupingProcessorId) {
        this.groupingProcessorId = groupingProcessorId;
    }
}
