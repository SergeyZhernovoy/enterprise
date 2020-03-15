package com.education.boot.tests;

import lombok.Data;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class Test1 {
    private final String address;

    public Test1(Collection<String> lines) {
        address = lines.stream().collect(Collectors.joining()); // FIXME: 5/1/19 это так?
    }

    @Override
    public String toString() {
        return "Test1{" +
                "address='" + address + '\'' +
                '}';
    }

    public static void main(String[] args) {
//        Test2 test2 = new Test2();
//        List<String> list = Arrays.asList("1","2","3");
//        test2.setAdrLines(list);
//
//        Test1 test1 = Optional.ofNullable(test2).map(Test2::getAdrLines).map(Test1::new).orElse(null);
//        System.out.println(test1.getAddress());
//        Charset ch = Charset.forName("UTF-8");
//        System.out.println(ch);

        try {
            int a = 1 / 0;
            System.out.println(1);
        } catch (Exception exc) {
            System.out.println(2);
        }

        //System.out.println(main("\u916E\u2E67\uCB8D\u16B9\u479D\u7669\u3F20\uD7DC\u75C5\u30F1\uEC7E\uA26B\uEEB0\u2F0E\u4828\u19C6"));

    }
}
