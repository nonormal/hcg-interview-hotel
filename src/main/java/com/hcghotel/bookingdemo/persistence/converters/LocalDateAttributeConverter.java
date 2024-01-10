package com.hcghotel.bookingdemo.persistence.converters;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDate attribute) {
        return attribute == null ? null : Timestamp.valueOf(attribute.atStartOfDay());
    }

    @Override
    public LocalDate convertToEntityAttribute(Timestamp dbData) {
        return dbData == null ? null : dbData.toLocalDateTime().toLocalDate();
    }
}