package com.example.bottomnaviagtion;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomnaviagtion.databinding.CustomTodoRowBinding;
import com.example.bottomnaviagtion.databinding.InbuxFragmentRecyclerLayoutBinding;

import java.util.ArrayList;

public class InbuxRecyclerAdapter extends  RecyclerView.Adapter<InbuxRecyclerAdapter.ViewHolder>{

    Context context;
    ArrayList<inbuxModel> inbuxData;
    InbuxRecyclerAdapter(Context context ,   ArrayList<inbuxModel> inbuxData ){
        this.context = context;
        this.inbuxData = inbuxData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_todo_row , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        inbuxModel currentItem = inbuxData.get(position);

        // Access the CheckBox and set its text
        holder.binding.checkbox.setText(currentItem.getItemName());
        holder.binding.taskDesc.setText(currentItem.getItemDesc());

        holder.binding.checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show a Toast when the CheckBox is checked

                DbManager db = new DbManager(context);
                db.DeleteTask(currentItem.getId());
                // Remove the item from the dataset
                inbuxData.remove(currentItem);

                // Notify the adapter of the item removal
                notifyItemRemoved(position);

                Toast.makeText(context, "Checkbox checked: " + currentItem.getItemName() + currentItem.getId(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return inbuxData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        CustomTodoRowBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = CustomTodoRowBinding.bind(itemView);
        }
    }


}
