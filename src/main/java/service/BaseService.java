package service;

import controllers.rest.exceptions.IllegalDeleteException;
import controllers.rest.exceptions.IllegalSaveException;
import mappers.BaseMapper;
import persistence.dto.BaseDTO;
import persistence.entities.BaseEntity;
import persistence.repository.GenericRepository;
import persistence.util.TransactionUtil;

import java.util.List;

public class BaseService <ENTITY extends BaseEntity,DTO extends BaseDTO,ID> {
    private final GenericRepository<ENTITY, ID> genericRepository;
    private final BaseMapper<ENTITY, DTO> baseMapper;


    public BaseService(GenericRepository<ENTITY, ID> genericRepository, BaseMapper<ENTITY, DTO> baseMapper) {
        this.genericRepository = genericRepository;
        this.baseMapper = baseMapper;
    }

    public DTO findById(ID id) {
        return TransactionUtil.doInTransaction(entityManager -> {
            ENTITY entity = genericRepository.findById(id, entityManager);
            return baseMapper.toDTO(entity);
        });
    }

    public DTO findReferenceById(ID id) {
        return TransactionUtil.doInTransaction(entityManager -> {
            ENTITY entity = genericRepository.findReferenceById(id, entityManager);
            return baseMapper.toDTO(entity);
        });
    }


    public boolean save(DTO dto) {
        try {
            return TransactionUtil.doInTransaction(entityManager -> {
                ENTITY entity = baseMapper.toEntity(dto);
                return genericRepository.save(entity, entityManager);
            });
        }
        catch (Exception e){
            throw new IllegalSaveException("invalid save operation on entity: " + dto.getClass().getSimpleName(),e.getMessage());
        }
    }

    public boolean update(DTO dto) {
        return TransactionUtil.doInTransaction(entityManager -> {
            ENTITY entity = baseMapper.toEntity(dto);
            return genericRepository.update(entity, entityManager);
        });
    }

    public boolean delete(DTO dto) {
       try {
           return TransactionUtil.doInTransaction(entityManager -> {
               ENTITY entity = baseMapper.toEntity(dto);
               return genericRepository.delete(entity, entityManager);
           });
       }
    catch (Exception e){
                throw new IllegalDeleteException("invalid save operation on entity: " + dto.getClass().getSimpleName()+", "+e.getMessage());
             }
    }

    public List<DTO> findAll() {
        return (List<DTO>) TransactionUtil.doInTransaction(entityManager -> {
            List<ENTITY> entities = genericRepository.findAll(entityManager);
            return baseMapper.collectionToDto(entities);
        });
    }


    public boolean delete(ID id) {
       try {
           return TransactionUtil.doInTransaction(entityManager -> {
               ENTITY entity = genericRepository.findById(id, entityManager);
               return genericRepository.delete(entity, entityManager);
           });
       }
         catch (Exception e){
              throw new IllegalDeleteException("invalid delete operation on entity: " + e.getMessage());
         }
    }
}
