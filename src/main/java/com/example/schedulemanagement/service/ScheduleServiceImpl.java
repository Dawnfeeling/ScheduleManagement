package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {

        Schedule schedule = new Schedule(dto.getWriter(), dto.getPassword(), dto.getTitle(), dto.getContent());

        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {

        return scheduleRepository.findAllSchedules();
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {

        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateAllSchedule(Long id, String password, String writer, String content) {

        if (writer == null || content == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "제목이나 내용이 없습니다.");
        }

        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        //비밀번호가 다르면 오류
        if(!password.equals(schedule.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 다릅니다.");
        }

        int updateRow = scheduleRepository.updateAllSchedule(id, writer, content);

        if (updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "메모가 없습니다.");
        }

        return new ScheduleResponseDto(schedule);
    }

    @Override
    public void deleteSchedule(Long id, String password) {

        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        //비밀번호가 다르면 오류
        if(!password.equals(schedule.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 다릅니다.");
        }

        int deleteRow = scheduleRepository.deleteSchedule(id);

        if (deleteRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "메모가 없습니다.");
        }

    }
}
