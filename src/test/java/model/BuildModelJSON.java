package model;

import com.google.gson.Gson;
import javafx.geometry.Pos;

public class BuildModelJSON {
    public static String parseJSONString (PostBody postBody) {
        if (postBody == null) {
            throw new IllegalArgumentException("Invalid postBody");
        }
        return new Gson().toJson(postBody);
    }
}
