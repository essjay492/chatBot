package bot.beans.mobileProxy;

import org.springframework.stereotype.Component;

@Component
public class PriceDetails {
    
    private String orderTotal;

    public String getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }

}
