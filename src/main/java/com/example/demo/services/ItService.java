package com.example.demo.services;

import com.example.demo.entities.*;


public final class ItService {
    private final StaffMember webDeveloper;
    private final StaffMember webDesigner;
    private final StaffMember appDeveloper;

    public ItService(StaffMember webDeveloper, StaffMember webDesigner, StaffMember appDeveloper) {
        this.webDeveloper = webDeveloper;
        this.webDesigner = webDesigner;
        this.appDeveloper = appDeveloper;
    }

    public String fixSite(Customer customer){
        return this.webDeveloper.getPosition() + " " + this.webDeveloper.getFirstName() + ".";
    }

    public String fixInterface(Customer customer){
        return this.webDesigner.getPosition() + " " + this.webDesigner.getFirstName() +  ".";

    }

    public String fixApp(Customer customer){
        return this.appDeveloper.getPosition() + " " + this.appDeveloper.getFirstName() +  ".";

    }

    @Override
    public String toString(){
        return "There are Web-developer, Web-designer and App-developer in our IT service";
    }
}
