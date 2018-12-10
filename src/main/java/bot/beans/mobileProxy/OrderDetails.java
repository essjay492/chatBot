package bot.beans.mobileProxy;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class OrderDetails {
    
    private ArrayList<Item> notShippedItems;
    private PriceDetails priceDetails;
    
    public ArrayList<Item> getNotShippedItems() {
        return notShippedItems;
    }
    public void setNotShippedItems(ArrayList<Item> notShippedItems) {
        this.notShippedItems = notShippedItems;
    }
    public PriceDetails getPriceDetails() {
        return priceDetails;
    }
    public void setPriceDetails(PriceDetails priceDetails) {
        this.priceDetails = priceDetails;
    }

}
