package com.base.whatsappclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WhatsAppActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<UserDetails> arrayList;

    private TextView loadingText;

    private SwipeRefreshLayout swiperef;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_user);

        listView = findViewById(R.id.listView);
        loadingText = findViewById(R.id.loadingText);
        swiperef = findViewById(R.id.swiperef);

        arrayList =new ArrayList<>();


        setupUserValueEventListener();





        swiperef.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                setupUserValueEventListener();

                swiperef.setRefreshing(false);
            }
        });





    }

    private void setupUserValueEventListener() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("Twitter User");

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = firebaseUser.getUid();

        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear(); // Clear the list to avoid duplicates

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserDetails user = snapshot.getValue(UserDetails.class);
                    if (user != null && !snapshot.getKey().equals(userId)) {
                        arrayList.add(user);
                    }
                }

                // Create an ArrayAdapter to populate the ListView
                UserAdapter adapter = new UserAdapter(WhatsAppActivity.this, R.layout.user_list_item, arrayList, listView);
                listView.setAdapter(adapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(WhatsAppActivity.this, "list view error block", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.postimage) {
            startActivity(new Intent(WhatsAppActivity.this, Loginactivity.class));
            Toast.makeText(WhatsAppActivity.this, "Successfully Logout", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}