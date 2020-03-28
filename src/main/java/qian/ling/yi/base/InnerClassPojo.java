package qian.ling.yi.base;

/**
 * Pojo
 *
 * @author liuguobin
 * @date 2018/1/5
 */

public class InnerClassPojo {
    int i ;
    class InnerClass{
        int count;
        int weight;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public  void test() {
            i = 2;
        }

    }
}
