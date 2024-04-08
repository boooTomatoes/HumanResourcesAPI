package mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import persistence.dto.JobDTO;
import persistence.entities.Job;

@Mapper
public interface JobMapper extends BaseMapper<Job, JobDTO>{
    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);
}
