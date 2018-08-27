package com.agnt45.revaplacement.Classes;

public class CompanyInfo {
    public CompanyInfo(){
        //Empty Contructor
    }

    public long getCompanyCutoff() {
        return companyCutoff;
    }

    public void setCompanyCutoff(long companyCutoff) {
        this.companyCutoff = companyCutoff;
    }

    public long getCompanyPay() {
        return companyPay;
    }

    public void setCompanyPay(long companyPay) {
        this.companyPay = companyPay;
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompantPic() {
        return compantPic;
    }

    public void setCompantPic(String compantPic) {
        this.compantPic = compantPic;
    }

    long companyCutoff,companyPay;
    String companyDesc,companyName,compantPic;

    public CompanyInfo(long companyCutoff, long companyPay, String companyDesc, String companyName, String compantPic) {
        this.companyCutoff = companyCutoff;
        this.companyPay = companyPay;
        this.companyDesc = companyDesc;
        this.companyName = companyName;
        this.compantPic = compantPic;
    }
}
