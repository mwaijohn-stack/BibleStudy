package models.univerity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MassageModel {
    @SerializedName("message")
    @Expose
    private List<UniversityModel> message = null;
    @SerializedName("status")
    @Expose
    private Integer status;

    public List<UniversityModel> getMessage() {
        return message;
    }
    public void setMessage(List<UniversityModel> message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
