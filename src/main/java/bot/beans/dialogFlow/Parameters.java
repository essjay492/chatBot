package bot.beans.dialogFlow;

import org.springframework.stereotype.Component;

@Component
public class Parameters {
    private String item;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
    
}
