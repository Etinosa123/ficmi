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
import com.hfad.myficmiapp.R;
import com.hfad.myficmiapp.activities.MessagesActivity;
import com.hfad.myficmiapp.model.BookmarkModel;
import com.hfad.myficmiapp.model.FavDB;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.ViewHolder> {

    private Context context;
    private List<BookmarkModel> bookmarkModelList;
    boolean isChecked = false;
    FirebaseFirestore store = FirebaseFirestore.getInstance();
    CollectionReference userRef = store.collection("Users");


    public BookmarkAdapter(Context context, List<BookmarkModel> bookmarkModelList) {
        this.context = context;
        this.bookmarkModelList = bookmarkModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmark, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.speaker.setText(bookmarkModelList.get(position).getSpeaker());
        holder.topic.setText(bookmarkModelList.get(position).getTopic());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MessagesActivity.class);
                intent.putExtra("messages",bookmarkModelList.get(position));
                context.startActivity(intent);
            }
        });
        //binding image to adapter instead of glide or picasso
        //holder.image.setImageResource(bookmarkModelList.get(position).getTopic());

        //this part removes the bookmark from the activity
        //by uncheckin it
        /*
        holder.bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isChecked) {
                    userRef.document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("Favorites")
                            .document().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                holder.bookmark.setBackgroundResource(R.drawable.bookmarks);

                            }
                        }
                    });
                }
            }
        });

         */
     //   bookmarkModelList.remove(position);
    }

    @Override
    public int getItemCount() {
        return bookmarkModelList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView speaker, topic;
        Button bookmark;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            speaker = itemView.findViewById(R.id.speaker);
            topic = itemView.findViewById(R.id.topic);
            bookmark = itemView.findViewById(R.id.bookmark_msg);
        }
    }


}
