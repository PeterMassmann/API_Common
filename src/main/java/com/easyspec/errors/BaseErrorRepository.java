package com.easyspec.errors;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface BaseErrorRepository<E extends BaseErrorLog> extends CrudRepository<E, Long> {

    Long deleteAllByCreationBefore(LocalDateTime date);

}
