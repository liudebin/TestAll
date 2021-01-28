package qian.ling.yi.base.serializable;

import java.io.*;

/**
 * Created by guobin.liu@holaverse.com on 2018/9/14.
 */

public class SerializeTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person person = new Person(1234, "wang");
        System.out.println("Person Serial" + person);
//        FileOutputStream fos = new FileOutputStream("Person.txt");
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        oos.writeObject(person);
//        oos.flush();
//        oos.close();
        FileInputStream fileInputStream = new FileInputStream("Person.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Person sPerson = (Person) objectInputStream.readObject();
        System.out.println(sPerson.id);
        System.out.println(sPerson);
    }
}
