package com.example.testdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Request request;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        TextView member_name = findViewById(R.id.member_name);
        editText = findViewById(R.id.member_id);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                RequestQueue requestQueue;
                requestQueue = Volley.newRequestQueue(getApplicationContext());

                request = new StringRequest(Request.Method.POST, "http://beta.www.mycoin.bz/sample/eugenetest.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            Log.e("object", object.toString());
                            String name = object.getString("return");
                            member_name.setText(name);


                        } catch (Exception e) {
                            Log.e("errorcatch", e.toString());
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("volleyError", error.toString());
                    }

                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();

                        String text = editText.getText().toString();
                        parameters.put("member_id", text);
                        return parameters;
                    }
                };
                requestQueue.add(request);
            }
        });
    }


}