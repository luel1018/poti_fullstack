package org.example.poti_fullstack.chat.room;

import org.example.poti_fullstack.chat.room.model.ChatRoom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Slice<ChatRoom> findAllByHostUserIdxOrGuestUserIdx(Long hostUserIdx, Long guestUserIdx, Pageable pageable);
    ChatRoom findByHostUserIdxAndGuestUserIdx(Long hostUserIdx, Long guestUserIdx);
    void deleteChatRoomByIdx(Long roomIdx);
}
