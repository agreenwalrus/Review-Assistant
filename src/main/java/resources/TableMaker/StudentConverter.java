package resources.TableMaker;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import data.Student;
import data.mark.LabMark;

import java.lang.reflect.Type;

public class StudentConverter implements JsonSerializer<Student> {
    @Override
    public JsonElement serialize(Student src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("cell-class", "student-name-call");
        object.addProperty("value", src.getFulName());

        return object;
    }

}