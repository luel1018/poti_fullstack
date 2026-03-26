package org.example.poti_fullstack.chat.message;

import lombok.RequiredArgsConstructor;
import org.example.poti_fullstack.chat.message.model.ChatMessageDto;
import org.example.poti_fullstack.common.model.BaseResponse;
import org.example.poti_fullstack.user.model.AuthUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat/message")
    public ResponseEntity sendMessage(ChatMessageDto.Send req, StompHeaderAccessor accessor) {
        Authentication authentication = (Authentication) accessor.getSessionAttributes().get("user");
        AuthUserDetails user = (AuthUserDetails) authentication.getPrincipal();
        ChatMessageDto.Res res = chatMessageService.saveMessage(req, user.getIdx());
        return ResponseEntity.ok(BaseResponse.success(res));
    }
}
