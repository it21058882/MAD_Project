package com.example.madoriginal.Feedback;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.HashMap;
import java.util.List;

public class ViewFeedBack extends AppCompatActivity {

    Button button;
    ListView listView;
    List<Feedbacks> user;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feed_back);

        listView = (ListView)findViewById(R.id.listview);


        user = new ArrayList<>();

        ref = FirebaseDatabase.getInstance().getReference("Feedbacks");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user.clear();

                for (DataSnapshot studentDatasnap : dataSnapshot.getChildren()) {

                    Feedbacks feedbacks = studentDatasnap.getValue(Feedbacks.class);
                    user.add(feedbacks);
                }

                MyAdapter adapter = new MyAdapter(ViewFeedBack.this, R.layout.custom_feedback_details, (ArrayList<Feedbacks>) user);
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
                view = inflater.inflate(R.layout.custom_feedback_details, null);

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

            holder.button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                            .setTitle("Do you want to delete this item?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    final String idd = user.get(position).getId();
                                    FirebaseDatabase.getInstance().getReference("Feedbacks").child(idd).removeValue();
                                    //remove function not written
                                    Toast.makeText(myContext, "Item deleted successfully", Toast.LENGTH_SHORT).show();

                                }
                            })

                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            })
                            .show();
                }
            });

            holder.button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
                    View view1 = inflater.inflate(R.layout.custom_update_feedback, null);
                    dialogBuilder.setView(view1);

                    final EditText editText1 = (EditText) view1.findViewById(R.id.name);
                    final EditText editText2 = (EditText) view1.findViewById(R.id.email);
                    final EditText editText3 = (EditText) view1.findViewById(R.id.message);
                    final Button buttonupdate = (Button) view1.findViewById(R.id.updatefeedback);

                    final AlertDialog alertDialog = dialogBuilder.create();
                    alertDialog.show();

                    final String idd = user.get(position).getId();
                    final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Feedbacks").child(idd);
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String id = (String) snapshot.child("id").getValue();
                            String name = (String) snapshot.child("name").getValue();
                            String email = (String) snapshot.child("email").getValue();
                            String message = (String) snapshot.child("suggession").getValue();

                            editText1.setText(name);
                            editText2.setText(email);
                            editText3.setText(message);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                    buttonupdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String name = editText1.getText().toString();
                            String email = editText2.getText().toString();
                            String message = editText3.getText().toString();

                            if (name.isEmpty()) {
                                editText1.setError("User Name is required");
                            }else if (email.isEmpty()) {
                                editText2.setError("Email is required");
                            }else if (message.isEmpty()) {
                                editText3.setError("Feedback is required");
                            }else {

                                HashMap map = new HashMap();
                                map.put("name", name);
                                map.put("email", email);
                                map.put("suggession", message);
                                reference.updateChildren(map);

                                Toast.makeText(ViewFeedBack.this, "Updated successfully", Toast.LENGTH_SHORT).show();

                                alertDialog.dismiss();
                            }
                        }
                    });
                }
            });

            return view;

        }
    }
}