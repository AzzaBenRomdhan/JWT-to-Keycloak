package com.azza.jwtauthentication.auth;

import com.azza.jwtauthentication.email.EmailService;
import com.azza.jwtauthentication.email.EmailTemplateName;
import com.azza.jwtauthentication.role.RoleRepository;
import com.azza.jwtauthentication.security.JwtService;
import com.azza.jwtauthentication.user.Token;
import com.azza.jwtauthentication.user.TokenRepository;
import com.azza.jwtauthentication.user.User;
import com.azza.jwtauthentication.user.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

//@Service
@RequiredArgsConstructor
public class AuthenticationService {
   /*private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    public void register(RegisterRequest request) throws MessagingException {
        var userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalArgumentException("ROLE USER was not initialized"));

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();

        userRepository.save(user);
        System.out.println("User registered: " + user.getEmail());

        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {

        var newToken = generateAndSaveActivationToken(user);
        System.out.println("New activation token generated: " + newToken);

        // Envoyer l'email
        emailService.sendEmail(user.getEmail(), user.fullname(), EmailTemplateName.ACTIVATE_ACCOUNT, activationUrl, newToken, "Account activation");
    }

    private String generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationCode(6);
        System.out.println("Generated token: " + generatedToken);

        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();

        tokenRepository.save(token);
        System.out.println("Token saved in database: " + generatedToken);

        return generatedToken;
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var claims = new HashMap<String, Object>();
        var user = ((User) auth.getPrincipal());
        claims.put("fullname", user.fullname());

        var jwt = jwtService.generateToken(claims, user);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

    public void activateAccount(String token) throws MessagingException {
        Optional<Token> foundToken = tokenRepository.findByToken(token);
        if (foundToken.isEmpty()) {
            throw new RuntimeException("Invalid token");
        }
        Token savedToken = foundToken.get();

        if (LocalDateTime.now().isAfter(savedToken.getExpiredAt())) {
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Activation token has expired. A new token has been sent to the same email address.");
        }

        var user = userRepository.findById(savedToken.getUser().getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setEnabled(true);
        userRepository.save(user);

        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);
    }*/
}
