package bot.beans.dialogFlow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FulfillmentMessages {
    
    @Autowired
    private Text text;
  
//    private ListSelect listSelect;
//    private Image image;

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

//    public ListSelect getListSelect() {
//        return listSelect;
//    }
//
//    public void setListSelect(ListSelect listSelect) {
//        this.listSelect = listSelect;
//    }

//    public Image getImage() {
//        return image;
//    }
//
//    public void setImage(Image image) {
//        this.image = image;
//    }

}
