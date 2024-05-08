package com.example.workmate;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class UserDetailActivity extends AppCompatActivity {

    // Declare UI elements
    TextView detailJob, detailName, detailEmail, detailNumber;
    ImageView detailImage;
    String key = ""; // To store key passed from previous activity
    String imageUrl = ""; // To store image URL passed from previous activity
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        // Initialize back button
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to previous activity
                Intent intent = new Intent(UserDetailActivity.this, UserView.class);
                startActivity(intent);
                finish();
            }
        });

        // Initialize UI elements
        detailJob = findViewById(R.id.detailJob);
        detailImage = findViewById(R.id.detailImage);
        detailName = findViewById(R.id.detailName);
        detailEmail = findViewById(R.id.detailEmail);
        detailNumber = findViewById(R.id.detailNumber);

        // Retrieve data passed from previous activity
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            // Set text views with data passed from previous activity
            detailJob.setText(bundle.getString("Job"));
            detailName.setText(bundle.getString("Name"));
            detailEmail.setText(bundle.getString("Email"));
            detailNumber.setText(bundle.getString("Number"));
            key = bundle.getString("Key");
            imageUrl = bundle.getString("Image");

            // Load image into ImageView using Glide library
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }
    }
}
