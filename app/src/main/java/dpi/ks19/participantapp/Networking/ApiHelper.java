package dpi.ks19.participantapp.Networking;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


import dpi.ks19.participantapp.CallbackInterface.CollegeInterface;
import dpi.ks19.participantapp.CallbackInterface.OTPInterface;
import dpi.ks19.participantapp.CallbackInterface.QrResponse;
import dpi.ks19.participantapp.CallbackInterface.EventsByCluster;
import dpi.ks19.participantapp.Model.EventClass;
import dpi.ks19.participantapp.R;

public class ApiHelper{

    Context ctx;
    private static ApiHelper instance;
    SharedPreferences sharedPreferences;

    String baseUrl;

    public ApiHelper(Context ctx){
        CookieManager manager = new CookieManager();
        CookieHandler.setDefault(manager);
        this.ctx = ctx;
        //baseUrl = "http://www.kuruksastra.in/participants/";
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
        });

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
                Log.d("VERIFY_JSON_RESPONSE", response.toString());
                //check whether otp is verified
                try{
                    callback.isOTPVerified(response.getBoolean("valid"));
                }catch(JSONException e){}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VERIFY_JSON ERROR",error.toString());
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


    public void registerUser(String name, String password, String phone, String college, String aid, int accomadation){

        String newAid = aid.replace("KSCA19","");
        JSONObject params = new JSONObject();
        try{

            params.put("name",name);
            params.put("password",password);
            Log.d("NEW_AID",newAid);
            params.put("aid",newAid);//format KSCA19+ three digit // default zero
            params.put("phone",phone);
            params.put("college",college);
            params.put("hostel",accomadation);
        }catch (Exception e){
            Log.d("AID_ERROR",newAid);
        }

        String url = baseUrl+"addParticipant.php";

        JsonObjectRequest registerRequest = new JsonObjectRequest(url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("REGISTER_USER",response.toString());
                //callback.registerStatus(true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("REGISTER_USER_ERROR",error.toString());
                //callback.registerStatus(false);
            }
        });

        registerRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        CustomRequestQueue.getInstance(ctx).setRequest(registerRequest);
    }


    public void loginUser(String email){
        String URL = baseUrl+"Mlogin.php";
        JSONObject params= new JSONObject();

        try{
            params.put("email",email);
        }catch (Exception e){}

        JsonObjectRequest loginRequest = new JsonObjectRequest(Request.Method.POST, URL, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("LOGIN_RESPONSE",response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("LOGIN_ERROR",error.toString());
            }
        });

        loginRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        CustomRequestQueue.getInstance(ctx).setRequest(loginRequest);
    }


    public void loginVerify(String otp, final OTPInterface callback){
        String URL = baseUrl+"MverifyOTP.php";

        JSONObject params= new JSONObject();
        try{
            params.put("otp",otp);
        }catch (Exception e){}

        JsonObjectRequest loginRequest = new JsonObjectRequest(Request.Method.POST, URL, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("LOGIN_VERIFY_RESPONSE",response.toString());
                try{
                    saveUserData(response);
                    callback.isOTPVerified(response.getBoolean("valid"));
                }catch(JSONException e){}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("LOGIN_VERIFY_ERROR",error.toString());
            }
        });

        CustomRequestQueue.getInstance(ctx).setRequest(loginRequest);
    }


    public void getQrCode(final QrResponse callback){
        String URL = baseUrl+"getQR.php";

        ImageRequest qrRequest = new ImageRequest(URL, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                callback.getQRCode(true, response);
            }
        }, 180, 180, ImageView.ScaleType.FIT_XY, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("QR_RESPONSE_ERROR",error.toString());
                callback.getQRCode(false, null);
            }
        });

        qrRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        CustomRequestQueue.getInstance(ctx).setRequest(qrRequest);
    }


    public void getColleges(final CollegeInterface callback){
        String url = baseUrl+"getColleges.php";

        JsonArrayRequest collegeListRequest = new JsonArrayRequest(url,  new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("CLG_JSON_RESPONSE", response.toString());
                callback.getCollegeList(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("CLG_JSON_ERROR",error.toString());
            }
        });
        CustomRequestQueue.getInstance(ctx).setRequest(collegeListRequest);
    }


    public void getEventsForCluster(final int day, String cluster, final EventsByCluster callback){

        String URL = baseUrl+"getEventsByCluster.php";

        HashMap<String,String>params = new HashMap<>();
        params.put("cluster",cluster);

        JsonObjectRequest eventsRequest = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.getEventsByCluster(parseData(day, response),true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.getEventsByCluster(null,false);
            }
        });

        eventsRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        CustomRequestQueue.getInstance(ctx).setRequest(eventsRequest);
    }


    private ArrayList<EventClass> parseData(int day, JSONObject eventListObject){
        ArrayList<EventClass>data = new ArrayList<>();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        Date startDate, endDate;

        try{
            JSONArray eventList = eventListObject.getJSONArray("events");
            Log.d("NO_OF_EVENTS",eventList.length()+"");
            for(int i=0; i<eventList.length(); i++){
                EventClass temp = new EventClass();
                JSONObject singleEvent = eventList.getJSONObject(i);

                startDate = dateformat.parse(singleEvent.getString("fromTime"));
                endDate = dateformat.parse(singleEvent.getString("toTime"));
                Log.d("DATE",startDate.getDate()+"");
                if(startDate.getDate() == day){
                    temp.eventName = singleEvent.getString("ename");
                    temp.startTimeHours = startDate.getHours()+"";
                    temp.startTimMin = startDate.getMinutes()+"";
                    temp.endTimeMin = endDate.getMinutes()+"";
                    temp.endTimeHours = endDate.getHours()+"";
                    temp.venue = singleEvent.getString("venue");
                    data.add(temp);
                }

            }

        }catch (Exception e){
            Log.d("ERROR_EVENTS",e.toString());

        }
        return data;
    }


    private void saveUserData(JSONObject object){
        try{
            sharedPreferences = ctx.getSharedPreferences(ctx.getString(R.string.shared_pref), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
             editor.putString(ctx.getString(R.string.name), object.getString("name"));
            editor.putString(ctx.getString(R.string.email), object.getString("email"));
             editor.putString(ctx.getString(R.string.college_name), object.getString("college"));
             editor.putString(ctx.getString(R.string.phone_number), object.getString("phone"));
             editor.putString(ctx.getString(R.string.hostel_accomodation), object.getString("accomodation"));
             editor.apply();
            editor.commit();
            Log.d("JSON_USER","SAVE_SUCCESS");
        }catch (JSONException e){
            Log.d("JSON_USER","ERROR");
        }
    }

}
