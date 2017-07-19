package com.hsenid;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vidushka on 13/07/17.
 */
@JsonPropertyOrder({"status", "code", "message", "data", "links"})
public class SuccessMessage {
    private String status; // State of the request
    private int code; // State of the request
    private String message; // HTTP status code
    private List<JSONObject> data = new ArrayList<JSONObject>(); // Resources to include in the response

    public SuccessMessage() {
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("code")
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("data")
    public List<JSONObject> getData() {
        return data;
    }

    public void setData(List<JSONObject> data) {
        this.data = data;
    }

    // Add data(resources) to represent
    public void addData(JSONObject jsonObject) {
        data.add(jsonObject);
    }

    public JSONObject generateSuccessMessage() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("status", getStatus());
            jsonObject.put("code", getCode());
            jsonObject.put("message", getMessage());
            jsonObject.put("data", getData());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}