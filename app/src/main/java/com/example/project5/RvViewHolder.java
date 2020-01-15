package com.example.project5;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RvViewHolder extends RecyclerView.ViewHolder {

    TextView title_;
    TextView notes_;
    int id;
    Context context;
    DatabaseHelper mydb;
    Cursor cursor;

    public RvViewHolder(@NonNull View itemView) {
        super(itemView);
        title_=itemView.findViewById(R.id.title1);
        notes_=itemView.findViewById(R.id.notes1);
        context=itemView.getContext();
        mydb=new DatabaseHelper(context);
        cursor=mydb.getData();

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,Main2Activity.class);
                while(cursor.moveToNext()) {
                    if (id == cursor.getInt(0)) {
                        i.putExtra("title", cursor.getString(1));
                        i.putExtra("notes", cursor.getString(2));
                        i.putExtra("id",id);
                        context.startActivity(i);
                    }
                }
            }
        });
        itemView.setLongClickable(true);
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mydb.delete(id);
                Intent i=new Intent(context,MainActivity.class);
                context.startActivity(i);
                return false;
            }
        });

    }


    void populate(NotesModel item)
    {
        title_.setText(item.getTitle());
        notes_.setText(item.getNotes());
        id=item.getId();
    }
}
