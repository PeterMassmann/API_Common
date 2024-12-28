package com.easyspec.apicommon.scannable.repositories;

import com.easyspec.apicommon.errors.BaseErrorLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@NoRepositoryBean
public interface BaseErrorRepository<E extends BaseErrorLog> extends CrudRepository<E, Long> {

    Long deleteAllByCreationBefore(LocalDateTime date);

}
