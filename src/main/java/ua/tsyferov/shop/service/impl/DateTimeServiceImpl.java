package ua.tsyferov.shop.service.impl;

import org.springframework.stereotype.Service;
import ua.tsyferov.shop.service.DateTimeService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class DateTimeServiceImpl implements DateTimeService {

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }

    @Override
    public Date toDate(final LocalDateTime timestamp) {
        return Date.from(timestamp.atZone(ZoneId.systemDefault()).toInstant());
    }
}
