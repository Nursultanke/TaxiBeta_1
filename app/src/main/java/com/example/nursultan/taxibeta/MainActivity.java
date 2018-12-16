package com.example.nursultan.taxibeta;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private EditText mPhoneText;
    private EditText mCodeText;

    private Button mSendBtn;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;

    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;

    private int btnType = 0;


    RelativeLayout rellay1;
    LinearLayout linlay2;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);


        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rellay1 = findViewById(R.id.rellay1);
        linlay2 = findViewById(R.id.linlay2);


        handler.postDelayed(runnable, 1000);

        mAuth = FirebaseAuth.getInstance();

        mPhoneText = findViewById(R.id.mPhoneText);
        mCodeText = findViewById(R.id.mCodeText);

        mSendBtn = findViewById(R.id.mSendBtn);

        mAuth = FirebaseAuth.getInstance();

        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btnType == 0){

                mPhoneText.setEnabled(false);
                mSendBtn.setEnabled(false);


                String phoneNumber = mPhoneText.getText().toString();

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        phoneNumber,
                        60,
                        TimeUnit.SECONDS,
                        MainActivity.this,
                        mCallBacks
                );
                }
                else {
                    mSendBtn.setEnabled(false);

                    String varificationCode = mCodeText.getText().toString();
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, varificationCode);
                    signInWithPhoneAuthCredential(credential);



                }
            }
        });

        mCallBacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {


                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
                btnType = 1;
                linlay2.setVisibility(View.VISIBLE);
                mSendBtn.setText("Code");
                mSendBtn.setEnabled(true);
            }

        };


    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//
//        if (currentUser == null){
//            Intent authIntent = new Intent(this, AuthActivity.class);
//            startActivity(authIntent);
//            finish();
//        }
//    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent mainIntent = new Intent(MainActivity.this, AuthActivity.class);
                            startActivity(mainIntent);
                        

                            FirebaseUser user = task.getResult().getUser();
                            // ...
                        } else {

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    public void onClick(View view) {
        Intent ings = new Intent(MainActivity.this, Guest_page.class);
        startActivity(ings);
        finish();
    }


    public void onTip(View view) {

        Intent ing = new Intent(MainActivity.this, AuthActivity.class);

        startActivity(ing);
    }
}






