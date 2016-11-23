package com.givit.controller.master;

import com.givit.model.master.*;
import com.givit.model.second.Country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.google.gson.Gson;

/**
 * Created by Julian on 11/14/2016.
 */
@RestController
@RequestMapping("/givit")
public class APIController {

    @Autowired
    SecurityController securityController;

    @Autowired
    CountryController countryController;

    @CrossOrigin
    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    //@ResponseBody
    public APIResponse signUp(@RequestBody String json) {
        return securityController.signUp(json);
    }

    @CrossOrigin
    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    //@ResponseBody
    public APIResponse signIn(@RequestBody String json) {
        return securityController.signIn(json);
    }

    @CrossOrigin
    @RequestMapping(value = "/refresh-token", method = RequestMethod.POST)
    //@ResponseBody
    public APIResponse refreshToken(@RequestBody String json) {
        return securityController.refreshToken(json);
    }

    @CrossOrigin
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    //@ResponseBody
    public APIResponse test(@RequestBody String json) {
        return securityController.test(json);
    }

    //@CrossOrigin(origins = "http://192.168.1.12:8080")
    @CrossOrigin
    @RequestMapping("/get-all2")
    //@ResponseBody
    public Iterable getAllObjects2() {
        return countryController.getAllObjects2();
    }

    //@CrossOrigin(origins = "http://192.168.1.12:8080")
    @CrossOrigin
    @RequestMapping("/get-all")
    //@ResponseBody
    public APIResponse getAllObjects() {
        return countryController.getAllObjects();
    }

    @RequestMapping(value = "/create-customer", method = RequestMethod.POST)
    //@ResponseBody
    public APIResponse createCustomer(@RequestBody String json) {
        return countryController.createCustomer(json);
    }

    //@CrossOrigin(origins = "http://192.168.1.12:8080")
    @CrossOrigin
    @RequestMapping("/customers")
    //@ResponseBody
    public APIResponse getCustomers() {
       return countryController.getCustomers();
    }


    @RequestMapping("/get-by-code")
    //@ResponseBody
    public Country getByCode(String code) {
        return countryController.getByCode(code);
    }

    @RequestMapping("/get-by-name")
    //@ResponseBody
    public Country getByName(String name) {
        return countryController.getByName(name);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long id) {
        return countryController.delete(id);
    }

    @RequestMapping("/create")
    @ResponseBody
    public String create(String code, String name) {
        return countryController.create(code, name);
    }

    @RequestMapping("/update")
    @ResponseBody
    public String updateCountry(long id, String name) {
        return countryController.updateCountry(id, name);
    }
}
