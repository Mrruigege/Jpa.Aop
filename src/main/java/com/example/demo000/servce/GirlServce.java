package com.example.demo000.servce;

import com.example.demo000.controller.GirlRepostory;
import com.example.demo000.vo.Girl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GirlServce {
    @Autowired
    private GirlRepostory girlRepostory;
    public void insertTwo(){
        Girl girlA = new Girl();
        girlA.setAge(11);
        girlA.setName("shiwua");
        girlRepostory.save(girlA);
        Girl girlB = new Girl();
        girlB.setAge(11);
        girlB.setName("shiwub");
        girlRepostory.save(girlB);
    }
}
