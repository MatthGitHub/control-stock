package com.mercadolibre.stock.model.dto.validators;

import com.mercadolibre.stock.utils.PatternUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DepositValidator implements ConstraintValidator<ValidDeposit, String> {
    private static final Set<String> ISO_COUNTRIES = Set.of(Locale.getISOCountries());

    @Override
    public void initialize(ValidDeposit validDeposit) {
    }

    @Override
    public boolean isValid(String deposit, ConstraintValidatorContext context){
        return (validateDeposit(deposit));
    }

    private boolean validateDeposit(String deposit) {
        if(Objects.isNull(deposit)) return false;
        Pattern pattern = Pattern.compile(PatternUtils.DEPOSIT_PATTERN);
        Matcher matcher = pattern.matcher(deposit);

        if(matcher.matches()){
            try {
                return ISO_COUNTRIES.contains(deposit.substring(0, 2));
            } catch (Exception e){
                return false;
            }
        }
        return false;
    }
}