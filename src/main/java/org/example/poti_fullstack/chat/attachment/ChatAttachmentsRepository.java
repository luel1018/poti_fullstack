package org.example.poti_fullstack.chat.attachment;

import org.example.poti_fullstack.chat.attachment.model.ChatAttachments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatAttachmentsRepository extends JpaRepository<ChatAttachments, Long> {
}
