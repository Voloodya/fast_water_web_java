package dao;

import entity.Geographkoords;

import java.util.List;

public interface GeographKoordsDao {

    List<Geographkoords> getAll();
    List<Geographkoords> searh(String koords);
    void add (String geogrKoords, String typekoords, int srid, double radiusaction,double heighSeaLevel, String description);
    void dell (String geographKoords);
    void dell (int idKoords);
    void update (String geogrKoords, String typekoords, int srid, double radiusaction,double heighSeaLevel, String description);
}
