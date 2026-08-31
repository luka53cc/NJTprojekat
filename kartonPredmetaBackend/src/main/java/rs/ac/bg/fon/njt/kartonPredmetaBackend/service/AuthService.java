package rs.ac.bg.fon.njt.kartonPredmetaBackend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.JPARepo.UserRepository;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.dto.LoginRequestDTO;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.dto.LoginResponseDTO;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.exception.BusinessException;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.model.User;
import rs.ac.bg.fon.njt.kartonPredmetaBackend.security.JwtUtil;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {
        User user = userRepository.findByKorisnickoIme(dto.getKorisnickoIme())
                .orElseThrow(() -> new BusinessException("Pogrešno korisničko ime ili lozinka"));

        if (!passwordEncoder.matches(dto.getLozinka(), user.getLozinka())) {
            throw new BusinessException("Pogrešno korisničko ime ili lozinka");
        }

        String token = jwtUtil.generisiToken(user.getKorisnickoIme(), user.getUloga());
        return new LoginResponseDTO(token, user.getKorisnickoIme(), user.getUloga());
    }
}