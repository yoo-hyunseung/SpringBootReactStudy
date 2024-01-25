package com.spring.study.data;

import com.spring.study.beans.Data;
import com.spring.study.beans.User;

import java.util.List;

public interface DataService {
    List<Data> retrieveData(User user);
}
