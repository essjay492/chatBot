package bot.beans.mobileProxy;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class OrderMap {
    
    private ArrayList<Order> orderMap;

    public ArrayList<Order> getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(ArrayList<Order> orderMap) {
        this.orderMap = orderMap;
    }

}
