package com.jpa.jpaCode.Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseEntity {

    private Date created_date;

    private Date updated_date;

    private long created_by;

    private long updated_by;

}
