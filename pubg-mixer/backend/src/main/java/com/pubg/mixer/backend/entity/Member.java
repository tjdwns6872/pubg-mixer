package com.pubg.mixer.backend.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname", nullable = false, unique = true, length = 50)
    private String nickname;

    @Column(name = "tier", nullable = false)
    private int tier;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * 닉네임과 티어를 부분 수정한다.
     *
     * <p>JPA 더티체킹을 사용하므로 트랜잭션 안에서 이 메서드를 호출하면,
     * 별도의 save 호출 없이 변경사항이 반영된다.</p>
     *
     * <p>DTO 단에서 잡기 어려운 공백 문자열 같은 케이스는 서비스 계층에서
     * 먼저 검증한 뒤 이 메서드로 내려온다고 가정한다.</p>
     */
    public void update(String nickname, Integer tier) {
        if (nickname != null) {
            this.nickname = nickname;
        }
        if (tier != null) {
            this.tier = tier;
        }
    }
}
