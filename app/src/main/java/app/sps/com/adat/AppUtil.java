package app.sps.com.adat;

/**
 * Created by Jai1.Kumar on 24-04-2018.
 */

public interface AppUtil {
    public  String host = "http://taxipro-taxi-proj.a3c1.starter-us-west-1.openshiftapps.com/TaxiManagement/";
    //public  String host = "http://192.168.43.63:8080/StoreManagement/";
    public  String transListUrl = host+"mTransList";
    public  String loginUrl = host+"mLogin";
    public  String customerListUrl =host+"mCustomerList";
    public  String locationListUrl = host+"mLocations";
    public  String partnerListUrl = host+"mPartnerList";
    public  String productListUrl = host+"mProductList";
    public String saveTransactionUrl = host +"mNewTransaction";
    public String saveCustomer = host + "mNewCustomer";
    public String saveLocation = host + "mNewLocation";
    public String saveProduct = host + "mNewProduct";
    public String saveActivity = host + "mNewActivity";

}
