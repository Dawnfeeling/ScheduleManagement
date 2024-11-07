package com.example.schedulemanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequestDto {
    private String writer;
    private String password;
    private String title;
    private String content;
}
