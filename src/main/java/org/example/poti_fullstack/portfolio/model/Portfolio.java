package org.example.poti_fullstack.portfolio.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.poti_fullstack.common.model.BaseEntity;
import org.example.poti_fullstack.section.model.Section;
import org.example.poti_fullstack.user.model.User;
import org.example.poti_fullstack.utils.StringListConverter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Portfolio extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String title; // 프로젝트 제목
    private String period; // 진행 기간
    private String role; // 프로젝트에서 맡은 역할
    @Convert(converter = StringListConverter.class)
    private List<String> keywords; // 프로젝트의 키워드

    private String theme;       // 테마
    private String layoutType;  // 레이아웃

    private String Image; // 프로젝트 대표 이미지 경로 저장

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @OneToMany(mappedBy = "portfolio", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Section> sectionList = new ArrayList<>();

}
