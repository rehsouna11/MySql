package ae.com.mysql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //initializing EditText view and declaring strings for name and pass
    EditText et_name,et_pass;
    String login_name,login_pass;

    //----------------------------------------------------------------------------


    //the on create method
    //get views and assign them

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name = (EditText) findViewById(R.id.user_name);
        et_pass = (EditText) findViewById(R.id.user_pass);
    }

    //----------------------------------------------------------------------------


    //onclick method for submit
    public void userLogin(View view){

        //get text from EditViews
        login_name = et_name.getText().toString();
        login_pass = et_pass.getText().toString();

        // declaring a string called method to recognize the
        // login in the async task
        String method = "login";

        //create a new BackgroundTask from custom class and execute it
        //pass to it name and pass and method string
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,login_name,login_pass);

    }

    //----------------------------------------------------------------------------

    //onclick method for registration
    public void userReg (View view){

        //start a new activity for registration
        startActivity(new Intent(this,Main2Activity.class));

    }

    //----------------------------------------------------------------------------

}
