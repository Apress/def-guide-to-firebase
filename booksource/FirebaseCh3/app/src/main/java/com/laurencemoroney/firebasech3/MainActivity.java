package com.laurencemoroney.firebasech3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button writeButton;
    TextView viewText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("portfolios");
        writeButton = (Button) findViewById(R.id.writeButton);
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<StockPortfolio> myFolios = new ArrayList<StockPortfolio>();

                StockPortfolio myFolio = new StockPortfolio("demoFolio", "lmoroney", "lm@hotmail.com");

                ArrayList<Stock> myFolioHoldings = new ArrayList<Stock>();
                myFolioHoldings.add(new Stock("GOOG", "Google", 100));
                myFolioHoldings.add(new Stock("AAPL", "Apple", 50));
                myFolioHoldings.add(new Stock("MSFT", "Microsoft", 10));
                myFolio.portfolioHoldings = myFolioHoldings;

                StockPortfolio myOtherFolio = new StockPortfolio("realFolio", "lmoroney", "lmwork@hotmail.com");

                ArrayList<Stock> myOtherFolioHoldings = new ArrayList<Stock>();
                myOtherFolioHoldings.add(new Stock("IBM", "IBM", 50));
                myOtherFolioHoldings.add(new Stock("MMM", "3M", 10));
                myOtherFolio.portfolioHoldings = myOtherFolioHoldings;

                myFolios.add(myFolio);
                myFolios.add(myOtherFolio);
                myRef.setValue(myFolios);

                //myRef.setValue("Hello, World");
            }
        });
        viewText = (TextView) findViewById(R.id.viewText);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<StockPortfolio>> t = new GenericTypeIndicator<ArrayList<StockPortfolio>>() {};
                ArrayList<StockPortfolio> myFolios = dataSnapshot.getValue(t);

                String value = myFolios.get(0).portfolioName;
                viewText.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Ch3", "Failed to read value.", error.toException());
            }
        });

        Button queryButton = (Button) findViewById(R.id.queryButton);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = myRef.orderByChild("portfolioName").equalTo("demoFolio");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        GenericTypeIndicator<ArrayList<StockPortfolio>> t = new GenericTypeIndicator<ArrayList<StockPortfolio>>() {};
                        ArrayList<StockPortfolio> myFolios = dataSnapshot.getValue(t);
                        String pName="";
                        if(myFolios != null){
                            pName = myFolios.get(0).portfolioName;

                        }

                        viewText.setText(pName);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        Button updateButton = (Button) findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = myRef.orderByChild("portfolioName").equalTo("demoFolio");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        DataSnapshot nodeShot = dataSnapshot.getChildren().iterator().next();
                        String key = nodeShot.getKey();
                        HashMap<String, Object> update = new HashMap<>();
                        update.put("portfolioOwner", "New Owner");
                        myRef.child(key).updateChildren(update);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = myRef.orderByChild("portfolioName").equalTo("demoFolio");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        DataSnapshot nodeShot = dataSnapshot.getChildren().iterator().next();
                        String key = nodeShot.getKey();
                        myRef.child(key).removeValue();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
