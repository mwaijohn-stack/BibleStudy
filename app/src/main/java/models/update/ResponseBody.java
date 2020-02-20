package models.update;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseBody {

    @SerializedName("message")
    @Expose
    private models.update.Massage message;

    @SerializedName("status")
    @Expose
    private String status;

    public models.update.Massage getMessage() {
        return message;
    }

    public void setMessage(models.update.Massage message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
