package com.example.bottomnaviagtion;


import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.bottomnaviagtion.databinding.FragmentTodayBinding;


public class todayFrag extends Fragment {

    FragmentTodayBinding binding;

    public todayFrag() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentTodayBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Dialog dialog  = new Dialog(getContext());

        binding.btnPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvTodo.setText("");

                dialog.setContentView(R.layout.dialog_design);

                dialog.show();


                ImageButton btnAddTask = dialog.findViewById(R.id.btnAddTask);



                btnAddTask.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        EditText tvDtitle = dialog.findViewById(R.id.tvDtitle);
                        String title = tvDtitle.getText().toString().trim();
                        EditText tvDdesc = dialog.findViewById(R.id.tvDdesc);
                        String desc = tvDdesc.getText().toString().trim();
                        if (title.isEmpty()) {
                            // Show error for empty title
                            tvDtitle.setError("Title cannot be empty");
                            return;
                        }

                        if (desc.isEmpty()) {
                            // Show error for empty title
                            tvDdesc.setError("Title cannot be empty");

                            return;
                        }

                        tvDtitle.setError(null);
                        tvDdesc.setError(null);


                        DbManager db = new DbManager(getContext());

                        long res = db.addRecords(title , desc);


                        if(res == 0 )
                        {
                            Toast.makeText(getContext(), "Data is not Inserted ", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            tvDtitle.setText("");
                            tvDdesc.setText("");
                        }
                    }
                });
            }
        });







        return view;
    }



}