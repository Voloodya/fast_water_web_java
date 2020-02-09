package service;

import entity.Post;
import view.FloodView;

import java.util.Date;
import java.util.List;

public interface FloodService {

    List<FloodView> getAllFloodViev();
    List<FloodView> searhFloodViev(Date date);
    List<FloodView> searhFloodViev(Post post);
    List<FloodView> searhFloodViev(String loclity);

}
