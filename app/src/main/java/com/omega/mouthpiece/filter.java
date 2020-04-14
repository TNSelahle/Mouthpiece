package com.omega.mouthpiece;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
public class filter extends AppCompatActivity
{
    private Spinner dropdownSortBy, dropdownRatings;
    public  String sortCriteria, ratingCriteria;
    private Button apply, cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        sortCriteria="none";
        ratingCriteria="none";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sort_fragment);
        setDropdownContents();
        apply=findViewById(R.id.fragment_button_apply);
        cancel=findViewById(R.id.fragment_button_cancel);
        apply.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                filter();//Set the public variables to the selected values
                Intent intent = new Intent(getApplicationContext(), MouthSelection.class);
                intent.putExtra("sort", sortCriteria);
                intent.putExtra("rating", ratingCriteria);
                //the two lines above provide extra content so we can access the filter variable data from the other screen.
                startActivity(intent);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                sortCriteria="none";
                ratingCriteria="none";
                //Clear the criteria for any sorting and return to the mouth selection screen
                Intent intent = new Intent(getApplicationContext(), MouthSelection.class);
                startActivity(intent);
            }
        });
    }

    public void setDropdownContents()
    {
        dropdownSortBy = (Spinner) findViewById(R.id.fragment_dropdown_sort);
        List<String> sortList = new ArrayList<String>();
        sortList.add("Name");
        sortList.add("Downloads");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, sortList);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        dropdownSortBy.setAdapter(dataAdapter);

        dropdownRatings = (Spinner) findViewById(R.id.fragment_dropdown_type);
        List<String> ratingsList = new ArrayList<String>();
        ratingsList.add("1");
        ratingsList.add("2");
        ratingsList.add("3");
        ratingsList.add("4");
        ratingsList.add("5");
        ratingsList.add("6");
        ratingsList.add("7");
        ratingsList.add("8");
        ratingsList.add("9");
        ratingsList.add("10");
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,R.layout.spinner_item, ratingsList);
        dataAdapter2.setDropDownViewResource(R.layout.spinner_item);
        dropdownRatings.setAdapter(dataAdapter2);
    }


    public void filter()
    {
        Spinner mySpinner = (Spinner) findViewById(R.id.fragment_dropdown_sort);
        sortCriteria = mySpinner.getSelectedItem().toString();
        Spinner mySpinner2= (Spinner) findViewById(R.id.fragment_dropdown_type);
        ratingCriteria = mySpinner2.getSelectedItem().toString();
    }

}
