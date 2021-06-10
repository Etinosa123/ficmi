package com.hfad.myficmiapp.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hfad.myficmiapp.R;
import com.hfad.myficmiapp.adapter.TheWordAdapter;
import com.hfad.myficmiapp.model.TheWordModel;

import java.util.ArrayList;
import java.util.List;


public class TheWordFragment extends Fragment {

    RecyclerView theWordRecyclerView;
    FirebaseFirestore store;
    TheWordAdapter theWordAdapter;
    List<TheWordModel> theWordModelList;
    ProgressDialog pd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_the_word, container, false);
        theWordRecyclerView = root.findViewById(R.id.the_word_rec);

      store = FirebaseFirestore.getInstance();


        pd = new ProgressDialog(getActivity());
      pd.setCancelable(false);
      pd.setMessage("Loading....");
      pd.show();

        //for the word
        theWordRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        theWordRecyclerView.setHasFixedSize(true);
        theWordModelList = new ArrayList<>();
        theWordAdapter = new TheWordAdapter(getContext(), theWordModelList);
        theWordRecyclerView.setAdapter(theWordAdapter);


        EventChangeListener();

         /*
        store.collection("Word")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                TheWordModel theWordModel = document.toObject(TheWordModel.class);
                                theWordModelList.add(theWordModel);
                                theWordAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

          */



        return  root;
    }

    private void EventChangeListener() {
        store.collection("Word").orderBy("speaker", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null){
                            if (pd.isShowing()){
                                pd.dismiss();
                            }
                            Log.e("Tag", error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()){
                            if (dc.getType() == DocumentChange.Type.ADDED){

                                theWordModelList.add(dc.getDocument().toObject(TheWordModel.class));
                            }
                            theWordAdapter.notifyDataSetChanged();
                            if (pd.isShowing()){
                                pd.dismiss();
                            }
                        }

                    }
                });
    }
}