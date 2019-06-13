package com.janeli.pay;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepayApplicationTests {

    @Test
    public void contextLoads() {
        List<String> list = new ArrayList();
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add(String.valueOf(i));
            if ((i % 2) == 0) {
                list2.add(String.valueOf(i));
            }
        }
        System.out.println("list:" + list);
        System.out.println("list2" + list2);
        list.retainAll(list2);
        System.out.println("retain" + list);

    }
    @Test
    public void dateTime(){
        System.out.print(System.currentTimeMillis());
    }

}
