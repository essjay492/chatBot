package bot.beans.dialogFlow;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Attributes {
    private List<String> productDisplayName;
    private List<String> productDisplayText;
    private List<String> skuFinalPrice;
    public List<String> getProductDisplayName() {
        return productDisplayName;
    }
    public void setProductDisplayName(List<String> productDisplayName) {
        this.productDisplayName = productDisplayName;
    }
    public List<String> getProductDisplayText() {
        return productDisplayText;
    }
    public void setProductDisplayText(List<String> productDisplayText) {
        this.productDisplayText = productDisplayText;
    }
    public List<String> getSkuFinalPrice() {
        return skuFinalPrice;
    }
    public void setSkuFinalPrice(List<String> skuFinalPrice) {
        this.skuFinalPrice = skuFinalPrice;
    }
    
    
}
