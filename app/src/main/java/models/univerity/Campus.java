package models.univerity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Campus {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("schools")
    @Expose
    private List<SchoolModel> schools = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SchoolModel> getSchools() {
        return schools;
    }

    public void setSchools(List<SchoolModel> schools) {
        this.schools = schools;
    }

}
