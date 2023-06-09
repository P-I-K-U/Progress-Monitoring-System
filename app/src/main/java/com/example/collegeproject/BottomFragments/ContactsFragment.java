package com.example.collegeproject.BottomFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeproject.Contacts.ContactAdapter;
import com.example.collegeproject.Contacts.ContactModel;
import com.example.collegeproject.HomeActivity;
import com.example.collegeproject.R;
import com.example.collegeproject.databinding.FragmentContactsBinding;
import com.example.collegeproject.profile.ProfileActivity;
import com.example.collegeproject.studentData.StudentData;
import com.example.collegeproject.teacherData.TeacherData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String roll, name, phoneNo;
    LinearLayoutManager layoutManager;
    List<ContactModel> userList;
    ContactAdapter adapter;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public ContactsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactsFragment newInstance(String param1, String param2) {
        ContactsFragment fragment = new ContactsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    FragmentContactsBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentContactsBinding.inflate(inflater,container,false);
        userList = new ArrayList<>();


        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();


        // teacher

        db.collection("College_Project").document("teacher").collection("teacher_details").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot teacherEmail : task.getResult().getDocuments()){
                                TeacherData data = teacherEmail.toObject(TeacherData.class);
                                if(data.getEmail().equals(mAuth.getCurrentUser().getEmail())){

                                    binding.topAppBar.inflateMenu(R.menu.years_menu);

                                    db.collection("College_Project").document("student").collection("4th Year").get()
                                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                    if(task.isSuccessful()){
                                                        binding.topAppBar.setTitle("Contacts of 4th Year");
                                                        userList.clear();
                                                        for(DocumentSnapshot stuRollNo : task.getResult().getDocuments()){
                                                            StudentData data = stuRollNo.toObject(StudentData.class);
                                                            if(data !=null){
                                                                phoneNo = data.getPersonal_phone();
                                                                name = data.getFull_name();
                                                                roll = data.getRoll_number();
                                                                userList.add(new ContactModel(name, roll, phoneNo));
                                                                adapter = new ContactAdapter(userList);
                                                                layoutManager = new LinearLayoutManager(getContext());
                                                                layoutManager.setOrientation(RecyclerView.VERTICAL);
                                                                binding.recyclerview.setLayoutManager(layoutManager);
                                                                binding.recyclerview.setAdapter(adapter);
                                                                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
                                                                binding.recyclerview.addItemDecoration(dividerItemDecoration);
                                                                adapter.notifyDataSetChanged();
                                                            }

                                                        }
                                                    }
                                                }
                                            });

                                    binding.topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                                        @Override
                                        public boolean onMenuItemClick(MenuItem item) {
                                            switch (item.getItemId()){
                                                case R.id.first:
                                                    db.collection("College_Project").document("student").collection("1st Year").get()
                                                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                    if(task.isSuccessful()){
                                                                        binding.topAppBar.setTitle("Contacts of 1st Year");
                                                                        int size = userList.size();
                                                                        userList.clear();
                                                                        if(size !=0){
                                                                            adapter.notifyItemRangeRemoved(0, size);
                                                                        }
                                                                        for(DocumentSnapshot stuRollNo : task.getResult().getDocuments()){
                                                                            StudentData data = stuRollNo.toObject(StudentData.class);
                                                                            if(data !=null){
                                                                                phoneNo = data.getPersonal_phone();
                                                                                name = data.getFull_name();
                                                                                roll = data.getRoll_number();
                                                                                userList.add(new ContactModel(name, roll, phoneNo));
                                                                                adapter = new ContactAdapter(userList);
                                                                                layoutManager = new LinearLayoutManager(getContext());
                                                                                layoutManager.setOrientation(RecyclerView.VERTICAL);
                                                                                binding.recyclerview.setLayoutManager(layoutManager);
                                                                                binding.recyclerview.setAdapter(adapter);
                                                                                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
                                                                                binding.recyclerview.addItemDecoration(dividerItemDecoration);
                                                                                adapter.notifyDataSetChanged();
                                                                            }

                                                                        }
                                                                    }
                                                                }
                                                            });
                                                    break;

                                                case R.id.second:
                                                    db.collection("College_Project").document("student").collection("2nd Year").get()
                                                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                    if(task.isSuccessful()){
                                                                        binding.topAppBar.setTitle("Contacts of 2nd Year");
                                                                        int size = userList.size();
                                                                        userList.clear();
                                                                        if(size !=0){
                                                                            adapter.notifyItemRangeRemoved(0, size);
                                                                        }
                                                                        for(DocumentSnapshot stuRollNo : task.getResult().getDocuments()){
                                                                            StudentData data = stuRollNo.toObject(StudentData.class);
                                                                            if(data !=null){
                                                                                phoneNo = data.getPersonal_phone();
                                                                                name = data.getFull_name();
                                                                                roll = data.getRoll_number();
                                                                                userList.add(new ContactModel(name, roll, phoneNo));
                                                                                adapter = new ContactAdapter(userList);
                                                                                layoutManager = new LinearLayoutManager(getContext());
                                                                                layoutManager.setOrientation(RecyclerView.VERTICAL);
                                                                                binding.recyclerview.setLayoutManager(layoutManager);
                                                                                binding.recyclerview.setAdapter(adapter);
                                                                                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
                                                                                binding.recyclerview.addItemDecoration(dividerItemDecoration);
                                                                                adapter.notifyDataSetChanged();
                                                                            }

                                                                        }
                                                                    }
                                                                }
                                                            });
                                                    break;

                                                case R.id.third:
                                                    db.collection("College_Project").document("student").collection("3rd Year").get()
                                                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                    if(task.isSuccessful()){
                                                                        binding.topAppBar.setTitle("Contacts of 3rd Year");
                                                                        int size = userList.size();
                                                                        userList.clear();
                                                                        if(size !=0){
                                                                            adapter.notifyItemRangeRemoved(0, size);
                                                                        }
                                                                        for(DocumentSnapshot stuRollNo : task.getResult().getDocuments()){
                                                                            StudentData data = stuRollNo.toObject(StudentData.class);
                                                                            if(data !=null){
                                                                                phoneNo = data.getPersonal_phone();
                                                                                name = data.getFull_name();
                                                                                roll = data.getRoll_number();
                                                                                userList.add(new ContactModel(name, roll, phoneNo));
                                                                                adapter = new ContactAdapter(userList);
                                                                                layoutManager = new LinearLayoutManager(getContext());
                                                                                layoutManager.setOrientation(RecyclerView.VERTICAL);
                                                                                binding.recyclerview.setLayoutManager(layoutManager);
                                                                                binding.recyclerview.setAdapter(adapter);
                                                                                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
                                                                                binding.recyclerview.addItemDecoration(dividerItemDecoration);
                                                                                adapter.notifyDataSetChanged();
                                                                            }

                                                                        }
                                                                    }
                                                                }
                                                            });
                                                    break;

                                                case R.id.fourth:
                                                    db.collection("College_Project").document("student").collection("4th Year").get()
                                                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                    if(task.isSuccessful()){
                                                                        binding.topAppBar.setTitle("Contacts of 4th Year");
                                                                        int size = userList.size();
                                                                        userList.clear();
                                                                        if(size !=0){
                                                                            adapter.notifyItemRangeRemoved(0, size);
                                                                        }
                                                                        for(DocumentSnapshot stuRollNo : task.getResult().getDocuments()){
                                                                            StudentData data = stuRollNo.toObject(StudentData.class);
                                                                            if(data !=null){
                                                                                phoneNo = data.getPersonal_phone();
                                                                                name = data.getFull_name();
                                                                                roll = data.getRoll_number();
                                                                                userList.add(new ContactModel(name, roll, phoneNo));
                                                                                adapter = new ContactAdapter(userList);
                                                                                layoutManager = new LinearLayoutManager(getContext());
                                                                                layoutManager.setOrientation(RecyclerView.VERTICAL);
                                                                                binding.recyclerview.setLayoutManager(layoutManager);
                                                                                binding.recyclerview.setAdapter(adapter);
                                                                                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
                                                                                binding.recyclerview.addItemDecoration(dividerItemDecoration);
                                                                                adapter.notifyDataSetChanged();
                                                                            }

                                                                        }
                                                                    }
                                                                }
                                                            });
                                                    break;
                                            }

                                            return true;
                                        }


                                    });

                                }
                            }
                        }
                    }
                });


        // student

        db.collection("College_Project").document("student").collection("1st Year").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot stuRollNo : task.getResult().getDocuments()){
                                StudentData data = stuRollNo.toObject(StudentData.class);
                                if(data.getEmail().equals(mAuth.getCurrentUser().getEmail())){
                                    db.collection("College_Project").document("teacher").collection("teacher_details").get()
                                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                    if(task.isSuccessful()){
                                                        for(DocumentSnapshot teacherEmail : task.getResult().getDocuments()){
                                                            TeacherData data1 = teacherEmail.toObject(TeacherData.class);
                                                            if(data1!=null){
                                                                phoneNo = data1.getPhone_no();
                                                                name = data1.getFull_name();

                                                                userList.add(new ContactModel(name, data1.getEmail(), phoneNo));
                                                                adapter = new ContactAdapter(userList);
                                                                layoutManager = new LinearLayoutManager(getContext());
                                                                layoutManager.setOrientation(RecyclerView.VERTICAL);
                                                                binding.recyclerview.setLayoutManager(layoutManager);
                                                                binding.recyclerview.setAdapter(adapter);
                                                                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
                                                                binding.recyclerview.addItemDecoration(dividerItemDecoration);
                                                                adapter.notifyDataSetChanged();
                                                            }
                                                        }
                                                    }
                                                }
                                            });
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
                                    db.collection("College_Project").document("teacher").collection("teacher_details").get()
                                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                    if(task.isSuccessful()){
                                                        for(DocumentSnapshot teacherEmail : task.getResult().getDocuments()){
                                                            TeacherData data1 = teacherEmail.toObject(TeacherData.class);
                                                            if(data1!=null){
                                                                phoneNo = data1.getPhone_no();
                                                                name = data1.getFull_name();

                                                                userList.add(new ContactModel(name, data1.getEmail(), phoneNo));
                                                                adapter = new ContactAdapter(userList);
                                                                layoutManager = new LinearLayoutManager(getContext());
                                                                layoutManager.setOrientation(RecyclerView.VERTICAL);
                                                                binding.recyclerview.setLayoutManager(layoutManager);
                                                                binding.recyclerview.setAdapter(adapter);
                                                                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
                                                                binding.recyclerview.addItemDecoration(dividerItemDecoration);
                                                                adapter.notifyDataSetChanged();
                                                            }
                                                        }
                                                    }
                                                }
                                            });
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
                                    db.collection("College_Project").document("teacher").collection("teacher_details").get()
                                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                    if(task.isSuccessful()){
                                                        for(DocumentSnapshot teacherEmail : task.getResult().getDocuments()){
                                                            TeacherData data1 = teacherEmail.toObject(TeacherData.class);
                                                            if(data1!=null){
                                                                phoneNo = data1.getPhone_no();
                                                                name = data1.getFull_name();

                                                                userList.add(new ContactModel(name, data1.getEmail(), phoneNo));
                                                                adapter = new ContactAdapter(userList);
                                                                layoutManager = new LinearLayoutManager(getContext());
                                                                layoutManager.setOrientation(RecyclerView.VERTICAL);
                                                                binding.recyclerview.setLayoutManager(layoutManager);
                                                                binding.recyclerview.setAdapter(adapter);
                                                                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
                                                                binding.recyclerview.addItemDecoration(dividerItemDecoration);
                                                                adapter.notifyDataSetChanged();
                                                            }
                                                        }
                                                    }
                                                }
                                            });
                                }
                            }
                        }
                    }
                });

        db.collection("College_Project").document("student").collection("4th Year").get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()){
                                    for(DocumentSnapshot stuRollNo : task.getResult().getDocuments()){
                                        StudentData data = stuRollNo.toObject(StudentData.class);
                                        if(data.getEmail().equals(mAuth.getCurrentUser().getEmail())){
                                            db.collection("College_Project").document("teacher").collection("teacher_details").get()
                                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                            if(task.isSuccessful()){
                                                                for(DocumentSnapshot teacherEmail : task.getResult().getDocuments()){
                                                                    TeacherData data1 = teacherEmail.toObject(TeacherData.class);
                                                                    if(data1!=null){
                                                                        phoneNo = data1.getPhone_no();
                                                                        name = data1.getFull_name();

                                                                        userList.add(new ContactModel(name, data1.getEmail(), phoneNo));
                                                                        adapter = new ContactAdapter(userList);
                                                                        layoutManager = new LinearLayoutManager(getContext());
                                                                        layoutManager.setOrientation(RecyclerView.VERTICAL);
                                                                        binding.recyclerview.setLayoutManager(layoutManager);
                                                                        binding.recyclerview.setAdapter(adapter);
                                                                        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
                                                                        binding.recyclerview.addItemDecoration(dividerItemDecoration);
                                                                        adapter.notifyDataSetChanged();
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    });
                                        }
                                    }
                                }
                            }
                        });



          /* *****************************************
                          hide bottom bar
             ***************************************** */

        binding.recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                HomeActivity homeActivity = (HomeActivity) getActivity();
                // scroll down

                if (dy > 0 && homeActivity.findViewById(R.id.bottom).getVisibility() == View.VISIBLE) {

                    homeActivity.findViewById(R.id.bottom).setVisibility(View.GONE);
                    TranslateAnimation animate = new TranslateAnimation(0, 0, 0, homeActivity.findViewById(R.id.bottom).getHeight());
                    animate.setDuration(400);
                    homeActivity.findViewById(R.id.bottom).startAnimation(animate);

                }
                // scroll up
                if (dy < -10 && homeActivity.findViewById(R.id.bottom).getVisibility() == View.GONE) {
                    homeActivity.findViewById(R.id.bottom).setVisibility(View.VISIBLE);
                    TranslateAnimation animate = new TranslateAnimation(0, 0, homeActivity.findViewById(R.id.bottom).getHeight(), 0);
                    // duration of animation
                    animate.setDuration(200);
                    animate.setFillAfter(true);
                    homeActivity.findViewById(R.id.bottom).startAnimation(animate);
                }
            }
        });

        return binding.getRoot();
    }


    /* *****************************************
             initialize the data for adapter
       ***************************************** */
   /* private void initData() {



        userList = new ArrayList<>();
        db.collection("College_Project").document("student").collection("academic_details")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            List<DocumentSnapshot> list = task.getResult().getDocuments();
                            rollNoList = new ArrayList<>();
                         for(DocumentSnapshot document : list){
                             AcademicData data = document.toObject(AcademicData.class);
                            roll = data.getRoll_number();
                            rollNoList.add(roll);

                            Toast.makeText(getContext(), roll , Toast.LENGTH_SHORT).show();

                         }
                        }
                    }
                });

        db.collection("College_Project").document("student").collection("personal_details")
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<DocumentSnapshot> list = task.getResult().getDocuments();
                        nameList = new ArrayList<>();
                        phoneList = new ArrayList<>();
                        for(DocumentSnapshot document : list) {
                            PersonalData data = document.toObject(PersonalData.class);
                            phoneNo = data.getPersonal_phone();
                            System.out.println(phoneNo);
                            phoneList.add(phoneNo);

                            name = data.getFull_name();

                            nameList.add(name);
                            userList.add(new ContactModel(name,phoneNo));
                            System.out.println(userList.size());
                            adapter = new ContactAdapter(userList);
                            adapter.notifyDataSetChanged();

                        }
                    }
                });

           // System.out.println(nameList.size());




        *//*userList.add(new ContactModel(R.drawable.a5, "Mark", "9568326352745", "9876536210"));
        userList.add(new ContactModel(R.drawable.a3, "Komal", "9568320005745", "98765363210"));
        userList.add(new ContactModel(R.drawable.a4, "Mahima", "9568300125745", "98766523210"));
        userList.add(new ContactModel(R.drawable.a5, "Soni", "9568324105745", "9876543000"));
        userList.add(new ContactModel(R.drawable.a3, "Hari", "9568324195745", "98765432650"));
        userList.add(new ContactModel(R.drawable.a3, "Kamran", "956832825745", "9876543220"));
        userList.add(new ContactModel(R.drawable.a5, "Mohan", "9876543560"));
        userList.add(new ContactModel(R.drawable.a4, "Sita", "9568324165745", "9876543216"));
        userList.add(new ContactModel(R.drawable.a5, "Ganesh", "9568329625745", "98765432210"));
        userList.add(new ContactModel(R.drawable.a5, "Pappu", "9568324335745", "9876543213"));
        userList.add(new ContactModel(R.drawable.a0, "Sidharth", "9568334125745", "9876544210"));
        userList.add(new ContactModel(R.drawable.a3, "Yash", "9568324129745", "9876543217"));
        userList.add(new ContactModel(R.drawable.a4, "Ambika", "956832435745", "9876543610"));
        userList.add(new ContactModel(R.drawable.a3, "Babu Rao", "9568323125745", "9876443210"));
        userList.add(new ContactModel(R.drawable.a5, "Shyam", "9568324125795", "9876543610"));
        userList.add(new ContactModel(R.drawable.a4, "Raju Rastogi", "9568024125745", "8876543210"));
        userList.add(new ContactModel(R.drawable.a5, "Om Puri", "9568624125745", "9876563210"));
        userList.add(new ContactModel(R.drawable.a3, "Tanveer", "9568327125745", "9876546210"));
        userList.add(new ContactModel(R.drawable.a0, "Yash", "9568324155745", "9876543215"));
        userList.add(new ContactModel(R.drawable.a5, "Ram", "9568324126745", "9876543219"));
        userList.add(new ContactModel(R.drawable.a0, "Mark", "9568322525745", "9876543280"));
        userList.add(new ContactModel(R.drawable.a5, "Kamran", "9568354125745", "9876548210"));
   *//* }*/


    /* *****************************************
                set data to adapter
     ***************************************** */
   

    /*private void initRecyclerView () {

        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ContactAdapter(userList);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        adapter.notifyDataSetChanged();
    }*/
}