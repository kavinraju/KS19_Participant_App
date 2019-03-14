package dpi.ks19.participantapp.Networking;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.HashMap;
import java.util.Map;

import dpi.ks19.participantapp.CallbackInterface.CollegeInterface;
import dpi.ks19.participantapp.CallbackInterface.OTPInterface;
import dpi.ks19.participantapp.R;

public class ApiHelper {

    Context ctx;
    private static ApiHelper instance;
    SharedPreferences sharedPreferences;

    String baseUrl;
    public ApiHelper(Context ctx){
        CookieManager manager = new CookieManager();
        CookieHandler.setDefault(manager);
        this.ctx = ctx;
        baseUrl = "https://protocolfest.co.in/ks/participants/";
        sharedPreferences = ctx.getSharedPreferences(ctx.getString(R.string.cookie_shared), Context.MODE_PRIVATE);
    }

    public static ApiHelper getInstance(Context ctx){
        if(instance == null){
            instance = new ApiHelper(ctx);
        }
        return instance;
    }


    public void generateOTP(String email){
        String URL = baseUrl+"generateOTP.php";
        Log.d("EMAIL:",email);
        //create json object
        HashMap<String, String> params = new HashMap<>();
        params.put("email",email);
        JSONObject json = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,URL,json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("GENERATE_JSON_RESPONSE", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("GENERATE_JSON_ERROR",error.toString());
            }
        }){//save the cookie session locally
            //clear when the user logs out
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                Map<String,String> responseHeader = response.headers;
                String cookies = responseHeader.get("Set-Cookie");
                Log.d("SET_COOKIE",cookies);
                //saving the cookie
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(ctx.getString(R.string.cookie_key),cookies);
                editor.apply();
                editor.commit();
                return super.parseNetworkResponse(response);
            }
        };

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        CustomRequestQueue.getInstance(ctx).setRequest(jsonRequest);
    }


    public void verifyOTP(String otp,final OTPInterface callback){

        String URL=baseUrl+"verifyOTP.php";
        Log.d("OTP:",otp);
        //create json object
        HashMap<String, String> params = new HashMap<>();
        params.put("otp",otp);
        JSONObject json = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,URL,json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("JSON_RESPONSE", response.toString());
                //check whether otp is verified
                try{
                    callback.isOTPVerified(response.getBoolean("valid"));
                }catch(JSONException e){}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("JSON ERROR",error.toString());
            }
        })/*{
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String>headers = new HashMap<>();
                String cookie = sharedPreferences.getString(ctx.getString(R.string.cookie_key),"NOT_FOUND");
                Log.d("GET_COOKIE",cookie);
                headers.put("cookie",cookie);
                return headers;
            }
        }*/;

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        CustomRequestQueue.getInstance(ctx).setRequest(jsonRequest);
    }


    public void registerUser(String name, String phone, String college, String aid, String accomadation){

        HashMap<String, String>params = new HashMap<>();
        params.put("name",name);
        params.put("phone",phone);
        params.put("college",college);
        params.put("accomadation",accomadation);

        String url = baseUrl+"addParticipant.php";

        JsonObjectRequest registerRequest = new JsonObjectRequest(url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("REGISTER_USER",response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("REGISTER_USER_ERROR",error.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String>headers = new HashMap<>();
                String cookie = sharedPreferences.getString(ctx.getString(R.string.cookie_key),"NOT_FOUND");
                headers.put("cookie",cookie);
                return headers;
            }
        };
        registerRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        CustomRequestQueue.getInstance(ctx).setRequest(registerRequest);
    }


    public void getColleges(final CollegeInterface callback){
        String url = baseUrl+"getColleges.php";

        JsonArrayRequest collegeListRequest = new JsonArrayRequest(url,  new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("JSON_RESPONSE", response.toString());
                callback.getCollegeList(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("JSON ERROR",error.toString());
            }
        });
        CustomRequestQueue.getInstance(ctx).setRequest(collegeListRequest);
    }
}
