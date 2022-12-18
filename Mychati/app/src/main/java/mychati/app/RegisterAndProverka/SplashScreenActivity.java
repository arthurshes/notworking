package mychati.app.RegisterAndProverka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import mychati.app.CategUserActivity;
import mychati.app.Client.HomeActivity;
import mychati.app.R;
import mychati.app.Shops.ShopCategoryActivity;
import mychati.app.Shops.ShopHomeActivity;


public class SplashScreenActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference shop;

    private DatabaseReference clientse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mAuth = FirebaseAuth.getInstance();

shop= FirebaseDatabase.getInstance().getReference().child("shops");
clientse=FirebaseDatabase.getInstance().getReference().child("client");
        currentUser = mAuth.getCurrentUser();


        if (currentUser != null) {
            openShop();
        }
    }

    private void openShop() {
shop.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if (snapshot.child(mAuth.getCurrentUser().getUid()).exists()) {

Intent shop=new Intent(SplashScreenActivity.this, ShopHomeActivity.class);

startActivity(shop);

        } else {
            openCLient();
            Toast.makeText(SplashScreenActivity.this, "Не курьер", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});

    }

    private void openCLient() {
        clientse.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(mAuth.getCurrentUser().getUid()).exists()) {
startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));


                } else {
startActivity(new Intent(SplashScreenActivity.this, CategUserActivity.class));
                    Toast.makeText(SplashScreenActivity.this, "Не курьер", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}