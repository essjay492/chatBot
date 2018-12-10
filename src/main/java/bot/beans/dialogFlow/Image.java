package bot.beans.dialogFlow;

import org.springframework.stereotype.Component;

@Component
public class Image {

    private String imageUri;
    private String accessibilityText;
    
    public String getImageUri() {
        return imageUri;
    }
    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
    public String getAccessibilityText() {
        return accessibilityText;
    }
    public void setAccessibilityText(String accessibilityText) {
        this.accessibilityText = accessibilityText;
    }
}
