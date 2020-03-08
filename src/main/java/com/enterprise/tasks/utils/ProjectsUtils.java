package com.enterprise.tasks.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ProjectsUtils {

    public static LocalDateTime convertDateToLocalDateTime(Date convertDate) {
        return convertDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * Generate Matricule Employées: xxx|xxx|xx|x|xx
     */
    public static String generateMatriculeEmployee(String nom, String prenom, LocalDateTime dateNaissance, String role) {
        System.out.println("################# DATE NAISSANCE ... " +dateNaissance);
        String matricule = "";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = formatter.format(dateNaissance);
        String day = strDate.substring(8);
        String year = strDate.substring(2, 4);
        String matricule1 = nom.substring(0, 3).toUpperCase();
        String matricule2 = prenom.substring(0, 3).toUpperCase();
        String matricule3 = year;
        String matricule4 = role.substring(0, 1).toUpperCase();
        String matricule5 = day;

        matricule = matricule1 + matricule2 + matricule3 + matricule4 + matricule5;

        System.out.println("################# Matricule ... " +matricule);
        return matricule;
    }
}
