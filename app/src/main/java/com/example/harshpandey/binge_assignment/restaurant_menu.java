package com.example.harshpandey.binge_assignment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class restaurant_menu extends AppCompatActivity {

    FirebaseFirestore db_m = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);
        Log.d("menuActivity","started the page");
        final String id_restaurant = getIntent().getStringExtra("root");
        final String descriptionRestaurant = getIntent().getStringExtra("description");
        final String nameRestaurant = getIntent().getStringExtra("restaurant_name");
        TextView textView = (TextView) findViewById(R.id.description_in_menu);
        textView.setText(descriptionRestaurant);
        TextView textView_name = (TextView) findViewById(R.id.name_restaurant_menu);
        textView_name.setText("Name "+nameRestaurant);

        Log.d("menuActivity","made object"+"\n\n\n restaurant id = "+ id_restaurant);
        String path = "restaurant/"+id_restaurant+"/menu";
        db_m.collection(path)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Log.d("menu activity\n\n\n\n","completed");
                        if (task.isSuccessful()) {
                            //String list = "";
                            ArrayList<menu_items> itm = new ArrayList<menu_items>();
                            //menu_items itm = new menu_items();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("menu activity \n\n\n\n", document.getId() + " => " + document.getData().get("item")+" and price "+document.getData().get("price") );
                                document.getData().get("item");
                                String item,price,desc;
                                item = document.getData().get("item").toString();
                                price = "Rs "+document.getData().get("price").toString();
                                desc = document.getData().get("description").toString();
                                itm.add(new menu_items(item,price,desc));
                                //list= list+document.getData().toString()+":";
                            }

                            pass_to_menuAdapter(itm);

                        } else {
                            Log.d("menu activity\n\n\n\n", "Error getting documents: ", task.getException());
                        }
                    }
                });
//        db_m.collection(path)
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d("menu activity", document.getId() + " => " + document.getData());
//                            }
//                        } else {
//                            Log.d("menu activity", "Error getting documents: ", task.getException());
//                        }
//                    }
//                });
    }

    public void pass_to_menuAdapter(ArrayList<menu_items> list)
    {
//        TextView tv = (TextView) findViewById(R.id.show_preview_menu);
//        tv.setText(list.toString());
        menuAdapter menAdapter = new menuAdapter(this,list);
        ListView listView = (ListView) findViewById(R.id.listview_menu);
        Log.d("restaurantActivity","setting the adapter to listview_restaurants");
        listView.setAdapter(menAdapter);

    }

}
