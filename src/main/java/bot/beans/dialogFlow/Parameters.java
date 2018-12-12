package bot.beans.dialogFlow;

import org.springframework.stereotype.Component;

@Component
public class Parameters {
    private String item;
    private String stateName;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
    
}
