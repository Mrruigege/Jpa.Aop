package com.example.demo000.controller;

import com.example.demo000.vo.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * jpa接口类，继承jpaRepository（jpa仓库）
 */
public interface GirlRepostory extends JpaRepository<Girl,Integer> {
    /**
     * 通过年龄查找
     */
    public List<Girl> findByAge(Integer age);
}
