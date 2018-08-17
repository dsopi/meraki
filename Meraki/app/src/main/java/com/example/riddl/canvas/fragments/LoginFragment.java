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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class LoginFragment extends Fragment implements View.OnClickListener {

    FirebaseAuth mAuth;
    EditText emailAddress, password;


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        Button button = view.findViewById(R.id.login_btn);
        button.setOnClickListener(this);
        TextView forgotPassword = view.findViewById(R.id.forgot_password);
        forgotPassword.setOnClickListener(this);
        TextView newAccount = view.findViewById(R.id.not_a_member);
        newAccount.setOnClickListener(this);

        emailAddress = view.findViewById(R.id.emailEditText);
        password = view.findViewById(R.id.passwordEditText);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case(R.id.login_btn):
                Toast.makeText(getActivity(), "Login Button", Toast.LENGTH_SHORT).show();

                String email = emailAddress.getText().toString();
                String pass = password.getText().toString();
                signIn(email, pass);
                break;

            case(R.id.forgot_password):
                ForgotPasswordFragment forgotPassFragment = new ForgotPasswordFragment();
                ((AccountActivity)getActivity()).changeFragment(forgotPassFragment);
                break;

            case(R.id.not_a_member):
                NewAccountFragment newAccFragment = new NewAccountFragment();
                ((AccountActivity)getActivity()).changeFragment(newAccFragment);
                break;

        }
    }


    private void signIn(String email, String pass){

        if (email.length() == 0 || pass.length() == 0){
            return;
        }
        else {
            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.w(TAG, "SUCCESS");
                                FirebaseUser user = mAuth.getCurrentUser();

                                Intent i = new Intent(getActivity(), MainActivity.class);
                                startActivity(i);
                                getActivity().finish();

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "FAIL");
                                Toast.makeText(getActivity(), "Sign in failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
    }

}
