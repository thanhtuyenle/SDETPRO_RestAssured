package model;

import io.restassured.http.Header;
import sun.plugin2.message.HeartbeatMessage;

import java.util.function.Function;

public interface RequestCapability {
    Header defaultHeader = new Header("Content-type", "application/json; charset=UTF-8");
    Header acceptJsonHeader = new Header("Accept", "application/json");
    static Header getAuthenticatedHeader(String encodeCredStr) {
        if(encodeCredStr == null)
            throw new IllegalArgumentException("[ERR] encodeCredStr is null");
        return new Header("Authorization", "Basic ".concat(encodeCredStr));
    }

    Function<String, Header> getAuthenticatedHeader = encodeCredStr -> {
        if(encodeCredStr == null)
            throw new IllegalArgumentException("[ERR] encodeCredStr is null");
        return new Header("Authorization", "Basic ".concat(encodeCredStr));
    };
}
