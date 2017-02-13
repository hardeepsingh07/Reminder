package edu.cpp.cs580.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WebControllerTest {

   

    @Test
    public void test() throws Exception {
    	WebController controller = new WebController();
        String uName ="kevin";
        String temp =controller.validateInput(uName);
        assertEquals("Invalid", temp);
    }

}
