package com.example.demo000.controller;

import com.example.demo000.servce.GirlServce;
import com.example.demo000.vo.Girl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class GirlController {
    //把jpa库自动装配进来
    @Autowired
    private GirlRepostory girlRepostory;

    /**
     *  get请求查询所有的girl数据
     * @return 返回全部girl数据
     */
    @GetMapping("/girls")
    public List<Girl> getGirl(){
        return girlRepostory.findAll();
    }

    /**
     * 以post请求增加一个女生
     * @return 返回存入的girl
     * 验证传入的对象，如果有条件不满足，则把错误条件返回给BindingResult对象
     */
    @PostMapping("/girls")
    public Girl saveGirl(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return null;
        }
        girl.setName(girl.getName());
        girl.setAge(girl.getAge());
        return girlRepostory.save(girl);
    }

    /**
     * 通过get方法得到单个信息，通过主键获得
     * @param id 要查询的girl的主键id
     * @return 返回一个Girl类型的数据
     * 2.0.5后就不要支持用findOne方法了，用以下方法查询数据
     */
    @GetMapping("/girl/{id}")
    public Girl findOne(@PathVariable("id") Integer id){
        return girlRepostory.findById(id).get();
    }

    /**
     * 通过put请求更新一条数据
     * @param id 要更新的主键，如果存在则更新，不存在则增加一条数据
     * @param age
     * @param name
     * @return
     */
    @PutMapping("/girl/{id}")
    public Girl girlUpdate(@PathVariable("id") Integer id,
                           @RequestParam("age") Integer age,
                           @RequestParam("name") String name ){
        Girl girl = new Girl();
        girl.setId(id);
        girl.setAge(age);
        girl.setName(name);
       return girlRepostory.save(girl);
    }

    /**
     *  通过delete请求删除数据
     * @param id 要删除的id
     */
    @DeleteMapping(value = "/delete/{id}")
    public void girlDelete(@PathVariable("id") Integer id){
        girlRepostory.deleteById(id);
    }

    /**
     * 通过自定义方法通过age来查找
     * @param age 要查找的age
     * @return 放回list集合，可能会有年龄相同的girl
     */
    @GetMapping(value = "/find/{age}")
    public List<Girl> find(@PathVariable("age") Integer age){
        return girlRepostory.findByAge(age);
    }

    //自动装配service
    @Autowired
    private GirlServce girlServce;

    /**
     * @Transactional 是事务管理，插入的两条数据要么同时成功，要么就都不插入
     */
    @Transactional
    @PostMapping(value = "/girl/save")
    public void saveTwo(){
        girlServce.insertTwo();
    }
}
