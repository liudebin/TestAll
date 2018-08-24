package qian.ling.yi.base.JVMCustom;

import org.apache.commons.lang.ArrayUtils;

/**
 * Acc
 *
 * @author liuguobin
 * @date 2018/4/30
 */

public class AccFlagBuilder {
    enum Flag4 {
        synth('1', "synthetic"),
        anno('2', "annotation"),
        enumFlag('4', "enum");

        char flag;
        String name;
        Flag4(char flag, String name) {
            this.flag = flag;
            this.name = name;
        }

        public char getFlag() {
            return flag;
        }

        public Flag4 setFlag(char flag) {
            this.flag = flag;
            return this;
        }

        public String getName() {
            return name;
        }

        public Flag4 setName(String name) {
            this.name = name;
            return this;
        }
    }

    enum Flag3 {
        inter('2', "interface"),
        abs('4', "abstract");

        char flag;
        String name;
        Flag3(char flag, String name) {
            this.flag = flag;
            this.name = name;
        }

        public char getFlag() {
            return flag;
        }

        public Flag3 setFlag(char flag) {
            this.flag = flag;
            return this;
        }

        public String getName() {
            return name;
        }

        public Flag3 setName(String name) {
            this.name = name;
            return this;
        }
    }

    enum Flag2 {
        fin('1', "final"),
        sup('2', "super");

        char flag;
        String name;
        Flag2(char flag, String name) {
            this.flag = flag;
            this.name = name;
        }

        public char getFlag() {
            return flag;
        }

        public Flag2 setFlag(char flag) {
            this.flag = flag;
            return this;
        }

        public String getName() {
            return name;
        }

        public Flag2 setName(String name) {
            this.name = name;
            return this;
        }
    }

    enum Flag1 {
        pub('1', "public");

        char flag;
        String name;
        Flag1(char flag, String name) {
            this.flag = flag;
            this.name = name;
        }

        public char getFlag() {
            return flag;
        }

        public Flag1 setFlag(char flag) {
            this.flag = flag;
            return this;
        }

        public String getName() {
            return name;
        }

        public Flag1 setName(String name) {
            this.name = name;
            return this;
        }
    }


    static String[] getFlags(char[] flags) {
        if (flags.length < 4) {
            throw new RuntimeException("acc_flag error");
        }
        String[] flagStr = new String[0];
        if (flags[0] != '0') {
            for (Flag4 flag : Flag4.values()) {
                if (flag.getFlag() == flags[0]) {
                    ArrayUtils.add(flagStr, flag.getName());
                    break;
                }
            }
        }

        if (flags[1] != '0') {
            for (Flag3 flag : Flag3.values()) {
                if (flag.getFlag() == flags[1]) {
                    ArrayUtils.add(flagStr, flag.getName());
                    break;
                }
            }
        }
        if (flags[2] != '0') {
            for (Flag2 flag : Flag2.values()) {
                if (flag.getFlag() == flags[2]) {
                    flagStr = (String[]) ArrayUtils.add(flagStr, flag.getName());
                    break;
                }
            }
        }

        if (flags[3] != '0') {
            for (Flag1 flag : Flag1.values()) {
                if (flag.getFlag() == flags[3]) {
                    flagStr = (String[]) ArrayUtils.add(flagStr, flag.getName());
                    break;
                }
            }

        }
        return flagStr;
    }

}
