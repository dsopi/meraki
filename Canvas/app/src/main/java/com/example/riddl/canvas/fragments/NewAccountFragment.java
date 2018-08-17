package com.example.riddl.canvas.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.riddl.canvas.R;
import com.example.riddl.canvas.activities.AccountActivity;
import com.example.riddl.canvas.activities.MainActivity;
import com.example.riddl.canvas.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.ContentValues.TAG;

public class NewAccountFragment extends Fragment implements View.OnClickListener {

    private DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    EditText username, emailAddress, password, confirmPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_account, container, false);

        Button registerBtn = view.findViewById(R.id.register_btn);
        TextView alreadyAccount = view.findViewById(R.id.already_a_member);

        username = view.findViewById(R.id.usernameEditText);
        emailAddress = view.findViewById(R.id.emailEditText);
        password = view.findViewById(R.id.passwordEditText);
        confirmPassword = view.findViewById(R.id.confirmPasswordEditText);

        alreadyAccount.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case(R.id.already_a_member):
                LoginFragment logFragment = new LoginFragment();
                ((AccountActivity)getActivity()).changeFragment(logFragment);
                break;

            case(R.id.register_btn):

                //if username != null, pass != null, confirmPass matches the pass
                String email = emailAddress.getText().toString();
                String pass = password.getText().toString();
                String userN = username.getText().toString();
                String confirmPass = confirmPassword.getText().toString();

                createUser(email, pass, confirmPass, userN);
                break;
        }
    }


    private void createUser(final String email, String pass, String confirmPass, final String userN){
        Log.w(TAG, "createUser was attempted");
        if (userN.length() == 0){
            Toast.makeText(getActivity(), "Username cannot be empty", Toast.LENGTH_SHORT).show();
            Log.w(TAG, "no username");
        }
        else if (email.length() == 0){
            Toast.makeText(getActivity(), "Email cannot be empty", Toast.LENGTH_SHORT).show();
            Log.w(TAG, "email empty");
        }
        else if (pass.length() <= 6 || confirmPass.length() <= 6){
            Toast.makeText(getActivity(), "The password must be more than six characters", Toast.LENGTH_SHORT).show();
            Log.w(TAG, "pass or confirm is empty");
        }
        else if (!pass.equals(confirmPass)){
            Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            Log.w(TAG, "passwords aren't equal " + pass + " " + confirmPass);
        }
        else{
                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.w(TAG, "successful");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    writeNewUser(user.getUid(), userN, email);

                                    Intent i = new Intent(getActivity(), MainActivity.class);
                                    startActivity(i);
                                    getActivity().finish();

                                } else {
                                    Log.w(TAG, "the user wasn't created", task.getException());
                                    Toast.makeText(getActivity(), "Registration failed",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


        }
    }

    private void writeNewUser(String userID, String userName, String emailAddr){
        User user = new User(userName, emailAddr, "https://tinyurl.com/k88p4we");
        mDatabase.child("users").child(userID).child("profile").setValue(user);
    }
}