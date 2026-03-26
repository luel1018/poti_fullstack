package org.example.poti_fullstack.orders.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.poti_fullstack.user.model.User;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Setter
    @ColumnDefault("false")
    private boolean paid;

    private int paymentPrice;

    @Setter
    private String pgPaymentId;
    private String merchantUid;
    private String planCode;
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;
}