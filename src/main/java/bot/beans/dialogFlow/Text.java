package bot.beans.dialogFlow;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class Text {
   
    private ArrayList<String> text;
    
    public ArrayList<String> getText() {
        return text;
    }

    public void setText(ArrayList<String> text) {
        this.text = text;
    }

}
