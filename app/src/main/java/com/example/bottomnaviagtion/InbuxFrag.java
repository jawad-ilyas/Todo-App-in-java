    package com.example.bottomnaviagtion;

    import android.database.Cursor;
    import android.os.Bundle;

    import androidx.fragment.app.Fragment;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Toast;

    import com.example.bottomnaviagtion.databinding.FragmentInbuxBinding;
    import com.example.bottomnaviagtion.databinding.InbuxFragmentRecyclerLayoutBinding;

    import java.util.ArrayList;


    public class InbuxFrag extends Fragment {


        FragmentInbuxBinding binding;

        View view;
        public InbuxFrag() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment

            binding = FragmentInbuxBinding.inflate(inflater , container , false );
             view = binding.getRoot();



            DbManager db = new DbManager(getContext());

            int  count = db.getTotalRecordsCount();

            binding.tvTotalCounts.setText(String.valueOf(count));

            if(count == 0)
            {
                return inflater.inflate(R.layout.fragment_inbux , container, false);

            }
            else {

                InbuxFragmentRecyclerLayoutBinding inbuxBinding;
                inbuxBinding = InbuxFragmentRecyclerLayoutBinding.inflate(inflater , container, false);




                RecyclerView recyclerView = inbuxBinding.rvForInbox;

                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                ArrayList<inbuxModel> inbuxData  = new ArrayList<>();

                DbManager dbManager = new DbManager(getContext());
                dbManager.updateDeleteCount();
                 dbManager.addDelete();
//                Toast.makeText(getContext(), (int)value, Toast.LENGTH_SHORT).show();
                // Retrieve data from the database
                Cursor cursor = dbManager.readRecords();

                while(cursor.moveToNext())
                {
                    inbuxModel  model= new inbuxModel(cursor.getString(1) , cursor.getString(2) , cursor.getInt(0));
                    inbuxData.add(model);

                }


                InbuxRecyclerAdapter adapter = new InbuxRecyclerAdapter(getContext()  , inbuxData);
                recyclerView.setAdapter(adapter);


                view =   inbuxBinding.getRoot();
            }



            return view;
        }
    }