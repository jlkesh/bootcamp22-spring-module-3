package dev.jlkeesh.springadvanced.listeners;

import dev.jlkeesh.springadvanced.events.UserCreatedEvent;
import dev.jlkeesh.springadvanced.events.VerificationMailSentEvent;
import dev.jlkeesh.springadvanced.user.User;
import dev.jlkeesh.springadvanced.user.UserRepository;
import dev.jlkeesh.springadvanced.utils.MailService;
import dev.jlkeesh.springadvanced.utils.TokenGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventListener {
    private final UserRepository userRepository;
    private final TokenGenerator tokenGenerator;
    private final MailService mailService;

    @TransactionalEventListener(value = UserCreatedEvent.class, phase = TransactionPhase.AFTER_COMMIT)
    @Async
    /*@Order(1)*/
    public CompletableFuture<VerificationMailSentEvent> userCreatedEventListener(UserCreatedEvent event) {
        log.info("Ticked userCreatedEventListener with : {}", event);
        Optional<User> optionalUser = userRepository.findById(event.getUserId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            tokenGenerator.generateToken(user);
            return CompletableFuture.completedFuture(new VerificationMailSentEvent(user.getOtp(), user.getEmail()));
        }
        return null;
    }

    /*@Order(2)*/
    @EventListener(value = VerificationMailSentEvent.class ,condition = "#event.email ne null")
    public void sendVerificationEmail(VerificationMailSentEvent event) {
        log.info("Ticked sendVerificationEmail with : {}", event);
        Map<String, Object> model = Map.of("otp", event.getOtp(), "email", event.getEmail());
        mailService.sendEmail(model);
    }

}
