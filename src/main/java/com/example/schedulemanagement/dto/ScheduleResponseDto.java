package com.example.schedulemanagement.dto;

import com.example.schedulemanagement.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private String create_time;
    private String update_time;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.writer = schedule.getWriter();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.create_time = schedule.getCreate_time();
        this.update_time = schedule.getUpdate_time();
    }
}
