package com.example.collegeproject.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.collegeproject.databinding.ActivityProfileBinding;
import com.example.collegeproject.studentData.StudentData;
import com.example.collegeproject.teacherData.TeacherData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class ProfileActivity extends AppCompatActivity {
    private ActivityProfileBinding binding;
    FirebaseFirestore db;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        db.collection("College_Project").document("student").collection("4th Year").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot stuRollNo : task.getResult().getDocuments()){
                                StudentData data = stuRollNo.toObject(StudentData.class);
                                if(data.getEmail().equals(mAuth.getCurrentUser().getEmail())){
                                    binding.constraintLayout.setVisibility(View.VISIBLE);
                                    binding.collapsingToolbar.setTitle(data.getFull_name());
                                    binding.email.setText(data.getEmail());
                                    binding.pPhone.setText(data.getPersonal_phone());
                                    binding.fPhone.setText(data.getFather_phone());
                                    binding.departmentName.setText(data.getDepartment());
                                    binding.aFee.setText(data.getAcademic_fee());
                                    if (data.getHostel_fee().isEmpty())
                                        binding.hFee.setText("N/A");
                                    else
                                        binding.hFee.setText(data.getHostel_fee());
                                }
                            }
                        }
                    }
                });

        db.collection("College_Project").document("student").collection("3rd Year").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot stuRollNo : task.getResult().getDocuments()){
                                StudentData data = stuRollNo.toObject(StudentData.class);
                                if(data.getEmail().equals(mAuth.getCurrentUser().getEmail())){
                                    binding.constraintLayout.setVisibility(View.VISIBLE);
                                    binding.collapsingToolbar.setTitle(data.getFull_name());
                                    binding.email.setText(data.getEmail());
                                    binding.pPhone.setText(data.getPersonal_phone());
                                    binding.fPhone.setText(data.getFather_phone());
                                    binding.departmentName.setText(data.getDepartment());
                                    binding.aFee.setText(data.getAcademic_fee());
                                    if (data.getHostel_fee().isEmpty())
                                        binding.hFee.setText("N/A");
                                    else
                                        binding.hFee.setText(data.getHostel_fee());
                                }
                            }
                        }
                    }
                });


        db.collection("College_Project").document("student").collection("2nd Year").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot stuRollNo : task.getResult().getDocuments()){
                                StudentData data = stuRollNo.toObject(StudentData.class);
                                if(data.getEmail().equals(mAuth.getCurrentUser().getEmail())){
                                    binding.constraintLayout.setVisibility(View.VISIBLE);
                                    binding.collapsingToolbar.setTitle(data.getFull_name());
                                    binding.email.setText(data.getEmail());
                                    binding.pPhone.setText(data.getPersonal_phone());
                                    binding.fPhone.setText(data.getFather_phone());
                                    binding.departmentName.setText(data.getDepartment());
                                    binding.aFee.setText(data.getAcademic_fee());
                                    if (data.getHostel_fee().isEmpty())
                                        binding.hFee.setText("N/A");
                                    else
                                        binding.hFee.setText(data.getHostel_fee());
                                }
                            }
                        }
                    }
                });


        db.collection("College_Project").document("student").collection("1st Year").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot stuRollNo : task.getResult().getDocuments()){
                                StudentData data = stuRollNo.toObject(StudentData.class);
                                if(data.getEmail().equals(mAuth.getCurrentUser().getEmail())){
                                    binding.constraintLayout.setVisibility(View.VISIBLE);
                                    binding.collapsingToolbar.setTitle(data.getFull_name());
                                    binding.email.setText(data.getEmail());
                                    binding.pPhone.setText(data.getPersonal_phone());
                                    binding.fPhone.setText(data.getFather_phone());
                                    binding.departmentName.setText(data.getDepartment());
                                    binding.aFee.setText(data.getAcademic_fee());
                                    if (data.getHostel_fee().isEmpty())
                                        binding.hFee.setText("N/A");
                                    else
                                        binding.hFee.setText(data.getHostel_fee());
                                }
                            }
                        }
                    }
                });

        //teacher

        db.collection("College_Project").document("teacher").collection("teacher_details")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot teacherEmail : task.getResult().getDocuments()){
                                TeacherData data = teacherEmail.toObject(TeacherData.class);
                                if(data.getEmail().equals(mAuth.getCurrentUser().getEmail())){
                                    binding.constraintLayout.setVisibility(View.VISIBLE);
                                    binding.collapsingToolbar.setTitle(data.getFull_name());
                                    binding.email.setText(data.getEmail());
                                    binding.pPhone.setText(data.getPhone_no());
                                    binding.departmentName.setText(data.getDepartment());
                                    binding.fnoText.setVisibility(View.GONE);
                                    binding.fnoLayout.setVisibility(View.GONE);
                                    binding.aFeeText.setVisibility(View.GONE);
                                    binding.aFeeLayout.setVisibility(View.GONE);
                                    binding.hFeeText.setVisibility(View.GONE);
                                    binding.hFeeLayout.setVisibility(View.GONE);

                                }
                            }
                        }
                    }
                });






       /* db.collection("College_Project").document("C.S.E/student/4th Year/"+mAuth.getCurrentUser().getUid()+"/"+mAuth.getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    StudentData data = task.getResult().toObject(StudentData.class);
                    if(data !=null){
                        binding.constraintLayout.setVisibility(View.VISIBLE);
                        binding.collapsingToolbar.setTitle(data.getFull_name());
                        binding.email.setText(data.getEmail());
                        binding.pPhone.setText(data.getPersonal_phone());
                        binding.fPhone.setText(data.getFather_phone());
                        binding.departmentName.setText(data.getDepartment());
                        binding.aFee.setText(data.getAcademic_fee());
                        if (data.getHostel_fee().isEmpty())
                            binding.hFee.setText("N/A");
                        else
                            binding.hFee.setText(data.getHostel_fee());
                    }
                }
            }
        });

        db.collection("College_Project").document("C.S.E/student/3rd Year/"+mAuth.getCurrentUser().getUid()+"/"+mAuth.getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    StudentData data = task.getResult().toObject(StudentData.class);
                    if(data !=null){
                        binding.constraintLayout.setVisibility(View.VISIBLE);
                        binding.collapsingToolbar.setTitle(data.getFull_name());
                        binding.email.setText(data.getEmail());
                        binding.pPhone.setText(data.getPersonal_phone());
                        binding.fPhone.setText(data.getFather_phone());
                        binding.departmentName.setText(data.getDepartment());
                        binding.aFee.setText(data.getAcademic_fee());
                        if (data.getHostel_fee().isEmpty())
                            binding.hFee.setText("N/A");
                        else
                            binding.hFee.setText(data.getHostel_fee());
                    }

                }
            }
        });

        db.collection("College_Project").document("C.S.E/student/2nd Year/"+mAuth.getCurrentUser().getUid()+"/"+mAuth.getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    StudentData data = task.getResult().toObject(StudentData.class);
                    if(data !=null){
                        binding.constraintLayout.setVisibility(View.VISIBLE);
                        binding.collapsingToolbar.setTitle(data.getFull_name());
                        binding.email.setText(data.getEmail());
                        binding.pPhone.setText(data.getPersonal_phone());
                        binding.fPhone.setText(data.getFather_phone());
                        binding.departmentName.setText(data.getDepartment());
                        binding.aFee.setText(data.getAcademic_fee());
                        if (data.getHostel_fee().isEmpty())
                            binding.hFee.setText("N/A");
                        else
                            binding.hFee.setText(data.getHostel_fee());
                    }
                }
            }
        });

        db.collection("College_Project").document("C.S.E/student/1st Year/"+mAuth.getCurrentUser().getUid()+"/"+mAuth.getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    StudentData data = task.getResult().toObject(StudentData.class);
                    if(data !=null){
                        binding.constraintLayout.setVisibility(View.VISIBLE);
                        binding.collapsingToolbar.setTitle(data.getFull_name());
                        binding.email.setText(data.getEmail());
                        binding.pPhone.setText(data.getPersonal_phone());
                        binding.fPhone.setText(data.getFather_phone());
                        binding.departmentName.setText(data.getDepartment());
                        binding.aFee.setText(data.getAcademic_fee());
                        if (data.getHostel_fee().isEmpty())
                            binding.hFee.setText("N/A");
                        else
                            binding.hFee.setText(data.getHostel_fee());
                    }

                }
            }
        });


        // teacher

        db.collection("College_Project").document("C.S.E/teacher/"+mAuth.getCurrentUser().getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    TeacherData data = task.getResult().toObject(TeacherData.class);
                    if(data !=null){
                        binding.constraintLayout.setVisibility(View.VISIBLE);
                        binding.collapsingToolbar.setTitle(data.getFull_name());
                        binding.email.setText(data.getEmail());
                        binding.pPhone.setText(data.getPhone_no());
                        binding.departmentName.setText(data.getDepartment());
                        binding.fnoText.setVisibility(View.GONE);
                        binding.fnoLayout.setVisibility(View.GONE);
                        binding.aFeeText.setVisibility(View.GONE);
                        binding.aFeeLayout.setVisibility(View.GONE);
                        binding.hFeeText.setVisibility(View.GONE);
                        binding.hFeeLayout.setVisibility(View.GONE);
                    }
                }
            }
        });*/


        /*db.collection("College_Project").document("student").collection("student_details")
            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot document : task.getResult().getDocuments()) {
                            StudentData data = document.toObject(StudentData.class);
                            if (data != null) {
                                db.collection("College_Project").document("student").collection("student_details")
                                        .document(document.getId()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if (task.isSuccessful() && mAuth.getCurrentUser().getEmail().equals(task.getResult().getData().get("e_mail"))) {
                                                    // Toast.makeText(ProfileActivity.this, "data Found", Toast.LENGTH_SHORT).show();

                                                    binding.constraintLayout.setVisibility(View.VISIBLE);
                                                    binding.collapsingToolbar.setTitle(data.getFull_name());
                                                    binding.email.setText(data.getEmail());
                                                    binding.pPhone.setText(data.getPersonal_phone());
                                                    binding.fPhone.setText(data.getFather_phone());
                                                    binding.departmentName.setText(data.getDepartment());
                                                    binding.aFee.setText(data.getAcademic_fee());
                                                    if (data.getHostel_fee().isEmpty())
                                                        binding.hFee.setText("N/A");
                                                    else
                                                        binding.hFee.setText(data.getHostel_fee());

                                                }


                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                            }
                                        });


                            }
                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ProfileActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        db.collection("College_Project").document("teacher").collection("teacher_details")
            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot document : task.getResult().getDocuments()) {
                            TeacherData data = document.toObject(TeacherData.class);
                            if (data != null) {
                                db.collection("College_Project").document("teacher").collection("teacher_details")
                                        .document(document.getId()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if (task.isSuccessful() && mAuth.getCurrentUser().getEmail().equals(task.getResult().getData().get("email"))) {

                                                     binding.constraintLayout.setVisibility(View.VISIBLE);
                                                     binding.collapsingToolbar.setTitle(data.getFull_name());
                                                     binding.email.setText(data.getEmail());
                                                     binding.pPhone.setText(data.getPhone_no());
                                                     binding.departmentName.setText(data.getDepartment());
                                                     binding.fnoText.setVisibility(View.GONE);
                                                     binding.fnoLayout.setVisibility(View.GONE);
                                                     binding.aFeeText.setVisibility(View.GONE);
                                                     binding.aFeeLayout.setVisibility(View.GONE);
                                                     binding.hFeeText.setVisibility(View.GONE);
                                                     binding.hFeeLayout.setVisibility(View.GONE);

                                                }
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                            }
                                        });


                            }
                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ProfileActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });*/

        binding.fab.setOnClickListener(v -> {
            onBackPressed();
        });
    }




}


