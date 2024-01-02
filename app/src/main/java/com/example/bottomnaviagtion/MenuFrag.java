package com.example.bottomnaviagtion;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bottomnaviagtion.databinding.FragmentMenuBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFrag extends Fragment {



    FragmentMenuBinding binding;

    public MenuFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentMenuBinding.inflate(inflater, container, false);

        DbManager db = new DbManager(getContext());
        int totalTaskCom = db.getTotalDeleteCount();
        int totalTaskRem = db.getTotalRecordsCount();

        binding.tvTotalComp.setText(String.valueOf(totalTaskCom));
        binding.tvTotalReminder.setText(String.valueOf(totalTaskRem));

        return  binding.getRoot();
    }
}