package models.update;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostUpdateRequest {
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
    @SerializedName("gender")
    @Expose
    private String gender;
//    @SerializedName("id")
//    @Expose
//    private String id;

    public PostUpdateRequest(String firstName, String middleName,
                             String lastName, String registrationNumber, String msisdn, String gender) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.registrationNumber = registrationNumber;
        this.msisdn = msisdn;
        this.gender = gender;
    }

}
