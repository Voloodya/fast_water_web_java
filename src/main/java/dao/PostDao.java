package dao;

import entity.*;

import java.util.List;

public interface PostDao {

    List<Post> getAll();
    Post searсh(int idPost);
    Post searсh(String namePost);
    List<Post> searh(Geographkoords geographkoords);
//    List<Post> searh(Geometry koords);
    void add (String namePost, Double distanceBeetwenSensors, Geographkoords geographkoordsByGeographKoordsId, Basinlocality basinlocalityByBasinLocalityId, Ground groundByGroundId, Locality localityByLocalityId, String description);
    void dell (Post post);
    void dell (int idPost);
    void update (String namePost, Double distanceBeetwenSensors, Geographkoords geographkoordsByGeographKoordsId, Basinlocality basinlocalityByBasinLocalityId, Ground groundByGroundId, Locality localityByLocalityId, String description);
}
