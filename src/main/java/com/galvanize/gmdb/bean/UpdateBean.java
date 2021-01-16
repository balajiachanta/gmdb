package com.galvanize.gmdb.bean;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class UpdateBean {

    private String title;
    private String rating;
}
