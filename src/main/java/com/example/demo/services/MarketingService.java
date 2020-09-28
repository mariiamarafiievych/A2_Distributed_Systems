package com.example.demo.services;


import com.example.demo.entities.Supplier;
import com.example.demo.entities.StaffMember;

public final class MarketingService {
    private final StaffMember CaManager;
    private final StaffMember SeoSpecialist;

    public MarketingService(StaffMember CaManager, StaffMember SeoSpecialist) {
        this.CaManager = CaManager;
        this.SeoSpecialist = SeoSpecialist;
    }

    public String installTargeting(Supplier supplier){
        return this.CaManager.getPosition() + " " + this.CaManager.getFirstName() +  ".";
    }

    public String promotion(Supplier supplier){
        return this.SeoSpecialist.getPosition() + " " + this.SeoSpecialist.getFirstName() +  ".";

    }

    @Override
    public String toString(){
        return "There are CA Manager and SEO-specialist in our Marketing service";
    }

}
