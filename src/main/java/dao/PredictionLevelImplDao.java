package dao;

import entity.Predictionlevelwater;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
@Repository("PredictionLevelWaterDao")
public class PredictionLevelImplDao implements PredictionLevelWaterDao {

    private static final Log logger = LogFactory.getLog(PredictionLevelImplDao.class);

    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Predictionlevelwater> getAll() {

        List<Predictionlevelwater> listPost=sessionFactory.getCurrentSession().createQuery("from Predictionlevelwater e",Predictionlevelwater.class).list();
        return listPost;
    }

    @Override
    public void dell(int idPredictionWater) {


    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



}
