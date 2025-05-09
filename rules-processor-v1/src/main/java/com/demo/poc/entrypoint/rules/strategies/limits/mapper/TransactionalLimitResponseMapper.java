package com.demo.poc.entrypoint.rules.strategies.limits.mapper;

import java.math.BigDecimal;

import com.demo.poc.entrypoint.rules.strategies.limits.dto.TransactionLimitResponseDto;
import com.demo.poc.entrypoint.rules.strategies.limits.enums.TransferLimit;
import com.demo.poc.entrypoint.rules.strategies.limits.rule.TransactionalLimitRule;
import com.demo.poc.entrypoint.rules.strategies.limits.utils.NumberFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TransactionalLimitResponseMapper {

  @Mapping(target = "maximumAmount", source = "transactionLimit.level", qualifiedByName = "mapMaximumAmount")
  TransactionLimitResponseDto toResponse(TransactionalLimitRule.TransactionLimit transactionLimit);

  @Named("mapMaximumAmount")
  static String mapMaximumAmount(String levelCode) {
    BigDecimal maximumAmount = BigDecimal.valueOf(TransferLimit.parse(levelCode).getMaximumAmount());
    return "S/" + NumberFormatter.formatNumber(maximumAmount, 2);
  }
}
