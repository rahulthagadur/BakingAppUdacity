package com.example.thagadur.bakingappudacity.app;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;

public class ErrorHandler {

    public static void processError(Context context, int statusCode, ResponseBody body) {
        String message = null;
        try {
            JSONObject jsonObject = new JSONObject(body.string());
            if (jsonObject.has("non_field_errors")) {
                JSONArray jsonArray = jsonObject.getJSONArray("non_field_errors");
                message = jsonArray.getString(0);
            } else if (jsonObject.has("detail")) {
                message = jsonObject.getString("detail");
            } else {
                for (int i = 0; i < jsonObject.length(); i++) {
                    JSONArray keyArray = new JSONArray(
                            jsonObject.getString(jsonObject.names().getString(i)));
                    message = keyArray.getString(0);


                }
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        switch (statusCode) {
            case 400:
                if (message != null) {
                    ToastBuilder.build(context, message);
                    Log.e("400error", "" + message);

                }
                break;
            case 401:
                if (message != null) {
                    ToastBuilder.build(context, message);
                    Log.e("401error", "" + message);

                }
                break;
            case 403:
                if (message != null) {
                    ToastBuilder.build(context, message);
                    Log.e("403error", "" + message);

                }

                break;
            case 404:
                if (message != null) {
                    ToastBuilder.build(context, message);
                    Log.e("404error", "" + message);

                }
                break;

        }
    }
}
