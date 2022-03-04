package com.example.CopIt;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegisterViewPresenter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        linkLogin();

    }

    private void linkLogin(){
        Button backToLoginBut = (Button)findViewById(R.id.backtologin);
        backToLoginBut.setOnClickListener(new View.OnClickListener(){
           public void onClick(View view){
               finish();
           }
        });
    }

    /**
     * Checks username for correctness, and ensures doesn't exist in DB, sets errors in EditText.
     * @param username EditText to get name and set errors within.
     * @return True iff. no errors with username.
     */
    public boolean checkUsername(EditText username) {
        //Check username requirements.
        if (username.getText().length() < 5) {
            username.setError("Username must be between 5-20 characters");
            return false;
        } else {
            String usernameRegex = "[A-Za-z0-9]{5,20}";
            Pattern usernamePattern = Pattern.compile(usernameRegex);
            if (!usernamePattern.matcher(username.getText()).matches()) {
                username.setError("Username must only include letters or numbers");
                return false;
            }
        }
        checkUsernameExists(username);
        return true;
    }
    /**
     * Checks password for correctness and ample strength.
     * @param password EditText to get password and set errors within.
     * @return True iff. no problems with password.
     */
    public boolean checkPassword (EditText password) {
        if (password.getText().length() < 5) {
            password.setError("Password length must be between 5-20 characters");
            return false;
        } else {
            String passwordRegex = "([A-Za-z0-9]{4,20}[0-9]+)|([0-9]+[A-Za-z0-9]{4,20})";
            Pattern passwordPattern = Pattern.compile(passwordRegex);
            if (!passwordPattern.matcher(password.getText()).matches()) {
                password.setError("Password must include letters and at least one number 0-9");
                return false;
            }
        }
        return true;
    }

    /**
     * Checks email for correctness, sets errors in EditText.
     * @param email EditText to get email from and set errors within.
     * @return True iff. no errors with email.
     */
    public boolean checkEmail (EditText email) {
        String emailRegex = "[A-Za-z0-9]+@[A-Za-z.]+[.][A-Za-z.]+";
        Pattern emailPattern = Pattern.compile(emailRegex);
        if (!emailPattern.matcher(email.getText()).matches()) {
            email.setError("Please enter a valid email address");
            return false;
        }
        return true;
    }

    /**
     * Checks name for correctness, sets errors in EditText
     * @param name EditText to get name and set errors within.
     * @return True iff. no errors with name.
     */
    public boolean checkName (EditText name) {
        String nameRegex = "[A-Za-z ]+ [A-Za-z ]+";
        Pattern namePattern = Pattern.compile(nameRegex);
        if (!namePattern.matcher(name.getText()).matches()) {
            name.setError("Please enter a valid name");
            return false;
        }
        return true;
    }

    public boolean checkNumber (EditText number) {
        if (number.getText().toString().length() != 10) {
            number.setError("Enter valid phone number");
            return false;
        }
        return true;
    }

    /**
     * Handler for SIGN UP button in activity_sign_up.xml.
     * @param view Modifies given view to let user know of errors in registration fields.
     */
    public void registerHandler(View view) {

        EditText username = findViewById(R.id.usernamebox);
        username.setError(null);
        EditText password = findViewById(R.id.passwordbox);
        password.setError(null);
        EditText phone = findViewById(R.id.phonebox);
        phone.setError(null);
        EditText email = findViewById(R.id.emailbox);
        email.setError(null);
        EditText name = findViewById(R.id.namebox);
        name.setError(null);

        Boolean regError = false;

        if (!checkUsername(username)) regError = true;
        if (!checkPassword(password)) regError = true;
        if (!checkEmail(email)) regError = true;
        if (!checkName(name)) regError = true;
        if (!checkNumber(phone)) regError = true;
        if (!regError) { //No error and username verified, we register an account
            registerAccount(username.getText().toString(), password.getText().toString(), phone.getText().toString(), email.getText().toString(), name.getText().toString());
        }
    }

    /**
     * Check if given username is in use, adds error to given EditText if username already exists.
     * @param username EditText box to get username from, and display errors within.
     */
    private void checkUsernameExists(final EditText username) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://backhomediesel.ddns.net/~liam/project-backhomediesel/html/checkUserExists.php";
        StringRequest strReq = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Response is: "+ response);
                        if (response.compareTo("FALSE") == 0) {
                            //System.out.println("User not exist");
                        } else  if (response.compareTo("TRUE") == 0) {
                            username.setError("User already exists.");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error");
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username.getText().toString());
                return params;
            }
        };
        queue.add(strReq);
    }

    /**
     * Creates new account given parameters, returns true on success.
     * @param username Username for new user
     * @param password Password for new user
     * @param email Email for new user
     * @param name Name for new user
     */
    private void registerAccount(final String username, final String password, final String phone, final String email, final String name) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://backhomediesel.ddns.net/~liam/project-backhomediesel/html/newUser.php";
        StringRequest strReq = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Response is -- "+ response + "\n");
                        if (response.compareTo("TRUE") == 0) { //Successful
                            Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Registration Error", Toast.LENGTH_SHORT).show();
                System.out.println(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("passhash", password);
                params.put("email", email);
                params.put("phone", phone);
                params.put("name", name);
                return params;
            }
        };
        queue.add(strReq);
    }
}
