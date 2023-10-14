package com.edufc.springstudy.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class BaseService {
    @Autowired
    protected Mapper mapper;
}
