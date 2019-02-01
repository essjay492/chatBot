package bot.beans.mobileProxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class User {

    private String token;
//    private String authToken;
    private String email = "sramacha@walmart.com";
    private String password = "12345678";
    private String orderId;
    @Autowired
    private AccessTokenVo accessTokenVO;
    private String authToken;
    private String refreshToken;
    
    
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
//    public String getAuthToken() {
//        return authToken;
//    }
//    public void setAuthToken(String authToken) {
//        this.authToken = authToken;
//    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public AccessTokenVo getAccessTokenVO() {
        return accessTokenVO;
    }
    public void setAccessTokenVO(AccessTokenVo accessTokenVO) {
        this.accessTokenVO = accessTokenVO;
    }
    public String getAuthToken() {
        return authToken;
    }
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
    public String getRefreshToken() {
        return refreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
