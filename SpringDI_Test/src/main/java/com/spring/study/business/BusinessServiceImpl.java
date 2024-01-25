package com.spring.study.business;

import com.spring.study.beans.Data;
import com.spring.study.beans.User;
import com.spring.study.data.DataService;
import com.spring.study.data.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BusinessServiceImpl {

    @Autowired
    private DataService dataService;
    public long calculateSum(User user){
//        DataServiceImpl dataService = new DataServiceImpl();
//        DataService dataService = new DataServiceImpl();
//        dataService.retrieveData(user);
        long sum = 0;
//        for(Data data : dataService.retrieve)
        return sum;
    }
}
