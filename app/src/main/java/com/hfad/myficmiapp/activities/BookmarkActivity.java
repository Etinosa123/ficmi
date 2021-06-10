package com.hfad.myficmiapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.hfad.myficmiapp.R;
import com.hfad.myficmiapp.adapter.BookmarkAdapter;
import com.hfad.myficmiapp.model.BookmarkModel;
import com.hfad.myficmiapp.model.DevotionModel;
import com.hfad.myficmiapp.model.FavDB;

import java.util.ArrayList;
import java.util.List;

public class BookmarkActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<BookmarkModel> bookmarkModelList = new ArrayList<>();
    private BookmarkAdapter adapter;
    ProgressDialog pd;

    FirebaseFirestore store = FirebaseFirestore.getInstance();
    CollectionReference userRef = store.collection("Users");
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        recyclerView = findViewById(R.id.bookmark_rec);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BookmarkAdapter(this, bookmarkModelList);

        toolbar = findViewById(R.id.toolbar_bookmark);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        store.collection("Favorites").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (DocumentSnapshot doc : task.getResult().getDocuments()){

                        BookmarkModel bookmarkModel = doc.toObject(BookmarkModel.class);
                        bookmarkModelList.add(bookmarkModel);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

        });


        recyclerView.setAdapter(adapter);

    }
 /*
    private void EventChangeListener() {
       userRef.document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("Favorites")
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

                                bookmarkModelList.add(dc.getDocument().toObject(BookmarkModel.class));
                            }else if (dc.getType() == DocumentChange.Type.REMOVED){
                                bookmarkModelList.remove(dc.getDocument().toObject(BookmarkModel.class));
                            }
                            adapter.notifyDataSetChanged();
                            if (pd.isShowing()){
                                pd.dismiss();
                            }
                        }

                    }
                });
    }

  */

}