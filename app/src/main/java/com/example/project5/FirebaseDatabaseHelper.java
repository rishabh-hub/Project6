package com.example.project5;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseDatabaseHelper {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    ArrayList<NotesModel> data=new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(ArrayList<NotesModel> data,ArrayList<String> keys);

    }

    public FirebaseDatabaseHelper() {
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("data");
    }

    public void readNotes(final DataStatus dataStatus)
    {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data.clear();
                ArrayList<String> keys=new ArrayList<>();
                for (DataSnapshot keyNode :dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    NotesModel notesModel=keyNode.getValue(NotesModel.class);
                    data.add(notesModel);
                }
                dataStatus.DataIsLoaded(data,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
