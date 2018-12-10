package bot.beans.mobileProxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class User {

    private String token;
//    private String authToken;
    private String email;
    private String password;
    private String orderId;
    @Autowired
    private AccessTokenVo accessTokenVO;
    
    
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
}
