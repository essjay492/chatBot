package bot.beans.mobileProxy;

import org.springframework.stereotype.Component;

@Component
public class AccessTokenVo {
    
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
