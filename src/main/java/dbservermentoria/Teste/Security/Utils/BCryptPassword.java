package dbservermentoria.Teste.Security.Utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPassword {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("senha"));
    }
}