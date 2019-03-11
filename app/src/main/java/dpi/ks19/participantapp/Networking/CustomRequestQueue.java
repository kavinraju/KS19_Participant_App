package dpi.ks19.participantapp.Networking;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

class CustomRequestQueue {
    private Context ctx;
    private static CustomRequestQueue instance;

    CustomRequestQueue(Context ctx){
        this.ctx = ctx;

    }

    static CustomRequestQueue getInstance(Context ctx){
        if(instance == null){
            instance = new CustomRequestQueue(ctx);
        }
        return instance;
    }

    <T> void setRequest(Request<T> request){
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        requestQueue.add(request);
    }
}
