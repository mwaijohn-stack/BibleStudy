package models.attendance;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AttendanceDataSend {

    @SerializedName("data")
    @Expose
    private List<AttendanceRequest> data = null;

    public List<AttendanceRequest> getData() {
        return data;
    }

    public void setData(List<AttendanceRequest> data) {
        this.data = data;
    }


}