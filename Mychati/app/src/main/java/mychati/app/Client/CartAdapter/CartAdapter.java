package mychati.app.Client.CartAdapter;

public class CartAdapter {
    private String TovarValue,ProductId,Price,tovarname,tovarcartShopuid,tovarImage;
    public CartAdapter(){

    }

    public CartAdapter(String tovarValue, String productId, String price, String tovarname, String tovarcartShopuid, String tovarImage) {
        this.TovarValue = tovarValue;
      this.  ProductId = productId;
     this.   Price = price;
        this.tovarname = tovarname;
        this.tovarcartShopuid = tovarcartShopuid;
        this.tovarImage = tovarImage;
    }


    public String getTovarValue() {
        return TovarValue;
    }

    public void setTovarValue(String tovarValue) {
        TovarValue = tovarValue;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getTovarname() {
        return tovarname;
    }

    public void setTovarname(String tovarname) {
        this.tovarname = tovarname;
    }

    public String getTovarcartShopuid() {
        return tovarcartShopuid;
    }

    public void setTovarcartShopuid(String tovarcartShopuid) {
        this.tovarcartShopuid = tovarcartShopuid;
    }

    public String getTovarImage() {
        return tovarImage;
    }

    public void setTovarImage(String tovarImage) {
        this.tovarImage = tovarImage;
    }
}
