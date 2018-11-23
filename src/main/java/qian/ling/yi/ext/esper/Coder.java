package qian.ling.yi.ext.esper;

/**
 * 定义事件模型
 *
 * @date: 2018/10/25.
 * @author: guobin.liu@holaverse.com
 */

public class Coder {
    private String Name;
    private int age;
    private double Salary;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }
}
