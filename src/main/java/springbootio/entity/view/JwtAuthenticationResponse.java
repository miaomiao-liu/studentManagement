package springbootio.entity.view;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID =1L;
    private final String token;
    private final String username;


    public JwtAuthenticationResponse(String token, String username) {

        this.token = token;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }



}
