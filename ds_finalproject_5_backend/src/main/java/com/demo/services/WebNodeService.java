package com.demo.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.demo.services.contracts.IWebNodeService;

@Service
@Qualifier("WebNodeService")
public class WebNodeService implements IWebNodeService{

}
