package utils;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UserDataReader {

    public static JSONObject getUserData() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("user-data.json")));
        return new JSONObject(content);
    }
}
