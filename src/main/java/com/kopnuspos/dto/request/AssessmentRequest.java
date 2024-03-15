package com.kopnuspos.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class AssessmentRequest {
    private String title;
    private String description;
    private String deadline;
}
