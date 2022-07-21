package org.epoch.ddd.domain.service;

import org.epoch.data.domain.Auditable;
import org.epoch.data.repository.BaseRepository;
import org.epoch.ddd.domain.CrudDomain;

/**
 * @author Marshal
 * @since 2022/7/20
 */
public class BaseServiceImpl<R extends BaseRepository<T, ID>, D extends CrudDomain<R, T, ID>, T extends Auditable<ID>, ID> {
}
