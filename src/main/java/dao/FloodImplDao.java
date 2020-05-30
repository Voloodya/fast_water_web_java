package dao;

import entity.Flood;
import entity.Post;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Service
@SuppressWarnings("unchecked")
@Transactional
@Repository("FloodDao")
public class FloodImplDao implements FloodDao {

    private static final Log logger = LogFactory.getLog(GeographKoordsImplDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Flood> getAll() {
        List<Flood> listFlood=sessionFactory.getCurrentSession().createQuery("FROM Flood e ORDER BY postId, date",Flood.class).list();
        return listFlood;
    }

    @Override
    public List<Flood> searh(Date date) {
        return null;
    }

    @Override
    public List<Flood> searh(Post post) {
        return null;
    }

    @Override
    public List<Flood> searh(String locality) {
        Query query=sessionFactory.getCurrentSession().createQuery("from Flood where postByPostId.localityByLocalityId.nameLocality=:locality",Flood.class);
        query.setParameter("locality", locality);
        List<Flood> floodList=query.list();
        return floodList;
    }

    @Override
    public void add(Date date, Time time, Double longitudeDay, Double snow, Double rain, Double snowRain, Double airHumidity, Double temperatureDay, Double temperatureNight, Double levelSnow, Double hardnessSnow, Double levelFreezingGround, Double temperatureWater, Double heightIceOnWater, double levelWater, int warningFlood, Post postByPostId, String solaractivity) {

    }

    @Override
    public void add(Flood flood) {
        sessionFactory.getCurrentSession().saveOrUpdate(flood);
    }

    @Override
    public void dell(Flood flood) {

    }

    @Override
    public void dell(int idflood) {

    }

    @Override
    public void update(Date date, Time time, Double longitudeDay, Double snow, Double rain, Double snowRain, Double airHumidity, Double temperatureDay, Double temperatureNight, Double levelSnow, Double hardnessSnow, Double levelFreezingGround, Double temperatureWater, Double heightIceOnWater, double levelWater, int warningFlood, Post postByPostId, String solaractivity) {
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
