package com.givit.controller.master;

import com.givit.model.master.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/givit/v1")
public class APIController {

    @Autowired
    SecurityController securityController;

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    //@ResponseBody
    public APIResponse register(@RequestBody String json) {
        return securityController.register(json);
    }

    @CrossOrigin
    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    //@ResponseBody
    public APIResponse signIn(@RequestBody String json) {
        return securityController.signIn(json);
    }

    @CrossOrigin
    @RequestMapping(value = "/check-connect", method = RequestMethod.POST)
    //@ResponseBody
    public APIResponse refreshToken(@RequestBody String json) {
        return securityController.checkConnect(json);
    }
    
    @CrossOrigin
    @RequestMapping(value = "/sign-out", method = RequestMethod.POST)
    //@ResponseBody
    public APIResponse signOut(@RequestBody String json) {
        return securityController.signOut(json);
    }
}
