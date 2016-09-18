/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Przemek
 */
public class BaseService {
    private final String DATE_FORMAT = "yyyy-MM-dd";
    final DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern(DATE_FORMAT);
}
