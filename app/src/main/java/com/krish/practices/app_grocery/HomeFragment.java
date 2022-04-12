package com.krish.practices.app_grocery;

import android.app.ProgressDialog;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment , AppCompatActivity {


        RecyclerView recyclerView;
        ArrayList<User> userArrayList;
        Adapter adapter;
        FirebaseFirestore ff;
        ProgressDialog progressDialog;


        public ColorSpace.Model get(int position) {
            return null;
        }

        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;

        public HomeFragment() {
            // Required empty public constructor
        }

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        public static HomeFragment newInstance(String param1, String param2) {
            HomeFragment fragment = new HomeFragment();
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

                super.onCreate(savedInstanceState);
                setContentView(R.layout.fragment_home);

                progressDialog = new ProgressDialog(this);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Fetching Data...");
                progressDialog.show();

                recyclerView = findViewById(R.id.recycler_view);
                recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                recyclerView.setLayoutManager(gridLayoutManager);

                ff = FirebaseFirestore.getInstance();
                userArrayList = new ArrayList<User>();
                adapter = new Adapter(HomeFragment.this, userArrayList, this);

                recyclerView.setAdapter(adapter);

                EventChangelistener();

            }
        }

        private void EventChangelistener() {
            ff.collection("categories").orderBy("Categoris_Name", Query.Direction.ASCENDING)
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                            if (error != null) {
                                if (progressDialog.isShowing())
                                    progressDialog.dismiss();
                                Log.e("Firestore Error", error.getMessage());
                                return;
                            }
                            for (DocumentChange dc : value.getDocumentChanges()) {
                                if (dc.getType() == DocumentChange.Type.ADDED) {
                                    userArrayList.add(dc.getDocument().toObject(User.class));
                                }
                                adapter.notifyDataSetChanged();
                                if (progressDialog.isShowing())
                                    progressDialog.dismiss();
                            }
                        }
                    });
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_home, container, false);
        }

        @Override
        public void onItemClick(int position) {
//        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
//
//        intent.putExtra("Fruit_Name",userArrayList.get(position).getFruit_Name());
//        intent.putExtra("Price",userArrayList.get(position).getPrice());
//        intent.putExtra("Quantity",userArrayList.get(position).getQuantity());
//        intent.putExtra("Image",userArrayList.get(position).getImage());
//
//        startActivity(intent);
        }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
}