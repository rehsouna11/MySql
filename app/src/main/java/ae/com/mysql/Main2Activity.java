package ae.com.mysql;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
    EditText et_name,et_user_name,et_user_pass;
    String name,user_name,user_pass;

    //----------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        et_name = (EditText) findViewById(R.id.name);
        et_user_name = (EditText) findViewById(R.id.user_name);
        et_user_pass = (EditText) findViewById(R.id.user_pass);
    }


    //----------------------------------------------------------------------------


        public void userReg (View view){



            name = et_name.getText().toString();
            user_name = et_user_name.getText().toString();
            user_pass = et_user_pass.getText().toString();
            String method = "register";

            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(method,name,user_name,user_pass);}


    //----------------------------------------------------------------------------
    }

