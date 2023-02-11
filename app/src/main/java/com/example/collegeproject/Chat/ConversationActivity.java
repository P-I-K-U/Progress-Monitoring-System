package com.example.collegeproject.Chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.collegeproject.R;
import com.example.collegeproject.databinding.ActivityConversationBinding;

public class ConversationActivity extends AppCompatActivity {

    private ActivityConversationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConversationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.topAppBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        binding.backCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        binding.profileImage.setImageResource(intent.getIntExtra("image",0));
        binding.title.setText(intent.getStringExtra("dname"));




    }

}