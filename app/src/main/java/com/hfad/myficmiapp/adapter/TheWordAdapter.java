package com.hfad.myficmiapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.hfad.myficmiapp.R;
import com.hfad.myficmiapp.activities.MessagesActivity;
import com.hfad.myficmiapp.model.TheWordModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheWordAdapter extends RecyclerView.Adapter<TheWordAdapter.ViewHolder> {

    Context context;
    List<TheWordModel> list;
    Boolean isChecked = true;
    Boolean isUnChecked = true;
    FirebaseFirestore store = FirebaseFirestore.getInstance();
    FirebaseAuth auth;
    private UploadTask bookmarkTask;
    CollectionReference userRef = store.collection("Users");


    public TheWordAdapter(Context context, List<TheWordModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.the_word, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.speaker.setText(list.get(position).getSpeaker());
        holder.topic.setText(list.get(position).getTopic());
        holder.date.setText(list.get(position).getDate());
        holder.bookmark.setOnClickListener(v -> {
            //if button is checked
            if (isChecked){

                String saveCurrentDate;

                Calendar calForDate = Calendar.getInstance();

                SimpleDateFormat currentDate = new SimpleDateFormat("MM dd,yyyy");
                saveCurrentDate = currentDate.format(calForDate.getTime());


                Map<String, Object> map = new HashMap<>();
                map.put("speaker", holder.speaker.getText().toString());
                map.put("topic", holder.topic.getText().toString());
                map.put("date", saveCurrentDate);

               store.collection("Favorites").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                       .collection("User").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                   @Override
                   public void onComplete(@NonNull Task<DocumentReference> task) {
                       if (task.isSuccessful()){
                           Toast.makeText(context, "Bookmarked Successfully", Toast.LENGTH_SHORT).show();
                       }
                   }
               });
            }//if button is unChecked
            /*
            if(isUnChecked){
                userRef.document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .collection("Favorites").document().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            holder.bookmark.setBackgroundResource(R.drawable.bookmarks);
                            Toast.makeText(context, "Removed from Bookmarks", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            
             */

        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MessagesActivity.class);
                intent.putExtra("messages",list.get(position));
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView speaker, topic, date;
        Button bookmark;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            speaker = itemView.findViewById(R.id.speaker);
            topic = itemView.findViewById(R.id.topic);
            date  = itemView.findViewById(R.id.current_date);
            store = FirebaseFirestore.getInstance();
            auth = FirebaseAuth.getInstance();

            //ADD BOOKMARK BTN
            bookmark = itemView.findViewById(R.id.bookmark_msg);


        }
    }
}

