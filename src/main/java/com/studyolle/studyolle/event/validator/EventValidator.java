package com.studyolle.studyolle.event.validator;

import com.studyolle.studyolle.event.form.EventForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

@Component
public class EventValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return EventForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EventForm eventForm = (EventForm) target;

        if (isNotValidEndEnrollmentDateTime(eventForm)) {
            errors.rejectValue("endEnrollmentDateTime", "wrong.datetime", "모임 접수 종료 일시를 정확히 입력하세요.");
        }

        if (isNotValidEndDateTime(eventForm)) {
            errors.rejectValue("endEnrollmentDateTime", "wrong.datetime", "모임 종료 일시를 정확히 입력하세요.");
        }

        if (isNotValidStartDateTime(eventForm)) {
            errors.rejectValue("endEnrollmentDateTime", "wrong.datetime", "모임 시작 일시를 정확히 입력하세요.");

        }
    }

    private boolean isNotValidStartDateTime(EventForm eventForm) {
        return eventForm.getStartDateTime().isBefore(eventForm.getEndEnrollmentDateTime());
    }

    private static boolean isNotValidEndEnrollmentDateTime(EventForm eventForm) {
        return eventForm.getEndEnrollmentDateTime().isBefore(LocalDateTime.now());
    }

    private static boolean isNotValidEndDateTime(EventForm eventForm) {
        LocalDateTime endDateTime = eventForm.getEndDateTime();
        return endDateTime.isBefore(eventForm.getStartDateTime()) || endDateTime.isBefore(eventForm.getEndEnrollmentDateTime());
    }
}