package com.example.harshpandey.binge_assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

        EditText EmailView;
        EditText PasswordView;
    Button SignInButton;
        private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SignInButton = (Button)findViewById(R.id.email_sign_in_button);

        EmailView = (EditText) findViewById(R.id.email);

        PasswordView = (EditText) findViewById(R.id.password);


        //SignInButton.setEnabled(true);
        //SignInButton.setBackgroundColor(Color.parseColor("#ff833a"));

        //FirebaseAuth mAuth;
        // ...
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

    }

    public void attemptLogin(View view) {
        //true if authentication is required
        Boolean login =true;
        String email = EmailView.getText().toString();
        String password = PasswordView.getText().toString();

        //emptying the fields
        EmailView.setText("");
        PasswordView.setText("");

        Log.v("\n\n\n\nlogin activity", "email " + email + "\n password " + password);
//        if (email.equals("harsh@") && password.equals("harsh")) {
//            //Log.e("login activity", "\n\n\n\n\n\n\n entered correct details");
//            Intent i = new Intent(this,restaurantsActivity.class);
//            startActivity(i);
//        } else {
//            Log.d("login activity","wrong credentials");
//        }
        if(login) {

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Both fields are mandatory",
                        Toast.LENGTH_SHORT).show();

            } else {
                //SignInButton.setEnabled(false);
                //SignInButton.setBackgroundColor(Color.parseColor("#D3D3D3"));
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("Login activity", "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent i = new Intent(getApplicationContext(), restaurantsActivity.class);
                                    startActivity(i);
                                    //updateUI(user);
                                } else {
                                    //SignInButton.setEnabled(true);
                                    //SignInButton.setBackgroundColor(Color.parseColor("#ff833a"));

                                    // If sign in fails, display a message to the user.
                                    Log.w("Login activity", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(getApplicationContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // ...
                            }
                        });
            }
        }
        else
        {
            Intent i = new Intent(getApplicationContext(), restaurantsActivity.class);
            startActivity(i);
        }
//        SignInButton.setEnabled(true);
//        SignInButton.setBackgroundColor(Color.parseColor("#ff833a"));

    }
}
