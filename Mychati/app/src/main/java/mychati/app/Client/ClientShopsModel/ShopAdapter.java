package mychati.app.Client.ClientShopsModel;

public class ShopAdapter {
    private String MagName,MagCity,MagAdress,MagUid,MagLogo,MagNumberReg,MagNumber,MagCategory;
    public ShopAdapter() {

    }


    public ShopAdapter(String magName, String magCity, String magAdress, String magUid, String magLogo, String magNumberReg, String magNumber, String magCategory) {
       this. MagName = magName;
      this.  MagCity = magCity;
    this.    MagAdress = magAdress;
     this.   MagUid = magUid;
    this.    MagLogo = magLogo;
  this.      MagNumberReg = magNumberReg;
     this.   MagNumber = magNumber;
     this.   MagCategory = magCategory;
    }

    public String getMagName() {
        return MagName;
    }

    public void setMagName(String magName) {
        MagName = magName;
    }

    public String getMagCity() {
        return MagCity;
    }

    public void setMagCity(String magCity) {
        MagCity = magCity;
    }

    public String getMagAdress() {
        return MagAdress;
    }

    public void setMagAdress(String magAdress) {
        MagAdress = magAdress;
    }

    public String getMagUid() {
        return MagUid;
    }

    public void setMagUid(String magUid) {
        MagUid = magUid;
    }

    public String getMagLogo() {
        return MagLogo;
    }

    public void setMagLogo(String magLogo) {
        MagLogo = magLogo;
    }

    public String getMagNumberReg() {
        return MagNumberReg;
    }

    public void setMagNumberReg(String magNumberReg) {
        MagNumberReg = magNumberReg;
    }

    public String getMagNumber() {
        return MagNumber;
    }

    public void setMagNumber(String magNumber) {
        MagNumber = magNumber;
    }

    public String getMagCategory() {
        return MagCategory;
    }

    public void setMagCategory(String magCategory) {
        MagCategory = magCategory;
    }
}
