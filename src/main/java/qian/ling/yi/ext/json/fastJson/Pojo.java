package qian.ling.yi.ext.json.fastJson;

/**
 * Created by liuguobin on 2016/10/9.
 */
public class Pojo {
    public String name;
//    public enum sex{man, woman};
    public int age;
    public String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
