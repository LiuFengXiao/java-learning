package Mapper;

import POJO.Partment;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface PartmentMapper {
    public Partment getPartment(int id);
    public void insertPartment(Partment department);
    public void updatePartment(Partment department);
}
