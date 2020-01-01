package com.example.project5;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RvViewHolder extends RecyclerView.ViewHolder {

    TextView title_;
    TextView notes_;
    Context context;

    public RvViewHolder(@NonNull View itemView) {
        super(itemView);
        title_=itemView.findViewById(R.id.title1);
        notes_=itemView.findViewById(R.id.notes1);
        context=itemView.getContext();
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,Main2Activity.class);
                context.startActivity(i);
            }
        });
    }

    void populate(NotesModel item)
    {
        title_.setText(item.getTitle());
        notes_.setText(item.getNotes());
    }
}
