package dao;

import entity.Post;
import entity.Predictionlevelwater;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
    public void add(Predictionlevelwater predictLevelWater) {
        sessionFactory.getCurrentSession().saveOrUpdate(predictLevelWater);
    }

    @Override
    public void dell(int idPredictionWater) {


    }

    @Override
    public void dell(String namePost) {
        Query q=sessionFactory.getCurrentSession().createQuery("DELETE FROM Predictionlevelwater e WHERE postId.namePost=:namePost");
        q.setParameter("namePost", namePost);
        q.executeUpdate();
    }

    @Override
    public List<Predictionlevelwater> search(int idPost) {
        Query query=sessionFactory.getCurrentSession().createQuery("from Predictionlevelwater where postId.idPost=:idPost",Predictionlevelwater.class);
        query.setParameter("idPost", idPost);
        List<Predictionlevelwater> predictWaterList=query.list();
        return  predictWaterList;
    }

    @Override
    public void dell(Predictionlevelwater predictionlevelwater) {
        sessionFactory.getCurrentSession().remove(predictionlevelwater);
    }

    @Override
    public void dellAll() {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM Predictionlevelwater e").executeUpdate();
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



}
