package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final Map<Long, Schedule> schedulesList = new HashMap<>();

    @PostMapping
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto dto) {
        //식별자(id)가 1씩 증가
        Long scheduleId = schedulesList.isEmpty() ? 1 : Collections.max(schedulesList.keySet()) + 1;

        //요청받은 데이터로 Schedule객체 생성
        Schedule schedule = new Schedule(
                scheduleId,
                dto.getWriter(),
                dto.getPassword(),
                dto.getTitle(),
                dto.getContent()
        );

        schedulesList.put(scheduleId, schedule);

        return new ScheduleResponseDto(schedule);
    }

    @GetMapping("/{id}")
    public ScheduleResponseDto findSchedulesById(@PathVariable long id) {
        Schedule schedule = schedulesList.get(id);

        return new ScheduleResponseDto(schedule);
    }

    @PutMapping("/{id}")
    public ScheduleResponseDto updateSchedule(@PathVariable long id, @RequestBody ScheduleRequestDto dto) {
        Schedule schedule = schedulesList.get(id);

        schedule.updateSchedule(dto);

        return new ScheduleResponseDto(schedule);
    }

    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable long id) {
        schedulesList.remove(id);
    }
}
