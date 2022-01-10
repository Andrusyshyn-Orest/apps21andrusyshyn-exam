package domain;

import json.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private Tuple<String, Integer>[] exams;

    public Student(String name, String surname,
                   Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = exams;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject jsonObject = super.toJsonObject();
        JsonArray jsonArray;
        int length = this.exams.length;
        Json[] arr = new Json[length];
        for (int i = 0; i < length; i++) {
            Tuple<String, Integer> exam = this.exams[i];
            JsonString courseName = new JsonString(exam.key);
            JsonNumber courseMark = new JsonNumber(exam.value);
            JsonBoolean coursePassed;
            if (exam.value > 2) {
                coursePassed = new JsonBoolean(true);
            }
            else {
                coursePassed = new JsonBoolean(false);
            }
            JsonObject jsonObjectinArray = new JsonObject(
                    new JsonPair("course", courseName),
                    new JsonPair("mark", courseMark),
                    new JsonPair("passed", coursePassed)
            );
            arr[i] = jsonObjectinArray;
        }
        jsonArray = new JsonArray(arr);
        jsonObject.add(new JsonPair("exams", jsonArray));
        return jsonObject;
    }
}