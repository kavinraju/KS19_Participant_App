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
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.HashMap;
import java.util.Map;

import dpi.ks19.participantapp.CallbackInterface.GenerateOTPInterface;
import dpi.ks19.participantapp.R;

public class ApiHelper {

    Context ctx;
    private static ApiHelper instance;
    SharedPreferences sharedPreferences;

    public ApiHelper(Context ctx){
        CookieManager manager = new CookieManager();
        CookieHandler.setDefault(manager);
        this.ctx = ctx;
        sharedPreferences = ctx.getSharedPreferences(ctx.getString(R.string.cookie_shared), Context.MODE_PRIVATE);
    }

    public static ApiHelper getInstance(Context ctx){
        if(instance == null){
            instance = new ApiHelper(ctx);
        }
        return instance;
    }

    public void generateOTP(String email, final GenerateOTPInterface callback){
        String URL="http://45.251.34.245:7167/ks/participants/generateOTP.php";
        Log.d("EMAIL:",email);
        //create json object
        HashMap<String, String> params = new HashMap<>();
        params.put("email",email);
        JSONObject json = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,URL,json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("JSON_RESPONSE", response.toString());
                //check whether it is a valid email id
                try{
                    callback.isEmailVerified(response.getBoolean("valid"));
                }catch(JSONException e){}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("JSON ERROR",error.toString());
            }
        }){
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                Map<String,String> responseHeader = response.headers;
                String cookies = responseHeader.get("Set-Cookie");
                Log.d("COOKIE",cookies);
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


    public void verifyOTP(String otp,final GenerateOTPInterface callback){

        String URL="http://45.251.34.245:7167/ks/participants/verifyOTP.php";
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
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String>headers = new HashMap<>();
                String cookie = sharedPreferences.getString(ctx.getString(R.string.cookie_key),"NOT_FOUND");
                headers.put("cookie",cookie);
                return headers;
            }
        };

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        CustomRequestQueue.getInstance(ctx).setRequest(jsonRequest);
    }
}
