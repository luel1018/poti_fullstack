package org.example.poti_fullstack.notification;

import org.example.poti_fullstack.notification.model.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    Optional<NotificationEntity> deleteByUserIdx(Long userIdx);
    List<NotificationEntity> findAllByUserIdx(Long userIdx);
    Optional<NotificationEntity> findByEndpoint(String endpoint);
}
