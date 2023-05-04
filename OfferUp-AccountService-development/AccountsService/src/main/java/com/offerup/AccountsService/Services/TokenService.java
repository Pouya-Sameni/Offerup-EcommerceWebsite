package com.offerup.AccountsService.Services;


import com.offerup.AccountsService.dtos.Password;
import com.offerup.AccountsService.dtos.TokenClaim;
import com.offerup.AccountsService.dtos.UserEntity;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.Random;


@Service
public class TokenService {


    private final String USER_CLAIM_KEY = "username";
    private final String ID_CLAIM_KEY = "userId";
    @Value("${clientSecret}")
    private String client_secret;



    //Validate Token
    public TokenClaim parseSessionToken(String token) throws SignatureException {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(client_secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
        // Get the subject (which should be "validation token")
        String subject = claims.getSubject();
        if (!"validation token".equals(subject)) {
            throw new SignatureException("Invalid token subject");
        }

        // Get the value of the CLAIM_KEY claim
        String username = (String) claims.get(USER_CLAIM_KEY);
        String userId = (String) claims.get(ID_CLAIM_KEY);

        TokenClaim claim = new TokenClaim("User Found", userId, username);
        if (username == null) {
            throw new SignatureException("Invalid token claims");
        }
        // Token is valid, return the claims
        return claim;
    }

    private static String generateSalt(String cipher, int keySize) {
        byte[] randomKeyBytes = new byte[keySize / 8];
        Random random = new Random();
        random.nextBytes(randomKeyBytes);
        return Base64.getEncoder().encodeToString(randomKeyBytes);
    }


    //TODO: Create Salt and Hash
    public Password hashPassword (String givenPass){
        PasswordEncoder encoder = new BCryptPasswordEncoder(10);

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[32];
        random.nextBytes(salt);

        String saltHex = DatatypeConverter.printHexBinary(salt);
        String encodedPass = encoder.encode((givenPass + saltHex));

        Password password = new Password(encodedPass, saltHex);

        return password;
    }


    /*
    Create Token for login credentials
     */
    public String createSessionToken (UserEntity userEntity){


        String token = Jwts.builder()
                .claim(USER_CLAIM_KEY, userEntity.getUsername())
                .claim(ID_CLAIM_KEY, userEntity.getId().toString())
                .setSubject("validation token")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 hours
                .signWith(SignatureAlgorithm.HS512, client_secret)
                .compact();

        return token;
    }

    //TODO: Implemenet a password checker here
    public boolean checkPassword (UserEntity user, String password){
        PasswordEncoder encoder = new BCryptPasswordEncoder(10);

        String securePass = user.getPassword().getPassword();

        String storeSalt = user.getPassword().getSalt();

        String encodedPass = encoder.encode(password + storeSalt);

        return encoder.matches((password+storeSalt), securePass);
    }


}
