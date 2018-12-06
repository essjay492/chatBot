package bot.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FulfillmentMessages {
    
    @Autowired
    private Text text;
    @Autowired
//    private Image image;

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

//    public Image getImage() {
//        return image;
//    }
//
//    public void setImage(Image image) {
//        this.image = image;
//    }

}
