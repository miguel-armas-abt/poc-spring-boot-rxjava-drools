package com.demo.poc.commons.custom.exceptions;

import com.demo.poc.commons.core.errors.exceptions.EmptyBaseUrlException;
import com.demo.poc.commons.core.errors.exceptions.GenericException;
import com.demo.poc.commons.core.errors.exceptions.InvalidFieldException;
import com.demo.poc.commons.core.errors.exceptions.JsonReadException;
import com.demo.poc.commons.core.errors.exceptions.NoSuchParamMapperException;
import com.demo.poc.commons.core.errors.exceptions.NoSuchRestClientErrorExtractorException;
import com.demo.poc.commons.core.errors.exceptions.NoSuchRestClientException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;

import java.util.Arrays;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Getter
@RequiredArgsConstructor
public enum ErrorDictionary {

  //system=01.00.xx
  ERROR_READING_JSON("01.00.01", "Error reading JSON", INTERNAL_SERVER_ERROR, JsonReadException.class),

  //no such properties and components: 01.01.xx
  NO_SUCH_REST_CLIENT("01.01.01", "No such rest client", INTERNAL_SERVER_ERROR, NoSuchRestClientException.class),
  NO_SUCH_REST_CLIENT_ERROR_EXTRACTOR("01.01.02", "No such rest client error extractor", INTERNAL_SERVER_ERROR, NoSuchRestClientErrorExtractorException.class),
  NO_SUCH_PARAM_MAPPER("01.01.03", "No such param mapper", INTERNAL_SERVER_ERROR, NoSuchParamMapperException.class),
  EMPTY_BASE_URL("01.01.04", "Base URL is required", INTERNAL_SERVER_ERROR, EmptyBaseUrlException.class),
  NO_SUCH_RULE_MAPPER("01.01.05", "No such rule mapper", INTERNAL_SERVER_ERROR, NoSuchRuleMapperException.class),

  //business and bad requests: 01.02.xx
  INVALID_FIELD("01.02.01", "Invalid field", BAD_REQUEST, InvalidFieldException.class),
  ERROR_COMPILING_RULES("01.01.01", "Error compiling rules", INTERNAL_SERVER_ERROR, RulesCompilationException.class),

  //transaction limits: 01.03.xx
  NO_SUCH_TRANSFER_LIMIT("01.03.01", "No such transfer limit", INTERNAL_SERVER_ERROR, NoSuchTransferLimitException.class);

  private final String code;
  private final String message;
  private final HttpStatusCode httpStatus;
  private final Class<? extends GenericException> exceptionClass;

  public static ErrorDictionary parse(Class<? extends GenericException> exceptionClass) {
    return Arrays.stream(ErrorDictionary.values())
        .filter(errorDetail -> errorDetail.getExceptionClass().isAssignableFrom(exceptionClass))
        .findFirst()
        .orElseThrow(() -> new GenericException("No such exception"));
  }
}
