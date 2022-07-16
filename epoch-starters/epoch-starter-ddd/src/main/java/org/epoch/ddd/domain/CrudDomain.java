package org.epoch.ddd.domain;

import org.epoch.data.domain.Auditable;
import org.epoch.data.repository.BaseRepository;

/**
 * @author Marshal
 * @since 2022/7/14
 */
public interface CrudDomain<R extends BaseRepository<T, ID>, T extends Auditable<ID>, ID> {

}
