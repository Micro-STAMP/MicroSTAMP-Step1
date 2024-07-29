package microstamp.step1.service;

import microstamp.step1.dto.loss.LossInsertDto;
import microstamp.step1.dto.loss.LossReadDto;
import microstamp.step1.dto.loss.LossUpdateDto;

import java.util.List;
import java.util.UUID;

public interface LossService {

    List<LossReadDto> findAll();

    LossReadDto findById(UUID id);

    List<LossReadDto> findByProjectId(UUID id);

    LossReadDto insert(LossInsertDto lossInsertDto);

    void update(UUID id, LossUpdateDto lossUpdateDto);

    void delete(UUID id);
}
