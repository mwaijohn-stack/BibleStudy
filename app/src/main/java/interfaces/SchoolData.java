package interfaces;

import java.util.List;

public interface SchoolData {

    public List<String> getUniversities();
    public void getCampus(String university_id);
    public void getSchools(String campus_id);
    public void getCourses(String school_id);
}


