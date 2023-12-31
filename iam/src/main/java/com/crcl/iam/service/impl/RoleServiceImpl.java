package com.crcl.iam.service.impl;

import com.crcl.iam.domain.Role;
import com.crcl.iam.dto.RoleDto;
import com.crcl.iam.mappers.RoleMapper;
import com.crcl.iam.repository.RoleRepository;
import com.crcl.iam.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleDto save(RoleDto roleDto) {
        Role role = this.roleMapper.toEntity(roleDto);
        return roleMapper.toDto(roleRepository.save(role));
    }

    @Override
    public List<RoleDto> saveAll(List<RoleDto> entities) {
        return entities.stream().map(this::save).toList();
    }

    @Override
    public void deleteById(String id) {
        roleRepository.findById(id).ifPresent(role -> {
            role.setEnabled(false);
            roleRepository.save(role);
            log.info("Role with id {} was disabled", role.getId());
        });

    }

    @Override
    public RoleDto findById(String id) {
        return roleRepository.findById(id)
                .map(roleMapper::toDto)
                .orElse(null);
    }


    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toDto)
                .toList();
    }

    @Override
    public Page<RoleDto> findAll(Pageable pageable) {
        return roleRepository.findAll(pageable)
                .map(roleMapper::toDto);
    }

    @Override
    public RoleDto update(RoleDto roleDto, String id) {
        return roleRepository.findById(id)
                .map(role -> roleMapper.toEntity(roleDto))
                .map(roleRepository::save)
                .map(roleMapper::toDto)
                .orElse(null);
    }
}
