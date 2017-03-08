package com.hariofspades.stickyheader;

/**
 * Created by hari on 14/7/15.
 */

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class CustomRequest extends Request<JSONObject> {

    private ImageLoader imageLoader;
    private Listener<JSONObject> listener;
    private Map<String, String> params;
    private ErrorListener errorlistener;

//    public CustomRequest(String url, Map<String, String> params,
//                         Listener<JSONObject> reponseListener, ErrorListener errorListener) {
//        super(Method.GET, url, errorListener);
//        this.listener = reponseListener;
//        this.params = params;
//    }

    //GET Method
    public CustomRequest(int method, String url, Listener<JSONObject> responseListener, ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = responseListener;
        this.errorlistener=errorListener;
    }
    //POST Method
    public CustomRequest(int method, String url, Map<String, String> params,
                         Listener<JSONObject> responseListener, ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = responseListener;
        this.errorlistener=errorListener;
        this.params = params;
    }

    protected Map<String, String> getParams()
            throws com.android.volley.AuthFailureError {
        return params;
    };

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        // TODO Auto-generated method stub
        listener.onResponse(response);
    }
}
