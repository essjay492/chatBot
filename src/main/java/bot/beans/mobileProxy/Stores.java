package bot.beans.mobileProxy;

import org.springframework.stereotype.Component;

@Component
public class Stores {
    
    private String storeId;
    private String name;
    private String id;
    private String state;
    
    public String getStoreId() {
        return storeId;
    }
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    

}
