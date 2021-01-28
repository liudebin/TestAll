package qian.ling.yi.base.serializable;

import java.io.Serializable;

/**
 * Created by guobin.liu@holaverse.com on 2018/9/14.
 */

public class Person  implements Serializable {
    private static final long serialVersionUID = 1234567891L;
    public int id;
    public String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return "Person: " + id + " " + name;
    }
}