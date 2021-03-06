package bsuirAPI.bsuirScheduleJson;

import com.google.gson.*;

import java.lang.reflect.Type;

public class LessonDeserializer implements JsonDeserializer<Lesson> {

    @Override
    public Lesson deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Lesson lesson = new Lesson();
        JsonObject jsonObject = json.getAsJsonObject();

        lesson.setLessonTime(jsonObject.get("lessonTime").getAsString());
        lesson.setLessonType(jsonObject.get("lessonType").getAsString());
        lesson.setSubject(jsonObject.get("subject").getAsString());
        lesson.setNumSubgroup(jsonObject.get("numSubgroup").getAsInt());

        JsonArray weeks = jsonObject.getAsJsonArray("weekNumber");
        for(JsonElement week: weeks){
            lesson.getWeekNumber().add(new Integer(week.getAsInt()));
        }

        return lesson;
    }
}
