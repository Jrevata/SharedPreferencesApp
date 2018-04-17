package com.jrevatas7.sharedpreferencesapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class DashBoardActivity extends AppCompatActivity {

    private static final String TAG = DashBoardActivity.class.getSimpleName();

    // SharedPreferences
    private SharedPreferences sharedPreferences;

    private TextView usernameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        usernameText = (TextView)findViewById(R.id.fullname_text);

        // init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // get username from SharedPreferences
        String username = sharedPreferences.getString("username", null);
        Log.d(TAG, "username: " + username);

        User user = UserRepository.getUser(username);

        usernameText.setText(user.getFullname());
    }

    public void callLogout(View view){
        // remove from SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean success = editor.putBoolean("islogged", false).commit();
//        boolean success = editor.clear().commit(); // not recommended
        Intent intent = new Intent(DashBoardActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void callConfiguration(View view){
        Intent intent = new Intent(this, MyPreferencesActivity.class);
        startActivity(intent);
    }

}
