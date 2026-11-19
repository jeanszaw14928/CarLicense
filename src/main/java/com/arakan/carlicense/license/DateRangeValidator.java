package com.arakan.carlicense.license;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import com.arakan.carlicense.license.LicenseDto;
public class DateRangeValidator
        implements ConstraintValidator<ValidDateRange, LicenseDto> {

    @Override
    public boolean isValid(
            LicenseDto dto,
            ConstraintValidatorContext context) {

        if (dto == null ||
            dto.getIssueDate() == null ||
            dto.getExpiryDate() == null) {

            return true;
        }

        if (dto.getExpiryDate().isBefore(dto.getIssueDate())) {

            context.disableDefaultConstraintViolation();

            context.buildConstraintViolationWithTemplate(
                    context.getDefaultConstraintMessageTemplate()
            )
            .addPropertyNode("expiryDate")
            .addConstraintViolation();

            return false;
        }
        return true;
    }
}
