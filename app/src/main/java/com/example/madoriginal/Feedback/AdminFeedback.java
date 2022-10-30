package com.example.madoriginal.Feedback;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.madoriginal.Feedback.Models.Feedbacks;
import com.example.madoriginal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminFeedback extends AppCompatActivity {

    ListView listView;
    List<Feedbacks> user;
    DatabaseReference ref;
    TextView textView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_feedback);

        listView = (ListView)findViewById(R.id.listview);
        textView = (TextView) findViewById(R.id.count);

        user = new ArrayList<>();

        ref = FirebaseDatabase.getInstance().getReference("Feedbacks");

//

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user.clear();

                for (DataSnapshot studentDatasnap : dataSnapshot.getChildren()) {

                    Feedbacks feedbacks = studentDatasnap.getValue(Feedbacks.class);
                    user.add(feedbacks);
                    textView.setText(String.valueOf(user.size()));
                }

                MyAdapter adapter = new MyAdapter(AdminFeedback.this, R.layout.custom_feedback_admin, (ArrayList<Feedbacks>) user);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    static class ViewHolder {

        TextView COL1;
        TextView COL2;
        TextView COL3;
        TextView COL4;
        ImageView imageView;
        Button button1;
        Button button2;
    }

    class MyAdapter extends ArrayAdapter<Feedbacks> {
        LayoutInflater inflater;
        Context myContext;
        List<Feedbacks> user;


        public MyAdapter(Context context, int resource, ArrayList<Feedbacks> objects) {
            super(context, resource, objects);
            myContext = context;
            user = objects;
            inflater = LayoutInflater.from(context);
            int y;
            String barcode;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            final ViewHolder holder;
            if (view == null) {
                holder = new ViewHolder();
                view = inflater.inflate(R.layout.custom_feedback_admin, null);

                holder.COL1 = (TextView) view.findViewById(R.id.id);
                holder.COL2 = (TextView) view.findViewById(R.id.name);
                holder.COL3 = (TextView) view.findViewById(R.id.email);
                holder.COL4 = (TextView) view.findViewById(R.id.message);
                holder.imageView = (ImageView) view.findViewById(R.id.imageView2);
                holder.button1 = (Button) view.findViewById(R.id.edit);
                holder.button2 = (Button) view.findViewById(R.id.delete);


                view.setTag(holder);
            } else {

                holder = (ViewHolder) view.getTag();
            }

            holder.COL1.setText("ID:- "+user.get(position).getId());
            holder.COL2.setText("Name:- "+user.get(position).getName());
            holder.COL3.setText("Email:- "+user.get(position).getEmail());
            holder.COL4.setText("Feedback:- "+user.get(position).getSuggession());
            System.out.println(holder);

            return view;

        }
    }
}