package com.base.whatsappclone;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends ArrayAdapter<UserDetails> {
    private int layoutResource;
    private ListView listView;

    public UserAdapter(Context context, int resource, List<UserDetails> objects,ListView listView) {
        super(context, resource, objects);
        layoutResource = resource;
        this.listView =listView;

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(layoutResource, null);
        }

        UserDetails user = getItem(position);
        if (user != null) {
            TextView nameTextView = view.findViewById(R.id.userNameTextView);


            nameTextView.setText(user.getProfileName());

        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected user from the adapter based on the position
                UserDetails selectedUser = getItem(position);
                String profileName = selectedUser.getProfileName();

                if (selectedUser != null) {
                    // Start a new activity, passing the selected user's information
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.putExtra("profileName", profileName);
                    getContext().startActivity(intent);
                }
            }
        });




        return view;
    }
}