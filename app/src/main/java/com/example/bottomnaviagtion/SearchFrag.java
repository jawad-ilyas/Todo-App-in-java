package com.example.bottomnaviagtion;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bottomnaviagtion.databinding.FragmentSearchBinding;


public class SearchFrag extends Fragment {


    FragmentSearchBinding binding;
    public SearchFrag() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentSearchBinding.inflate(inflater , container , false);

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String searchValue = binding.tvSearch.getText().toString().trim();
                Toast.makeText(getContext(), searchValue, Toast.LENGTH_SHORT).show();

                if(searchValue.isEmpty()) {

                    binding.tvSearch.setError("Please Enter Your Task");
                }
                else {
                    DbManager db = new DbManager(getContext());
                    Cursor cursor = db.searchRecords(searchValue);

                    if (cursor.moveToNext()) {
                        String taskName = cursor.getString(1);
                        String taskDescription = cursor.getString(2);
                        binding.searchLinearLayout.setVisibility(View.VISIBLE);
                        // Set the retrieved values to your UI elements
                        binding.taskTitle.setText("Title : " +taskName);
                        binding.taskDesc.setText( "Desc :  " + taskDescription);
                    } else {

                        binding.taskTitle.setText("");
                        binding.searchTitle.setText("No Task Found Against your Query");
                        binding.taskDesc.setText("");
                        Toast.makeText(getContext(), "no result found", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


        return binding.getRoot();
    }
}