package com.jahongir.mini_transaction.domains;

import com.jahongir.mini_transaction.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * @author jahongir
 * @created 31/01/23 - 22:55
 * @project Mini_transaction/IntelliJ IDEA
 */
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String phoneNumber;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    @Builder.Default
    private UserStatus status = UserStatus.ACTIVE;
    @OneToMany(mappedBy = "user")
    private List<Card> cardList;
}
