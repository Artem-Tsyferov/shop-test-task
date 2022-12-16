package ua.tsyferov.shop.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.tsyferov.shop.service.DateTimeService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DateTimeService")
class DateTimeServiceImplTest {

    private DateTimeService service;

    @BeforeEach
    void setUp() {
        service = new DateTimeServiceImpl();
    }

    @Test
    void shouldReturnCurrentDateTime() {
        final LocalDateTime expected = LocalDateTime.now();

        final LocalDateTime actual = service.now();

        assertThat(actual).isEqualToIgnoringNanos(expected);
    }

    @Test
    void shouldGetDateFromProvidedLocalDateTimeValue() {
        final LocalDateTime timestamp = LocalDateTime.of(2020, 6, 25, 9, 30, 30, 500);
        final long expected = toEpochMillis(timestamp);

        final Date actual = service.toDate(timestamp);

        assertThat(actual.getTime()).isEqualTo(expected);
    }

    private long toEpochMillis(final LocalDateTime timestamp) {
        return ZonedDateTime.of(timestamp, ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}