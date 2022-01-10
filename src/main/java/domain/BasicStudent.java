package domain;


import json.JsonObject;
import json.JsonPair;
import json.JsonString;
import json.Jsonable;
import json.JsonNumber;


/**
 * Created by Andrii_Rodionov on 1/5/2017.
 */
public class BasicStudent implements Jsonable {

    private String name;
    private String surname;
    private Integer year;

    public BasicStudent() {
    }

    public BasicStudent(String name, String surname, Integer year) {
        this.name = name;
        this.surname = surname;
        this.year = year;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonPair jname = new JsonPair("name", new JsonString(this.name));
        JsonPair jsurname = new JsonPair("surname",
                new JsonString(this.surname));
        JsonPair jyear = new JsonPair("year", new JsonNumber(this.year));
        JsonObject jsonObject = new JsonObject(jname, jsurname, jyear);
        return jsonObject;
    }
}
