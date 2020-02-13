package models;


public class Registration {

    private String first_name;
    private String middle_name;
    private String last_name;
    private String msisdn;
    private String gender;
    private String county_id;
    private String campus_id;
    private String registration_number;
    private String course_id;
    private String new_hostel;
    private String zone_id;
    private String hostel_name;
    private String room;
    private String hostel_id;
    private String year;

    public Registration(){}

    public Registration(String firstName, String middleName, String lastName, String msisdn, String gender,
                        String countyId, String campusId, String registrationNumber, String courseId, String newHostel,
                        String zoneId, String hostelName, String room, String hostelId, String year) {
        this.first_name = firstName;
        this.middle_name = middleName;
        this.last_name = lastName;
        this.msisdn = msisdn;
        this.gender = gender;
        this.county_id = countyId;
        this.campus_id = campusId;
        this.registration_number = registrationNumber;
        this.course_id = courseId;
        this.new_hostel = newHostel;
        this.zone_id = zoneId;
        this.hostel_name = hostelName;
        this.room = room;
        this.hostel_id = hostelId;
        this.year = year;
    }

}