package bot.beans.dialogFlow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListSelect {
    
    private String title;
    @Autowired
//    private List<Item> items;
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
//    public List<Item> getItems() {
//        return items;
//    }
//    public void setItems(List<Item> items) {
//        this.items = items;
//    }
}
