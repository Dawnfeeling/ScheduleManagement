package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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

    @GetMapping
    public List<ScheduleResponseDto> findAllSchedules() {
        List<ScheduleResponseDto> responseScheduleList;

        responseScheduleList = schedulesList.values().stream().map(ScheduleResponseDto::new).toList();

        return responseScheduleList;
    }

    @GetMapping("/{id}")
    public ScheduleResponseDto findSchedulesById(@PathVariable long id) {
        Schedule schedule = schedulesList.get(id);

        return new ScheduleResponseDto(schedule);
    }

    @PutMapping("/{id}")
    public ScheduleResponseDto updateSchedule(@PathVariable long id, @RequestBody ScheduleRequestDto dto) {
        Schedule schedule = schedulesList.get(id);

        //작성자와 비밀번호가 동일하면 수정
        if(dto.getWriter().equals(schedule.getWriter()) && dto.getPassword().equals(schedule.getPassword())){
            schedule.updateSchedule(dto);
        }

        return new ScheduleResponseDto(schedule);
    }

    @PatchMapping("/{id}")
    public ScheduleResponseDto someUpdateSchedule(@PathVariable long id, @RequestBody ScheduleRequestDto dto) {
        Schedule schedule = schedulesList.get(id);

        //작성자와 비밀번호가 동일하면 수정
        if(dto.getWriter().equals(schedule.getWriter()) && dto.getPassword().equals(schedule.getPassword())){
            //값이 null인 경우 기존 값으로 대체
            if(dto.getTitle() == null){
                dto.setTitle(schedule.getTitle());
            }
            if(dto.getContent() == null){
                dto.setContent(schedule.getContent());
            }
            schedule.updateSchedule(dto);
        }

        return new ScheduleResponseDto(schedule);
    }

    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable long id, @RequestBody ScheduleRequestDto dto) {
        Schedule schedule = schedulesList.get(id);

        //작성자와 비밀번호가 동일하면 삭제
        if(dto.getWriter().equals(schedule.getWriter()) && dto.getPassword().equals(schedule.getPassword())){
            schedulesList.remove(id);
        }
    }
}
