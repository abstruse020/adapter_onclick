package com.example.harshpandey.binge_assignment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class restaurantsActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //final TextView displaytext =  (TextView) findViewById(R.id.displayText);
        //String[] restaurantsName={""};
        db.collection("restaurant")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        String datastore_name = "",datastore_add="",datastore_timing="",resId="",datastore_image="";
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("restaurantsActiveity", document.getId() + " => " + document.getData());
                                datastore_name =datastore_name+document.getData().get("name")+":";
                                datastore_add = datastore_add + "Address "+document.getData().get("address")+":";
                                datastore_timing = datastore_timing + document.getData().get("timing")+"b";
                                resId = resId + document.getId() + ":";
                                datastore_image= datastore_image + document.getData().get("image") + ":";

                            }
                            ready_for_adapter(datastore_name, datastore_add, datastore_timing, resId, datastore_image);
                        } else {
                            Log.w("restaurantsActivety", "Error getting documents.", task.getException());
                        }
                       // displaytext.setText(datastore);// remove it later

                        //restaurantsName = datastore.split(":");
                    }
                });

    }

    public void ready_for_adapter(String dataset_name,String dataset_add,String dataset_timing,String resId,String dataset_image)
    {
        ArrayList<Restaurants> restaurants_list = new ArrayList<Restaurants>();
        String[] separated_name = dataset_name.split(":");
        String[] separated_add = dataset_add.split(":");
        String[] separated_timing = dataset_timing.split("b");
        String[] separated_id = resId.split(":");
        String[] separated_image = dataset_image.split(":");

        for(int i=0; i<separated_name.length ; i++)
        {
            restaurants_list.add(new Restaurants(separated_name[i],separated_add[i],separated_timing[i], separated_id[i], separated_image[i]));
        }
        restaurantsAdapter restAdapter = new restaurantsAdapter(this,restaurants_list);
        ListView listView = (ListView) findViewById(R.id.listview_restaurants);
        Log.d("restaurantActivity","setting the adapter to listview_restaurants");
        listView.setAdapter(restAdapter);

    }



}
