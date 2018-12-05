package bot.beans;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Greeting {

    private final String content;

    public Greeting(String content) {
        this.content = content;
    }

}
