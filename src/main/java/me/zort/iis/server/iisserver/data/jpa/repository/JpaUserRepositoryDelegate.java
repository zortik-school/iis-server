package me.zort.iis.server.iisserver.data.jpa.repository;

import me.zort.iis.server.iisserver.data.IdAdjustmentStrategy;
import me.zort.iis.server.iisserver.data.jpa.JpaCrudRepository;
import me.zort.iis.server.iisserver.data.jpa.JpaMapper;
import me.zort.iis.server.iisserver.data.jpa.entity.UserEntity;
import me.zort.iis.server.iisserver.domain.user.User;
import me.zort.iis.server.iisserver.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaUserRepositoryDelegate extends JpaCrudRepository<User, UserEntity, Long> implements UserRepository {
    private final JpaUserRepository repository;
    private final JpaMapper<User, UserEntity> mapper;

    public JpaUserRepositoryDelegate(
            JpaUserRepository repository,
            JpaMapper<User, UserEntity> mapper, IdAdjustmentStrategy idAdjustmentStrategy) {
        super(repository, mapper, idAdjustmentStrategy);

        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username).map(mapper::toDomain);
    }
}
