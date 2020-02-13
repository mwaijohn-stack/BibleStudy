package utilities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref
{
    private static SharedPreferences mSharedPref;
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String SECOND_NAME = "SECOND_NAME";
    public static final String LAST_NAME = "LAST_NAME";
    public static final String REG_NUMBER = "REG_NUMBER";
    public static final String PHONE_NUMBER = "PHONE_NUMBER";
    public static final String GENDER = "GENDER";
    public static final String UNIVERSITY_ID = "UNIVERSITY_ID";
    public static final String CAMPUS_ID = "CAMPUS_ID";
    public static final String SCHOOL_ID = "SCHOOL_ID";
    public static final String COURSE_ID = "COURSE_ID";
    public static final String HOSTEL_ID = "HOSTEL_ID";
    public static final String ROOM_NUMBER = "ROOM_NUMBER";
    public static final String YEAR_OF_STUDY = "YEAR_OF_STUDY";
    public static final String COUNTY_ID = "COUNTY_ID";

    public static final String STUDENT_ID = "STUDENT_ID";

    private SharedPref()
    {

    }

    public static void init(Context context)
    {
        if(mSharedPref == null)
            mSharedPref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }

    public static String read(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static void write(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
        //prefsEditor.apply();
    }
}
