package bot.beans.dialogFlow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchResponse {
    @Autowired
    private List<MainArea> mainArea;

    public List<MainArea> getMainArea() {
        return mainArea;
    }

    public void setMainArea(List<MainArea> mainArea) {
        this.mainArea = mainArea;
    }
    
}
