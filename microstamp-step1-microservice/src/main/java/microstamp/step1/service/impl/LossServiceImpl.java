package microstamp.step1.service.impl;

import microstamp.step1.client.MicroStampClient;
import microstamp.step1.data.Loss;
import microstamp.step1.dto.loss.LossInsertDto;
import microstamp.step1.dto.loss.LossReadDto;
import microstamp.step1.dto.loss.LossUpdateDto;
import microstamp.step1.exception.Step1NotFoundException;
import microstamp.step1.mapper.LossMapper;
import microstamp.step1.repository.LossRepository;
import microstamp.step1.service.LossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Component
public class LossServiceImpl implements LossService {

    @Autowired
    private LossRepository lossRepository;

    @Autowired
    private MicroStampClient microStampClient;

    public List<LossReadDto> findAll() {
        return lossRepository.findAll().stream()
                .map(LossMapper::toDto)
                .sorted(Comparator.comparing(LossReadDto::getName))
                .toList();
    }

    public LossReadDto findById(UUID id) throws Step1NotFoundException {
        return LossMapper.toDto(lossRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Loss", id.toString())));
    }

    public List<LossReadDto> findByAnalysisId(UUID id) {
        return lossRepository.findByAnalysisId(id).stream()
                .map(LossMapper::toDto)
                .sorted(Comparator.comparing(LossReadDto::getName))
                .toList();
    }

    public LossReadDto insert(LossInsertDto lossInsertDto) throws Step1NotFoundException {
        microStampClient.getAnalysisById(lossInsertDto.getAnalysisId());

        Loss loss = LossMapper.toEntity(lossInsertDto);
        lossRepository.save(loss);

        return LossMapper.toDto(loss);
    }

    public void update(UUID id, LossUpdateDto lossUpdateDto) throws Step1NotFoundException {
        Loss loss = lossRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Loss", id.toString()));

        loss.setName(lossUpdateDto.getName());

        lossRepository.save(loss);
    }

    public void delete(UUID id) throws Step1NotFoundException {
        Loss loss = lossRepository.findById(id)
                .orElseThrow(() -> new Step1NotFoundException("Loss", id.toString()));
        lossRepository.deleteHazardsAssociation(id.toString());
        lossRepository.deleteById(loss.getId());
    }
}
