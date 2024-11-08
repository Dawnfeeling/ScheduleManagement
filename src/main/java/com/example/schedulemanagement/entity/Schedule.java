package com.example.schedulemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
public class Schedule {
    private Long id;
    private String writer;
    private String password;
    private String title;
    private String content;
    private String create_time;
    private String update_time;

    public Schedule(String writer, String password, String title, String content) {
        this.writer = writer;
        this.password = password;
        this.title = title;
        this.content = content;
        this.create_time = getCurrentTime(); // 생성 시점의 시간 설정
        this.update_time = getCurrentTime(); // 생성 시점의 시간 설정
    }

    public void updateAllSchedule(String title, String content) {
        this.title = title;
        this.content = content;
        this.update_time = getCurrentTime();  // 수정 시점의 시간 설정
    }

    //현재 시간을 String으로 리턴하는 메소드
    private String getCurrentTime() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}