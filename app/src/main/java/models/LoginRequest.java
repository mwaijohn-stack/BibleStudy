package models;

public class LoginRequest {
    String msisdn;
    String registration_number;

    public LoginRequest(String msisdn, String registration_number) {
        this.msisdn = msisdn;
        this.registration_number = registration_number;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }
}
