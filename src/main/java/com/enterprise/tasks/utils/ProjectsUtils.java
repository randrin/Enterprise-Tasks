package com.enterprise.tasks.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ProjectsUtils {

    public static LocalDateTime convertDateToLocalDateTime(Date convertDate) {
        return convertDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
