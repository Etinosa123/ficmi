package com.hfad.myficmiapp.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.type.Date;
import com.hfad.myficmiapp.R;
import com.hfad.myficmiapp.adapter.DevotionAdapter;
import com.hfad.myficmiapp.adapter.TheWordAdapter;
import com.hfad.myficmiapp.model.DevotionModel;
import com.hfad.myficmiapp.model.TheWordModel;

import java.util.ArrayList;
import java.util.List;

public class DevotionActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    FirebaseFirestore store;
    DevotionAdapter devotionAdapter;
    List<DevotionModel> devotionModelList;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devotion);

        toolbar = findViewById(R.id.toolbar_devotion);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.dev_rec);

        store = FirebaseFirestore.getInstance();


        pd = new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setMessage("Loading....");
        pd.show();

        //for the word
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        devotionModelList = new ArrayList<>();
        devotionAdapter = new DevotionAdapter(this, devotionModelList);
        recyclerView.setAdapter(devotionAdapter);


        EventChangeListener();

    }

    private void EventChangeListener() {
        store.collection("Devotion").orderBy("scripture", Query.Direction.ASCENDING)
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

                                devotionModelList.add(dc.getDocument().toObject(DevotionModel.class));
                            }
                            devotionAdapter.notifyDataSetChanged();
                            if (pd.isShowing()){
                                pd.dismiss();
                            }
                        }

                    }
                });
    }
}