package com.article.admin.mvc.community.service;

import com.article.admin.mvc.community.entity.Tabs;
import com.article.admin.mvc.community.service.repository.TabsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TabsServiceImp {

    @Autowired
    TabsRepository tabsRepository;


    public Tabs insertTabs(Tabs tabs) {
        try {
            return tabsRepository.insert(tabs);
        } catch (Exception e) {
            return null;
        }


    }

    public Tabs saveTabs(Tabs tabs) {
        return tabsRepository.save(tabs);
    }


    public List<Tabs> getAll(){
        return tabsRepository.findAll();
    }


    public void delete(String tabName){
        try{
            tabsRepository.deleteByTabName(tabName);
        }catch (Exception e){
            System.err.println("删除Tabs出错----===------>>>>>>"+e.getMessage());
        }

    }

}
