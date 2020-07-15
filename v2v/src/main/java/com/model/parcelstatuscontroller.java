package com.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class parcelstatuscontroller {
    @Autowired
    ParcelcheckDAO parcelcheckDAO;
    @RequestMapping(path="/check",method = RequestMethod.GET )
    @ResponseBody
    public boolean  loginValidation(@RequestParam("username") String username,@RequestParam("password") String password)
    {
        boolean username1=parcelcheckDAO.loginValidation(username,password);

        return username1;

    }
    @RequestMapping(path="/profiles",method = RequestMethod.POST)
    public ResponseEntity<Object> createProfile(@RequestBody  Parceltrackcheckmodel parceltrackcheckmodel)
    {
        parcelcheckDAO.createProfile(parceltrackcheckmodel);
        return new ResponseEntity<>("Profile is created successfully", HttpStatus.OK);
    }
    @RequestMapping(path="/registerparcel",method = RequestMethod.POST)
    public ResponseEntity<Object> createNewParcel(@RequestBody ParcelRegistermodel parcelRegistermodel)
    {
        parcelcheckDAO.createNewParcel(parcelRegistermodel);
        return new ResponseEntity<>("Registered new parcel successfully", HttpStatus.OK);
    }
    @RequestMapping(path = "/getprofiles",method = RequestMethod.GET)
    public ResponseEntity<Object> getProfiles()
    {
        return new ResponseEntity<>(parcelcheckDAO.getProfiles(),HttpStatus.OK);
    }
    @RequestMapping(path = "/trackiddetails",method = RequestMethod.GET)
    public ResponseEntity<Object> getTrackIdDetails(@RequestParam("track_id") String track_id)
    {
        return new ResponseEntity<>(parcelcheckDAO.getTrackIdDetails(track_id),HttpStatus.OK);
    }





}
