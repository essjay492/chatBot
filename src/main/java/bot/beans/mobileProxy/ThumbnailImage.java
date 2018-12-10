package bot.beans.mobileProxy;

import org.springframework.stereotype.Component;

@Component
public class ThumbnailImage {
    
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
