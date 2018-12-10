package bot.beans.mobileProxy;

import org.springframework.stereotype.Component;

@Component
public class Item {
    
    private String displayName;
    private String quantity;
    private String state;
    private String amount;
    private ThumbnailImage thumbnailImage;
    
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public ThumbnailImage getThumbnailImage() {
        return thumbnailImage;
    }
    public void setThumbnailImage(ThumbnailImage thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }
}
