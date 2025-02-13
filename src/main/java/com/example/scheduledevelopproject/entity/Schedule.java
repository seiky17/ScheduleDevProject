package com.example.scheduledevelopproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    // 객체 지향적으로 schedule 테이블과 user 테이블을 매핑
    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Schedule(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    // 할일 수정 메서드
    public void updateSchedule(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
