package ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dyvoker.androidresume.R;

import java.util.Calendar;
import java.util.Locale;

/**
 * "About me" page
 */
public class AboutMeFragment extends Fragment {

    public AboutMeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about_me, container, false);

        TextView age = (TextView) rootView.findViewById(R.id.ageText);
        age.setText(String.format(Locale.ENGLISH, "%d", getAge(1993, 9, 23)));

        return rootView;
    }

    private int getAge(int year, int month, int day){
        Calendar dateOfBirth = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        dateOfBirth.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < dateOfBirth.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        return age;
    }
}
