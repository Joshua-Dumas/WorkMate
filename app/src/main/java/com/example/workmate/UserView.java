package com.example.workmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class UserView extends AppCompatActivity {

    // Firebase database reference
    DatabaseReference databaseReference;

    // Logout button
    Button logout;

    // Firebase event listener
    ValueEventListener eventListener;

    // RecyclerView for displaying data
    RecyclerView recyclerView;

    // List to hold data retrieved from Firebase
    List<DataClass> dataList;

    // Adapter for RecyclerView
    MyAdapter2 adapter;

    // SearchView for filtering data
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        // Initialize views and set listeners
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logout functionality
                Intent intent = new Intent(UserView.this, Login.class);
                startActivity(intent);
                finish();
                Toast.makeText(UserView.this, "Logout Successful !", Toast.LENGTH_SHORT).show();
            }
        });

        // Initialize RecyclerView and SearchView
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);
        searchView.clearFocus();

        // Set layout manager for RecyclerView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(UserView.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Initialize AlertDialog for showing progress
        AlertDialog.Builder builder = new AlertDialog.Builder(UserView.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        // Initialize dataList and adapter
        dataList = new ArrayList<>();
        adapter = new MyAdapter2(UserView.this, dataList);
        recyclerView.setAdapter(adapter);

        // Get reference to Firebase database
        databaseReference = FirebaseDatabase.getInstance().getReference("Employees");
        dialog.show();

        // Event listener for retrieving data from Firebase
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Clear existing data
                dataList.clear();
                // Iterate through each child node
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    // Get DataClass object from snapshot
                    DataClass dataClass = itemSnapshot.getValue(DataClass.class);
                    // Set key for the DataClass object
                    dataClass.setKey(itemSnapshot.getKey());
                    // Add DataClass object to dataList
                    dataList.add(dataClass);
                }
                // Notify adapter of data change
                adapter.notifyDataSetChanged();
                // Dismiss progress dialog
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Dismiss progress dialog in case of error
                dialog.dismiss();
            }
        });

        // Search functionality for filtering data
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Call searchList method to filter data based on search query
                searchList(newText);
                return true;
            }
        });
    }

    // Method to filter data based on search query
    public void searchList(String text){
        ArrayList<DataClass> searchList = new ArrayList<>();
        // Iterate through dataList
        for (DataClass dataClass: dataList){
            // Check if dataJob contains the search query
            if (dataClass.getDataJob().toLowerCase().contains(text.toLowerCase())){
                // Add matching data to searchList
                searchList.add(dataClass);
            }
        }
        // Update adapter with filtered data
        adapter.searchDataList(searchList);
    }
}
