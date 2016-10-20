package web.action;

import com.opensymphony.xwork2.ActionSupport;
import service.Service;
public class Register extends ActionSupport{
    private Service service1;
    private String username;
    private String password;
    private String token;
    private int id;
    public Register()
    {
        super();
        service1 = new Service();
    }
    @Override
    public String execute()
    {
        Integer state;
        // TODO: 输入验证
        state = service1.addNewUser(username,password);
        if(state == 1)
            return SUCCESS;
        else
            return ERROR;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return username;
    }

    public String getToken() {
        return username;
    }

    public int getId() {
        return id;
    }
}
